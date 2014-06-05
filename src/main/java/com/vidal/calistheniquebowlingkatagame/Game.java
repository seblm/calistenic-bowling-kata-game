package com.vidal.calistheniquebowlingkatagame;

import java.util.LinkedList;

public class Game {

    private LinkedList<PinfallNumber> rolls;

    public Game(LinkedList<PinfallNumber> rolls) {
        this.rolls = rolls;
    }

    public Game() {
        this(new LinkedList<>());
    }

    public Game roll(PinfallNumber pinfall) {
        LinkedList<PinfallNumber> newPinfalls = new LinkedList<>(rolls);
        newPinfalls.addLast(pinfall);

        normalizeFrame(pinfall, newPinfalls);

        return new Game(newPinfalls);
    }

    private void normalizeFrame(PinfallNumber pinfall, LinkedList<PinfallNumber> newPinfalls) {
        if (isStrike(pinfall, newPinfalls)) {
            newPinfalls.addLast(new PinfallNumber(0));
        }
    }

    private boolean isStrike(PinfallNumber pinfall, LinkedList<PinfallNumber> newPinfalls) {
        return newPinfalls.size() % 2 == 1
                && pinfall.equals(new PinfallNumber(10));
    }

    public Score score() {
        Score score = new Score(new PinfallNumber(0));

        for (int i = 0; i < rolls.size(); i += 2) {
            score = computeScoreForFrame(score, i);
        }

        return score;
    }

    private Score computeScoreForFrame(Score score, int rollIndex) {
        score = computeStrikeBonus(score, rollIndex);

        score = computeSpareBonus(score, rollIndex);

        score = computeClassicScore(score, rollIndex);
        return score;
    }

    private Score computeClassicScore(Score score, int rollIndex) {
        score = getRollScore(score, rollIndex);
        score = getRollScore(score, rollIndex + 1);

        return score;
    }

    private Score getRollScore(Score score, int rollIndex) {
        if (rolls.size() > rollIndex) {
            score = new Score(score, rolls.get(rollIndex));
        }
        return score;
    }

    private Score computeSpareBonus(Score score, int rollIndex) {
        if (isSpareForPreviousFrame(rollIndex)) {
            score = new Score(score, rolls.get(rollIndex));
        }
        return score;
    }

    private Score computeStrikeBonus(Score score, int rollIndex) {
        if (isStrikeForPreviousFrame(rollIndex)) {
            score = new Score(score, new PinfallNumber(rolls.get(rollIndex), rolls.get(rollIndex + 1)));
        }
        return score;
    }

    private boolean isStrikeForPreviousFrame(int rollIndex) {
        return rollIndex >= 2
                && rolls.get(rollIndex - 2).equals(new PinfallNumber(10));
    }

    private boolean isSpareForPreviousFrame(int rollIndex) {
        return rollIndex >= 2
                && new PinfallNumber(rolls.get(rollIndex - 2), rolls.get(rollIndex - 1)).equals(new PinfallNumber(10))
                && !isStrikeForPreviousFrame(rollIndex);
    }

}

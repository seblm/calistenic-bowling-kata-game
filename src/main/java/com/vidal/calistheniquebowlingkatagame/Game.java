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

        return new Game(newPinfalls);
    }

    public Score score() {
        Score score = new Score(new PinfallNumber(0));

        for (PinfallNumber roll : rolls) {
            score = new Score(roll, score);
        }

        return score;
    }
}

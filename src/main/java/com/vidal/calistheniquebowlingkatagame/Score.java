package com.vidal.calistheniquebowlingkatagame;

public class Score {
    private PinfallNumber pinfallNumber;

    public Score(PinfallNumber pinfallNumber) {
        this.pinfallNumber = pinfallNumber;
    }

    public Score(PinfallNumber pinfall, Score score) {
        pinfallNumber = new PinfallNumber(pinfall, score.pinfallNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Score score1 = (Score) o;

        PinfallNumber otherCount = score1.pinfallNumber;
        if (!pinfallNumber.equals(otherCount)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return pinfallNumber.hashCode();
    }

    @Override
    public String toString() {
        return "Score{" +
                "pinfallNumber=" + pinfallNumber +
                '}';
    }
}

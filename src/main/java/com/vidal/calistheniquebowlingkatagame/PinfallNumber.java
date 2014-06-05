package com.vidal.calistheniquebowlingkatagame;

public class PinfallNumber {
    private int pinfall;

    public PinfallNumber(int pinfall) {
        this.pinfall = pinfall;
    }

    public PinfallNumber(PinfallNumber newCount, PinfallNumber previousCount) {
        this.pinfall = newCount.pinfall + previousCount.pinfall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PinfallNumber that = (PinfallNumber) o;

        if (pinfall != that.pinfall) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return pinfall;
    }

    @Override
    public String toString() {
        return String.valueOf(pinfall);
    }
}

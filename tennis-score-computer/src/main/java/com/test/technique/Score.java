package com.test.technique;

public enum Score {
    ZERO(0, "0"),
    FIFTEEN(1, "15"),
    THIRTY(2, "30"),
    FORTY(3, "40"),
    ADVANTAGE(4, "advantage"),
    WIN(5, "win");

    private int number;
    private String value;

    Score(final int number, final String value) {
        this.value = value;
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}
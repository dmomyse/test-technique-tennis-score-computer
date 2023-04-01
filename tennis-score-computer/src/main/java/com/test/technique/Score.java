package com.test.technique;

public enum Score {
    ZERO("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    ADVANTAGE("advantage"),
    WIN("win");

    private String value;

    Score(final String value) {
        this.value = value;
    }

    public Score playerHasScored(final Score opponentScore) {
        return switch (this) {
            case ZERO -> FIFTEEN;
            case FIFTEEN -> THIRTY;
            case THIRTY -> FORTY;
            case FORTY -> computeFortyScoring(opponentScore);
            case ADVANTAGE, WIN -> WIN;
        };
    }

    private Score computeFortyScoring(final Score opponentScore) {
        if (opponentScore == Score.FORTY) {
            return ADVANTAGE;
        } else if (opponentScore == Score.ADVANTAGE) {
            return FORTY;
        } else {
            return WIN;
        }
    }

    public Score opponentHasScored(final Score scorePB) {
        if (Score.FORTY == scorePB && Score.ADVANTAGE == this) {
            return Score.FORTY;
        }
        return this;
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}

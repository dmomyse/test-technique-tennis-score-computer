package com.test.technique;

public class Board {
    private Score scorePA;
    private Score scorePB;

    public Board() {
        this.scorePA = Score.ZERO;
        this.scorePB = Score.ZERO;
    }

    public Score getScorePA() {
        return scorePA;
    }

    public Score getScorePB() {
        return scorePB;
    }

    public void pAHasScored() {
        final Score initScorePB = scorePB;
        scorePB = scorePB.opponentHasScored(scorePA);
        scorePA = scorePA.playerHasScored(initScorePB);
    }

    public void pBHasScored() {
        final Score initScorePA = scorePA;
        scorePA = scorePA.opponentHasScored(scorePB);
        scorePB = scorePB.playerHasScored(initScorePA);
    }

    public void reset() {
        this.scorePA = Score.ZERO;
        this.scorePB = Score.ZERO;
    }

    public boolean hasAWinner() {
        return Score.WIN == scorePA || Score.WIN == scorePB;
    }

    public boolean isDeuce() {
        return Score.FORTY == scorePA && Score.FORTY == scorePB;
    }
}

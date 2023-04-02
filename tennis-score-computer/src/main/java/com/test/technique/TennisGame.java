package com.test.technique;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class TennisGame {
    private static final Logger LOGGER = LoggerFactory.getLogger(TennisGame.class);
    private static final String EXIT_GAME = "EXIT";
    private static final String PLAYER_A_NAME = "A";
    private static final String PLAYER_B_NAME = "B";
    private static final Pattern A_B_PATTERN = Pattern.compile("[A-B]+");
    private final Board board;
    private Boolean endGame;

    public TennisGame() {
        this.board = new Board();
        this.endGame = false;
    }

    public Board getBoard() {
        return board;
    }

    public Boolean getEndGame() {
        return endGame;
    }

    public TennisGame play() {
        Scanner input = new Scanner(System.in);

        LOGGER.info("Enter input: ");
        while (!endGame) {
            // Read input from user after normalizing it
            String inputUser = normalize(input.nextLine());

            // Check if user wants to end the game
            if (EXIT_GAME.equals(inputUser)) {
                endGame = true;
                LOGGER.info("Exit game");
                continue;
            }

            // Validate input from user is correct
            if (!validateInput(inputUser)) {
                LOGGER.info("{} is not valid", inputUser);
                continue;
            }

            char[] inputChars = inputUser.toCharArray();
            for (int i = 0; i < inputChars.length; i++) {
                String playerWonPoint = String.valueOf(inputChars[i]);

                boolean hasPlayerAScored = PLAYER_A_NAME.equals(playerWonPoint);
                if (hasPlayerAScored) {
                    board.pAHasScored();
                } else {
                    board.pBHasScored();
                }

                // evaluate winner.
                if (board.hasAWinner()) {
                    LOGGER.info("Player {} wins the game", hasPlayerAScored ? PLAYER_A_NAME : PLAYER_B_NAME);
                    board.reset();
                    i = inputChars.length;
                    LOGGER.info("New game, enter input: ");
                } else if (board.isDeuce()) {
                    LOGGER.info("deuce");
                } else {
                    LOGGER.info("Player {} : {} | Player {} : {}", PLAYER_A_NAME, board.getScorePA(), PLAYER_B_NAME, board.getScorePB());
                }
            }
        }
        return this;
    }

    private String normalize(final String toNormalize) {
        return isNotEmpty(toNormalize) ? toNormalize.toUpperCase() : EMPTY;
    }

    /**
     * Verify that the input only contains A or B character
     *
     * @param input from user to verify
     * @return true if the input is validate, false otherwise
     */
    private boolean validateInput(final String input) {
        return A_B_PATTERN.matcher(input).matches();
    }
}

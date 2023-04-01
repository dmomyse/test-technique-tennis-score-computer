package com.test.technique;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

import static com.test.technique.Score.ZERO;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class TennisGame {
    private static final Logger LOGGER = LoggerFactory.getLogger(TennisGame.class);
    private static final String EXIT_GAME = "EXIT";
    private static final String PLAYER_A_NAME = "A";
    private static final String PLAYER_B_NAME = "B";
    private static Player playerA = new Player(PLAYER_A_NAME, ZERO);
    private static Player playerB = new Player(PLAYER_B_NAME, ZERO);

    public void play() {
        boolean endGame = false;
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

            LOGGER.info(inputUser);
            char[] inputChars = inputUser.toCharArray();
            for (int i = 0; i < inputChars.length; i++) {
                String playerWonPoint = String.valueOf(inputChars[i]);

                if (playerA.getName().equals(playerWonPoint)) {
                    Score newScore = Score.values()[playerA.getScore().getNumber() + 1];
                    calculateNewScore(playerA, playerB, newScore);
                } else {
                    Score newScore = Score.values()[playerB.getScore().getNumber() + 1];
                    calculateNewScore(playerB, playerA, newScore);
                }
            }
        }
    }

    private void calculateNewScore(final Player player1, final Player player2, final Score newScore) {
        Score score = newScore;
        if (score == Score.WIN || (score.getNumber() > 3 && player2.getScore().getNumber() < 3)) {
            displayWinner(player1.getName());
            resetGame();
            return;
        }

        if (score == Score.FORTY && player2.getScore() == Score.FORTY) {
            LOGGER.info("deuce");
        } else if (player1.getScore() == Score.FORTY && player2.getScore() == Score.ADVANTAGE) {
            score = Score.FORTY;
            player1.setScore(score);
            player2.setScore(score);
            LOGGER.info("deuce");
        } else {
            if (PLAYER_A_NAME.equals(player1.getName())) {
                LOGGER.info("Player {} : {} | Player {} : {}", player1.getName(), newScore, player2.getName(), player2.getScore());
            } else {
                LOGGER.info("Player {} : {} | Player {} : {}", player2.getName(), player2.getScore(), player1.getName(), newScore);
            }
        }

        player1.setScore(score);
    }

    private void resetGame() {
        playerA = new Player(PLAYER_A_NAME, ZERO);
        playerB = new Player(PLAYER_B_NAME, ZERO);
        LOGGER.info("New game enter input:");
    }

    private void displayWinner(final String name) {
        LOGGER.info("Player {} wins the game", name);
    }

    private String normalize(final String toNormalize) {
        final String normalized = isNotEmpty(toNormalize) ? toNormalize.toUpperCase() : EMPTY;
        LOGGER.info("Normalizing input {} to {}", toNormalize, normalized);
        return normalized;
    }

    /**
     * Verify that the input only contains A or B character
     *
     * @param input from user to verify
     * @return true if the input is validate, false otherwise
     */
    private boolean validateInput(final String input) {
        return input.matches("[A-B]+");
    }
}

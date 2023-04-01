package com.test.technique;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.stream.Stream;

import static com.test.technique.Score.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class TennisGameTest {
    private final TennisGame tennisGame;

    TennisGameTest() {
        this.tennisGame = new TennisGame();
    }

    private void provideUserInput(final String input) {
        final InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @Nested
    class Play {
        static Stream<Arguments> exitInputInDifferentCaseSensitive() {
            return Stream.of(arguments("EXIT"),
                    arguments("exit"),
                    arguments("ExIt"),
                    arguments("eXiT"));
        }

        @ParameterizedTest
        @MethodSource("exitInputInDifferentCaseSensitive")
        void should_end_game_when_input_is_exit_case_insensitive(final String input) {
            // Given
            provideUserInput(input);

            // When
            TennisGame result = tennisGame.play();

            // Then
            assertThat(result.getEndGame()).isTrue();
        }

        @Test
        void should_end_game_even_if_there_are_score() {
            // Given
            provideUserInput("aba\nexit");

            // When
            TennisGame result = tennisGame.play();

            // Then
            assertThat(result.getEndGame()).isTrue();
            assertThat(result.getBoard().getScorePA()).isEqualTo(THIRTY);
            assertThat(result.getBoard().getScorePB()).isEqualTo(FIFTEEN);
        }

        @Test
        void should_validate_input_before_processing() {
            // Given
            provideUserInput("wrongInput\nexit");

            // When
            TennisGame result = tennisGame.play();

            // Then
            assertThat(result.getEndGame()).isTrue();
            assertThat(result.getBoard().getScorePA()).isEqualTo(ZERO);
            assertThat(result.getBoard().getScorePB()).isEqualTo(ZERO);
        }

        @Test
        void should_reset_after_a_winner_is_declared() {
            // Given
            provideUserInput("abaaa\nexit");

            // When
            TennisGame result = tennisGame.play();

            // Then
            assertThat(result.getEndGame()).isTrue();
            assertThat(result.getBoard().getScorePA()).isEqualTo(ZERO);
            assertThat(result.getBoard().getScorePB()).isEqualTo(ZERO);
        }

        @Test
        void should_reset_after_a_winner_is_declared_and_deuce() {
            // Given
            provideUserInput("abababaa\nexit");

            // When
            TennisGame result = tennisGame.play();

            // Then
            assertThat(result.getEndGame()).isTrue();
            assertThat(result.getBoard().getScorePA()).isEqualTo(ZERO);
            assertThat(result.getBoard().getScorePB()).isEqualTo(ZERO);
        }
    }
}
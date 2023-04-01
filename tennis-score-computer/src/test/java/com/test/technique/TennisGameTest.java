package com.test.technique;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TennisGameTest {
    private final TennisGame tennisGame;

    TennisGameTest() {
        this.tennisGame = new TennisGame();
    }

    private void mockUserInput(final String input) {
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
            mockUserInput(input);

            // When
            tennisGame.play();

            // Then
        }
    }
}
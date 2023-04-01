package com.test.technique;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.test.technique.Score.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ScoreTest {

    private static Stream<Arguments> playerHasScoredArguments() {
        return Stream.of(
                arguments(ZERO, ZERO, FIFTEEN),
                arguments(FIFTEEN, ZERO, THIRTY),
                arguments(THIRTY, ZERO, FORTY),
                arguments(ADVANTAGE, ZERO, WIN),
                arguments(WIN, ZERO, WIN),
                arguments(FORTY, ZERO, WIN),
                arguments(FORTY, FIFTEEN, WIN),
                arguments(FORTY, THIRTY, WIN),
                arguments(FORTY, FORTY, ADVANTAGE),
                arguments(FORTY, ADVANTAGE, FORTY)
        );
    }

    @ParameterizedTest
    @MethodSource("playerHasScoredArguments")
    void playerHasScored_should_increment_the_score(final Score given, final Score opponentScore, final Score expected) {
        // When
        final Score result = given.playerHasScored(opponentScore);
        // Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void opponentHasScored_should_decrement_the_score_when_score_is_advantage() {
        // Given
        final Score player = ADVANTAGE;
        final Score opponent = FORTY;
        final Score expected = FORTY;
        // When
        final Score result = player.opponentHasScored(opponent);
        // Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void opponentHasScored_should_do_nothing_when_score_is_not_advantage() {
        // Given
        final Score player = THIRTY;
        final Score opponent = FORTY;
        final Score expected = THIRTY;
        // When
        final Score result = player.opponentHasScored(opponent);
        // Then
        assertThat(result).isEqualTo(expected);
    }
}
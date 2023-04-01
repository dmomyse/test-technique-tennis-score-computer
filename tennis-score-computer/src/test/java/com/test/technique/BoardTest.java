package com.test.technique;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BoardTest {

    public static Stream<Arguments> pxHasScoreActionArguments() {
        return Stream.of(
                // Test when scored is max there is no crash
                arguments(
                        getConsumerStream(IntStream.rangeClosed(1, 10).mapToObj(a -> Board::pAHasScored)),
                        Score.WIN,
                        Score.ZERO,
                        true),
                // Test that the board is reset after
                arguments(
                        getConsumerStream(Stream.concat(IntStream.rangeClosed(1, 10).mapToObj(a -> Board::pAHasScored), Stream.of(Board::reset))),
                        Score.ZERO,
                        Score.ZERO,
                        false),
                // Test that the game is still ongoing
                arguments(
                        getConsumerStream(Stream.of(Board::pAHasScored, Board::pBHasScored, Board::pAHasScored)),
                        Score.THIRTY,
                        Score.FIFTEEN,
                        false),
                // Test player A is the winner
                arguments(
                        getConsumerStream(Stream.of(Board::pAHasScored, Board::pAHasScored, Board::pAHasScored, Board::pAHasScored)),
                        Score.WIN,
                        Score.ZERO,
                        true),
                // Test player B is the winner
                arguments(
                        getConsumerStream(Stream.of(Board::pBHasScored, Board::pBHasScored, Board::pBHasScored, Board::pBHasScored)),
                        Score.ZERO,
                        Score.WIN,
                        true),
                // Test player A can have advantage
                arguments(
                        getConsumerStream(Stream.of(Board::pAHasScored, Board::pAHasScored, Board::pBHasScored, Board::pBHasScored, Board::pBHasScored, Board::pAHasScored, Board::pAHasScored)),
                        Score.ADVANTAGE,
                        Score.FORTY,
                        false),
                // Test player A win after having Advantage
                arguments(
                        getConsumerStream(Stream.of(Board::pAHasScored, Board::pAHasScored, Board::pBHasScored, Board::pBHasScored, Board::pBHasScored, Board::pAHasScored, Board::pAHasScored, Board::pAHasScored)),
                        Score.WIN,
                        Score.FORTY,
                        true),
                // Test it doesn't crash with empty action
                arguments(
                        Stream.empty(),
                        Score.ZERO,
                        Score.ZERO,
                        false)
        );
    }

    private static Stream<Consumer<Board>> getConsumerStream(final Stream<Consumer<Board>> consumerBoard) {
        return consumerBoard;
    }

    @ParameterizedTest
    @MethodSource("pxHasScoreActionArguments")
    void pXHasScored_should_modified_score_according_to_current_player_and_opponent_score(final Stream<Consumer<Board>> givenAction, final Score scorePlayerA, final Score scorePlayerB, final Boolean isFinished) {
        // Given
        Board board = new Board();

        // When
        givenAction
                .forEach(consumerAction -> consumerAction.accept(board));
        // Then
        assertThat(board.hasAWinner()).isEqualTo(isFinished);
        assertThat(board.getScorePA()).isEqualTo(scorePlayerA);
        assertThat(board.getScorePB()).isEqualTo(scorePlayerB);
    }

    public static Stream<Arguments> isDeuceArguments() {
        return Stream.of(
                // Test deuce is triggered
                arguments(
                        getConsumerStream(Stream.of(Board::pBHasScored, Board::pBHasScored, Board::pBHasScored, Board::pAHasScored, Board::pAHasScored, Board::pAHasScored)),
                        true),
                // Test deuce is triggered after an advantage
                arguments(
                        getConsumerStream(Stream.of(Board::pBHasScored, Board::pBHasScored, Board::pBHasScored, Board::pAHasScored, Board::pAHasScored, Board::pAHasScored, Board::pBHasScored, Board::pAHasScored)),
                        true),
                // Test that deuce is not triggered when not necessary
                arguments(
                        getConsumerStream(Stream.of(Board::pBHasScored, Board::pAHasScored, Board::pAHasScored, Board::pAHasScored)),
                        false),
                // Test that deuce is not triggered when there is an advantage
                arguments(
                        getConsumerStream(Stream.of(Board::pBHasScored, Board::pBHasScored, Board::pBHasScored, Board::pAHasScored, Board::pAHasScored, Board::pAHasScored, Board::pBHasScored)),
                        false)
        );
    }

    @ParameterizedTest
    @MethodSource("isDeuceArguments")
    void isDeuce_when_player_and_opponent_have_both_forty(final Stream<Consumer<Board>> givenAction, final Boolean isDeuce) {
        // Given
        Board board = new Board();
        // When
        givenAction
                .forEach(consumerAction -> consumerAction.accept(board));
        // Then
        assertThat(board.isDeuce()).isEqualTo(isDeuce);
    }
}
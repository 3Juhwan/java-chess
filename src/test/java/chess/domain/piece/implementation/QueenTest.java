package chess.domain.piece.implementation;

import chess.domain.board.BoardState;
import chess.domain.piece.PieceDto;
import chess.domain.piece.PieceState;
import chess.domain.piece.PieceType;
import chess.domain.player.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QueenTest {

    private PieceState whiteQueen;
    private BoardState boardState;
    private Map<Position, PieceDto> boardDto;
    private PieceDto whitePiece = new PieceDto(PieceType.QUEEN, Team.WHITE);
    private PieceDto blackPiece = new PieceDto(PieceType.QUEEN, Team.BLACK);

    @BeforeEach
    void setUp() {
        whiteQueen = Queen.of(Position.of("C4"), Team.WHITE);
        boardDto = new HashMap<>();
        boardState = BoardState.of(boardDto);
    }

    @ParameterizedTest
    @ValueSource(strings = {"A2", "A4", "A6", "C6", "E6", "E4", "E2", "C2"})
    @DisplayName("진행 경로에 아무것도 없는 경우 이동 가능")
    void moveToEmpty(String target) {
        assertThat(whiteQueen.move(Position.of(target), boardState))
                .isInstanceOf(Queen.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"A2", "A4", "A6", "C6", "E6", "E4", "E2", "C2"})
    @DisplayName("진행 타겟에 우리편이 있는 경우 예외 발생")
    void moveToAlly(String target) {
        boardDto.put(Position.of(target), whitePiece);
        boardState = BoardState.of(boardDto);
        assertThatThrownBy(() -> whiteQueen.move(Position.of(target), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"A2:B3", "A4:B4", "A6:B5", "C6:C5", "E6:D5", "E4:D4", "E2:D3", "C2:C3"}, delimiter = ':')
    @DisplayName("진행 경로에 우리편이 있는 경우 예외 발생")
    void allyOnPath(String target, String path) {
        boardDto.put(Position.of(path), whitePiece);
        boardState = BoardState.of(boardDto);
        assertThatThrownBy(() -> whiteQueen.move(Position.of(target), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"A2", "A4", "A6", "C6", "E6", "E4", "E2", "C2"})
    @DisplayName("진행 타겟에 적군이 있는 경우 이동 가능")
    void moveToEnemy(String target) {
        boardDto.put(Position.of(target), blackPiece);
        boardState = BoardState.of(boardDto);
        assertThat(whiteQueen.move(Position.of(target), boardState))
                .isInstanceOf(Queen.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"A2:B3", "A4:B4", "A6:B5", "C6:C5", "E6:D5", "E4:D4", "E2:D3", "C2:C3"}, delimiter = ':')
    @DisplayName("진행 경로에 적군이 있는 경우 예외 발생")
    void enemyOnPath(String target, String path) {
        boardDto.put(Position.of(path), blackPiece);
        boardState = BoardState.of(boardDto);
        assertThatThrownBy(() -> whiteQueen.move(Position.of(target), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"A1", "A3", "A5", "B2", "E5"})
    @DisplayName("진행 규칙에 어긋나는 경우 예외 발생")
    void movePolicyException(String input) {
        assertThatThrownBy(() -> whiteQueen.move(Position.of(input), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"A1", "A3", "A5", "B2", "E5"})
    @DisplayName("진행 타겟에 적군이 있지만 진행 규칙에 어긋나는 경우 예외 발생")
    void moveToEnemyException(String target) {
        boardDto.put(Position.of(target), blackPiece);
        boardState = BoardState.of(boardDto);
        assertThatThrownBy(() -> whiteQueen.move(Position.of(target), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

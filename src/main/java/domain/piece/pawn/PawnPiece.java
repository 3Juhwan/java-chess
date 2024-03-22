package domain.piece.pawn;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.Type;
import domain.position.Position;

public abstract class PawnPiece implements Piece {
    private static final int DISTANCE_TWO = 2;
    private static final int DISTANCE_ONE = 1;

    private final Color color;

    protected PawnPiece(Color color) {
        this.color = color;
    }

    public final void validate(final Position source, final Position target, final Piece other) {
        validateColorDifference(other);
        validateForwardMovement(source, target);
        if (isPawnMovement(source, target, other)) {
            return;
        }
        throw new IllegalArgumentException("잘못된 방향으로 이동하고 있습니다.");
    }

    private void validateColorDifference(final Piece other) {
        if (this.color().equals(other.color())) {
            throw new IllegalArgumentException("같은 팀의 말을 잡을 수 없습니다.");
        }
    }

    protected abstract void validateForwardMovement(final Position source, final Position target);

    private boolean isPawnMovement(final Position source, final Position target, final Piece other) {
        return isMovingTwoDistanceForward(source, target, other) ||
                isMovingOneDistanceForward(source, target, other) ||
                isMovingOneDistanceDiagonal(source, target, other);
    }

    private boolean isMovingTwoDistanceForward(final Position source, final Position target, final Piece other) {
        return isAtSameRank(source) && source.isStraightAt(target)
                && source.isDistanceAt(target, DISTANCE_TWO) && nonPieceExist(other);
    }

    protected abstract boolean isAtSameRank(final Position source);

    private boolean isMovingOneDistanceForward(final Position source, final Position target, final Piece other) {
        return source.isStraightAt(target) && source.isDistanceAt(target, DISTANCE_ONE) && nonPieceExist(other);
    }

    private boolean isMovingOneDistanceDiagonal(final Position source, final Position target, final Piece other) {
        return source.isDiagonalAt(target) && source.isAdjacentAt(target) && isOpposite(other);
    }

    private boolean nonPieceExist(Piece other) {
        return other.color().isNeutrality();
    }

    private boolean isOpposite(Piece other) {
        if (nonPieceExist(other)) {
            return false;
        }
        return !this.color().equals(other.color());
    }

    @Override
    public Color color() {
        return color;
    }

    @Override
    public final Type type() {
        return Type.PAWN;
    }
}

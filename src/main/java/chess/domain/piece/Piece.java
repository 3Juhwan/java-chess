package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.Position;
import java.util.List;

public abstract class Piece {

    private final Team team;
    protected Position position;

    public Piece(Team team, Position position) {
        this.team = team;
        this.position = position;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    public String getTeamName() {
        return team.name();
    }

    public char getColValue() {
        return position.getCol().getValue();
    }

    public int getRowValue() {
        return position.getRow().getValue();
    }

    public abstract double getScore();

    public abstract List<Position> findPath(Position destination);

    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    public boolean isSameTeam(Piece piece) {
        return piece.isSameTeam(team);
    }

    public boolean isBlackTeam() {
        return team == Team.BLACK;
    }

    public boolean isBlank() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public void move(Position destination) {
        position = destination;
    }
}

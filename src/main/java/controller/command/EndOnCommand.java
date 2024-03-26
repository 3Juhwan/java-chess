package controller.command;

import domain.board.ChessBoard;
import view.OutputView;

public class EndOnCommand implements Command {
    @Override
    public void execute(final ChessBoard board, final OutputView outputView) {
        outputView.printScore(board.calculateScore());
    }

    @Override
    public boolean isNotEnded() {
        return false;
    }
}

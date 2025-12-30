package useCases;

import entities.board.Move;
import entities.board.Board;

import java.util.List;

public class MoveSelectorUC {

    private final MoveGeneratorUC generator = new MoveGeneratorUC();
    private final MoveValidatorUC validator = new MoveValidatorUC();

    public Move selectBestMove(Board board) {

        List<Move> moves = generator.generateMoves(board);

        for (Move m : moves) {
            if (validator.isLegal(board, m) && board.getRegle().getTour()== board.getPlato().get(m.depart()).getCouleur()) {
                return m; // premier coup légal
            }
        }

        return null; // mat ou pat
    }
}

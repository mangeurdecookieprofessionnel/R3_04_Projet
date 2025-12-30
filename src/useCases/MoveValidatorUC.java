package useCases;

import entities.board.IPiece;
import entities.board.Move;
import entities.board.Board;
import entities.board.Square;
import entities.modeles.Couleur;
import entities.pieces.King;

import java.util.List;
import java.util.Map;

public class MoveValidatorUC {

    public boolean isLegal(Board board, Move move) {
        Square from = move.depart();
        Square to = move.arrivee();

        IPiece moving = board.getPlato().get(from);
        IPiece captured = board.getPlato().get(to);

        if (moving == null) return false;

        // 1️⃣ Simuler le coup
        board.getPlato().remove(from);
        board.getPlato().put(to, moving);

        // 2️⃣ Trouver le roi du joueur
        Square roi = findKing(board, moving.getCouleur());

        // 3️⃣ ICI vérifier si le roi est sur une case attaquée
        // (pour un roi qui se déplace, 'roi' sera la case 'to')
        boolean illegal = isSquareAttacked(board, roi, moving.getCouleur());

        // 4️⃣ Annuler la simulation
        board.getPlato().remove(to);
        board.getPlato().put(from, moving);
        if (captured != null) {
            board.getPlato().put(to, captured);
        }

        return !illegal;
    }

    private Square findKing(Board board, Couleur couleur) {
        for (Map.Entry<Square, IPiece> e : board.getPlato().entrySet()) {
            if (e.getValue() instanceof King && e.getValue().getCouleur() == couleur) {
                return e.getKey();
            }
        }
        return null;
    }

    public boolean isSquareAttacked(Board board, Square square, Couleur couleur) {

        if (square == null) return false;

        MoveGeneratorUC moveGenerator = new MoveGeneratorUC();
        List<Move> moves = moveGenerator.generateMoves(board);
        for (Move move : moves) {
            if(move.arrivee().equals(square)) {
                return true;
            }
        }
        return false;
    }
}

package useCases;

import entities.board.IPiece;
import entities.board.Move;
import entities.board.Board;
import entities.board.Square;
import entities.modeles.Couleur;

import java.util.*;

public class MoveGeneratorUC {

    public List<Move> generateMoves(Board board) {

        List<Move> moves = new ArrayList<>();
        Couleur tour = board.getRegle().getTour();

        Map<Square, IPiece> bord = new HashMap<>(board.getPlato());

        for (Map.Entry<Square, IPiece> entry : bord.entrySet()) {

            Square from = entry.getKey();
            IPiece piece = entry.getValue();

            if (piece.getCouleur() != tour) continue;

            Set<Square> destinations = board.DeplacementsPossible(from);

            for (Square to : destinations) {
                moves.add(new Move(from, to));
            }
        }

        return moves;
    }
}

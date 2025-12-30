package useCases;

import Appli.IPiece;
import Appli.Move;
import Appli.Plato;
import Appli.Square;
import Modele.Couleur;

import java.util.*;

public class MoveGeneratorUC {

    public List<Move> generateMoves(Plato plato) {

        List<Move> moves = new ArrayList<>();
        Couleur tour = plato.getRegle().getTour();

        Map<Square, IPiece> bord = new HashMap<>(plato.getPlato());

        for (Map.Entry<Square, IPiece> entry : bord.entrySet()) {

            Square from = entry.getKey();
            IPiece piece = entry.getValue();

            if (piece.getCouleur() != tour) continue;

            Set<Square> destinations = plato.DeplacementsPossible(from);

            for (Square to : destinations) {
                moves.add(new Move(from, to));
            }
        }

        return moves;
    }
}

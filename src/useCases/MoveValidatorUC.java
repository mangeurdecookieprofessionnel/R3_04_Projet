package useCases;

import Appli.IPiece;
import Appli.Move;
import Appli.Plato;
import Appli.Square;
import Modele.Couleur;
import Pieces.King;

import java.util.List;
import java.util.Map;

public class MoveValidatorUC {

    public boolean isLegal(Plato plato, Move move) {
        Square from = move.depart();
        Square to = move.arrivee();

        IPiece moving = plato.getPlato().get(from);
        IPiece captured = plato.getPlato().get(to);

        if (moving == null) return false;

        // 1️⃣ Simuler le coup
        plato.getPlato().remove(from);
        plato.getPlato().put(to, moving);

        // 2️⃣ Trouver le roi du joueur
        Square roi = findKing(plato, moving.getCouleur());

        // 3️⃣ ICI vérifier si le roi est sur une case attaquée
        // (pour un roi qui se déplace, 'roi' sera la case 'to')
        boolean illegal = isSquareAttacked(plato, roi, moving.getCouleur());

        // 4️⃣ Annuler la simulation
        plato.getPlato().remove(to);
        plato.getPlato().put(from, moving);
        if (captured != null) {
            plato.getPlato().put(to, captured);
        }

        return !illegal;
    }

    private Square findKing(Plato plato, Couleur couleur) {
        for (Map.Entry<Square, IPiece> e : plato.getPlato().entrySet()) {
            if (e.getValue() instanceof King && e.getValue().getCouleur() == couleur) {
                return e.getKey();
            }
        }
        return null;
    }

    public boolean isSquareAttacked(Plato plato, Square square, Couleur couleur) {

        if (square == null) return false;

        MoveGeneratorUC moveGenerator = new MoveGeneratorUC();
        List<Move> moves = moveGenerator.generateMoves(plato);
        for (Move move : moves) {
            if(move.arrivee().equals(square)) {
                return true;
            }
        }
        return false;
    }
}

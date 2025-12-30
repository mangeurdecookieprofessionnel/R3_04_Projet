package Factory;

import Appli.Square;
import Modele.Couleur;
import Appli.IPiece;
import Pieces.King;
import Pieces.Rook;

import java.util.HashMap;
import java.util.Map;

public class PieceFactory {
    public static Map<Square, IPiece> Begin() {
        Map<Square, IPiece> map = new HashMap<>();
        map.put(new Square(4, 7), new King(Couleur.BLACK));
        map.put(new Square(6, 6), new Rook(Couleur.WHITE));
        map.put(new Square(2, 3), new King(Couleur.WHITE));
        return map;
    }
}

package Pieces;

import Appli.IPiece;
import Appli.Plato;
import Appli.Square;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestKing {

    @Test
    public void test(){

        IPiece roi = new King("Blanc");
        Square squareKing = new Square(5,5);

        System.out.println(roi.afficherMouvementPiece(squareKing));
    }
}

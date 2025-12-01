package Pieces;

import Appli.IPiece;
import Appli.Square;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRook {

    @Test
    public void test(){

        IPiece roi = new Rook("Blanc");
        Square squareRook = new Square(5,5);

        System.out.println(roi.afficherMouvementPiece(squareRook));
    }
}

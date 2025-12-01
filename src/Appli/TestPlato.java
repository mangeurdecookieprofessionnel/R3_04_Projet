package Appli;

import Pieces.King;
import Pieces.Rook;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPlato {

    @Test
    public void test(){
        Plato plato = new Plato();
        IPiece roi = new King("Blanc");
        IPiece tour = new Rook("Blanc");
        Square squareKing = new Square(5,5);
        Square squareRook = new Square(5,5);
        assertEquals(plato.afficherMouvement(roi, squareKing),"1)   X : 4        Y :6\n" +
                "2)   X : 5        Y :6\n" +
                "3)   X : 6        Y :6\n" +
                "4)   X : 6        Y :5\n" +
                "5)   X : 6        Y :4\n" +
                "6)   X : 5        Y :4\n" +
                "7)   X : 4        Y :4\n" +
                "8)   X : 4        Y :5\n");

        assertEquals(plato.afficherMouvement(tour,squareRook), "1)   X : 5        Y :6\n" +
                "2)   X : 5        Y :7\n" +
                "3)   X : 6        Y :5\n" +
                "4)   X : 7        Y :5\n" +
                "5)   X : 5        Y :4\n" +
                "6)   X : 5        Y :3\n" +
                "7)   X : 5        Y :2\n" +
                "8)   X : 5        Y :1\n" +
                "9)   X : 5        Y :0\n" +
                "10)   X : 4        Y :5\n" +
                "11)   X : 3        Y :5\n" +
                "12)   X : 2        Y :5\n" +
                "13)   X : 1        Y :5\n" +
                "14)   X : 0        Y :5\n");
    }


}

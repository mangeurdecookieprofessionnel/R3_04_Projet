package Appli;

import Pieces.King;
import Pieces.Rook;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPlato {
    Plato plato = new Plato();
    Square squareKing = new Square(4,0);
    Square squareRook = new Square(0,0);
    @Test
    public void testKing(){
        assertEquals("1) X : 3 - Y : 1\n2) X : 4 - Y : 1\n3) X : 5 - Y : 1\n4) X : 5 - Y : 0\n5) X : 3 - Y : 0\n",plato.afficherMouvement(squareKing));
    }
    @Test
    public void testRook(){
        assertEquals("1) X : 0 - Y : 1\n" +
                "2) X : 1 - Y : 0\n" +
                "3) X : 0 - Y : 2\n" +
                "4) X : 2 - Y : 0\n" +
                "5) X : 0 - Y : 3\n" +
                "6) X : 3 - Y : 0\n" +
                "7) X : 0 - Y : 4\n" +
                "8) X : 4 - Y : 0\n" +
                "9) X : 0 - Y : 5\n" +
                "10) X : 5 - Y : 0\n" +
                "11) X : 0 - Y : 6\n" +
                "12) X : 6 - Y : 0\n" +
                "13) X : 0 - Y : 7\n" +
                "14) X : 7 - Y : 0\n",
                plato.afficherMouvement(squareRook));
    }


}

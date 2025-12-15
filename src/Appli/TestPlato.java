package Appli;

import Pieces.King;
import Pieces.Rook;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        ArrayList<Square> list = new ArrayList<>();
        list.add(new Square(0,1));
        list.add(new Square(1,0));
        list.add(new Square(0,2));
        list.add(new Square(2,0));
        list.add(new Square(0,3));
        list.add(new Square(3,0));
        list.add(new Square(0,4));
        list.add(new Square(0,5));
        list.add(new Square(5,0));
        list.add(new Square(0,6));
        list.add(new Square(6,0));
        list.add(new Square(0,7));
        list.add(new Square(7,0));

        assertEquals(list,plato.DeplacementsPossible(squareRook));
    }


}

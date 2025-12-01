package Pieces;

import Appli.IPiece;
import Appli.Plato;
import Appli.Square;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestKing {

    @Test
    public void test(){

        IPiece roi = new King("Blanc");
        Square squareKing = new Square(5,5);

        ArrayList<Square> list = new ArrayList<>();
        list.add(new Square(4,6));
        list.add(new Square(5,6));
        list.add(new Square(6,6));
        list.add(new Square(6,5));
        list.add(new Square(6,4));
        list.add(new Square(5,4));
        list.add(new Square(4,4));
        list.add(new Square(4,5));

        assertEquals(roi.mouvement(squareKing), list);
    }
}

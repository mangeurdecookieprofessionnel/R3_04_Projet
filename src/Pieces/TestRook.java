package Pieces;

import Appli.IPiece;
import Appli.Square;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRook {

    @Test
    public void testMid(){

        IPiece tour = new Rook(Couleur.WHITE);
        Square squareRook = new Square(5,5);

        ArrayList<Square> list = new ArrayList<>();
        list.add(new Square(5,6));
        list.add(new Square(5,4));
        list.add(new Square(6,5));
        list.add(new Square(4,5));
        list.add(new Square(5,7));
        list.add(new Square(5,3));
        list.add(new Square(7,5));
        list.add(new Square(3,5));
        list.add(new Square(5,2));
        list.add(new Square(2,5));
        list.add(new Square(5,1));
        list.add(new Square(1,5));
        list.add(new Square(5,0));
        list.add(new Square(0,5));

        assertEquals(list, tour.mouvement(squareRook));
    }
    @Test
    public void testCorner(){

        IPiece tour = new Rook(Couleur.WHITE);
        Square squareRook = new Square(0,0);

        ArrayList<Square> list = new ArrayList<>();
        list.add(new Square(0,1));
        list.add(new Square(1,0));
        list.add(new Square(0,2));
        list.add(new Square(2,0));
        list.add(new Square(0,3));
        list.add(new Square(3,0));
        list.add(new Square(0,4));
        list.add(new Square(4,0));
        list.add(new Square(0,5));
        list.add(new Square(5,0));
        list.add(new Square(0,6));
        list.add(new Square(6,0));
        list.add(new Square(0,7));
        list.add(new Square(7,0));

        assertEquals(list, tour.mouvement(squareRook));
    }
}

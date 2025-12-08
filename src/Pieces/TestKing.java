package Pieces;

import Appli.IPiece;
import Appli.Plato;
import Appli.Square;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestKing {

    @Test
    public void testmid(){

        IPiece roi = new King(Couleur.WHITE);
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

        assertEquals(list,roi.mouvement(squareKing));
    }
    @Test
    public void testside(){
        IPiece roi = new King(Couleur.WHITE);
        Square squareKing = new Square(0,5);

        ArrayList<Square> list = new ArrayList<>();
        list.add(new Square(0,6));
        list.add(new Square(1,6));
        list.add(new Square(1,5));
        list.add(new Square(1,4));
        list.add(new Square(0,4));
        assertEquals(list,roi.mouvement(squareKing));

    }
    @Test
    public void testcorner(){
        IPiece roi = new King(Couleur.WHITE);
        Square squareKing = new Square(7,7);
        ArrayList<Square> list = new ArrayList<>();
        list.add(new Square(7,6));
        list.add(new Square(6,6));
        list.add(new Square(6,7));
        assertEquals(list,roi.mouvement(squareKing));

    }
}

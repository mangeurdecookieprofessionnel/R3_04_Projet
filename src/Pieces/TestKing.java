package Pieces;

import Appli.IPiece;
import Appli.Plato;
import Appli.Square;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestKing {

    @Test
    public void testmid(){

        IPiece roi = new King(Couleur.WHITE);
        Square squareKing = new Square(5,5);

        Set<Square> list = new HashSet<>();
        list.add(new Square(4,6));
        list.add(new Square(5,6));
        list.add(new Square(6,6));
        list.add(new Square(6,5));
        list.add(new Square(6,4));
        list.add(new Square(5,4));
        list.add(new Square(4,4));
        list.add(new Square(4,5));

        List<Square> occupee = new ArrayList<>();
        occupee.add(new Square(1,5));

        Set<Square> mouvement = new HashSet<>(roi.mouvement(squareKing, occupee));

        assertEquals(list,mouvement);
    }
    @Test
    public void testside(){
        IPiece roi = new King(Couleur.WHITE);
        Square squareKing = new Square(0,5);

        Set<Square> list = new HashSet<>();
        list.add(new Square(0,6));
        list.add(new Square(1,6));
        list.add(new Square(1,5));
        list.add(new Square(1,4));
        list.add(new Square(0,4));

        List<Square> occupee = new ArrayList<>();
        occupee.add(new Square(1,5));

        Set<Square> mouvement = new HashSet<>(roi.mouvement(squareKing, occupee));

        assertEquals(list,mouvement);

    }
    @Test
    public void testcorner(){
        IPiece roi = new King(Couleur.WHITE);
        Square squareKing = new Square(7,7);
        Set<Square> list = new HashSet<>();
        list.add(new Square(7,6));
        list.add(new Square(6,6));
        list.add(new Square(6,7));

        List<Square> occupee = new ArrayList<>();
        occupee.add(new Square(1,5));

        Set<Square> mouvement = new HashSet<>(roi.mouvement(squareKing, occupee));

        assertEquals(list,mouvement);
    }
}

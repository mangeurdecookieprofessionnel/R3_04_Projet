package Pieces;

import Appli.IPiece;
import Appli.Square;
import Modele.Couleur;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRook {

    @Test
    public void test(){

        IPiece tour = new Rook(Couleur.WHITE);
        Square squareRook = new Square(5,5);

        Set<Square> list = new HashSet<>();
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
        list.add(new Square(5,0));

        List<Square> occupee = new ArrayList<>();
        occupee.add(new Square(1,5));

        Set<Square> mouvement = new HashSet<>(tour.mouvement(squareRook, occupee));

        assertEquals(list, mouvement);
    }
}

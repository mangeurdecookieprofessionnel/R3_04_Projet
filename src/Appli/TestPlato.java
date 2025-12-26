package Appli;

import Modele.Couleur;
import Pieces.King;
import Pieces.Rook;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPlato {
    @Test
    public void testPlato() {

        Plato plato = new Plato();


        Set<Square> list = new HashSet<>();
        list.add(new Square(0, 1));
        list.add(new Square(0, 2));
        list.add(new Square(0, 3));
        list.add(new Square(0, 4));
        list.add(new Square(0, 5));
        list.add(new Square(0, 6));

        list.add(new Square(1, 0));
        list.add(new Square(2, 0));
        list.add(new Square(3, 0));
        list.add(new Square(0, 7));

        Set<Square> mouvement = new HashSet<>(plato.DeplacementsPossible(new Square(0,0)));

        assertEquals(mouvement, list);


        Set<Square> list2 = new HashSet<>();
        list2.add(new Square(0, 7));
        Set<Square> manger = new HashSet<>(plato.manger(new Square(0,0)));
        assertEquals(manger, list2);

    }
}

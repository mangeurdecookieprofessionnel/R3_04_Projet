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
    	// Création du plateau
        Plato plato = new Plato();

        
        // Liste des mouvements possibles pour le roi blanc
        Set<Square> listRoiB = new HashSet<>();
        listRoiB.add(new Square(1, 2));
        listRoiB.add(new Square(1, 3));
        listRoiB.add(new Square(1, 4));
        listRoiB.add(new Square(2, 4));
        listRoiB.add(new Square(3, 4));
        listRoiB.add(new Square(3, 3));
        listRoiB.add(new Square(3, 2));
        listRoiB.add(new Square(2, 2));

        // Création de la liste mouvement via la méthode testée
        Set<Square> mouvementsRoiB = new HashSet<>(plato.DeplacementsPossible(new Square(2,3)));
        
        // Comparaison des deux listes
        assertEquals(mouvementsRoiB, listRoiB);
        
        
        // Liste des mouvements possibles pour la tour blanche
        Set<Square> listTourB = new HashSet<>();
        listTourB.add(new Square(6, 7));
        listTourB.add(new Square(7, 6));
        listTourB.add(new Square(6, 5));
        listTourB.add(new Square(6, 4));
        listTourB.add(new Square(6, 3));
        listTourB.add(new Square(6, 2));
        listTourB.add(new Square(6, 1));
        listTourB.add(new Square(6, 0));
        listTourB.add(new Square(5, 6));
        listTourB.add(new Square(4, 6));
        listTourB.add(new Square(3, 6));
        listTourB.add(new Square(2, 6));
        listTourB.add(new Square(1, 6));
        listTourB.add(new Square(0, 6));

        // Création de la liste mouvement via la méthode testée
        Set<Square> mouvementsTourB = new HashSet<>(plato.DeplacementsPossible(new Square(6,6)));
        
        // Comparaison des deux listes
        assertEquals(mouvementsTourB, listTourB);
        
        
        // Liste des mouvements possibles pour le roi noir
        Set<Square> listRoiN = new HashSet<>();
        listRoiN.add(new Square(5, 7));
        listRoiN.add(new Square(5, 6));
        listRoiN.add(new Square(4, 6));
        listRoiN.add(new Square(3, 6));
        listRoiN.add(new Square(3, 7));

        // Création de la liste mouvement via la méthode testée
        Set<Square> mouvementsRoiN = new HashSet<>(plato.DeplacementsPossible(new Square(4,7)));
        
        // Comparaison des deux listes
        assertEquals(mouvementsRoiN, listRoiN);
        
        // On bouge la tour
        plato.jouerUnCoup(new Move(new Square(6, 6), new Square(3, 6)));
        
        // Le roi noir prend la tour
        plato.jouerUnCoup(new Move(new Square(4, 7), new Square(3, 6)));
    }
}

package Pieces;

import Appli.IPiece;
import Appli.Square;
import Modele.Couleur;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestKing {

    @Test
    // Test des possibilités dans une coordonnée quelconque
    public void testmid(){
    	// Création du roi aux coordonnées [5;5]
    	IPiece roi = new King(Couleur.WHITE);
        Square squareKing = new Square(5,5);

        // Liste des mouvements possibles
        Set<Square> list = new HashSet<>();
        list.add(new Square(4,4));
        list.add(new Square(4,5));
        list.add(new Square(4,6));
        list.add(new Square(5,4));
        list.add(new Square(5,6));
        list.add(new Square(6,4));
        list.add(new Square(6,5));
        list.add(new Square(6,6));
        
        // Création de la liste mouvement via la méthode testée
        Set<Square> mouvements = roi.aplatir(roi.mouvement(squareKing));

        // Comparaison des deux listes
        assertEquals(list,mouvements);
    }
    
    @Test
    // Test des possibilités sur le côté
    public void testside(){
    	// Création du roi aux coordonnées [0;5]
        IPiece roi = new King(Couleur.WHITE);
        Square squareKing = new Square(0,5);

        // Liste des mouvements possibles
        Set<Square> list = new HashSet<>();
        list.add(new Square(1,5));
        list.add(new Square(0,6));
        list.add(new Square(1,4));
        list.add(new Square(0,4));
        list.add(new Square(1,6));

        // Création de la liste mouvement via la méthode testée
        Set<Square> mouvements = roi.aplatir(roi.mouvement(squareKing));

        // Comparaison des deux listes
        assertEquals(list,mouvements);
    }
    
    @Test
    // Test des possibilités en corner
    public void testcorner(){
    	// Création du roi aux coordonnées [7;7]
        IPiece roi = new King(Couleur.WHITE);
        Square squareKing = new Square(7,7);
        
        // Liste des mouvements possibles
        Set<Square> list = new HashSet<>();
        list.add(new Square(6,6));
        list.add(new Square(6,7));
        list.add(new Square(7,6));
        
        // Création de la liste mouvement via la méthode testée
        Set<Square> mouvements = roi.aplatir(roi.mouvement(squareKing));

        // Comparaison des deux listes
        assertEquals(list,mouvements);
    }
}

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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.Assert.*;

public class TestKing {

    @Test
    // Test des possibilités dans une coordonnée quelconque
    public void testmid(){
    	// Création du roi aux coordonnées [5;5]
    	IPiece roi = new King(Couleur.WHITE);
        Square squareKing = new Square(5,5);

        // Liste des mouvements possibles
        Set<Square> list = new HashSet<>();
        list.add(new Square(4,6));
        list.add(new Square(5,6));
        list.add(new Square(6,6));
        list.add(new Square(6,5));
        list.add(new Square(6,4));
        list.add(new Square(5,4));
        list.add(new Square(4,4));
        list.add(new Square(4,5));
        
        // Création d'une list de coordonnées occupées (ici vide)
        List<Square> occupee = new ArrayList<>();
        
        // Création de la liste mouvement via la méthode testée
        Set<Square> mouvement = new HashSet<>(roi.mouvement(squareKing, occupee));

        // Comparaison des deux listes
        assertEquals(list,mouvement);
    }
    
    @Test
    // Test des possibilités sur le côté
    public void testside(){
    	// Création du roi aux coordonnées [0;5]
        IPiece roi = new King(Couleur.WHITE);
        Square squareKing = new Square(0,5);

        // Liste des mouvements possibles
        Set<Square> list = new HashSet<>();
        list.add(new Square(0,6));
        list.add(new Square(1,6));
        list.add(new Square(1,4));
        list.add(new Square(1,5));
        list.add(new Square(0,4));

        // Création d'une list de coordonnées occupées (ici vide)
        List<Square> occupee = new ArrayList<>();

        // Création de la liste mouvement via la méthode testée
        Set<Square> mouvement = new HashSet<>(roi.mouvement(squareKing, occupee));

        // Comparaison des deux listes
        assertEquals(list,mouvement);
    }
    
    @Test
    // Test des possibilités en corner
    public void testcorner(){
    	// Création du roi aux coordonnées [7;7]
        IPiece roi = new King(Couleur.WHITE);
        Square squareKing = new Square(7,7);
        
        // Liste des mouvements possibles
        Set<Square> list = new HashSet<>();
        list.add(new Square(7,6));
        list.add(new Square(6,6));
        list.add(new Square(6,7));
        
        // Création d'une list de coordonnées occupées (ici vide)
        List<Square> occupee = new ArrayList<>();
        
        // Création de la liste mouvement via la méthode testée
        Set<Square> mouvement = new HashSet<>(roi.mouvement(squareKing, occupee));

        // Comparaison des deux listes
        assertEquals(list,mouvement);
    }
    
    @Test
    // Test des possibilités avec des coordonnées occupées
    public void testoccupeee(){
    	// Création du roi aux coordonnées [5;5]
        IPiece roi = new King(Couleur.WHITE);
        Square squareKing = new Square(5,5);
        
        // Liste des mouvements possibles
        Set<Square> list = new HashSet<>();
        list.add(new Square(4,6));
        // Plus possible (occupée) : list.add(new Square(5,6));
        list.add(new Square(6,6));
        list.add(new Square(6,5));
        // Plus possible (occupée) : list.add(new Square(6,4));
        list.add(new Square(5,4));
        list.add(new Square(4,4));
        list.add(new Square(4,5));
        
        // Création d'une list de coordonnées occupées (ici vide)
        List<Square> occupee = new ArrayList<>();
        occupee.add(new Square(5,6));
        occupee.add(new Square(6,4));
        
        
        // Création de la liste mouvement via la méthode testée
        Set<Square> mouvement = new HashSet<>(roi.mouvement(squareKing, occupee));

        // Comparaison des deux listes
        assertEquals(list,mouvement);
    }
}

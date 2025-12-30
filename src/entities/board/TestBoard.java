package entities.board;

import org.junit.jupiter.api.Test;

import entities.modeles.EtatPartie;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBoard {
    @Test
    // Roi vs Roi
    public void testPlatoManqueMateriel() {
    	// Création du plateau
        Board board = new Board();

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
        Set<Square> mouvementsRoiB = new HashSet<>(board.DeplacementsPossible(new Square(2,3)));
        
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
        Set<Square> mouvementsTourB = new HashSet<>(board.DeplacementsPossible(new Square(6,6)));
        
        // Comparaison des deux listes
        assertEquals(mouvementsTourB, listTourB);
        
        
        // Liste des mouvements possibles pour le roi noir
        Set<Square> listRoiN = new HashSet<>();
        listRoiN.add(new Square(5, 7));
        listRoiN.add(new Square(3, 7));

        // Création de la liste mouvement via la méthode testée
        Set<Square> mouvementsRoiN = new HashSet<>(board.DeplacementsPossible(new Square(4,7)));
        
        // Comparaison des deux listes
        assertEquals(mouvementsRoiN, listRoiN);
        
        // Blanc : La tour bouge
        board.jouerUnCoup(new Move(new Square(6, 6), new Square(3, 6)));
        
        // Noir : Le roi bouge
        board.jouerUnCoup(new Move(new Square(4, 7), new Square(5, 7)));
        
        // Blanc : La tour bouge
        board.jouerUnCoup(new Move(new Square(3, 6), new Square(3, 7)));
        
        // Noir : Le roi bouge
        board.jouerUnCoup(new Move(new Square(5, 7), new Square(4, 6)));
        
        // Blanc : La roi bouge
        board.jouerUnCoup(new Move(new Square(2, 3), new Square(3, 3)));
        
        // Noir : Le roi prend la tour
        board.jouerUnCoup(new Move(new Square(4, 6), new Square(3, 7)));
        
        // Vérification qu'il ne reste que deux pièces dans le plateau 
        assertEquals(board.getPlato().size(), 2);
        
        // Vérification du compteur de coups et demi-coups
        assertEquals(board.rules.getNbCoup(),3);
        assertEquals(board.rules.getNbDemieCoup(),6);
    }
    
    @Test
    // Echec et mat
    public void testPlatoEchecEtMat() {
    	// Création du plateau
        Board board = new Board();
        
        board.jouerUnCoup(new Move(new Square(2,3), new Square(3,4)));
        board.jouerUnCoup(new Move(new Square(4,7), new Square(3,7)));
        
        board.jouerUnCoup(new Move(new Square(3,4), new Square(3,5)));
        board.jouerUnCoup(new Move(new Square(3,7), new Square(2,7)));
        
        board.jouerUnCoup(new Move(new Square(3,5), new Square(2,5)));
        board.jouerUnCoup(new Move(new Square(2,7), new Square(1,7)));
        
        board.jouerUnCoup(new Move(new Square(2,5), new Square(1,5)));
        board.jouerUnCoup(new Move(new Square(1,7), new Square(0,7)));
        
        board.jouerUnCoup(new Move(new Square(6,6), new Square(6,7)));
        
        assertEquals(board.DeplacementsPossible(new Square(0,7)), new HashSet<>());
        
        assertEquals(board.rules.getPartie(),EtatPartie.FIN);
        
        // Vérification du compteur de coups et demi-coups
        assertEquals(board.rules.getNbCoup(),4);
        assertEquals(board.rules.getNbDemieCoup(),9);
    }
}

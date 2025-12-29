package Pieces;

import Appli.Square;
import Modele.Couleur;
import java.util.ArrayList;
import java.util.List;
import static Appli.Plato.TAILLE;

public class Rook extends Piece {

    public Rook(Couleur couleur) {
        this.couleur = couleur;
        if (getCouleur() == Couleur.WHITE) {
            lettre = 'R';
        }
        else {
            lettre = 'r';
        }
    }
    
    @Override
    public List<List<Square>> mouvement(Square square) {
    	List<List<Square>> trajectoires = new ArrayList<>();
        
        // Nord
        List<Square> nord = new ArrayList<>();
        for (int i = 1; square.y() + i < TAILLE; i++) {
            nord.add(new Square(square.x(), square.y() + i));
        }
        trajectoires.add(nord);
        
        // Sud
        List<Square> sud = new ArrayList<>();
        for (int i = 1; square.y() - i >= 0; i++) {
            sud.add(new Square(square.x(), square.y() - i));
        }
        trajectoires.add(sud);
        
        // Est
        List<Square> est = new ArrayList<>();
        for (int i = 1; square.x() + i < TAILLE; i++) {
            est.add(new Square(square.x() + i, square.y()));
        }
        trajectoires.add(est);
        
        // Ouest
        List<Square> ouest = new ArrayList<>();
        for (int i = 1; square.x() - i >=0; i++) {
            ouest.add(new Square(square.x() - i, square.y()));
        }
        trajectoires.add(ouest);
        
        return trajectoires;
    }
}

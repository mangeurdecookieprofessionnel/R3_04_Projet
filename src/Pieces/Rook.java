package Pieces;

import Appli.IPiece;
import Appli.Square;
import Modele.Couleur;

import java.util.ArrayList;
import java.util.List;

import static Appli.Plato.TAILLE;

public class Rook implements IPiece {
    private Couleur couleur;
    private char lettre;

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
    public Couleur getCouleur() {
        return couleur;
    }
    
    @Override
    public char getLettre() {
        return lettre;
    }

    @Override
    public List<Square> mouvement(Square square, List<Square> occupee) {
        List<Square> squares = new ArrayList<>();
        
        // Tests : Nord
        for (int i = 1; square.y() + i < TAILLE; i++) {
            Square s = new Square(square.x(), square.y() + i);
            if (occupee.contains(s)) {
                break;
            }
            squares.add(s);
        }       
        // Tests : Sud
        for (int i = 1; square.y() - i >= 0; i++) {
            Square s = new Square(square.x(), square.y() - i);
            if (occupee.contains(s)) {
                break;
            }
            squares.add(s);
        }        
        // Tests : Est
        for (int i = 1; square.x() + i < TAILLE; i++) {
            Square s = new Square(square.x() + i, square.y());
            if (occupee.contains(s)) {
                break;
            }
            squares.add(s);
        }
        // Tests : Ouest
        for (int i = 1; square.x() - i >= 0; i++) {
            Square s = new Square(square.x() - i, square.y());
            if (occupee.contains(s)) {
                break;
            }
            squares.add(s);
        }

        return squares;
    }
}

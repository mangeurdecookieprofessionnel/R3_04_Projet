package Pieces;

import Appli.IPiece;
import Appli.Square;
import Modele.Couleur;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static Appli.Plato.TAILLE;

public class King implements IPiece {
    private Couleur couleur;
    private char lettre;

    public King(Couleur couleur) {
        this.couleur = couleur;
        if (getCouleur() == Couleur.WHITE) {
            this.lettre = 'K';
        }
        else {
            this.lettre = 'k';
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
    public List<Square> mouvement(Square square, List<Square> ocuppee) {
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(square.x()-1, square.y()+1));
        squares.add(new Square(square.x(),   square.y()+1));
        squares.add(new Square(square.x()+1, square.y()+1));
        squares.add(new Square(square.x()+1, square.y()));
        squares.add(new Square(square.x()+1, square.y()-1));
        squares.add(new Square(square.x(),   square.y()-1));
        squares.add(new Square(square.x()-1, square.y()-1));
        squares.add(new Square(square.x()-1, square.y()));

        Iterator<Square> iterator = squares.iterator();
        while (iterator.hasNext()) {
            Square s = iterator.next();
            if (s.x() < 0 || s.x() >= TAILLE || s.y() < 0 || s.y() >= TAILLE 
            		|| ocuppee.contains(s)) {
                iterator.remove();
            }
        }
        
        return squares;
    }
}

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
            lettre = 'K';
        }
        else {
            lettre = 'k';
        }
    }

    @Override
    public Couleur getCouleur() {
        return couleur;
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


    @Override
    public List<Square> mouvement1(Square square) {
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(square.x()-1, square.y()+1)); // Haut-Gauche
        squares.add(new Square(square.x(), square.y()+1));   // Haut
        squares.add(new Square(square.x()+1, square.y()+1)); // Haut-Droite
        squares.add(new Square(square.x()+1, square.y()));   // Droite
        squares.add(new Square(square.x()+1, square.y()-1)); // Bas-Droite
        squares.add(new Square(square.x(), square.y()-1));   // Bas
        squares.add(new Square(square.x()-1, square.y()-1)); // Bas-Gauche
        squares.add(new Square(square.x()-1, square.y()));   // Gauche

        Iterator<Square> iterator = squares.iterator();

        while(iterator.hasNext()) {
            Square s = iterator.next();
            if(s.x()<0 || s.x()>=TAILLE ||  s.y()<0 || s.y()>=TAILLE) {
                iterator.remove();
            }
        }


        return squares;
    }

    @Override
    public char getLettre() {
        return lettre;
    }


}

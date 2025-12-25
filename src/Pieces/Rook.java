package Pieces;

import Appli.IPiece;
import Appli.Move;
import Appli.Square;

import java.util.ArrayList;
import java.util.List;

import static Appli.Plato.TAILLE;

public class Rook implements IPiece {
    private Couleur couleur;

    public Rook(Couleur couleur) {
        this.couleur = couleur;
    }

    @Override
    public Couleur getCouleur() {
        return couleur;
    }

    @Override
    public List<Square> mouvement(Square square, List<Square> occupee) {
        List<Square> squares = new ArrayList<>();

        for (int i = 1; square.y() + i < TAILLE; i++) {
            Square s = new Square(square.x(), square.y() + i);
            if (occupee.contains(s)) {
                break;
            }
            squares.add(s);
        }

        for (int i = 1; square.y() - i >= 0; i++) {
            Square s = new Square(square.x(), square.y() - i);
            if (occupee.contains(s)) {
                break;
            }
            squares.add(s);
        }

        for (int i = 1; square.x() + i < TAILLE; i++) {
            Square s = new Square(square.x() + i, square.y());
            if (occupee.contains(s)) {
                break;
            }
            squares.add(s);
        }

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

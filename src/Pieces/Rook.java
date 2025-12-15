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
    public List<Square> mouvement(Square square) {
        List<Square> squares = new ArrayList<Square>();
        for(int i=1; i<TAILLE; i++){
            if(square.y()+i<TAILLE)
                squares.add(new Square(square.x(), square.y()+i));//haut
            if(square.y()-i>=0)
                squares.add(new Square(square.x(), square.y()-i));//bas
            if(square.x()+i<TAILLE)
                squares.add(new Square(square.x()+i, square.y()));//droite
            if(square.x()-i>=0)
                squares.add(new Square(square.x()-i, square.y()));//gauche
        }
        return squares;
    }

    @Override
    public String afficherMouvementPiece(Square square) {
        String mouvements="";
        int count=1;
        List<Square> squares = mouvement(square);
        for (Square square1 : squares) {
            mouvements+=(count++)+")   X : "+square1.x() + " " +"       Y :"+ square1.y()+"\n";
        }
        return mouvements;
    }

    @Override
    public Boolean prise(Square square) {
        return true;
    }
}

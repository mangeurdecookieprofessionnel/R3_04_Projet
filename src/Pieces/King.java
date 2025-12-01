package Pieces;

import Appli.IPiece;
import Appli.Move;
import Appli.Square;

import java.util.ArrayList;
import java.util.List;

public class King implements IPiece {

    private String couleur;

    public King(String couleur) {
        this.couleur = couleur;
    }

    @Override
    public String getCouleur() {
        return couleur;
    }

    @Override
    public List<Square> mouvement(Square square) {
        List<Square> squares = new ArrayList<>();
        squares.add(new Square(square.x()-1, square.y()+1)); // Haut-Gauche
        squares.add(new Square(square.x(), square.y()+1));   // Haut
        squares.add(new Square(square.x()+1, square.y()+1)); // Haut-Droite
        squares.add(new Square(square.x()+1, square.y()));   // Droite
        squares.add(new Square(square.x()+1, square.y()-1)); // Bas-Droite
        squares.add(new Square(square.x(), square.y()-1));   // Bas
        squares.add(new Square(square.x()-1, square.y()-1)); // Bas-Gauche
        squares.add(new Square(square.x()-1, square.y()));   // Gauche

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


}

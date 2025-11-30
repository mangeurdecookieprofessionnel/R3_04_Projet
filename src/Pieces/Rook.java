package Pieces;

import Appli.IPiece;
import Appli.Move;
import Appli.Square;

import java.util.ArrayList;
import java.util.List;

public class Rook implements IPiece {
    private String couleur;

    public Rook(String couleur) {
        this.couleur = couleur;
    }

    @Override
    public String getCouleur() {
        return couleur;
    }

    @Override
    public List<Square> mouvement(Square square) {
        List<Square> squares = new ArrayList<Square>();
        for(int i=1; i<8; i++){
            squares.add(new Square(square.getX(), square.getY()+i));
        }
        for(int i=1; i<8; i++){
            squares.add(new Square(square.getX()+i, square.getY()));
        }
        for(int i=1; i<8; i++){
            squares.add(new Square(square.getX(), square.getY()-i));
        }
        for(int i=1; i<8; i++){
            squares.add(new Square(square.getX()-i, square.getY()));
        }
        return squares;
    }
}

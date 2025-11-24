package Appli;

import java.util.ArrayList;
import java.util.List;

public class Plato {
    public Square[][]tab;//Tableau de IPiece
    private final int taille=8;

    //Constructeur pour créer un plateau
    public Plato(){
        tab = new Square[taille][taille];
        for (int i=0; i<taille; i++){
            for(int j=0; j<taille; j++){
                tab[i][j] = new Square(i, j);
            }
        }
    }

    public Square getSquare(int i, int j){
        return tab[i][j];
    }

    public List<Square> DeplacementsPossible(Square[][] tab) {
        List<Square> moves = new ArrayList<>();

        return moves;
    }
}

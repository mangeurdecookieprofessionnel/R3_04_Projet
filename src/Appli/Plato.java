package Appli;

import Pieces.King;
import Pieces.Rook;

import java.util.ArrayList;
import java.util.List;

public class Plato {
    public Square[][]tab;
    private final int TAILLE =8;

    //Constructeur pour créer un plateau
    public Plato(){
        tab = new Square[TAILLE][TAILLE];
        for (int i = 0; i< TAILLE; i++){
            for(int j = 0; j< TAILLE; j++){
                tab[i][j] = new Square(i, j);
            }
        }
    }

    public List<Square> DeplacementsPossible(IPiece piece, Square position){
        List<Square> liste = new ArrayList<Square>();
        for ( Square square : piece.mouvement(position) ){
            if (square.getX()< TAILLE && square.getX()>=0 && square.getY()< TAILLE && square.getY()>=0){
                liste.add(square);
            }
        }
        return liste;
    }

    public String afficherMouvement(IPiece piece, Square square){
        String mouvement = "";

        DeplacementsPossible(piece, square);

        int a=1;
        for (Square square1 : DeplacementsPossible(piece, square)){
            mouvement+=a+")   X : "+square1.getX() + " " +"       Y :"+ square1.getY()+"\n";
            a++;
        }

        return mouvement;
    }
}

package Appli;


import Pieces.King;
import Pieces.Rook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plato {
    private Map<Square, IPiece> plato;
    public Square[][]tab;
    public static final int TAILLE =8;

    public Plato(){
        plato = new HashMap<Square, IPiece>();
        plato.put(new Square(0, 0), new Rook("noir"));
        plato.put(new Square(0, 0), new Rook("blanc"));
        plato.put(new Square(0, 0), new King("blanc"));


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
            if (square.x()< TAILLE && square.x()>=0 && square.y()< TAILLE && square.y()>=0){
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
            mouvement+=a+")   X : "+square1.x() + " " +"       Y :"+ square1.y()+"\n";
            a++;
        }

        return mouvement;
    }
}

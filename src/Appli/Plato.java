package Appli;


import Pieces.Couleur;
import Pieces.King;
import Pieces.Rook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plato {
    private Map<Square, IPiece> plato;
    public static final int TAILLE =8;

    public Plato(){
        plato = new HashMap<Square, IPiece>();
        plato.put(new Square(4, 7), new King(Couleur.BLACK));
        plato.put(new Square(0, 0), new Rook(Couleur.WHITE));
        plato.put(new Square(4, 0), new King(Couleur.WHITE));
    }

    public List<Square> DeplacementsPossible(Square position){

        List<Square> liste = new ArrayList<>();
        for ( Square square : plato.get(position).mouvement(position) ){
            if (square.x()< TAILLE && square.x()>=0 && square.y()< TAILLE && square.y()>=0){
                liste.add(square);
            }
        }
        return liste;
    }

    public String afficherMouvement(Square square){
        StringBuilder mouvement = new StringBuilder();

        DeplacementsPossible(square);

        int a=1;
        for (Square square1 : DeplacementsPossible(square)){
            mouvement.append(a);
            mouvement.append(") X : ");
            mouvement.append(square1.x());
            mouvement.append(" - Y : ");
            mouvement.append(square1.y());
            mouvement.append("\n");
            a++;
        }

        return mouvement.toString();
    }
}

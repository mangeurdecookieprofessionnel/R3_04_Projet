package Appli;


import Modele.Couleur;
import Pieces.King;
import Pieces.Rook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plato {
    private Map<Square, IPiece> plato;
    public static final int TAILLE =8;
    Regle regle;
    private String prise;

    public Plato(){
        plato = new HashMap<Square, IPiece>();
        plato.put(new Square(0, 7), new King(Couleur.BLACK));
        plato.put(new Square(0, 0), new Rook(Couleur.WHITE));
        plato.put(new Square(4, 0), new King(Couleur.WHITE));
        regle = new Regle();
        prise = "-";
    }

    public String afficherPlato() {
        StringBuilder fen = new StringBuilder();

        for (int y = TAILLE - 1; y >= 0; y--) {
            int vide = 0;

            for (int x = 0; x < TAILLE; x++) {
                Square s = new Square(x, y);

                if (!plato.containsKey(s)) {
                    vide++;
                } else {
                    if (vide > 0) {
                        fen.append(vide);
                        vide = 0;
                    }
                    fen.append(plato.get(s).getLettre());
                }
            }

            if (vide > 0) {
                fen.append(vide);
            }

            if (y > 0) {
                fen.append('/');
            }
        }

        fen.append(regle.getTour() == Couleur.WHITE ? " w " : " b ");
        fen.append("- "+prise+" "+regle.getNbDemieCoup()+" "+regle.getNbCoup());

        return fen.toString();
    }





    public List<Square> DeplacementsPossible(Square position){
        List<Square> list = new ArrayList<>(plato.get(position).mouvement(position, squaresOccupee()));
        list.addAll(manger(position));
        return list;
    }

    public List<Square> squaresOccupee() {
        List<Square> squares = new ArrayList<>();

        for (Square square : plato.keySet()) {
            squares.add(square);
        }

        return squares;
    }

    public List<Square> manger(Square position) {
        List<Square> squares = new ArrayList<>();

        for (Square square : plato.get(position).mouvement1(position)) {
            if (plato.containsKey(square) && plato.get(square).getCouleur() != plato.get(position).getCouleur()) {
                squares.add(square);
            }
        }
        return squares;
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

    public void jouerUnCoup(Square positionInitiale, Square positionFinale){
        if (plato.get(positionInitiale).getCouleur() == regle.getTour()){
            if (DeplacementsPossible(positionInitiale).contains(positionFinale)){
                if (plato.get(positionFinale)!=null){
                    plato.put(positionFinale, plato.get(positionInitiale));
                    plato.remove(positionInitiale);
                    prise = "aa";
                    regle.resetNbDemiecoup();
                    regle.incNbcoup();

                }
                else {
                    plato.put(positionFinale, plato.get(positionInitiale));
                    plato.remove(positionInitiale);
                    prise = "-";
                    regle.incNbcoup();
                    regle.incNbdemieCoup();
                }
            }
        }
        regle.changeTour();
    }

    public boolean partieFini(){
        return regle.statutPartie();
    }
}

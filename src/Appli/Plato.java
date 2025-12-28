package Appli;

import Modele.Couleur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Factory.PieceFactory;

public class Plato {
    private Map<Square, IPiece> plato;
    public static final int TAILLE =8;
    Regle regle;
    private List<Move> moves;

    public Plato(){
        plato = new HashMap<Square, IPiece>();
        plato = PieceFactory.Begin();
        regle = new Regle();
    }

    /*
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
    */
    
    /**
     * Retourne une liste de tous les déplacements possibles (y compris les pièces mangeables)
     * @param position La position de la pièce qu'on souhaite déplacer
     * @return Liste de coordonnées (square)
     */
    public List<Square> DeplacementsPossible(Square position){
        List<Square> list = new ArrayList<>(plato.get(position).mouvement(position, this.squaresOccupee()));
        list.addAll(manger(position));
        return list;
    }

    /**
     * Retourne une liste de toutes les coordonnées occupées par une pièce quelconque (blanche et noire)
     * @return Liste de coordonnées (square)
     */
    public List<Square> squaresOccupee() {
        List<Square> squares = new ArrayList<>();

        for (Square square : plato.keySet()) {
            squares.add(square);
        }

        return squares;
    }

    /**
     * Retourne une liste des pièces qu'un pion peut "manger"/"tuer"
     * @param position La position de la pièce qu'on souhaite déplacer
     * @return Liste de coordonnées (square)
     */
    public List<Square> manger(Square position) {
        List<Square> squares = new ArrayList<>();

        for (Square square : plato.get(position).mouvement(position, squaresOccupee())) {
            if (plato.containsKey(square) && plato.get(square).getCouleur() != plato.get(position).getCouleur()) {
                squares.add(square);
            }
        }
        
        return squares;
    }

    /*
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
    */
    
    /**
     * Permet de jouer un coup
     * @param move Le déplacement voulu
     */
    public void jouerUnCoup(Move move){
    	if (plato.containsKey(move.getDepart())) {
    		if (plato.get(move.getDepart()).getCouleur() == regle.getTour()){
                if (DeplacementsPossible(move.getDepart()).contains(move.getArrivee())){
                    if (plato.get(move.getArrivee())!= null){
                    	plato.remove(move.getArrivee());
                        plato.put(move.getArrivee(), plato.get(move.getDepart()));
                        plato.remove(move.getDepart());
                        regle.resetNbDemiecoup();
                        regle.incNbcoup();
                        moves.add(move);
                    }
                    else {
                        plato.put(move.getArrivee(), plato.get(move.getDepart()));
                        plato.remove(move.getDepart());
                        regle.incNbcoup();
                        regle.incNbdemieCoup();
                        moves.add(move);
                    }
                }
            }
            regle.changeTour();
    	}
    }
}

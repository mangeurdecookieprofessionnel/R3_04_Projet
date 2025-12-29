package Appli;

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
        moves = new ArrayList<>();
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
     * Retourne une liste de tous les déplacements possibles (y compris les pièces mangeables)
     * @param position La position de la pièce qu'on souhaite déplacer
     * @return Liste de coordonnées (square)
     */
    public List<Square> DeplacementsPossible(Square position) {
        IPiece piece = plato.get(position);
        List<Square> coupsPossibles = new ArrayList<>();

        List<List<Square>> trajectoires = piece.mouvement(position);

        for (List<Square> chemin : trajectoires) {
            for (Square caseCible : chemin) {
                if (plato.containsKey(caseCible)) {
                    if (plato.get(caseCible).getCouleur() != piece.getCouleur()) {
                        coupsPossibles.add(caseCible);
                    }                 
                    break; 
                } 
                else {
                    coupsPossibles.add(caseCible);
                }
            }
        }
        return coupsPossibles;
    }
    
    /**
     * Permet de jouer un coup
     * @param move Le déplacement voulu
     */
    public void jouerUnCoup(Move move){
    	if (plato.containsKey(move.depart())) {
    		if (plato.get(move.depart()).getCouleur() == regle.getTour()){
                if (DeplacementsPossible(move.depart()).contains(move.arrivee())){
                    if (plato.get(move.arrivee()) != null){
                    	plato.remove(move.arrivee());
                        plato.put(move.arrivee(), plato.get(move.depart()));
                        plato.remove(move.depart());
                        regle.resetNbDemiecoup();
                        regle.incNbcoup();
                        moves.add(move);
                    }
                    else {
                        plato.put(move.arrivee(), plato.get(move.depart()));
                        plato.remove(move.depart());
                        regle.incNbcoup();
                        regle.incNbdemieCoup();
                        moves.add(move);
                    }
                }
            }
            regle.changeTour();
    	}
    }
    
    public Map<Square, IPiece> getPlato() {
    	return this.plato;
    }
    
    
}

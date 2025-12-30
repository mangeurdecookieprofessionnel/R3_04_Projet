package Appli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Factory.*;
import Modele.*;

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
    
    /**
     * Retourne une liste de tous les déplacements possibles (y compris les pièces mangeables)
     * @param position La position de la pièce qu'on souhaite déplacer
     * @return Collection de coordonnées (square)
     */
    private Set<Square> DeplacementsPhysiques(Square position) {
        IPiece piece = plato.get(position);
        Set<Square> coupsPossibles = new HashSet<>();

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
     * Retourne la liste finale des coups possibles et légaux
     * @param position La position de la pièce qu'on souhaite déplacer
     * @return Collection de coordonnées (square)
     */
    public Set<Square> DeplacementsPossible(Square position) {
        IPiece pieceBougee = plato.get(position);
        Couleur maCouleur = pieceBougee.getCouleur();
        
        Set<Square> coupsBruts = DeplacementsPhysiques(position);
        Set<Square> coupsLegaux = new HashSet<>();

        for (Square arrivee : coupsBruts) {
            
            IPiece pieceMangee = plato.get(arrivee);
            
            plato.remove(position);
            plato.put(arrivee, pieceBougee);
            
            Square posMonRoi = getPositionRoi(maCouleur);
            if (!estCaseMenacee(posMonRoi, maCouleur)) {
                coupsLegaux.add(arrivee);
            }

            plato.put(position, pieceBougee);
            if (pieceMangee != null) {
                plato.put(arrivee, pieceMangee);
            } else {
                plato.remove(arrivee);
            }
        }
        
        return coupsLegaux;
    }
    
    /**
     * Permet de jouer un coup
     * @param move Le déplacement voulu
     */
    public void jouerUnCoup(Move move){
    	if (plato.containsKey(move.depart())) {
    		if (plato.get(move.depart()).getCouleur() == regle.getTour()){
                if (DeplacementsPhysiques(move.depart()).contains(move.arrivee())){
                    if (plato.get(move.arrivee()) != null){
                    	plato.remove(move.arrivee());
                        plato.put(move.arrivee(), plato.get(move.depart()));
                        plato.remove(move.depart());
                        moves.add(move);
                        regle.resetPrise();
                    }
                    else {
                        plato.put(move.arrivee(), plato.get(move.depart()));
                        plato.remove(move.depart());
                        moves.add(move);
                        regle.incPrise();
                    }
                }
            }
    		regle.incNbdemieCoup();
    		if (regle.getNbDemieCoup() % 2 == 0) {
    			regle.incNbcoup();
    		}
            regle.changeTour();
            verificationEtatPartie(regle.getTour());
    	}
    }
    
    /**
     * Retourne le plateau (uniquement pour les tests)
     * @return Map<Square, IPiece> Le plateau
     */
    protected Map<Square, IPiece> getPlato() {
    	return this.plato;
    }
    
    /**
     * Retourne la position du roi de la couleur voulue
     * @param couleur La couleur du roi voulu
     * @return Square Les coordonnées du roi
     */
	private Square getPositionRoi(Couleur couleur) {
    	for (Map.Entry<Square, IPiece> entry : plato.entrySet()) {
            if (entry.getValue().getLettre() == (couleur == Couleur.WHITE ? 'K' : 'k')) {
                return entry.getKey();
            }
        }
    	throw new RuntimeException("Roi introuvable ! (Impossible aux échecs)");
    }
    
    /**
     * Vérifie l'état de la partie
     * @param couleurJoueur
     * @return EtatPartie NULLE FIN ou JEU
     */
    public EtatPartie verificationEtatPartie(Couleur couleurJoueur) {
    	
    	if (regle.getPrise() == 50) {
    		regle.setPartie(EtatPartie.NULLE);
    		System.out.println("50 coups sans prise");
    		return EtatPartie.NULLE;
    	}
    	
    	if (estManqueDeMateriel() == true) {
    		regle.setPartie(EtatPartie.NULLE);
    		System.out.println("Roi vs Roi");
    		return EtatPartie.NULLE;
    	}
    	
    	boolean aDesCoupsLegaux = false;
    	for (Square sq : plato.keySet()) {
    		if (plato.get(sq).getCouleur() == couleurJoueur) {
    			if (!DeplacementsPossible(sq).isEmpty()) {
    				aDesCoupsLegaux = true;
    				break;
    			}
    		}
    	}    	
    	if (aDesCoupsLegaux) {
    		regle.setPartie(EtatPartie.JEU);
    		return EtatPartie.JEU;
    	}
    	else {
	    	Square posRoi = getPositionRoi(couleurJoueur);
	    	if (estCaseMenacee(posRoi, couleurJoueur)) {
	    		regle.setPartie(EtatPartie.FIN);
	    		System.out.println("Echec et mat");
	    		return EtatPartie.FIN;
	    	}
	    	else {
	    		regle.setPartie(EtatPartie.NULLE);
	    		System.out.println("PAT");
	    		return EtatPartie.NULLE;
	    	}
    	}
    }
    
    /**
     * Vérifie si une case est menacée par une pièce adverse.
     * @param target La case à tester (souvent celle du Roi)
     * @param maCouleur La couleur du joueur qui s'inquiète pour sa sécurité
     * @return Un boolean : true si la case est menacée, false sinon
     */
    public boolean estCaseMenacee(Square target, Couleur maCouleur) {
        for (Map.Entry<Square, IPiece> entry : plato.entrySet()) {
            Square posEnnemi = entry.getKey();
            IPiece piece = entry.getValue();

            if (piece.getCouleur() != maCouleur) {
                Set<Square> coupsEnnemis = DeplacementsPhysiques(posEnnemi);
                if (coupsEnnemis.contains(target)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Vérifie si le matériel est insuffisant pour mater.
     * @return true si la partie est Nulle.
     */
    public boolean estManqueDeMateriel() {
        for (IPiece p : plato.values()) {
            char l = Character.toUpperCase(p.getLettre());
            if (l == 'P' || l == 'R' || l == 'Q') {
                return false; 
            }
        }
        if (plato.size() == 2) {
            return true;
        }
        return false;
    }
    
}

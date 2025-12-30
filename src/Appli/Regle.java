package Appli;

import Modele.*;

public class Regle {
    private int nbCoup;
    private int nbDemieCoup;
    private Couleur tour;
    private int prise;
    private EtatPartie partie;

    public Regle() {
        this.nbCoup = 0;
        this.nbDemieCoup = 0;
        this.tour = Couleur.WHITE;
        this.prise = 0;
        this.setPartie(EtatPartie.JEU);
    }

    public int getNbCoup() {
        return nbCoup;
    }
    
    public int getNbDemieCoup() {
        return nbDemieCoup;
    }

    public Couleur getTour() {
        return tour;
    }
    
    public int getPrise() {
    	return prise;
    }

    public void changeTour(){
        if (tour == Couleur.WHITE) {
            tour = Couleur.BLACK;
        }
        else {
            tour = Couleur.WHITE;
        }
    }

    public void incNbcoup(){
        this.nbCoup++;
    }

    public void incNbdemieCoup(){
        this.nbDemieCoup++;
    }
    
    public void incPrise(){
        this.prise++;
    }
    
    public void resetPrise() {
    	this.prise = 0;
    }

	public EtatPartie getPartie() {
		return partie;
	}

	public void setPartie(EtatPartie partie) {
		this.partie = partie;
	}
}

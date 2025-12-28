package Appli;

import Modele.Couleur;

public class Regle {
    private int nbCoup;
    private int nbDemieCoup;
    private Couleur tour;

    public Regle() {
        this.nbCoup = 0;
        this.nbDemieCoup = 0;
        this.tour = Couleur.WHITE;
    }

    public int getNbCoup() {
        return (nbCoup / 2) + 1;
    }

    public Couleur getTour() {
        return tour;
    }

    public void changeTour(){
        if(tour == Couleur.WHITE){
            tour = Couleur.BLACK;
        }
        else{
            tour = Couleur.WHITE;
        }
    }

    public void incNbcoup(){
        this.nbCoup++;
    }

    public void incNbdemieCoup(){
        this.nbDemieCoup++;
    }

    public void resetNbDemiecoup(){
        this.nbDemieCoup = 0;
    }

    public int getNbDemieCoup() {
        return nbDemieCoup;
    }
}

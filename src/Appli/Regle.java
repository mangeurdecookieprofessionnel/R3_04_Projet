package Appli;

import Modele.Couleur;

public class Regle {
    private int nbCoup;
    private int nbDemieCoup;
    private boolean fini;
    private Couleur tour;

    public Regle() {
        this.nbCoup = 0;
        this.nbDemieCoup = 0;
        this.fini = false;
        this.tour = Couleur.WHITE;
    }

    public int getNbCoup() {
        return (nbCoup / 2) + 1;
    }

    public boolean statutPartie(){
        return fini;
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

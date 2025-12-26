package Appli;

import Modele.Couleur;

public class Regle {
    private int nbCoup;
    private boolean fini;
    private Couleur tour;

    public Regle() {
        this.nbCoup = 0;
        this.fini = false;
        this.tour = Couleur.WHITE;
    }

    public int getNbCoup() {
        return nbCoup;
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
}

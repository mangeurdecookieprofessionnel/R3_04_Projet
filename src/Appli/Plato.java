package Appli;

public class Plato {
    public Ipiece [][]tab;//Tableau de IPiece
    private final int ligne=8;
    private final int colonne=8;

    //Constructeur pour créer un plateau
    public Plato(){
        tab = new Ipiece[ligne][colonne];
    }
}

package Appli;

public class Plato {
    public Ipiece [][]tab;
    private final int ligne=8;
    private final int colonne=8;

    public Plato() {
        tab = new Ipiece[ligne][colonne];
    }
}

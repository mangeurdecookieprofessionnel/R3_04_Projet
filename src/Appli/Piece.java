package Appli;

public abstract class Piece implements Ipiece {
    private String type;
    private String couleur;
    private int x;
    private int y;

    public Piece(String type, String couleur, int x, int y) {
        this.type = type;
        this.couleur = couleur;
        this.x = x;
        this.y = y;
    }
    public void getType(){
        this.type = type;
    }

    public void getPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void getCouleur(){
        this.couleur = couleur;
    }




}

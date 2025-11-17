package Appli;

public class Move {

    private int startX;//Ligne de début
    private int startY;//Colonne de début
    private int endX;//Ligne de fin
    private int endY;//Colonne de fin

    //Le constructeur prend l'emplacement de départ et l'emplacement de fin pour se déplacer
    public Move(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public int getStartX() { return startX; }
    public int getStartY() { return startY; }
    public int getEndX()   { return endX; }
    public int getEndY()   { return endY; }
}

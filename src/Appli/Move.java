package Appli;

public class Move {

    private Square depart;
    private Square arrivee;

    //Le constructeur prend l'emplacement de départ et l'emplacement de fin pour se déplacer
    public Move(Square depart, Square arrivee) {
        this.depart = depart;
        this.arrivee = arrivee;
    }
}

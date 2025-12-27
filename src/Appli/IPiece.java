package Appli;

import Modele.Couleur;

import java.util.List;

public interface IPiece {

    public Couleur getCouleur();

    public List<Square> mouvement(Square square, List<Square> ocuppee);

    public List<Square> mouvement1(Square square);

    public char getLettre();
}

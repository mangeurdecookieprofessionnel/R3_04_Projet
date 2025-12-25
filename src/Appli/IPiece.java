package Appli;

import Pieces.Couleur;

import java.util.List;

public interface IPiece {

    public Couleur getCouleur();

    public List<Square> mouvement(Square square, List<Square> ocuppee);


}

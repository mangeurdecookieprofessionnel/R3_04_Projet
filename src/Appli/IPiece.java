package Appli;

import Modele.Couleur;

import java.util.List;

public interface IPiece {
	/**
	 * Retourne la couleur de la pièce
	 * @return Couleur Couleur de la pièce
	 */
    public Couleur getCouleur();
    
    /**
     * Retourne la lettre de la pièce
     * @return char Caractère représentant la pièce
     */
    public char getLettre();

    /**
     * Retourne la liste des mouvements possibles dans le plateau de la pièce
     * @param square Position de la pièce
     * @param ocuppee List des coordonnées occupées par une pièce quelconque
     * @return
     */
    public List<Square> mouvement(Square square, List<Square> ocuppee);
}

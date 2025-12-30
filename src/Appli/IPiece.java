package Appli;

import Modele.Couleur;

import java.util.List;
import java.util.Set;

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
     * Retourne une liste "aplatie" de l'ensemble des mouvements possibles
     * @param trajectoires Liste de listes des trajectoires
     * @return Set<Square> HashSet des mouvements possibles
     */
    public Set<Square> aplatir(List<List<Square>> trajectoires);
    
    /**
     * Retourne les trajectoires possibles (sans se soucier des obstacles)
     * Une trajectoire est une liste ordonnée de cases (ex: vers le Nord).
     * @param square Position de la pièce
     * @return List<List<Square>> Liste de listes de trajectoires possibles.
     */
    public List<List<Square>> mouvement(Square square);
}

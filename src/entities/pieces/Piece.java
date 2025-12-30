package entities.pieces;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entities.board.IPiece;
import entities.board.Square;
import entities.modeles.Couleur;

public abstract class Piece implements IPiece {
	protected Couleur couleur;
    protected char lettre;
    
    public Couleur getCouleur() {
        return couleur;
    }
    
    public char getLettre() {
        return lettre;
    }
    
    public Set<Square> aplatir(List<List<Square>> trajectoires) {
    	Set<Square> list = new HashSet<>();
    	for (List<Square> chemin : trajectoires) {
            list.addAll(chemin);
        }
		return list;
    }
    
    public abstract List<List<Square>> mouvement(Square square);
}

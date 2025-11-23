package Pieces;

import Appli.IPiece;
import Appli.Move;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece implements IPiece {
    private String couleur;
    private Position position;

    public Piece(String couleur, int x, int y) {
        this.couleur = couleur;
        this.position = new Position(x, y);
    }

    @Override
    public String getType(){
        return this.getClass().getSimpleName().toLowerCase();
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public String getCouleur() {
        return couleur;
    }

    @Override
    public List<Move> DeplacementsPossible() {
        List<Move> deplacements = new ArrayList<>();
        for (Move move: mouvement()){
            if (move.getEndX()<9 && move.getEndX()>0 && move.getEndY()<9 && move.getEndY()>0){
                deplacements.add(move);
            }
        }
        return deplacements;
    }

    public abstract List<Move> mouvement();

}

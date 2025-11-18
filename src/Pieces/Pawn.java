package Pieces;

import Appli.Move;

import java.util.List;

public class Pawn extends Piece {
    public Pawn(char c, int x, int y) {
        super("pion", c, x, y);
    }

    @Override
    public void getPosition() {

    }

    @Override
    public void getCouleur() {

    }

    @Override
    public List<Move> DeplacementsPossible() {
        return List.of();
    }
}

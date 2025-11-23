package Pieces;

import Appli.Move;

import java.util.ArrayList;
import java.util.List;

public class Cavalier extends Piece{

    public Cavalier(String couleur, int x, int y) {
        super(couleur, x, y);
    }

    @Override
    public List<Move> mouvement() {
        List<Move> moves = new ArrayList<>();
        moves.add(new Move (getPosition().x(), getPosition().y(), getPosition().x()+2, getPosition().y()+1));
        moves.add(new Move (getPosition().x(), getPosition().y(), getPosition().x()+2, getPosition().y()-1));
        moves.add(new Move (getPosition().x(), getPosition().y(), getPosition().x()-2, getPosition().y()+1));
        moves.add(new Move (getPosition().x(), getPosition().y(), getPosition().x()-2, getPosition().y()-1));
        moves.add(new Move (getPosition().x(), getPosition().y(), getPosition().x()+1, getPosition().y()+2));
        moves.add(new Move (getPosition().x(), getPosition().y(), getPosition().x()+1, getPosition().y()-2));
        moves.add(new Move (getPosition().x(), getPosition().y(), getPosition().x()-1, getPosition().y()+2));
        moves.add(new Move (getPosition().x(), getPosition().y(), getPosition().x()-1, getPosition().y()-2));

        return moves;
    }

    @Override
    public void manger() {

    }

    @Override
    public boolean peutManger() {
        return false;
    }
}

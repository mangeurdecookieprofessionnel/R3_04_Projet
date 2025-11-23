package Pieces;

import Appli.Move;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {//Le Pion
    public Pawn(String couleur, int x, int y) {
        super(couleur, x, y);
    }

    @Override
    public List<Move> mouvement() {//Liste de mouvements du pion
        List<Move> moves = new ArrayList<>();
        if(getPosition().y()==2){ //On verifie si le pion est en position de départ
            moves.add(new Move(getPosition().x(),2, getPosition().x(), 4));
        }
        moves.add(new Move(getPosition().x(), getPosition().y(), getPosition().x(), getPosition().y()+1));
        return moves;
    }



    @Override
    public  void manger(){

    }

    @Override
    public boolean peutManger() {
        return false;
    }

}

package Appli;

import java.util.List;

public interface IPiece {

    public record Position(int x, int y) {}

    public String getType();

    public Position getPosition();

    public String getCouleur();

    public List<Move> DeplacementsPossible();

    public List<Move> mouvement();

    public void manger();

    public boolean peutManger();

}

package Appli;

import java.util.List;

public interface IPiece {

    public String getCouleur();

    public List<Square> mouvement(Square square);


}

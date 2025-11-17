package Appli;

import java.util.List;

public interface Ipiece {

    public void getType();

    public void getPosition();

    public void getCouleur();

    public List<Move> DeplacementsPossible();
}

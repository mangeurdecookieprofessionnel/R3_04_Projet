package Appli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plato {
    private Map<Square, IPiece> plato;

    public static final int TAILLE=8;

    public Plato(){
        plato  = new HashMap<Square, IPiece>();
    }
}

import Appli.IPiece;
import Appli.Plato;
import Appli.Square;
import Pieces.King;

public class Main{
    public static void main(String[] args) {

        Plato p = new Plato();
        IPiece roi = new King("Blanc");
        Square square = new Square(5,0);

        p.DeplacementsPossible(roi, square);

        for (Square square1 : p.DeplacementsPossible(roi, square)){
            System.out.println("X : "+square1.getX() + " " +"       Y :"+ square1.getY());
        }
    }

}

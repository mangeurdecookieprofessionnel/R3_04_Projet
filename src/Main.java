import Appli.IPiece;
import Appli.Plato;
import Appli.Square;
import Pieces.King;
import Pieces.Rook;

public class Main{
    public static void main(String[] args) {

        Plato p = new Plato();
        IPiece roi = new King("Blanc");
        IPiece tour = new Rook("Blanc");
        Square squareKing = new Square(4,0);
        Square squareRook = new Square(0,0);

        p.DeplacementsPossible(squareKing);

        int a=1, b=1;
        for (Square square1 : p.DeplacementsPossible(squareKing)){
            System.out.println(a+")X : "+square1.x() + " / Y :"+ square1.y());
            a++;
        }
        System.out.println("\n");
        for (Square square1 : p.DeplacementsPossible(squareRook)){
            System.out.println(b+")X : "+square1.x() + " / Y :"+ square1.y());
            b++;
        }
    }

}

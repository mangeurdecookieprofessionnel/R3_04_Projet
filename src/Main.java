import Appli.Move;
import Pieces.Cavalier;
import Pieces.Pawn;
import Pieces.Piece;

public class Main{
    public static void main(String[] args) {


        Piece piece = new Pawn("blanc", 4, 8);
        for (Move move: piece.DeplacementsPossible()){
            System.out.println(move.getEndX()+" "+move.getEndY());
        }
    }

}

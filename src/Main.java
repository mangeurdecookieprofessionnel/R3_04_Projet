import Appli.IPiece;
import Appli.Plato;
import Appli.Square;
import Modele.Couleur;
import Pieces.King;
import Pieces.Rook;

public class Main{
    public static void main(String[] args) {

        Plato p = new Plato();
        System.out.println(p.afficherPlato());

        p.jouerUnCoup(new Square(0,0), new Square(0,3));
        System.out.println(p.afficherPlato());

        p.jouerUnCoup(new Square(0,7), new Square(1,7));
        System.out.println(p.afficherPlato());

        p.jouerUnCoup(new Square(0,3), new Square(0,5));
        System.out.println(p.afficherPlato());

        p.jouerUnCoup(new Square(1,7), new Square(2,7));
        System.out.println(p.afficherPlato());

        p.jouerUnCoup(new Square(0,5), new Square(0,7));
        System.out.println(p.afficherPlato());

        p.jouerUnCoup(new Square(2,7), new Square(2,6));
        System.out.println(p.afficherPlato());

        p.jouerUnCoup(new Square(4,0), new Square(4,1));
        System.out.println(p.afficherPlato());

        p.jouerUnCoup(new Square(2,6), new Square(2,7));
        System.out.println(p.afficherPlato());

        p.jouerUnCoup(new Square(0,7), new Square(2,7)); // capture "illégale"
        System.out.println(p.afficherPlato());
    }


}

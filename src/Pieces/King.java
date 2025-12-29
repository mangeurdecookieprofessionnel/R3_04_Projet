package Pieces;

import Appli.Square;
import Modele.Couleur;
import java.util.ArrayList;
import java.util.List;
import static Appli.Plato.TAILLE;

public class King extends Piece {

    public King(Couleur couleur) {
        this.couleur = couleur;
        if (getCouleur() == Couleur.WHITE) {
            this.lettre = 'K';
        }
        else {
            this.lettre = 'k';
        }
    }

    @Override
    public List<List<Square>> mouvement(Square square) {
        List<List<Square>> trajectoires = new ArrayList<>();
        
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int nx = square.x() + dx[i];
            int ny = square.y() + dy[i];

            if (nx >= 0 && nx < TAILLE && ny >= 0 && ny < TAILLE) {
                List<Square> path = new ArrayList<>();
                path.add(new Square(nx, ny));
                trajectoires.add(path);
            }
        }
        return trajectoires;
    }
}

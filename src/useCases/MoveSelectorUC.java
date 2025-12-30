package useCases;

import Appli.Move;
import Appli.Plato;

import java.util.List;

public class MoveSelectorUC {

    private final MoveGeneratorUC generator = new MoveGeneratorUC();
    private final MoveValidatorUC validator = new MoveValidatorUC();

    public Move selectBestMove(Plato plato) {

        List<Move> moves = generator.generateMoves(plato);

        for (Move m : moves) {
            if (validator.isLegal(plato, m) && plato.getRegle().getTour()== plato.getPlato().get(m.depart()).getCouleur()) {
                return m; // premier coup légal
            }
        }

        return null; // mat ou pat
    }
}

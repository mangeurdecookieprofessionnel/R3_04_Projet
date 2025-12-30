package Adapters;

import Appli.Move;
import Appli.Plato;
import Appli.Square;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UCIAdapter {

    private Plato plato;

    public static void main(String[] args) throws Exception {
        new UCIAdapter().run();
    }

    public void run() throws Exception {

        plato = new Plato(); // plateau initial

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = in.readLine()) != null) {

            line = line.trim();
            if (line.isEmpty()) continue;

            String[] tokens = line.split("\\s+");
            String command = tokens[0];

            switch (command) {

                case "uci":
                    System.out.println("id name MonMoteur");
                    System.out.println("id author Moi");
                    System.out.println("uciok");
                    break;

                case "isready":
                    System.out.println("readyok");
                    break;

                case "ucinewgame":
                    plato = new Plato();
                    System.out.println("[UCI] Nouvelle partie");
                    break;

                case "position":
                    handlePosition(tokens);
                    break;

                case "go":
                    handleGo();
                    break;

                case "quit":
                    return;
            }

            System.out.flush();
        }
    }



    private void handlePosition(String[] tokens) {

        int i = 1;

        // startpos
        if (tokens[i].equals("startpos")) {
            plato = new Plato();
            i++;
        }

        // fen
        else if (tokens[i].equals("fen")) {

            StringBuilder fen = new StringBuilder();
            i++;

            while (i < tokens.length && !tokens[i].equals("moves")) {
                fen.append(tokens[i]).append(" ");
                i++;
            }

            plato = new Plato();
            plato.loadFromFEN(fen.toString().trim());

        }

        // appliquer les coups
        if (i < tokens.length && tokens[i].equals("moves")) {
            i++;
            while (i < tokens.length) {
                appliquerCoupUci(tokens[i]);
                i++;
            }
        }
    }


    private void appliquerCoupUci(String token) {

        if (token.length() < 4) return;

        // ex: e2e4
        Square from = uciToSquare(token.substring(0, 2));
        Square to   = uciToSquare(token.substring(2, 4));

        plato.jouerUnCoup(new Move(from, to));

    }

    private Square uciToSquare(String s) {

        if (s == null || s.length() != 2) {
            throw new IllegalArgumentException("Square UCI invalide : " + s);
        }

        char file = s.charAt(0); // a → h
        char rank = s.charAt(1); // 1 → 8

        int x = file - 'a';
        int y = rank - '1';

        return new Square(x, y);
    }



    private void handleGo() {

        Move move = plato.bestMove();
        if (move==null) {
            System.out.println("bestmove 0000");
            return;
        }
        System.out.println("bestmove " + toUCI(move));
    }

    private String toUCI(Move move) {
        return squareToUCI(move.depart()) + squareToUCI(move.arrivee());
    }

    private String squareToUCI(Square square) {
        char file = (char) ('a' + square.x());
        char rank = (char) ('1' + square.y());
        return "" + file + rank;
    }
}

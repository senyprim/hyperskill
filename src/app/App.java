package app;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Game game;
        while (true) {
            game = null;
            System.out.print("Input command:");
            try {
                game = Game.createGame(scanner.nextLine());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            if (game == null) break;
            System.out.println(game.getCloneMap().getPrintString());
            while (game.getState() == 0) {
                try {
                    game.turn();
                    System.out.println(game.getCloneMap().getPrintString());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            }
            System.out.println(Game.STATES[game.getState()]);
        }
    }
}
package src.comp_620.pentago;

public class Main {
    public static void main(String[] args) {
        Pentago game = new Pentago();
        System.out.println("Generating the first 16 moves beginning with black marbles");
        game.generateFirstNMoves();
        game.printBoard();
        System.out.println("Now beginning Minimax with black as the max and white as min!");
        while(!game.checkWin()){

        }
    }
}
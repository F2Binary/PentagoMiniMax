package src.comp_620.pentago;

public class Main {
    public static void main(String[] args) {
        Pentago game = new Pentago();
        System.out.println("Generating the first 16 moves beginning with black marbles");
        game.generateFirstNMoves();
        game.printBoard();
        long startTime = System.nanoTime();
        System.out.println("Now beginning Minimax with black as the max and white as min!");
        game.miniMax();
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Finished running in " + totalTime + " nanoseconds");
    }
}
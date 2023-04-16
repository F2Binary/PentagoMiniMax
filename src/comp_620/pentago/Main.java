package src.comp_620.pentago;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt"));
        long start = System.currentTimeMillis();
        for(int i = 0; i< 100; i++){
            Pentago game = new Pentago();
            System.out.println("Generating the first 16 moves beginning with black marbles");
            game.generateFirstNMoves();
            PentagoUtils.printBoard(game.getBoard());
            long startTime = System.currentTimeMillis();
            game.miniMax();
            long endTime   = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println("Finished running in " + totalTime + " ms");
            writer.write(totalTime == 0 ? "<error>\n" :((Long)totalTime) + "\n");
        }
        writer.close();
        long end = System.currentTimeMillis();
        System.out.println("Execution complete!");
        System.out.println("Total Execution time is: " + (end - start) + " ms");
    }
}
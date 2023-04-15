package src.comp_620.pentago;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Long> listOfExecutionTimes = new ArrayList<>();
        BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt"));
        for(int i = 0; i< 1000; i++){
            Pentago game = new Pentago();
            System.out.println("Generating the first 16 moves beginning with black marbles");
            game.generateFirstNMoves();
            PentagoUtils.printBoard(game.getBoard());
            long startTime = System.currentTimeMillis();
            //System.out.println("Now beginning Minimax with black as the max and white as min!");
            game.miniMax();
            long endTime   = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            listOfExecutionTimes.add(totalTime);
            System.out.println("Finished running in " + totalTime + " milliseoncds");
            writer.write(totalTime == 0 ? "<error>\n" :((Long)totalTime) + "\n");
        }
        writer.close();
        listOfExecutionTimes.forEach(System.out::println);
        }
}
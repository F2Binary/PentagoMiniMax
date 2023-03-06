package src.comp_620.pentago;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomizedBoardCoordinate {
    Random random;

    RandomizedBoardCoordinate() {
        random = new Random();
    }

    public int[][] doRandom(int[][] board, String player, HashMap<ArrayList<Integer>, String> mapOfUsedMoves) {
        int x = random.nextInt(6);
        int y = random.nextInt(6);
        int finalX1 = x;
        int finalY1 = y;
        ArrayList<Integer> temp = new ArrayList<>() {
            {
                add(finalX1);
                add(finalY1);
            }
        };
        while (mapOfUsedMoves.containsKey(temp)) {
            temp.remove(0);
            temp.remove(0);
            x = random.nextInt(6);
            y = random.nextInt(6);
            temp.add(x);
            temp.add(y);
        }
        int finalX = x;
        int finalY = y;
        ArrayList<Integer> cord = new ArrayList<>() {
            {
                add(finalX);
                add(finalY);
            }
        };

        mapOfUsedMoves.put(cord, "in_use_by:" + player);
        int q = random.nextInt(4);
        int r = random.nextInt(2);
        ArrayList<Integer> targetCoordinates = PentagoUtils.constructCoordinates(x, y, q, r);
        PentagoUtils.transformBoard(board, player, targetCoordinates);
        return board;
    }


}

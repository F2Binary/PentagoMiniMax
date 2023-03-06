package src.comp_620.pentago;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Pentago {
    private int[][] board;
    private PlayerState currentPlayer;
    private boolean gameover;

    private HashMap<ArrayList<Integer>, String> mapOfCompletedMoves;

    public Pentago() {
        board = new int[6][6];
        currentPlayer = PlayerState.BLACK_PIECE;
        gameover = false;
        mapOfCompletedMoves = new HashMap<>();
    }

    private void miniMax() {

    }

    public void generateFirstNMoves() {
        RandomizedBoardCoordinate boardCoordinates = new RandomizedBoardCoordinate();
        for (int i = 0; i < 17; i++) {
            currentPlayer = i % 2 != 0 ? PlayerState.WHITE_PIECE : PlayerState.BLACK_PIECE;
            board = boardCoordinates.doRandom(board, String.valueOf(currentPlayer), mapOfCompletedMoves);
        }
    }

    //ToDo
    public boolean checkWin() {
        return gameover;
    }
    public void printBoard(){
        for (int[] rows : board)
            System.out.println(Arrays.toString(rows));
    }
}

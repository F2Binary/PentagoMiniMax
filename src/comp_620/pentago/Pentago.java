package src.comp_620.pentago;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

public class Pentago {
    private int[][] board;
    private PlayerState currentPlayer;
    private HashMap<ArrayList<Integer>, String> mapOfCompletedMoves;
    private final int ALGORITHM_DEPTH = 4;

    public Pentago() {
        board = new int[6][6];
        currentPlayer = PlayerState.BLACK_PIECE;
        mapOfCompletedMoves = new HashMap<>();
    }
    public int[][] getBoard(){
        return board;
    }
    public void miniMax() {
        AlphaBetaPruning state = new AlphaBetaPruning(ALGORITHM_DEPTH, currentPlayer.playerToInt());
        ArrayList<Integer> bestMove = state.decision(board);
    }

    public void generateFirstNMoves() {
        RandomizedBoardCoordinate boardCoordinates = new RandomizedBoardCoordinate();
        for (int i = 0; i <= 16; i++) {
            currentPlayer = i % 2 != 0 ? PlayerState.WHITE_PIECE : PlayerState.BLACK_PIECE;
            board = boardCoordinates.doRandom(board, String.valueOf(currentPlayer), mapOfCompletedMoves);
        }
    }
}

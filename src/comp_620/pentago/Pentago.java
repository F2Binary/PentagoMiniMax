package src.comp_620.pentago;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Pentago {
    private int[][] board;
    private PlayerState currentPlayer;
    private HashMap<ArrayList<Integer>, String> mapOfCompletedMoves;
    private final int ALGORITHM_DEPTH = 4;
    final int alpha = Integer.MIN_VALUE;
    final int beta = Integer.MAX_VALUE;

    public Pentago() {
        board = new int[6][6];
        currentPlayer = PlayerState.BLACK_PIECE;
        mapOfCompletedMoves = new HashMap<>();
    }
    public int[][] getBoard(){
        return board;
    }
    public void miniMax() {
        int s = AlphaBetaPruning.launchMiniMax(board,ALGORITHM_DEPTH,currentPlayer.playerToInt(),alpha,beta);
    }

    public void generateFirstNMoves() {
        RandomizedBoardCoordinate boardCoordinates = new RandomizedBoardCoordinate();
        for (int i = 0; i <= 16; i++) {
            currentPlayer = i % 2 != 0 ? PlayerState.WHITE_PIECE : PlayerState.BLACK_PIECE;
            board = boardCoordinates.doRandom(board, String.valueOf(currentPlayer), mapOfCompletedMoves);
        }
    }
}

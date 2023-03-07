package src.comp_620.pentago;

import java.util.ArrayList;
import java.util.List;

public class AlphaBetaPruning {
    static final int BOARD_SIZE = 5;

    public static int launchMiniMax(int[][] board, int depth, int player, int alpha, int beta) {
        boolean maximizingPlayer = player == 0;
        // Check if the game is over or maximum depth has been reached
        if (depth == 0 || PentagoUtils.checkWin(board)) {
            return evaluate(board);
        }
        // Get all possible moves
        List<ArrayList<Integer>> moves = generateMoves(board);

        int bestScore;
        if (maximizingPlayer) {
            bestScore = Integer.MIN_VALUE;

            for (ArrayList<Integer> move : moves) {
                // Make the move
                int [][] tempBoard = makeMove(board, player, move);

                // Calculate the score
                int score = launchMiniMax(tempBoard, depth - 1, player, alpha, beta);
                bestScore = Math.max(bestScore, score);

                // Undo the move
                undoMove(tempBoard,move);

                // Alpha-beta pruning
                alpha = Math.max(alpha, bestScore);
                if (beta <= alpha) {
                    break;
                }
            }

        } else {
            bestScore = Integer.MAX_VALUE;

            for (ArrayList<Integer> move : moves) {
                // Make the move
                int [][] tempBoard = makeMove(board, player, move);
                //makeMove(move.position, move.quadrant, move.isClockwise);

                // Calculate the score
                int score = launchMiniMax(board, depth - 1, player, alpha, beta);
                bestScore = Math.min(bestScore, score);

                // Undo the move
                undoMove(tempBoard,move);

                // Alpha-beta pruning
                beta = Math.min(beta, bestScore);
                if (beta <= alpha) {
                    break;
                }
            }

        }
        return bestScore;
    }

    private static List<ArrayList<Integer>> generateMoves(int[][] board) {
        List<ArrayList<Integer>> listOfCoordinates = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    for (int z = 0; z < 4; z++) {
                        int finalI = i;
                        int finalJ = j;
                        int finalZ = z;
                        listOfCoordinates.add(new ArrayList<>() {
                            {
                                add(finalI);
                                add(finalJ);
                                add(finalZ);
                                add(0);
                            }
                        });
                        listOfCoordinates.add(new ArrayList<>() {
                            {
                                add(finalI);
                                add(finalJ);
                                add(finalZ);
                                add(1);
                            }
                        });
                    }
                }
            }
        }
        return listOfCoordinates;
    }

    private static int[][] makeMove(int[][] board, int player, ArrayList<Integer> move) {
        int[][] newBoard = new int[6][6];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, board[i].length);
        }
        PentagoUtils.transformBoard(board,player,move);
        return newBoard;
    }
    private static void undoMove(int[][] board, ArrayList<Integer> move) {
        PentagoUtils.reverseTransformBoard(board, move);
    }

    private static int evaluate(int[][] board) {
        int score = 0;

        // Evaluate rows and columns
        for (int i = 0; i < BOARD_SIZE; i++) {
            int rowSum = 0;
            int colSum = 0;

            for (int j = 0; j < BOARD_SIZE; j++) {
                rowSum += board[i][j];
                colSum += board[j][i];
            }

            score += getScore(rowSum);
            score += getScore(colSum);
        }

        // Evaluate diagonals
        int diagonal1Sum = 0;
        int diagonal2Sum = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            diagonal1Sum += board[i][i];
            diagonal2Sum += board[i][BOARD_SIZE - i - 1];
        }
        score += getScore(diagonal1Sum);
        score += getScore(diagonal2Sum);

        return score;
    }

    private static int getScore(int sum) {
        switch (sum) {
            case 5 -> {
                return 100000;
            }
            case -5 -> {
                return -100000;
            }
            case 4 -> {
                return 1000;
            }
            case -4 -> {
                return -1000;
            }
            case 3 -> {
                return 100;
            }
            case -3 -> {
                return -100;
            }
            case 2 -> {
                return 10;
            }
            case -2 -> {
                return -10;
            }
            default -> {
                return 0;
            }
        }

    }

}

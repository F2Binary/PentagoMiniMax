package src.comp_620.pentago;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AlphaBetaPruning {
    static final int BOARD_SIZE = 5;
    private int color;
    private int maxDepth;
    private Random rand;
    /**
     *
     */
    public AlphaBetaPruning(int maxDepth, int color) {
        this.color = color;
        this.maxDepth = maxDepth;
        rand = new Random();
    }

    private float maxValue(int[][] b, List<ArrayList<Integer>> state, float alpha, float beta, int depth) {
        if(depth > maxDepth)
            return eval(b);

        List<ArrayList<Integer>> moves = generateMoves(makeMove(b,color, state));
        if(moves.size() == 0)
            return Float.NEGATIVE_INFINITY;

        for (ArrayList<Integer> move : moves) {
            state.add(move);
            float tmp = minValue(b, state, alpha, beta, depth + 1);
            state.remove(state.lastIndexOf(move));
            if (tmp > alpha) {
                alpha = tmp;
            }

            if (beta <= alpha)
                break;
        }

        return alpha;
    }

    private float minValue(int[][] b, List<ArrayList<Integer>> state, float alpha, float beta, int depth) {
        if(depth > maxDepth)
            return eval(b);

        List<ArrayList<Integer>> moves = generateMoves(makeMove(b,color, state));
        if(moves.size() == 0)
            return Float.POSITIVE_INFINITY;

        for (ArrayList<Integer> move : moves) {
            state.add(move);
            float tmp = maxValue(b, state, alpha, beta, depth + 1);
            state.remove(state.lastIndexOf(move));
            if (tmp < beta) {
                beta = tmp;
            }

            if (beta <= alpha)
                break;
        }

        return beta;
    }

    private static int[][] makeMove(int[][] board, int player, List<ArrayList<Integer>> moves) {
        int[][] newBoard = new int[6][6];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, board[i].length);
        }
        for(var move : moves){
            PentagoUtils.transformBoard(board,player, move);
        }
        return newBoard;
    }
    public ArrayList<Integer> decision(final int[][] b) {
        // get maximum move
        final List<ArrayList<Integer>> moves = generateMoves(b);
        if(moves.size() == 0)
            return null;

        Vector<Future<Float>> costs = new Vector<>(moves.size());
        costs.setSize(moves.size());

        ExecutorService exec = Executors.newFixedThreadPool(moves.size());
        try {
            for (int i = 0; i < moves.size(); i++) {
                final ArrayList<Integer> move = moves.get(i);
                Future<Float> result = exec.submit(() -> {
                    List<ArrayList<Integer>> state = new ArrayList<>();
                    state.add(move);

                    return minValue(b, state, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 1);
                });
                costs.set(i, result);
            }
        } finally {
            exec.shutdown();
        }
        // max
        int maxi = -1;
        float max = Float.NEGATIVE_INFINITY;
        for(int i = 0; i < moves.size(); i++) {
            float cost;
            try {
                cost = costs.get(i).get();
            } catch (Exception e) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ignored) {
                }
                continue;
            }
            if(cost >= max) {
                if(Math.abs(cost-max) < 0.1)
                    if(rand.nextBoolean())
                        continue;

                max = cost;
                maxi = i;
            }
        }

        return moves.get(maxi);
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

    private float eval(int[][] board) {
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
        //Technically 10 is the maximum number one can get in this case (because we define 2 and 1)
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

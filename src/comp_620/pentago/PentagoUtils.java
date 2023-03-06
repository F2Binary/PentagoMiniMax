package src.comp_620.pentago;

import java.util.ArrayList;

public class PentagoUtils {

    public static ArrayList<Integer> constructCoordinates(int x, int y, int quadrant, int rotation) {
        ArrayList<Integer> coordinate = new ArrayList<>();
        coordinate.add(x);
        coordinate.add(y);
        coordinate.add(quadrant);
        coordinate.add(rotation);
        return coordinate;
    }

    public static void transformBoard(int[][] board, String player, ArrayList<Integer> coordinates) {
        int marbleToWrite = player.equals(PlayerState.BLACK_PIECE.toString()) ? 1 : 2;
        board[coordinates.get(0)][coordinates.get(1)] = marbleToWrite;
        quadrantRotation(board, coordinates.get(2), coordinates.get(3));
    }

    public static void quadrantRotation(int[][] board, int quadrant, int rotation) {
        if (rotation == 0) {
            clockWiseRotation(board, quadrant);
        } else {
            counterClockWiseRotation(board, quadrant);
        }
    }

    public static void clockWiseRotation(int[][] board, int quad) {
        switch (quad) {
            case 1 -> {
                int[] quadrantOneValues =
                        {board[0][0], board[0][1], board[0][2],
                                board[1][0], board[1][2], board[2][0],
                                board[2][1], board[2][2]};

                board[0][0] = quadrantOneValues[2];
                board[0][1] = quadrantOneValues[4];
                board[0][2] = quadrantOneValues[7];
                board[1][0] = quadrantOneValues[1];
                board[1][2] = quadrantOneValues[6];
                board[2][0] = quadrantOneValues[0];
                board[2][2] = quadrantOneValues[5];
                board[2][1] = quadrantOneValues[3];
            }
            case 2 -> {
                int[] quadrantTwoValues =
                        {board[0][3], board[0][4], board[0][5],
                                board[1][3], board[1][5],
                                board[2][3], board[2][4], board[2][5]};

                board[0][3] = quadrantTwoValues[2];
                board[0][4] = quadrantTwoValues[4];
                board[0][5] = quadrantTwoValues[7];
                board[1][3] = quadrantTwoValues[1];
                board[1][5] = quadrantTwoValues[6];
                board[2][3] = quadrantTwoValues[0];
                board[2][4] = quadrantTwoValues[5];
                board[2][5] = quadrantTwoValues[3];
            }
            case 3 -> {
                int[] quadrantThreeValues =
                        {board[3][0], board[3][1], board[3][2],
                                board[4][0], board[4][2],
                                board[5][0], board[5][1], board[5][2]};

                board[3][0] = quadrantThreeValues[2];
                board[3][1] = quadrantThreeValues[4];
                board[3][2] = quadrantThreeValues[7];
                board[4][0] = quadrantThreeValues[1];
                board[4][2] = quadrantThreeValues[6];
                board[5][0] = quadrantThreeValues[0];
                board[5][2] = quadrantThreeValues[5];
                board[5][1] = quadrantThreeValues[3];
            }
            case 4 -> {
                int[] quadrantFourValues =
                        {board[3][3], board[3][4], board[3][5],
                                board[4][3], board[4][5],
                                board[5][3], board[5][4], board[5][5]};

                board[3][3] = quadrantFourValues[2];
                board[3][4] = quadrantFourValues[4];
                board[3][5] = quadrantFourValues[7];
                board[4][3] = quadrantFourValues[1];
                board[4][5] = quadrantFourValues[6];
                board[5][3] = quadrantFourValues[0];
                board[5][4] = quadrantFourValues[5];
                board[5][5] = quadrantFourValues[3];
            }
        }
    }

    public static void counterClockWiseRotation(int[][] board, int quad) {
        switch (quad) {
            case 1 -> {
                int[] quadrantOneValues =
                        {board[2][0], board[1][0], board[0][0],
                                board[2][1], board[0][1],
                                board[2][2], board[0][2], board[1][2]};

                board[0][0] = quadrantOneValues[0];
                board[0][1] = quadrantOneValues[1];
                board[0][2] = quadrantOneValues[2];

                board[1][0] = quadrantOneValues[3];
                board[1][2] = quadrantOneValues[4];

                board[2][0] = quadrantOneValues[5];
                board[2][2] = quadrantOneValues[6];
                board[2][1] = quadrantOneValues[7];
            }
            case 2 -> {
                int[] quadrantTwoValues =
                        {board[2][3], board[1][3], board[0][3],
                                board[2][4], board[0][4],
                                board[2][5], board[0][5], board[1][5]};

                board[0][3] = quadrantTwoValues[0];
                board[0][4] = quadrantTwoValues[1];
                board[0][5] = quadrantTwoValues[2];

                board[1][3] = quadrantTwoValues[3];
                board[1][5] = quadrantTwoValues[4];

                board[2][3] = quadrantTwoValues[5];
                board[2][4] = quadrantTwoValues[6];
                board[2][5] = quadrantTwoValues[7];
            }
            case 3 -> {
                int[] quadrantThreeValues =
                        {board[5][0], board[4][0], board[3][0],
                                board[5][1], board[3][1],
                                board[5][2], board[3][2], board[4][2]};

                board[3][0] = quadrantThreeValues[0];
                board[3][1] = quadrantThreeValues[1];
                board[3][2] = quadrantThreeValues[2];

                board[4][0] = quadrantThreeValues[3];
                board[4][2] = quadrantThreeValues[4];

                board[5][0] = quadrantThreeValues[5];
                board[5][2] = quadrantThreeValues[6];
                board[5][1] = quadrantThreeValues[7];
            }
            case 4 -> {
                int[] quadrantFourValues =
                        {board[5][3], board[4][3], board[3][3],
                                board[5][4], board[3][4],
                                board[5][5], board[3][5], board[4][5]};

                board[3][3] = quadrantFourValues[0];
                board[3][4] = quadrantFourValues[1];
                board[3][5] = quadrantFourValues[2];

                board[4][3] = quadrantFourValues[3];
                board[4][5] = quadrantFourValues[4];

                board[5][3] = quadrantFourValues[5];
                board[5][4] = quadrantFourValues[6];
                board[5][5] = quadrantFourValues[7];
            }

        }

    }

}

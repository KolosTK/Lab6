package org.example;

import java.sql.SQLOutput;

public class Cinema {

    private int[][][] places = new int[5][10][20];
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String RESET = "\033[0m";

    public void bookSeats(int hallNumber, int row, int[] seats) {

    }

    public void cancelBooking(int hallNumber, int row, int[] seats) {

    }

    public void checkAvailability(int screen, int numSeats) {

    }

    private void printSearingColumns(int hallNumber) {
        final int seatIndexOffset = 1;
        final int rowHeaderOffset = 10;

        for (int i = 0; i < places[hallNumber][seatIndexOffset].length; ) {
            {
                if (i == 0) {
                    System.out.print("   ");
                }
                if (i < rowHeaderOffset) {
                    System.out.print(" ");
                }
                System.out.print(" " + ++i);
            }
        }
        System.out.println();
    }

    public void printSeatingArrangement(int hallNumber) {
        int indexOfRow = 1;
        printSearingColumns(hallNumber);

        for (int i = 0; i < places[hallNumber].length; i++) {
            if (indexOfRow < 10) {
                System.out.print(" ");
            }
            System.out.print(indexOfRow + " |");
            for (int j = 0; j < places[hallNumber][i].length; j++) {
                if (places[hallNumber][i][j] != 0) {
                    System.out.print(ANSI_RED + ANSI_YELLOW_BACKGROUND + " " + places[hallNumber][i][j] + " " + RESET);

                } else {
                    System.out.print(ANSI_GREEN + ANSI_BLACK_BACKGROUND + " " + places[hallNumber][i][j] + " " + RESET);
                }
            }
            System.out.print("| " + indexOfRow++ + "\n");
        }
        printSearingColumns(hallNumber);

    }


}

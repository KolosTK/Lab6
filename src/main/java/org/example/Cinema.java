package org.example;

public class Cinema {

    private int[][][] places = new int[5][10][20];
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    private static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String RESET = "\033[0m";

    public void bookSeats(int hallNumber, int row, int[] seats) throws SelectingSeatsException {
        for (int i = 0; i < seats.length; i++) {
            if (places[hallNumber-1][row-1][seats[i] - 1] == 1) {
                throw new SelectingSeatsException("Seats is already busy");
            }
        }
        for (int i = 0; i < seats.length; i++) {
            places[hallNumber-1][row-1][seats[i] - 1] = 1;
        }
    }

    public void cancelBooking(int hallNumber, int row, int[] seats) throws SelectingSeatsException {
        int countOfZero = 0;
        for (int i = 0; i < seats.length; i++) {
            if (places[hallNumber-1][row-1][seats[i] - 1] == 0) {
                countOfZero++;
                if (countOfZero >= seats.length) {throw new SelectingSeatsException("All selected seats is not busy");}
            }
            else {
                places[hallNumber-1][row-1][seats[i] - 1] = 0;
            }
        }
    }

    public void checkAvailability(int hallNumber, int numSeats) {

    }

    private void printNumberOfSearchingColumns(int hallNumber) {
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
        printNumberOfSearchingColumns(hallNumber);

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
        printNumberOfSearchingColumns(hallNumber);

    }


}

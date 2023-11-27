package org.example;

public class Cinema {
    private int _hallsAmount = 5;
    private int _rowsAmount = 10;
    private int _seatsAmount = 20;
    private int[][][] places = new int[_hallsAmount][_rowsAmount][_seatsAmount];
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    private static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String RESET = "\033[0m";

    public int get_rowsAmount() {
        return _rowsAmount;
    }

    public int get_hallsAmount() {
        return _hallsAmount;
    }

    public int get_seatsAmount() {
        return _seatsAmount;
    }

    public void bookSeats(int hallNumber, int row, int[] seats) throws SelectingSeatsException {
        for (int i = 0; i < seats.length; i++) {
            if (places[hallNumber - 1][row - 1][seats[i] - 1] == 1) {
                throw new SelectingSeatsException("Seats is already busy");
            }
        }
        for (int i = 0; i < seats.length; i++) {
            places[hallNumber - 1][row - 1][seats[i] - 1] = 1;
        }
    }

    public void cancelBooking(int hallNumber, int row, int[] seats) throws SelectingSeatsException {
        int countOfZero = 0;
        for (int i = 0; i < seats.length; i++) {
            if (places[hallNumber - 1][row - 1][seats[i] - 1] == 0) {
                countOfZero++;
                if (countOfZero >= seats.length) {
                    throw new SelectingSeatsException("All selected seats is not busy");
                }
            } else {
                places[hallNumber - 1][row - 1][seats[i] - 1] = 0;
            }
        }
    }

    public boolean checkAvailability(int hallNumber, int numSeats) {
        int amountOfAvailableSeats = 0;
        for (int i = 0; i < _rowsAmount; i++) {
            for (int j = 0; j < _seatsAmount; j++) {
                if ((places[hallNumber - 1][i][j] == 0 && amountOfAvailableSeats == 0)
                        || (places[hallNumber - 1][i][j] == 0 && amountOfAvailableSeats < numSeats)) {
                    amountOfAvailableSeats++;
                } else if (places[hallNumber - 1][i][j] == 0 && amountOfAvailableSeats == numSeats) {
                    return true;
                } else {
                    amountOfAvailableSeats = 0;
                }
            }
        }
        return false;
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
        hallNumber -= 1;

        printNumberOfSearchingColumns(hallNumber);

        for (int i = 0; i < _rowsAmount; i++) {
            if (indexOfRow < 10) {
                System.out.print(" ");
            }
            System.out.print(indexOfRow + " |");
            for (int j = 0; j < _seatsAmount; j++) {
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

    public int[] findBestAvailable(int hallNumber, int numSeats) {
        int suborderSeats = 0;
        int maxSuborder = 0;
        int[] result = new int[3]; //first number is row, second is first seat and third is last seat

        if (!checkAvailability(hallNumber, numSeats)) {
            return result;
        }

        for (int i = 0; i < _rowsAmount; i++) {
            for (int j = 0; j < _seatsAmount; j++) {
                if ((places[hallNumber - 1][i][j] == 0 && suborderSeats == 0)
                        || (places[hallNumber - 1][i][j] == 0 && suborderSeats < _seatsAmount)) {
                    suborderSeats++;
                    if (suborderSeats > maxSuborder) {
                        maxSuborder = suborderSeats;
                        result[0] = i + 1; // initialize row
                        result[1] = j + 1 - suborderSeats + 1; //initialize index of first element
                    }
                } else {
                    suborderSeats = 0;
                }
            }
        }
        result[2] = result[1] + numSeats - 1;
        return result;
    }


}

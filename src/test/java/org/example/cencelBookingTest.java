package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.channels.SelectionKey;

import static org.junit.jupiter.api.Assertions.*;

class cencelBookingTest {
    Cinema cinema = new Cinema();
    int[] canceledSeats;

    @BeforeEach
    public void setUp() throws SelectingSeatsException {

        int[] orderSeats = new int[]{3, 4, 5};
        cinema.bookSeats(3, 2, orderSeats);
    }

    @Test
    void cancelBookingIsSuccessful() throws SelectingSeatsException {
        canceledSeats = new int[]{3, 4, 5};
        Assertions.assertDoesNotThrow(() -> {
            cinema.cancelBooking(3, 2, canceledSeats);
        }, "All selected seats is not busy");
    }

    @Test
    void cancelBookingUnsuccessful() throws SelectingSeatsException {
        canceledSeats = new int[]{6, 7, 8};
        assertThrows(SelectingSeatsException.class, () -> {
            cinema.cancelBooking(3, 2, canceledSeats);
        }, "All selected seats is not busy");
    }

    @Test
    void cancelBookingNotAllSeats() throws SelectingSeatsException {
        canceledSeats = new int[]{5, 7};
        Assertions.assertDoesNotThrow(() -> {
            cinema.cancelBooking(3, 2, canceledSeats);
        }, "All selected seats is not busy");
    }
}
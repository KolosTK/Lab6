package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class checkAvailabilityTest {
    Cinema cinema = new Cinema();

    @Test
    void testCheckAvailabilitySuccessful() {
        assertTrue(cinema.checkAvailability(3, 5));
    }

    @Test
    void testCheckAvailabilityUnsuccessful() throws SelectingSeatsException {
        int[] seats = {3, 4};
        for (int i = 1; i <= cinema.get_rowsAmount(); i++) {
            cinema.bookSeats(2, i, seats);
        }
        assertFalse(cinema.checkAvailability(2, 18));
    }
}
# Lab6
Java Lab 6 
This is a program for reserving seats in the cinema.
Class Cinema has 5 halls, and each of them has 200 seats (20 columns and 10 rows).
In class Cinema exist methods
* bookSeats - reserve seats if they are available
* cancelBooking - cancel a reservation that was made before
* checkAvailability - check if a specific hall has a sufficient number of seats in one row
* printSeatingArrangement - show in console scheme of the hall
* printNumberOfSearchingColumns - show numbers of columns for best reading practice
* findBestAvailable - search for the biggest soborder of free seats 
* autoBook - call findBestAvailable method, and if the work of this method is successful, reserve seats
Also, I create JUnit tests for the following methods : autoBook, censelBooking, checkAvailability, findBestAvailable, bookSeats

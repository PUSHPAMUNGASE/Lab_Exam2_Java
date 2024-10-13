// BookingService.java
package service;

import entity.Booking;
import entity.Movie;
import entity.Showtime;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookingService {
    private HashMap<String, Movie> movies = new HashMap<>();
    private HashMap<String, Showtime> showtimes = new HashMap<>();
    private List<Booking> bookings = new ArrayList<>();

    public void addMovie(String movieId, String title) {
        Movie movie = new Movie(title);
        movies.put(movieId, movie);
    }

    public void addShowtime(String showtimeId, String movieId, String time, int numberOfSeats) throws Exception {
        Movie movie = movies.get(movieId);
        if (movie == null) {
            throw new Exception("Movie not found for the given movie ID.");
        }
        Showtime showtime = new Showtime(movie, time, numberOfSeats);
        showtimes.put(showtimeId, showtime);
    }

    public boolean checkSeatAvailability(String showtimeId, int seatNumber) throws Exception {
        Showtime showtime = showtimes.get(showtimeId);
        if (showtime == null) {
            throw new Exception("Showtime not found.");
        }
        return showtime.isSeatAvailable(seatNumber);
    }

    public void bookSeats(String showtimeId, List<Integer> seatNumbers) throws Exception {
        Showtime showtime = showtimes.get(showtimeId);
        if (showtime == null) {
            throw new Exception("Showtime not found.");
        }
        List<Integer> bookedSeats = new ArrayList<>();
        for (int seatNumber : seatNumbers) {
            if (showtime.isSeatAvailable(seatNumber)) {
                showtime.bookSeat(seatNumber);
                bookedSeats.add(seatNumber);
            } else {
                throw new Exception("Seat " + seatNumber + " is unavailable.");
            }
        }

        String bookingId = "B" + (bookings.size() + 1);
        Booking booking = new Booking(bookingId, showtime, bookedSeats);
        bookings.add(booking);
        logBookingDetails(booking);
    }

    private void logBookingDetails(Booking booking) throws IOException {
        try (FileWriter writer = new FileWriter("bookings_log.txt", true)) {
            writer.write("Booking ID: " + booking.getBookingId() + "\n");
            writer.write("Movie: " + booking.getShowtime().getMovie().getTitle() + "\n");
            writer.write("Showtime: " + booking.getShowtime().getTime() + "\n");
            writer.write("Seats: " + booking.getBookedSeatNumbers() + "\n");
            writer.write("------------------------------------------\n");
        }
    }
}

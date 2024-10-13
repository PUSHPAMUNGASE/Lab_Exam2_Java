// Showtime.java
package entity;

import java.util.ArrayList;
import java.util.List;

public class Showtime {
    private Movie movie;
    private String time;
    private List<Seat> seats;

    public Showtime(Movie movie, String time, int numberOfSeats) {
        this.movie = movie;
        this.time = time;
        this.seats = new ArrayList<>();
        for (int i = 1; i <= numberOfSeats; i++) {
            seats.add(new Seat(i));
        }
    }

    public Movie getMovie() {
        return movie;
    }

    public String getTime() {
        return time;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public boolean isSeatAvailable(int seatNumber) {
        if (seatNumber <= 0 || seatNumber > seats.size()) {
            return false;
        }
        return !seats.get(seatNumber - 1).isBooked();
    }

    public void bookSeat(int seatNumber) throws Exception {
        if (!isSeatAvailable(seatNumber)) {
            throw new Exception("Seat " + seatNumber + " is already booked or unavailable.");
        }
        seats.get(seatNumber - 1).book();
    }
}

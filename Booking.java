// Booking.java
package entity;

import java.util.List;

public class Booking {
    private String bookingId;
    private Showtime showtime;
    private List<Integer> bookedSeatNumbers;

    public Booking(String bookingId, Showtime showtime, List<Integer> bookedSeatNumbers) {
        this.bookingId = bookingId;
        this.showtime = showtime;
        this.bookedSeatNumbers = bookedSeatNumbers;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public List<Integer> getBookedSeatNumbers() {
        return bookedSeatNumbers;
    }
}


package operation;
import service.BookingService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CinemaBookingSystem {
    public static void main(String[] args) {
        BookingService bookingService = new BookingService();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("========= Cinema Booking System =========");
            System.out.println("1. Add a new Movie");
            System.out.println("2. Add a new Showtime");
            System.out.println("3. Check Seat Availability");
            System.out.println("4. Book Seats");
            System.out.println("5. Exit");
            System.out.println("=========================================");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    addMovie(bookingService, scanner);
                    break;
                case 2:
                    addShowtime(bookingService, scanner);
                    break;
                case 3:
                    checkSeatAvailability(bookingService, scanner);
                    break;
                case 4:
                    bookSeats(bookingService, scanner);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void addMovie(BookingService bookingService, Scanner scanner) {
        System.out.print("Enter Movie ID: ");
        String movieId = scanner.nextLine();
        System.out.print("Enter Movie Title: ");
        String title = scanner.nextLine();

        bookingService.addMovie(movieId, title);
        System.out.println("Movie added successfully.");
    }

    private static void addShowtime(BookingService bookingService, Scanner scanner) {
        try {
            System.out.print("Enter Showtime ID: ");
            String showtimeId = scanner.nextLine();
            System.out.print("Enter Movie ID for the Showtime: ");
            String movieId = scanner.nextLine();
            System.out.print("Enter Showtime (e.g., 10:00 AM): ");
            String time = scanner.nextLine();
            System.out.print("Enter the Number of Seats: ");
            int numberOfSeats = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            bookingService.addShowtime(showtimeId, movieId, time, numberOfSeats);
            System.out.println("Showtime added successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void checkSeatAvailability(BookingService bookingService, Scanner scanner) {
        try {
            System.out.print("Enter Showtime ID: ");
            String showtimeId = scanner.nextLine();
            System.out.print("Enter Seat Number to Check: ");
            int seatNumber = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            boolean isAvailable = bookingService.checkSeatAvailability(showtimeId, seatNumber);
            if (isAvailable) {
                System.out.println("Seat " + seatNumber + " is available.");
            } else {
                System.out.println("Seat " + seatNumber + " is already booked or unavailable.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void bookSeats(BookingService bookingService, Scanner scanner) {
        try {
            System.out.print("Enter Showtime ID: ");
            String showtimeId = scanner.nextLine();
            System.out.print("Enter seat numbers to book (comma separated, e.g., 1,2,3): ");
            String seatNumbersStr = scanner.nextLine();
            List<Integer> seatNumbers = Arrays.stream(seatNumbersStr.split(","))
                    .map(Integer::parseInt)
                    .toList();

            bookingService.bookSeats(showtimeId, seatNumbers);
            System.out.println("Seats " + seatNumbers + " booked successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Other necessary methods
}

class Ticket {
    private String pnr;
    private String trainNumber;
    private String classType;
    private String date;
    private String source;
    private String destination;

    public Ticket(String pnr, String trainNumber, String classType, String date, String source, String destination) {
        this.pnr = pnr;
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.date = date;
        this.source = source;
        this.destination = destination;
    }

    public String getPnr() {
        return pnr;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getClassType() {
        return classType;
    }

    public String getDate() {
        return date;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    // Other necessary methods
}

class ReservationSystem {
    private Map<String, Ticket> reservations;
    private Random random;

    public ReservationSystem() {
        this.reservations = new HashMap<>();
        this.random = new Random();
    }

    public void displayMenu() {
        System.out.println("1. Make Reservation");
        System.out.println("2. Cancel Reservation");
        System.out.println("3. View Ticket");
        System.out.println("4. Exit");
    }

    public void processOption(int option, User currentUser, Scanner scanner) {
        switch (option) {
            case 1:
                makeReservation(currentUser, scanner);
                break;
            case 2:
                cancelReservation(scanner);
                break;
            case 3:
                viewTicket(scanner);
                break;
            case 4:
                System.out.println("Exiting the Online Reservation System. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please choose a valid option.");
        }
    }

    private void makeReservation(User user, Scanner scanner) {
        System.out.println("Enter train number: ");
        String trainNumber = scanner.nextLine();
        System.out.println("Enter class type: ");
        String classType = scanner.nextLine();
        System.out.println("Enter date of journey: ");
        String date = scanner.nextLine();
        System.out.println("Enter source: ");
        String source = scanner.nextLine();
        System.out.println("Enter destination: ");
        String destination = scanner.nextLine();

        String pnr = generatePNR();
        Ticket reservationTicket = new Ticket(pnr, trainNumber, classType, date, source, destination);
        reservations.put(pnr, reservationTicket);

        System.out.println("Reservation Successful! Here is your ticket details:");
        displayTicketDetails(reservationTicket);
    }

    private void cancelReservation(Scanner scanner) {
        System.out.println("Enter PNR number for cancellation: ");
        String cancelPnr = scanner.nextLine();

        if (reservations.containsKey(cancelPnr)) {
            reservations.remove(cancelPnr);
            System.out.println("Reservation with PNR " + cancelPnr + " has been canceled.");
        } else {
            System.out.println("Invalid PNR number. Ticket not found.");
        }
    }

    private void viewTicket(Scanner scanner) {
        System.out.println("Enter PNR number to view ticket details: ");
        String viewPnr = scanner.nextLine();

        Ticket viewTicket = reservations.get(viewPnr);

        if (viewTicket != null) {
            System.out.println("Here is your ticket details:");
            displayTicketDetails(viewTicket);
        } else {
            System.out.println("Invalid PNR number. Ticket not found.");
        }
    }

    private void displayTicketDetails(Ticket ticket) {
        System.out.println("PNR: " + ticket.getPnr());
        System.out.println("Train Number: " + ticket.getTrainNumber());
        System.out.println("Class Type: " + ticket.getClassType());
        System.out.println("Date: " + ticket.getDate());
        System.out.println("Source: " + ticket.getSource());
        System.out.println("Destination: " + ticket.getDestination());
    }

    private String generatePNR() {
        return String.format("%09d", random.nextInt(1000000000));
    }
}

public class OnlineReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Implement the login module
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        User currentUser = new User(username, password);

        // Implement authentication logic to validate the user

        // If authentication is successful, proceed to the reservation system
        ReservationSystem reservationSystem = new ReservationSystem();

        while (true) {
            reservationSystem.displayMenu();
            System.out.println("Enter your choice (1-4): ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            reservationSystem.processOption(option, currentUser, scanner);
        }
    }
}
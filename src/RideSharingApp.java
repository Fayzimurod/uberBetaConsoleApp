import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RideSharingApp {
    private static Scanner scanner = new Scanner(System.in);
    private static RideManager rideManager = new RideManager();
    private static Ride currentRide = null;

    public static void main(String[] args) {
        initializeSystem();
        System.out.println("""
                 RIDE-SHARING SYSTEM SIMULATION (UBER)
                 System Design Console Application
                """);
        boolean run = true;
        while (run) {
            displayMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewDrivers();
                    break;
                case 2:
                    viewUsers();
                    break;
                case 3:
                    requestRide();
                    break;
                case 4:
                    startRide();
                    break;
                case 5:
                    completeRide();
                    break;
                case 6:
                    viewCurrentRide();
                    break;
                case 7:
                    viewRideHistory();
                    break;
                case 8:
                    System.out.println("\n Thank you for using the Ride-Sharing System!");
                    run = false;
                    break;
                default:
                    System.out.println("\n Invalid choice! Please try again.");
            }
            if (run) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        scanner.close();

    }


    private static void initializeSystem() {
        rideManager.addDriver(new Driver(1, "Abdunmalik Rafiqov", "Tashkent", 150.0));
        rideManager.addDriver(new Driver(2, "Johnny Halimjonov", "Andijon", 200.0));
        rideManager.addDriver(new Driver(3, "Asatillayev Javohir", "Toshken", 1000.0));
        rideManager.addDriver(new Driver(4, "Erkinov Paul", "Sergeli", 100.0));

        rideManager.addUser(new User(1, "Alice Brown", "Central Park", 100.0));
        rideManager.addUser(new User(2, "Bob Wilson", "Times Square", 150.0));
        rideManager.addUser(new User(3, "Charlie Green", "Brooklyn", 200.0));
    }

    private static void displayMenu() {
        System.out.println("\n" + "*".repeat(50));
        System.out.println("                    MAIN MENU");
        System.out.println("-".repeat(50));
        System.out.println("1. View All Drivers");
        System.out.println("2. View All Users");
        System.out.println("3. Request a Ride");
        System.out.println("4. Start Current Ride");
        System.out.println("5. Complete Current Ride");
        System.out.println("6. View Current Ride Details");
        System.out.println("7. View Ride History");
        System.out.println("8. Exit");
        System.out.println("-".repeat(50));
    }

    private static void viewDrivers() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                 AVAILABLE DRIVERS");
        System.out.println("=".repeat(50));

        if (rideManager.getDrivers().isEmpty()) {
            System.out.println("No drivers in the system.");
        } else {
            for (Driver driver : rideManager.getDrivers()) {
                System.out.println(driver);
            }
        }
    }

    private static void viewUsers() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                   SYSTEM USERS");
        System.out.println("=".repeat(50));

        if (rideManager.getUsers().isEmpty()) {
            System.out.println("No users in the system.");
        } else {
            for (User user : rideManager.getUsers()) {
                System.out.println(user);
            }
        }
    }

    private static void requestRide() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                   REQUEST A RIDE");
        System.out.println("=".repeat(50));


        if (currentRide != null && !currentRide.getStatus().equals("completed")) {
            System.out.println("✗ You already have an active ride. Please complete it first.");
            return;
        }

        System.out.println("\nAvailable Users:");
        for (User user : rideManager.getUsers()) {
            System.out.printf("%d. %s (Balance: $%.2f)\n", user.getId(), user.getFullName(), user.getBalance());
        }

        int userId = getIntInput("\nSelect user ID: ");
        User user = rideManager.findUserById(userId);

        if (user == null) {
            System.out.println("Invalid user ID!");
            return;
        }

        System.out.print("Enter pickup location: ");
        String pickup = scanner.nextLine();

        System.out.print("Enter dropoff location: ");
        String dropoff = scanner.nextLine();

        double distance = getDoubleInput("Enter distance (km): ");


        currentRide = rideManager.createRide(user, pickup, dropoff, distance);
        System.out.printf("\n✓ Ride created! Estimated price: $%.2f\n", currentRide.getTotalPrice());


        Driver driver = rideManager.matchRide(currentRide);
        if (driver != null) {
            System.out.printf("✓ Matched with driver: %s (Rating: %.1f)\n",
                    driver.getFullName(), driver.getRating());
            System.out.println("✓ Driver is on the way to pickup location!");
        } else {
            System.out.println("✗ No drivers available at the moment. Please try again later.");
            currentRide = null;
        }
    }

    private static void startRide() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                    START RIDE");
        System.out.println("=".repeat(50));

        if (currentRide == null) {
            System.out.println("✗ No active ride to start. Please request a ride first.");
            return;
        }

        if (!currentRide.getStatus().equals("matched")) {
            System.out.println("✗ Ride cannot be started. Current status: " + currentRide.getStatus());
            return;
        }

        currentRide.startRide();
        System.out.println("✓ Ride started! En route to destination...");
        System.out.println(currentRide);
    }

    private static void completeRide() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                  COMPLETE RIDE");
        System.out.println("=".repeat(50));

        if (currentRide == null) {
            System.out.println("✗ No active ride to complete.");
            return;
        }

        if (!currentRide.getStatus().equals("in-progress")) {
            System.out.println("✗ Ride is not in progress. Current status: " + currentRide.getStatus());
            return;
        }

        System.out.println("\nRide Details:");
        System.out.println(currentRide);
        System.out.printf("Amount to be charged: $%.2f\n", currentRide.getTotalPrice());

        double rating = getDoubleInput("\nRate your driver (1-5 stars): ");
        while (rating < 1 || rating > 5) {
            System.out.println("✗ Invalid rating! Please enter a value between 1 and 5.");
            rating = getDoubleInput("Rate your driver (1-5 stars): ");
        }

        if (rideManager.completeRide(currentRide, rating)) {
            System.out.println("\n✓ Ride completed successfully!");
            System.out.printf("✓ Driver rated: %.1f stars\n", rating);
            System.out.printf("✓ Amount charged: $%.2f\n", currentRide.getTotalPrice());
            System.out.printf("✓ Remaining balance: $%.2f\n", currentRide.getUser().getBalance());
            currentRide = null;
        } else {
            System.out.println("✗ Failed to complete ride. Insufficient balance!");
        }
    }

    private static void viewCurrentRide() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("              CURRENT RIDE DETAILS");
        System.out.println("=".repeat(50));


        if (currentRide == null) {
            System.out.println("No active ride.");
        } else {
            System.out.println(currentRide);
            System.out.println("\nPassenger: " + currentRide.getUser());
            if (currentRide.getDriver() != null) {
                System.out.println("Driver: " + currentRide.getDriver());
            }
        }
    }

    private static void viewRideHistory() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                 RIDE HISTORY");
        System.out.println("=".repeat(50));

        List<Ride> completedRides = new ArrayList<>();
        for (Ride ride : rideManager.getRides()) {
            if (ride.getStatus().equals("completed")) {
                completedRides.add(ride);
            }
        }

        if (completedRides.isEmpty()) {
            System.out.println("No completed rides yet.");
        } else {
            for (Ride ride : completedRides) {
                System.out.println(ride);
                System.out.printf("   Rating given: %.1f stars\n\n", ride.getRating());
            }
        }
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. " + prompt);
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return value;
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. " + prompt);
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        return value;
    }
}

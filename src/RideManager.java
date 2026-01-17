import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RideManager {
    private List<Ride> rides;
    private List<Driver> drivers;
    private List<User> users;
    private int rideIdCounter;

    public RideManager() {
        this.rides = new ArrayList<>();
        this.drivers = new ArrayList<>();
        this.users = new ArrayList<>();
        this.rideIdCounter = 1;
    }

    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<Driver> getDrivers() { return drivers; }
    public List<User> getUsers() { return users; }
    public List<Ride> getRides() { return rides; }

    public User findUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public Ride createRide(User user, String pickupLocation, String dropoffLocation, double distance) {
        Ride ride = new Ride(rideIdCounter++, user, pickupLocation, dropoffLocation, distance);
        rides.add(ride);
        return ride;
    }

    public Driver findNearestDriver(String location) {
        List<Driver> availableDrivers = new ArrayList<>();
        for (Driver driver : drivers) {
            if (driver.isAvailable()) {
                availableDrivers.add(driver);
            }
        }

        if (availableDrivers.isEmpty()) {
            return null;
        }


        Random random = new Random();
        return availableDrivers.get(random.nextInt(availableDrivers.size()));
    }

    public Driver matchRide(Ride ride) {
        Driver driver = findNearestDriver(ride.getPickupLocation());
        if (driver != null) {
            ride.assignDriver(driver);
            driver.setAvailability(false);
            return driver;
        }
        return null;
    }

    public boolean completeRide(Ride ride, double rating) {
        if (ride.getStatus().equals("in-progress")) {
            if (ride.getUser().deductBalance(ride.getTotalPrice())) {
                ride.completeRide(rating);
                if (ride.getDriver() != null) {
                    ride.getDriver().addEarnings(ride.getTotalPrice() * 0.8); // Driver gets 80%
                    ride.getDriver().setAvailability(true);
                }
                ride.getUser().setLocation(ride.getDropoffLocation());
                return true;
            }
        }
        return false;
    }
}


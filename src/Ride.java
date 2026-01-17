public class Ride {
    private int id;
    private User user;
    private String pickupLocation;
    private String dropoffLocation;
    private double distance;
    private double basePricePerKm;
    private double basePrice;
    private double totalPrice;
    private String status;
    private Driver driver;
    private double rating;

    public Ride(int id, User user, String pickupLocation, String dropoffLocation, double distance) {
        this.id = id;
        this.user = user;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.distance = distance;
        this.basePricePerKm = 2.5;
        this.basePrice = 5.0;
        this.totalPrice = calculatePrice();
        this.status = "pending";
        this.driver = null;
        this.rating = 0;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public double getDistance() {
        return distance;
    }

    public double getBasePricePerKm() {
        return basePricePerKm;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public Driver getDriver() {
        return driver;
    }

    public double getRating() {
        return rating;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    private double calculatePrice() {
        return basePrice + (distance * basePricePerKm);
    }

    public void assignDriver(Driver driver) {
        this.driver = driver;
        this.status = "matched";
    }

    public void startRide() {
        this.status = "in-progress";
    }

    public void completeRide(double rating) {
        this.status = "completed";
        this.rating = rating;
        if (this.driver != null) {
            this.driver.updateRating(rating);
        }
    }

    @Override
    public String toString() {
        return "Ride{" +
                "id=" + id +
                ", user=" + user +
                ", pickupLocation='" + pickupLocation + '\'' +
                ", dropoffLocation='" + dropoffLocation + '\'' +
                ", distance=" + distance +
                ", basePricePerKm=" + basePricePerKm +
                ", basePrice=" + basePrice +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                ", driver=" + driver +
                ", rating=" + rating +
                '}';
    }

    //    @Override
//    public String toString() {
//        String driverInfo = driver != null ? driver.getFullName() : "Not assigned";
//        return String.format("Ride #%d | User: %s | From: %s -> To: %s | Distance: %.1f km | Price: %.2f | Status: %s | Driver: %s",
//                id, user.getFullName(), pickupLocation, dropoffLocation, distance, totalPrice, status.toUpperCase(), driverInfo);
//    }
}

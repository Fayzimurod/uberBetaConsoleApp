public class Driver {
    private int id;
    private String fullName;
    private String location;
    private double balance;
    private boolean availability;
    private double distance;
    private double rating;
    private int totalRides;

    public Driver(int id, String fullName, String location, double balance) {
        this.id = id;
        this.fullName = fullName;
        this.location = location;
        this.balance = balance;
        this.availability = true;
        this.distance = 0;
        this.rating = 5.0;
        this.totalRides = 0;
    }

    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public String getLocation() { return location; }
    public double getBalance() { return balance; }
    public boolean isAvailable() { return availability; }
    public double getDistance() { return distance; }
    public double getRating() { return rating; }
    public int getTotalRides() { return totalRides; }

    public void setLocation(String location) { this.location = location; }
    public void setAvailability(boolean availability) { this.availability = availability; }
    public void setDistance(double distance) { this.distance = distance; }

    public void addEarnings(double amount) { this.balance += amount; }

    public void updateRating(double newRating){
        this.rating = ((this.rating + this.totalRides) + newRating) / (this.totalRides + 1);
        this.totalRides++;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", location='" + location + '\'' +
                ", balance=" + balance +
                ", availability=" + availability +
                ", distance=" + distance +
                ", rating=" + rating +
                ", totalRides=" + totalRides +
                '}';
//        availability ? "Available" : "Busy";
    }
}


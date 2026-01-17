public class User {
    private int id;
    private String fullName;
    private String location;
    private double balance;
    private double distance;

    public User(int id, String fullName, String location, double balance) { // 3 usages
        this.id = id;
        this.fullName = fullName;
        this.location = location;
        this.balance = balance;
        this.distance = 0;
    }

    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public String getLocation() { return location; }
    public double getBalance() { return balance; }
    public double getDistance() { return distance; }

    public void setLocation(String location) { this.location = location; }
    public void setDistance (double distance) { this.distance = distance; }

    public boolean deductBalance (double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    public void addBalance(double amount){
        this.balance += amount;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", location='" + location + '\'' +
                ", balance=" + balance +
                ", distance=" + distance +
                '}';
    }
}

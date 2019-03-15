package modelLayer;

public class Customer {

    private int customerId;
    private String email;
    private String password;
    private String usertype = "customer";
    private double balance = 0.0;
    private int numberOfOrders = 0;

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public Customer(int customerId, String email, String password, String usertype, double balance, int numberOfOrders) {
        this.customerId = customerId;
        this.email = email;
        this.password = password;
        this.usertype = usertype;
        this.balance = balance;
        this.numberOfOrders = numberOfOrders;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsertype() {
        return usertype;
    }

    public double getBalance() {
        return balance;
    }
}

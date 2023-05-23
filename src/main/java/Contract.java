import java.util.Date;

public abstract class Contract {
    protected String date;
    protected String customerName;
    protected String customerEmail;
    protected Vehicle vehicle;
    protected double totalPrice;
    protected double monthlyPayment;
    private String contractId;
    private Customer customer;
    private Dealership dealership;


    public Contract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicle = vehicle;
        this.contractId = vehicle.getId();
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getContractId() {
        return this.contractId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public String getDate() {
        return this.date;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    // setter for date
    public void setDate(Date date) {
        this.date = String.valueOf(date);
    }

    // setter for vehicle
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();

    public String getCustomerName() {
        return this.customer.getName();
    }

    public String getCustomerEmail() {
        return this.customer.getEmail();
    }

    public abstract double calculateTotalPrice();
}

public class LeaseContract extends Contract {
    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, vehicle);
        this.expectedEndingValue = vehicle.getPrice() * 0.5;
        this.leaseFee = vehicle.getPrice() * 0.07;
    }
    
    public double getExpectedEndingValue() {
        return this.expectedEndingValue;
    }

    public double getLeaseFee() {
        return this.leaseFee;
    }

    // setters for fields
    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }
    @Override
    public double getTotalPrice() {
        return vehicle.getPrice() - expectedEndingValue + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        double rate = 0.04;
        int months = 36;
        double monthlyRate = rate / 12;
        return (vehicle.getPrice() * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
    }
    @Override
    public double calculateTotalPrice() {
        return getVehicle().getPrice() - this.expectedEndingValue + this.leaseFee;
    }


    public void createContract() {
        LeaseContract contract = new LeaseContract(getCustomer(), chosenVehicle, contractType, expectedEndingValue, leaseFee);
    }
    Vehicle vehicle = dealership.findVehicleByVin(vin); // assuming 'dealership' is an instance of your Dealership class
}

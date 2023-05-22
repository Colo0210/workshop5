public class LeaseContract extends Contract {
    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, vehicle);
        this.expectedEndingValue = vehicle.getPrice() * 0.5;
        this.leaseFee = vehicle.getPrice() * 0.07;
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

    // Implement the other methods...
}

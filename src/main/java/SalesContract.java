public class SalesContract extends Contract {
    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private boolean finance;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicle, boolean finance) {
        super(date, customerName, customerEmail, vehicle);
        this.salesTaxAmount = vehicle.getPrice() * 0.05;
        this.recordingFee = 100;
        this.processingFee = vehicle.getPrice() < 10000 ? 295 : 495;
        this.finance = finance;
    }

    @Override
    public double getTotalPrice() {
        return vehicle.getPrice() + salesTaxAmount + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!finance) {
            return 0;
        }

        double rate = vehicle.getPrice() >= 10000 ? 0.0425 : 0.0525;
        int months = vehicle.getPrice() >= 10000 ? 48 : 24;
        double monthlyRate = rate / 12;
        return (vehicle.getPrice() * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
    }

    // Implement the other methods...
}

public class Vehicle {
    private String id;
    private String make;
    private String model;
    private int year;
    private double price;
    private String color;
    private int mileage;
    private String vehicleType;
    private boolean isSold;

    public Vehicle(String id, String make, String model, int year, double price, String color, int mileage, String vehicleType) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.color = color;
        this.mileage = mileage;
        this.vehicleType = vehicleType;
        this.isSold = false;
    }

    public String getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public int getMileage() {
        return mileage;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public boolean sell() {
        if (!isSold) {
            isSold = true;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id='" + id + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", mileage=" + mileage +
                ", vehicleType='" + vehicleType + '\'' +
                '}';
    }
    }

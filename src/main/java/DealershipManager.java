import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DealershipManager {
    private Dealership dealership;
    private final DealershipFileManager fileManager;
    private boolean hasUnsavedChanges;
    private Scanner scanner;

    public void startMainMenu() {
        int userInput;
        do {
            System.out.println("Please choose an option: ");
            System.out.println("1 - Add a vehicle");
            System.out.println("2 - Sell a vehicle");
            System.out.println("3 - Find vehicles by year range");
            System.out.println("4 - Find vehicles by color");
            System.out.println("5 - Find vehicles by mileage range");
            System.out.println("6 - Find vehicles by type (car, truck, SUV, van)");
            System.out.println("7 - Save dealership data");
            System.out.println("8 - Remove a vehicle");
            System.out.println("9 - Show all vehicles"); // new menu option
            System.out.println("99 - Quit");
            userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    addVehicle();
                    break;
                case 2:
                    sellVehicle();
                    break;
                case 3:
                    findVehiclesByYearRange();
                    break;
                case 4:
                    findVehiclesByColor();
                    break;
                case 5:
                    findVehiclesByMileageRange();
                    break;
                case 6:
                    findVehiclesByType();
                    break;
                case 7:
                    save();
                    break;
                case 8:
                    removeVehicle();
                    break;
                case 9:
                    showVehicles();
                    break;
                case 99:
                    // Exit the loop
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (userInput != 99);
        save();

    }


    public void showVehicles() {
        List<Vehicle> vehicles = this.dealership.getAllVehicles();
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }
    public List<Vehicle> findVehiclesByYearRange() {
        System.out.println("Enter starting year: ");
        int startYear = scanner.nextInt();
        System.out.println("Enter ending year: ");
        int endYear = scanner.nextInt();
        List<Vehicle> vehicles = dealership.getVehiclesByYearRange(startYear, endYear);
        vehicles.forEach(System.out::println);
        return vehicles;
    }

    public List<Vehicle> findVehiclesByMileageRange() {
        System.out.println("Enter starting mileage: ");
        int startMileage = scanner.nextInt();
        System.out.println("Enter ending mileage: ");
        int endMileage = scanner.nextInt();
        List<Vehicle> vehiclesByMileageRange = dealership.getVehiclesByMileageRange(startMileage, endMileage);
        vehiclesByMileageRange.forEach(System.out::println);
        return vehiclesByMileageRange;
    }

    public void findVehiclesByColor() {
        System.out.println("Enter color: ");
        String color = scanner.next();
        List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        vehicles.forEach(System.out::println);
    }
    public List<Vehicle> findVehiclesByType() {
        System.out.println("Enter vehicle type: ");
        String type = scanner.next();
        List<Vehicle> vehicles = dealership.getVehiclesByType(type);
        vehicles.forEach(System.out::println);
        return vehicles;
    }

    public DealershipManager(String filePath, String dealershipName, String dealershipAddress, String dealershipPhone) {
        Dealership dealership1;
        this.fileManager = new DealershipFileManager(filePath);
        try {
            dealership1 = fileManager.getDealership();
        } catch (IOException e) {
            System.out.println("Error loading dealership data: " + e.getMessage());
            dealership1 = new Dealership(dealershipName, dealershipAddress, dealershipPhone);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.dealership = dealership1;
        this.hasUnsavedChanges = false;
        this.scanner = new Scanner(System.in);
    }

    public DealershipManager(Dealership dealership) {
        this.dealership = dealership;
        this.fileManager = null;
        this.hasUnsavedChanges = false;
    }
    public void populateInventory() {
        try {
            dealership = fileManager.getDealership();
        } catch (IOException e) {
            System.out.println("Error loading inventory from file: " + e.getMessage());
        }
        // You can add some vehicles here as an example
        dealership.addVehicle(new Vehicle("1", "Toyota", "Corolla", 2020, 20000, "Blue", 10000, "Sedan"));
        dealership.addVehicle(new Vehicle("2", "Honda", "Civic", 2021, 25000, "Red", 5000, "Sedan"));
        dealership.addVehicle(new Vehicle("3", "Ford", "Mustang", 2019, 30000, "Blue", 20000, "Coupe"));
        dealership.addVehicle(new Vehicle("4", "Tesla", "Model 3", 2022, 40000, "White", 1000, "Sedan"));
        dealership.addVehicle(new Vehicle("5", "Nissan", "R34", 2002, 150000, "Blue", 60000, "Car"));
        dealership.addVehicle(new Vehicle("6", "Nissan", "240Z", 1971, 40000, "Red", 30000, "Car"));
        dealership.addVehicle(new Vehicle("7", "Chevrolet", "Impala", 1969, 30000, "Black", 80000, "Car"));
        dealership.addVehicle(new Vehicle("8", "Ford", "Bronco", 1980, 20000, "Brown", 50000, "SUV"));

        System.out.println("Inventory populated successfully.");
        hasUnsavedChanges = true;
    }

    // Method to find vehicles by color
    public List<Vehicle> findVehiclesByColor(String color) {
        List<Vehicle> vehiclesByColor = new ArrayList<>();
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                vehiclesByColor.add(vehicle);
            }
        }
        return vehiclesByColor;
    }

    // Method to add a vehicle
    public void addVehicle() {
        System.out.println("Enter vehicle details:");
        // Get the vehicle details from the user
        System.out.print("ID: ");
        String id = scanner.next();
        System.out.print("Make: ");
        String make = scanner.next();
        System.out.print("Model: ");
        String model = scanner.next();
        System.out.print("Year: ");
        int year = scanner.nextInt();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        System.out.print("Color: ");
        String color = scanner.next();
        System.out.print("Mileage: ");
        int mileage = scanner.nextInt();
        System.out.print("Vehicle Type: ");
        String vehicleType = scanner.next();

        // Create a new vehicle with the details
        Vehicle vehicle = new Vehicle(id, make, model, year, price, color, mileage, vehicleType);
        // Add the vehicle to the dealership
        dealership.addVehicle(vehicle);
        System.out.println("Vehicle added successfully.");
    }

    // Method to sell a vehicle
    public void sellVehicle() {
        System.out.println("Enter the vehicle ID: ");
        String vehicleId = scanner.next();

        Vehicle vehicleToSell = null;
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getId().equals(vehicleId)) {
                vehicleToSell = vehicle;
                break;
            }
        }

        if (vehicleToSell != null) {
            dealership.removeVehicle(vehicleToSell);
            System.out.println("Vehicle " + vehicleId + " has been sold.");
        } else {
            System.out.println("Unable to sell vehicle. Please check the vehicle ID and try again.");
        }
    }

    // Method to remove a vehicle
    public void removeVehicle() {
        System.out.println("Enter the vehicle ID: ");
        String vehicleId = scanner.next();

        Vehicle vehicleToRemove = null;
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getId().equals(vehicleId)) {
                vehicleToRemove = vehicle;
                break;
            }
        }

        if (vehicleToRemove != null && dealership.removeVehicle(vehicleToRemove)) {
            System.out.println("Vehicle " + vehicleId + " has been removed.");
        } else {
            System.out.println("Unable to remove vehicle. Please check the vehicle ID and try again.");
        }
    }




    // Method to save dealership data
    public void save() {
        if (hasUnsavedChanges) {
            try {
                fileManager.saveDealership(dealership);
                hasUnsavedChanges = false;
                System.out.println("Dealership data saved successfully.");
            } catch (IOException e) {
                System.out.println("Error saving dealership data: " + e.getMessage());
            }
        } else {
            System.out.println("No changes to save.");
        }
    }
}
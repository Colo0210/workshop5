import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
        public static void main(String[] args) {
            DealershipManager manager = new DealershipManager("filePath", "DealershipName", "DealershipAddress", "DealershipPhone");
            manager.populateInventory();
            manager.startMainMenu();

        Scanner scanner = new Scanner(System.in);
        int userInput;

        do {
            System.out.println("Welcome to the dealership. Please choose an option:");
            System.out.println("1 - Add a vehicle");
            System.out.println("2 - Sell a vehicle");
            System.out.println("3 - Find vehicles by year range");
            System.out.println("4 - Find vehicles by color");
            System.out.println("5 - Find vehicles by mileage range");
            System.out.println("6 - Find vehicles by type (car, truck, SUV, van)");
            System.out.println("7 - Save dealership data");
            System.out.println("9 - Remove a vehicle");
            System.out.println("10. View vehicles");
            System.out.println("11. Create a contract");
            System.out.println("99 - Quit");
            userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    manager.addVehicle();
                    break;
                case 2:
                    manager.sellVehicle();
                    break;
                case 3:
                    List<Vehicle> vehiclesByYearRange = manager.findVehiclesByYearRange();
                    System.out.println("Vehicles in the specified year range:");
                    for (Vehicle vehicle : vehiclesByYearRange) {
                        System.out.println(vehicle);
                    }
                    break;
                case 4:
                    System.out.println("Enter the color: ");
                    String color = scanner.next();
                    List<Vehicle> vehiclesByColor = manager.findVehiclesByColor(color);
                    System.out.println("Vehicles with the specified color:");
                    for (Vehicle vehicle : vehiclesByColor) {
                        System.out.println(vehicle);
                    }
                    break;
                case 5:
                    List<Vehicle> vehiclesByMileageRange = manager.findVehiclesByMileageRange();
                    System.out.println("Vehicles in the specified mileage range:");
                    for (Vehicle vehicle : vehiclesByMileageRange) {
                        System.out.println(vehicle);
                    }
                    break;
                case 6:
                    String type = scanner.nextLine();
                    List<Vehicle> vehiclesByType = manager.findVehiclesByType();
                    System.out.println("Vehicles of the specified type:");
                    for (Vehicle vehicle : vehiclesByType) {
                        System.out.println(vehicle);
                    }
                    break;
                case 7:
                    manager.save();
                    break;
                case 9:
                    manager.removeVehicle();
                    break;
                case 10:
                    manager.showVehicles();
                    break;
                case 11:
                    createContract();
                    break;
                case 99:
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (userInput != 99);
        scanner.close();
    }
    private static void createContract() {
        // prompt user for contract type
        System.out.println("Please choose a contract type:");
        System.out.println("1. Sales Contract");
        System.out.println("2. Lease Contract");

        String contractType = scanner.nextLine();

        switch (contractType) {
            case "1":
                createSalesContract();
                break;
            case "2":
                createLeaseContract();
                break;
            default:
                System.out.println("Invalid contract type, please try again");
        }
    }

    private static void createSalesContract() {
        // get customer and vehicle details
        System.out.println("Enter customer's name:");
        String name = scanner.nextLine();
        System.out.println("Enter customer's email:");
        String email = scanner.nextLine();
        // prompt user for vehicle VIN
        System.out.println("Enter vehicle's VIN:");
        String vin = scanner.nextLine();
        Vehicle vehicle = findVehicleByVin(vin); // assuming this method is implemented elsewhere
        System.out.println("Do you want to finance the vehicle (yes/no)?");
        boolean finance = scanner.nextLine().equalsIgnoreCase("yes");

        // create a sales contract and save it
        SalesContract contract = new SalesContract(getCurrentDate(), name, email, vehicle, finance);
        ContractManager.saveContract(contract);
    }

    private static void createLeaseContract() {
        // get customer and vehicle details
        System.out.println("Enter customer's name:");
        String name = scanner.nextLine();
        System.out.println("Enter customer's email:");
        String email = scanner.nextLine();
        // prompt user for vehicle VIN
        System.out.println("Enter vehicle's VIN:");
        String vin = scanner.nextLine();
        Vehicle vehicle = findVehicleByVin(vin); // assuming this method is implemented elsewhere

        // create a lease contract and save it
        LeaseContract contract = new LeaseContract(getCurrentDate(), name, email, vehicle);
        ContractManager.saveContract(contract);
    }

    private static String getCurrentDate() {
        // return current date in the format you need
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date dateobj = new Date();
        return df.format(dateobj);
    }
}
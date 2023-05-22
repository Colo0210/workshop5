import java.util.List;
import java.util.Scanner;

public class Main {
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
            System.out.println("11. Choose contract");
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
                    // handle contract choice
                    System.out.println("Please choose a contract type:");
                    System.out.println("1. Sale");
                    System.out.println("2. Lease");

                    int contractChoice = scanner.nextInt();
                    ContractType contractType = (contractChoice == 1) ? ContractType.SALE : ContractType.LEASE;

                    // show available vehicles
                    manager.showVehicles();

                    // prompt for vehicle ID
                    System.out.println("Please enter the ID of the vehicle you want:");
                    int vehicleId = scanner.nextInt();

                    // prompt for customer info
                    System.out.println("Please enter your name:");
                    String customerName = scanner.nextLine();

                    System.out.println("Please enter your email:");
                    String customerEmail = scanner.nextLine();

                    System.out.println("Please enter your phone number:");
                    String customerPhoneNumber = scanner.nextLine();

                    // create new contract and add it to the customer
                    Vehicle chosenVehicle = dealership.getVehicleById(vehicleId);
                    Customer customer = new Customer(customerName, customerEmail, customerPhoneNumber);
                    Contract contract = new Contract(chosenVehicle, customer, contractType);
                    customer.addContract(contract);

                    // remove the chosen vehicle from the dealership
                    dealership.removeVehicle(chosenVehicle);

                    break;
                case 99:
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (userInput != 99);
        scanner.close();
    }
}
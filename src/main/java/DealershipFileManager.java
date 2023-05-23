import java.io.*;
import java.util.ArrayList;

public class DealershipFileManager {
    private static String filePath;
    private static String tempFilePath;

    public DealershipFileManager(String filePath) {
        this.filePath = filePath;
        this.tempFilePath = "temp_" + filePath;
    }

    public static void saveToFile(Dealership dealership, ArrayList<Customer> customers, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(dealership);
            oos.writeObject(customers);
        } catch (IOException ex) {
            System.err.println("Error writing to file: " + ex.getMessage());
        }
    }

    public static Dealership loadFromFile(String filename) {
        Dealership dealership;
        ArrayList<Customer> customers;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            dealership = (Dealership) ois.readObject();
            customers = (ArrayList<Customer>) ois.readObject();

            // load the customers to the dealership's contractManager
            for (Customer customer : customers) {
                for (Contract contract : customer.getContracts()) {
                    dealership.getContractManager().addContract(contract);
                }
            }

        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Error reading from file: " + ex.getMessage());
            return null;
        }

        return dealership;
    }
    public Dealership getDealership() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String dealershipInfo = reader.readLine();
            String[] dealershipDetails = dealershipInfo.split("\\|");

            Dealership dealership = new Dealership(
                    dealershipDetails[0],
                    dealershipDetails[1],
                    dealershipDetails[2]
            );

            String vehicleInfo;
            while ((vehicleInfo = reader.readLine()) != null) {
                String[] vehicleDetails = vehicleInfo.split("\\|");

                String id = vehicleDetails[0];
                String vin = vehicleDetails[1];
                String make = vehicleDetails[2];
                String model = vehicleDetails[3];
                int year = Integer.parseInt(vehicleDetails[4]);
                double price = Double.parseDouble(vehicleDetails[5]);
                String color = vehicleDetails[6];
                int mileage = Integer.parseInt(vehicleDetails[7]);
                String vehicleType = vehicleDetails[8];

                Vehicle vehicle = new Vehicle(id, vin,  make, model, year, price, color, mileage, vehicleType);
                dealership.addVehicle(vehicle);
            }
            return dealership;

        } catch (FileNotFoundException e) {
            throw new IOException("File not found: " + filePath, e);
        } catch (IOException e) {
            throw new IOException("Error reading from file: " + filePath, e);
        } catch (NumberFormatException e) {
            throw new IOException("Error parsing vehicle data", e);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IOException("Invalid file format", e);
        }
    }

    public static void saveDealership(Dealership dealership) throws IOException {
        File file = new File(filePath);
        File tempFile = new File(tempFilePath);

        if (!file.exists()) {
            file.createNewFile();
        }

        // Write dealership and vehicles data to temp file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            writer.newLine();

            for (Vehicle vehicle : dealership.getAllVehicles()) {
                writer.write(vehicleToString(vehicle));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new IOException("Error writing to temp file: " + tempFilePath, e);
        }

        // Replace original file with updated temp file
        if (!file.delete()) {
            throw new IOException("Could not delete original file");
        }

        if (!tempFile.renameTo(file)) {
            throw new IOException("Could not rename temp file");
        }
    }


    private static String vehicleToString(Vehicle vehicle) {
        return vehicle.getId() + "|" +
                vehicle.getMake() + "|" +
                vehicle.getModel() + "|" +
                vehicle.getYear() + "|" +
                vehicle.getPrice() + "|" +
                vehicle.getColor() + "|" +
                vehicle.getMileage() + "|" +
                vehicle.getVehicleType();
    }
}
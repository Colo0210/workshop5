import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;
    private ContractManager contractManager;  // Contract manager

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
        this.contractManager = new ContractManager();
    }

    public List<Vehicle> getVehiclesByYearRange(int minYear, int maxYear) {
        return inventory.stream()
                .filter(v -> v.getYear() >= minYear && v.getYear() <= maxYear)
                .collect(Collectors.toList());
    }

    public ContractManager getContractManager() {
        return this.contractManager;
    }

    // A method to create a contract, adding it to both the customer and the contract manager
    public boolean createContract(Customer customer, Vehicle vehicle, ContractType contractType) {
        Contract contract;

        // depending on the type of contract, we create the correct one
        if (contractType == ContractType.SALE) {
            contract = new SalesContract(contract.date, contract.getCustomerName(), customerEmail, vehicle, boolean finance);
        } else if (contractType == ContractType.LEASE) {
            contract = new LeaseContract(String date,
                    String customerName, String customerEmail, Vehicle vehicle);
        } else {
            return false;
        }

        // we add the contract to the customer and to the contract manager
        customer.addContract(contract);
        contractManager.addContract(contract);

        return true;
    }

    // A method to find a contract by ID in the dealership. It uses the contract manager for this.
    public Optional<Contract> findContractById(String contractId) {
        return Optional.ofNullable(contractManager.findContractById(contractId));
    }
    public Vehicle findVehicleByVin(String vin) {
        // assuming you have a list of vehicles named 'vehicles'
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVin().equals(vin)) {
                return vehicle;
            }
        }
        return null;
    }


    public List<Vehicle> getVehiclesByMileageRange(int startMileage, int endMileage) {
        return inventory.stream()
                .filter(v -> v.getMileage() >= startMileage && v.getMileage() <= endMileage)
                .collect(Collectors.toList());
    }

    public void addVehicle(Vehicle vehicle) {inventory.add(vehicle);}{
        Vehicle[] vehicles = new Vehicle[0];
        for(Vehicle vehicle :vehicles)

    {
        if (vehicle.getId().equals(vehicle.getId())) {
            System.out.println("A vehicle with this ID already exists. Please enter a unique ID.");
        }
    }
}


    public boolean removeVehicle(Vehicle vehicle) {
        return inventory.remove(vehicle);
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        return inventory.stream()
                .filter(v -> v.getPrice() >= min && v.getPrice() <= max)
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        return inventory.stream()
                .filter(v -> v.getMake().equalsIgnoreCase(make) && v.getModel().equalsIgnoreCase(model))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        return inventory.stream()
                .filter(v -> v.getYear() >= min && v.getYear() <= max)
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        return inventory.stream()
                .filter(v -> v.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        return inventory.stream()
                .filter(v -> v.getMileage() >= min && v.getMileage() <= max)
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        return inventory.stream()
                .filter(v -> v.getVehicleType().equalsIgnoreCase(vehicleType))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getAllVehicles() {
        return inventory;
    }
    public void showVehicles() {
        if (inventory.size() == 0) {
            System.out.println("No vehicles currently in the dealership.");
            return;
        }

        for (Vehicle vehicle : inventory) {
            System.out.println(vehicle);
        }
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
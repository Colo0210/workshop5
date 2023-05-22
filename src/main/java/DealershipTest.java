public class DealershipTest {

    public static void main(String[] args) {
        testVehicle();
        testDealership();
        testDealershipManager();
    }

    public void testContractCreation() {
        Dealership dealership = new Dealership("Test Dealership", "123 Test St", "555-555-5555");
        Customer customer = new Customer("John Doe");
        Vehicle vehicle = new Vehicle("1","Ford", "Mustang", 2015, 15000.0, "Blue", 50000, "Car");
        dealership.addVehicle(vehicle);

        // Creating a new sale contract
        SalesContract saleContract = new SalesContract(Contract(vehicle, customer, dealership));
        customer.addContract(saleContract);
        dealership.getContractManager().addContract(saleContract);

        assert(customer.getContracts().contains(saleContract));
        assert(dealership.getContractManager().getContracts().contains(saleContract));
    }

    public void testContractRemoval() {
        Dealership dealership = new Dealership("Test Dealership", "123 Test St", "555-555-5555");
        Customer customer = new Customer("John Doe");
        Vehicle vehicle = new Vehicle("Ford", "Mustang", 2015, "Blue", 50000, 15000.0, "Car");
        dealership.addVehicle(vehicle);

        // Creating a new sale contract
        SalesContract saleContract = new SalesContract(vehicle, customer, dealership);
        customer.addContract(saleContract);
        dealership.getContractManager().addContract(saleContract);

        // Removing the contract
        customer.removeContract(saleContract);
        dealership.getContractManager().removeContract(saleContract);

        assert(!customer.getContracts().contains(saleContract));
        assert(!dealership.getContractManager().getContracts().contains(saleContract));
    }
    public static void testVehicle() {
        try {
            Vehicle vehicle = new Vehicle("1", "Toyota", "Camry", 2002, 25000, "White", 30000, "Car");
            assert vehicle.getId().equals("1");
            assert vehicle.getMake().equals("Toyota");
            assert vehicle.getModel().equals("Camry");
            assert vehicle.getYear() == 2002;
            assert vehicle.getPrice() == 25000;
            assert vehicle.getColor().equals("White");
            assert vehicle.getMileage() == 30000;
            assert vehicle.getVehicleType().equals("Car");

            System.out.println("All Vehicle tests passed!");

        } catch (Exception e) {
            System.out.println("An error occurred while testing Vehicle: " + e.getMessage());
        }
    }

    public static void testDealership() {
        try {
            Dealership dealership = new Dealership("Awesome Car Dealership", "123 Street, LA", "2144698446");
            assert dealership.getName().equals("Awesome Car Dealership");
            assert dealership.getAddress().equals("123 Street, LA");

            System.out.println("All Dealership tests passed!");

        } catch (Exception e) {
            System.out.println("An error occurred while testing Dealership: " + e.getMessage());
        }
    }

    public static void testDealershipManager() {
        try {
            Dealership dealership = new Dealership("Awesome Car Dealership", "123 Street, LA", "2144698446");
            DealershipManager manager = new DealershipManager(dealership);

            Vehicle vehicle = new Vehicle("1", "Toyota", "Camry", 2002, 25000, "White", 30000, "Car");

            System.out.println("All DealershipManager tests passed!");

        } catch (Exception e) {
            System.out.println("An error occurred while testing DealershipManager: " + e.getMessage());
        }
    }
}
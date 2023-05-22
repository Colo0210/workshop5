import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class Customer {
    private String name;
    private String address;
    private String email;
    private List<Contract> contracts;  // list to store contract objects

    // Constructor initializes the customer details and also the contracts list
    public Customer(String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.contracts = new ArrayList<>();
    }

    // Getter and setter methods
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Method to get all contracts of this customer
    public List<Contract> getContracts() {
        return this.contracts;
    }

    // Method to add a new contract to this customer's contracts list
    public void addContract(Contract contract) {
        this.contracts.add(contract);
    }

    // Method to remove a contract from this customer's contracts list
    public boolean removeContract(Contract contract) {
        return this.contracts.remove(contract);
    }

    // Method to find a contract by its ID
    public Optional<Contract> findContractById(String contractId) {
        for (Contract contract : contracts) {
            if (contract.getContractId().equals(contractId)) {
                return Optional.of(contract);
            }
        }
        return Optional.empty();  // if no contract with the given ID was found
    }
}
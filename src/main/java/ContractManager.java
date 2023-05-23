import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ContractManager {

    private static final String CONTRACTS_FILE = "contracts.txt";
    private static List<Contract> contracts = new ArrayList<>(); // Create a static list to store contracts

    public static void addContract(Contract contract) {
        contracts.add(contract); // Add contract to the list
    }

    public static void saveContract(Contract contract) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(CONTRACTS_FILE, true));
            StringBuilder sb = new StringBuilder();

            if (contract instanceof SalesContract) {
                SalesContract salesContract = (SalesContract) contract;
                sb.append("SALE|")
                        .append(contract.getDate()).append("|")
                        .append(contract.getCustomerName()).append("|")
                        .append(contract.getCustomerEmail()).append("|")
                        .append(contract.getVehicle().getVin()).append("|")
                        .append(contract.getVehicle().getYear()).append("|")
                        .append(contract.getVehicle().getMake()).append("|")
                        .append(contract.getVehicle().getModel()).append("|")
                        .append(contract.getVehicle().getVehicleType()).append("|")
                        .append(contract.getVehicle().getColor()).append("|")
                        .append(salesContract.getSalesTaxAmount()).append("|")
                        .append(salesContract.getTotalPrice()).append("|")
                        .append(salesContract.getRecordingFee()).append("|")
                        .append(salesContract.getProcessingFee()).append("|")
                        .append(salesContract.getTotalPrice()).append("|")
                        .append(salesContract.isFinance() ? "YES" : "NO").append("|")
                        .append(new DecimalFormat("0.00").format(salesContract.getMonthlyPayment()));
            } else if (contract instanceof LeaseContract) {
                LeaseContract leaseContract = (LeaseContract) contract;
                sb.append("LEASE|")
                        .append(contract.getDate()).append("|")
                        .append(contract.getCustomerName()).append("|")
                        .append(contract.getCustomerEmail()).append("|")
                        .append(contract.getVehicle().getVin()).append("|")
                        .append(contract.getVehicle().getYear()).append("|")
                        .append(contract.getVehicle().getMake()).append("|")
                        .append(contract.getVehicle().getModel()).append("|")
                        .append(contract.getVehicle().getVehicleType()).append("|")
                        .append(contract.getVehicle().getColor()).append("|")
                        .append(leaseContract.getExpectedEndingValue()).append("|")
                        .append(leaseContract.getLeaseFee()).append("|")
                        .append(leaseContract.getTotalPrice()).append("|")
                        .append(new DecimalFormat("0.00").format(leaseContract.getMonthlyPayment()));
            }
            writer.write(sb.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static Contract findContractById(String contractId) {
        for (Contract contract : contracts) {
            if (contract.getContractId().equals(contractId)) {
                return contract; // Return the contract if the ID matches
            }
        }
        return null; // Return null if no contract with the given ID is found
    }
}

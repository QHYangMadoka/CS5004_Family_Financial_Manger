package final_project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages lists of various asset and debt types and provides functionality to load and save these items to/from CSV files.
 */
public class AssetAndDebtManager {
    private static AssetAndDebtManager instance;
    private List<Bond> bondList = new ArrayList<>();
    private List<Stock> stockList = new ArrayList<>();
    private List<RealEstate> realEstateList = new ArrayList<>();
    private List<Debt> debtList = new ArrayList<>();


    private AssetAndDebtManager() {}

    public static synchronized AssetAndDebtManager getInstance() {
        if (instance == null) {
            instance = new AssetAndDebtManager();
        }
        return instance;
    }

    /**
     * Saves the list of bonds to a CSV file named "Bond.csv".
     * @throws IOException If there is an error writing to the file.
     */
    public void saveBondToCSV() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Bond.csv"));
        writer.write("Amount,InterestRate,Volatility,Maturity,Coupon,Description\n");
        for (Bond bond : bondList) {
            writer.write(String.format("%f,%f,%f,%d,%f,%s\n",
                    bond.getAmount(), bond.getInterestRate(), bond.getVolatility(),
                    bond.getMaturity(), bond.getCoupon(), bond.getDescription()));
        }
        writer.close();
    }

    /**
     * Saves the list of stocks to a CSV file named "Stock.csv".
     * @throws IOException If there is an error writing to the file.
     */
    public void saveStockToCSV() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Stock.csv"));
        // Add Dividend Growth Rate to the header
        writer.write("Amount,InterestRate,Volatility,Dividend,DividendGrowthRate,Description\n");
        for (Stock stock : stockList) {
            // Format string to include dividendGrowthRate
            writer.write(String.format("%f,%f,%f,%f,%f,%s\n",
                stock.getAmount(), 
                stock.getInterestRate(), 
                stock.getVolatility(),
                stock.getDividend(),
                stock.getDividendGrowthRate(), // Include the growth rate
                stock.getDescription()
            ));
        }
        writer.close();
    }

    /**
     * Saves the list of real estate to a CSV file named "RealEstate.csv".
     * @throws IOException If there is an error writing to the file.
     */
    public void saveRealEstateToCSV() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("RealEstate.csv"));
        writer.write("Amount,InterestRate,Volatility,NetOperatingIncome,Description\n");
        for (RealEstate realEstate : realEstateList) {
            writer.write(String.format("%f,%f,%f,%f,%s\n",
                    realEstate.getAmount(), realEstate.getInterestRate(), realEstate.getVolatility(),
                    realEstate.getNetOperatingIncome(), realEstate.getDescription()));
        }
        writer.close();
    }

    /**
     * Saves the list of debts to a CSV file named "Debt.csv".
     * @throws IOException If there is an error writing to the file.
     */
    public void saveDebtToCSV() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Debt.csv"));
        writer.write("Amount,InterestRate,IsOneTime,Time,Category,Description\n");
        for (Debt debt : debtList) {
            writer.write(String.format("%f,%f,%b,%d,%s,%s\n",
                    debt.getAmount(), debt.getInterestRate(), debt.isOneTime(),
                    debt.getTime(), debt.getCategory().toString(), debt.getDescription()));
        }
        writer.close();
    }

    // Getter methods for each list
    public List<Bond> getBondList() {
        return bondList;
    }

    public List<Stock> getStockList() {
        return stockList;
    }

    public List<RealEstate> getRealEstateList() {
        return realEstateList;
    }

    public List<Debt> getDebtList() {
        return debtList;
    }

    // Methods to add assets and debts to the lists
    public void addBond(Bond bond) throws IOException {
        bondList.add(bond);
        saveBondToCSV();
    }

    public void addStock(Stock stock) throws IOException {
        stockList.add(stock);
        saveStockToCSV();
    }

    public void addRealEstate(RealEstate realEstate) throws IOException {
        realEstateList.add(realEstate);
        saveRealEstateToCSV();
    }

    public void addDebt(Debt debt) throws IOException {
        debtList.add(debt);
        saveDebtToCSV();
    }

    // Methods to delete assets and debts from the lists
    public boolean deleteDebt(String description) throws IOException {
        boolean exists = debtList.stream().anyMatch(debt -> debt.getDescription().equals(description));
        if (exists) {
            debtList = debtList.stream().filter(debt -> !debt.getDescription().equals(description)).collect(Collectors.toList());
            saveDebtToCSV();
        }
        return exists;
    }
    
    public boolean deleteBond(String description) throws IOException {
        boolean exists = bondList.stream().anyMatch(bond -> bond.getDescription().equals(description));
        if (exists) {
            bondList = bondList.stream().filter(bond -> !bond.getDescription().equals(description)).collect(Collectors.toList());
            saveBondToCSV();
        }
        return exists;
    }
    
    public boolean deleteStock(String description) throws IOException {
        boolean exists = stockList.stream().anyMatch(stock -> stock.getDescription().equals(description));
        if (exists) {
            stockList = stockList.stream().filter(stock -> !stock.getDescription().equals(description)).collect(Collectors.toList());
            saveStockToCSV();
        }
        return exists;
    }
    
    public boolean deleteRealEstate(String description) throws IOException {
        boolean exists = realEstateList.stream().anyMatch(realEstate -> realEstate.getDescription().equals(description));
        if (exists) {
            realEstateList = realEstateList.stream().filter(realEstate -> !realEstate.getDescription().equals(description)).collect(Collectors.toList());
            saveRealEstateToCSV();
        }
        return exists;
    }

    public void loadBondsFromCSV() throws IOException {
        File file = new File("Bond.csv");
        if (!file.exists()) {
            return;
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine(); // Skip header
        bondList.clear();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 6) {
                Bond bond = new Bond(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]),
                                     Integer.parseInt(parts[3]), Double.parseDouble(parts[4]), parts[5]);
                bondList.add(bond);
            }
        }
        reader.close();
    }
    
    public void loadStocksFromCSV() throws IOException {
        File file = new File("Stock.csv");
        if (!file.exists()) {
            return;
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine(); // Skip header
        stockList.clear();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 6) {  // Now expecting 6 parts including dividendGrowthRate
                Stock stock = new Stock(
                    Double.parseDouble(parts[0]), // Amount
                    Double.parseDouble(parts[1]), // Interest Rate
                    Double.parseDouble(parts[2]), // Volatility
                    Double.parseDouble(parts[3]), // Dividend
                    Double.parseDouble(parts[4]), // Dividend Growth Rate
                    parts[5]                     // Description
                );
                stockList.add(stock);
            }
        }
        reader.close();
    }
    
    public void loadRealEstateFromCSV() throws IOException {
        File file = new File("RealEstate.csv");
        if (!file.exists()) {
            return;
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine(); // Skip header
        realEstateList.clear();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 5) {
                RealEstate realEstate = new RealEstate(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]),
                                                      Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), parts[4]);
                realEstateList.add(realEstate);
            }
        }
        reader.close();
    }
    
    public void loadDebtsFromCSV() throws IOException {
        File file = new File("Debt.csv");
        if (!file.exists()) {
            return;
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine(); // Skip header
        debtList.clear();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 6) {
                Debt debt = new Debt(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), Boolean.parseBoolean(parts[2]),
                                     Integer.parseInt(parts[3]), DebtCategory.valueOf(parts[4].toUpperCase()), parts[5]);
                debtList.add(debt);
            }
        }
        reader.close();
    }
}

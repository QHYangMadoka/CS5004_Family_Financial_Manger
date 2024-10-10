package final_project;

/**
 * Represents a balance sheet for a family, detailing all members' income and the family's assets and liabilities.
 */
public class BalanceSheet {
    private Family family;
    private AssetAndDebtManager assetManager;
    private double appliedInterestRate;

    /**
     * Constructs a BalanceSheet with references to a Family object and an AssetAndDebtManager object.
     * @param family The Family object containing family members' information.
     * @param assetManager The AssetAndDebtManager object managing the assets and debts.
     * @param appliedInterestRate discount rate to calculate present value of Asset and Debt
     */
    public BalanceSheet(Family family, AssetAndDebtManager assetManager, double appliedInterestRate) {
        this.family = family;
        this.assetManager = assetManager;
        this.appliedInterestRate = appliedInterestRate;
    }

    public void setAppliedInterestRate(double rate){
        this.appliedInterestRate = rate;
    }

    public double getAppliedInterestRate(){
        return appliedInterestRate;
    }

    /**
     * Prints the total assets and liabilities of the family.
     */
    public void printAssetsAndLiabilities() {
        System.out.println("Assets and Liabilities:");
        double totalAssets = calculateTotalAssets();
        double totalLiabilities = calculateTotalLiabilities();
        System.out.println("Total Assets: " + totalAssets);
        System.out.println("Total Liabilities: " + totalLiabilities);
    }

    /**
     * Calculates the present value of a specific debt.
     * @param debt The debt object to calculate.
     * @return The present value of the debt.
     */
    public double calculateDebtPV(Debt debt) {
        int years = debt.getTime() - 2024; // Calculates the number of years from the current year to the maturity year of the debt
        if (debt.isOneTime()) {
            return debt.getAmount() / Math.pow(1 + appliedInterestRate, years);
        } else {
            double totalPV = 0;
            double periodicPayment = debt.getAmount() * debt.getInterestRate(); // Assumes this calculates the periodic payment
            for (int i = 1; i <= years; i++) {
                totalPV += periodicPayment / Math.pow(1 + appliedInterestRate, i);
            }
            totalPV += debt.getAmount() / Math.pow(1 + appliedInterestRate, years); // Discounts the principal
            return totalPV;
        }
    }

    /**
     * Calculates the total present value of all assets.
     * @return The total present value of assets.
     */
    public double calculateTotalAssets() {
        double bondsTotal = assetManager.getBondList().stream().mapToDouble(Bond::calculateReturn).sum();
        double stocksTotal = assetManager.getStockList().stream().mapToDouble(Stock::calculateReturn).sum();
        double realEstateTotal = assetManager.getRealEstateList().stream().mapToDouble(RealEstate::calculateReturn).sum();
        return bondsTotal + stocksTotal + realEstateTotal;
    }

    /**
     * Calculates the total present value of all liabilities.
     * @return The total present value of liabilities.
     */
    public double calculateTotalLiabilities() {
        return assetManager.getDebtList().stream().mapToDouble(this::calculateDebtPV).sum();
    }

    /**
     * Calculates the net present value of the family's assets and liabilities.
     * @return The net present value.
     */
    public double calculateNetPresentValue() {
        double totalAssets = calculateTotalAssets();
        double totalLiabilities = calculateTotalLiabilities();
        return totalAssets - totalLiabilities;
    }


    public double calculateTotalIncomePV() {
        double totalDiscountedIncome = 0;
        for (Person member : family.getFamilyMembers()) {
            double annualIncome = member.getMonthlyIncome() * 12;
            int workingYears = Math.max(0, member.getBirthYear() + member.getLifeSpan() - 2024 - 20);
            if (annualIncome > 0 && workingYears > 0) {
                totalDiscountedIncome += annualIncome / Math.pow(1 + appliedInterestRate, workingYears);
            }
        }
        return totalDiscountedIncome;
    }


    public double calculateTotalExpenditurePV() {
        double totalDiscountedExpenditure = 0;
        for (Person member : family.getFamilyMembers()) {
            double annualExpenditure = member.getMonthlyExpenditure() * 12;
            int yearsToLive = Math.max(0, member.getBirthYear() + member.getLifeSpan() - 2024);
            if (annualExpenditure > 0) {
                totalDiscountedExpenditure += annualExpenditure / Math.pow(1 + appliedInterestRate, yearsToLive);
            }
        }
        return totalDiscountedExpenditure;
    }


        /**
     * Prints detailed income statements for each family member, including discounted values of future income.
     */
    public void printIncomeStatement() {
        System.out.println("Detailed Family Income Report:");
        double totalDiscountedIncome = 0;
        for (Person member : family.getFamilyMembers()) {
            double annualIncome = member.getMonthlyIncome() * 12;
            int workingYears = member.getBirthYear() + member.getLifeSpan() - 2024 - 20;  // 假设退休前20年
            if (annualIncome > 0 && workingYears > 0) {
                double discountedIncome = annualIncome / Math.pow(1 + appliedInterestRate, workingYears);
                totalDiscountedIncome += discountedIncome;
                System.out.println(member.getName() + ": " + discountedIncome);
            }
        }
        System.out.println("Total discounted income: " + totalDiscountedIncome);
    }

    /**
     * Prints detailed expenditure statements for each family member, including discounted values of future expenditures.
     */
    public void printExpenditureStatement() {
        System.out.println("Detailed Family Expenditure Report:");
        double totalDiscountedExpenditure = 0;
        for (Person member : family.getFamilyMembers()) {
            double annualExpenditure = member.getMonthlyExpenditure() * 12;
            int yearsToLive = member.getBirthYear() + member.getLifeSpan() - 2024;
            if (annualExpenditure > 0 && yearsToLive > 0) {
                double discountedExpenditure = annualExpenditure / Math.pow(1 + appliedInterestRate, yearsToLive);
                totalDiscountedExpenditure += discountedExpenditure;
                System.out.println(member.getName() + ": " + discountedExpenditure);
            }
        }
        System.out.println("Total discounted expenditure: " + totalDiscountedExpenditure);
    }

    /**
     * Prints details of all assets, including each asset's description and present value.
     */
    public void printAssetsDetails() {
        System.out.println("Asset:");
    
  
        System.out.println("Bond:");
        assetManager.getBondList().forEach(bond -> {
            double pv = bond.calculateReturn(); 
            System.out.println(bond.getDescription() + ": " + pv);
        });

        System.out.println("Stock:");
        assetManager.getStockList().forEach(stock -> {
            double pv = stock.calculateReturn(); 
            System.out.println(stock.getDescription() + ": " + pv);
        });
    
        System.out.println("RealEstate");
        assetManager.getRealEstateList().forEach(realEstate -> {
            double pv = realEstate.calculateReturn(); 
            System.out.println(realEstate.getDescription() + ": " + pv);
        });
    }

    /**
     * Prints details of all liabilities, including each liability's description and present value.
     */
    public void printLiabilitiesDetails() {
        System.out.println("Debt:");
        assetManager.getDebtList().forEach(debt -> {
            double pv = calculateDebtPV(debt);
            System.out.println(debt.getDescription() + ": " + pv);
        });
    }

    /**
     * Calculates and prints the net present value of the family's finances.
     */
    public void printNetPresentValue() {
        double totalIncomePV = calculateTotalIncomePV();
        double totalExpenditurePV = calculateTotalExpenditurePV();
        double totalAssetsPV = calculateTotalAssets();
        double totalLiabilitiesPV = calculateTotalLiabilities();
        double netPresentValue = totalIncomePV + totalAssetsPV - totalLiabilitiesPV - totalExpenditurePV;
        System.out.println("Family Net Value: " + netPresentValue);
    }

    public Family getFamily() {
        return this.family;
    }





}

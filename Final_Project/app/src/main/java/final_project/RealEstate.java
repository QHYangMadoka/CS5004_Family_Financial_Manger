package final_project;

/**
 * Represents a real estate asset.
 * The return on this asset is calculated as the present value of an infinite stream of net operating incomes (NOI),
 * added to the current market value of the property.
 */
public class RealEstate extends Asset {
    private double netOperatingIncome;  // Annual Net Operating Income, can be negative

    /**
     * Constructs a RealEstate instance with specified financial parameters.
     * @param amount The current market value of the property, must not be negative.
     * @param interestRate The discount rate used for calculating the present value of future incomes, must not be negative.
     * @param volatility The volatility associated with the property's value, must not be negative.
     * @param netOperatingIncome The annual income generated by the property, can be negative.
     * @param description A description of the property.
     * @throws IllegalArgumentException if any parameter is invalid.
     */
    public RealEstate(double amount, double interestRate, double volatility, double netOperatingIncome, String description) {
        super(amount, interestRate, volatility, description);
        this.netOperatingIncome = netOperatingIncome;
    }

    /**
     * Calculates the expected return of the real estate asset.
     * The return is calculated as the sum of the present value of an infinite stream of NOI and the property's market value.
     * @return The total calculated return.
     */
    @Override
    public double calculateReturn() {
        // Calculate the present value of an infinite stream of NOI
        double presentValueOfNOI = netOperatingIncome / getInterestRate();  // Using formula: PV = NOI / r

        // The return is the sum of the present value of NOI and the amount (current market value)
        return presentValueOfNOI + getAmount();
    }

    public double getNetOperatingIncome() {
        return netOperatingIncome;
    }


}

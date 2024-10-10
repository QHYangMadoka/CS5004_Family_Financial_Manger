package final_project;

/**
 * Abstract class Asset representing a financial asset.
 * This class provides the basic framework for handling asset properties and calculating returns.
 * The actual return calculation must be implemented by subclasses.
 */
public abstract class Asset {
    private double amount;
    private double interestRate;
    private double volatility;
    private final String description;


    /**
     * Constructs an Asset with specified parameters.
     * @param amount The monetary value of the asset, must not be negative.
     * @param interestRate The rate used for discounting or growth calculations, must not be negative.
     * @param volatility The measure of the asset's price fluctuations, must not be negative.
     * @param description A descriptive text about the asset, must not be null or empty.
     * @throws IllegalArgumentException if any parameter is invalid.
     */
    public Asset(double amount, double interestRate, double volatility, String description) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        if (interestRate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative.");
        }
        if (volatility < 0) {
            throw new IllegalArgumentException("Volatility cannot be negative.");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        this.amount = amount;
        this.interestRate = interestRate;
        this.volatility = volatility;
        this.description = description;
    }


    /**
     * Sets the monetary amount of the asset.
     * @param amount The monetary value to set, must not be negative.
     */
    public void setAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        this.amount = amount;
    }

    /**
     * Returns the monetary amount of the asset.
     * @return The monetary value of the asset.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the interest rate of the asset.
     * @param interestRate The interest rate to set, must not be negative.
     */
    public void setInterestRate(double interestRate) {
        if (interestRate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative.");
        }
        this.interestRate = interestRate;
    }

    /**
     * Returns the interest rate of the asset.
     * @return The interest rate of the asset.
     */
    public double getInterestRate() {
        return interestRate;
    }

    /**
     * Sets the volatility of the asset.
     * @param volatility The volatility to set, must not be negative.
     */
    public void setVolatility(double volatility) {
        if (volatility < 0) {
            throw new IllegalArgumentException("Volatility cannot be negative.");
        }
        this.volatility = volatility;
    }

    /**
     * Returns the volatility of the asset.
     * @return The volatility of the asset.
     */
    public double getVolatility() {
        return volatility;
    }

    /**
     * Abstract method to calculate and return the expected return of the asset.
     * Implementations must provide their own calculation mechanism based on the asset type.
     * @return the expected return as a double.
     */
    public abstract double calculateReturn();




    public String getDescription() {
        return description;
    }
}
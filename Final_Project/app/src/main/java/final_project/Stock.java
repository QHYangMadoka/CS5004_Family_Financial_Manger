package final_project;

/**
 * Represents a stock as a financial asset.
 * The return on the stock is calculated using the Dividend Discount Model (DDM),
 * which considers the present value of infinite future dividend payments.
 */
public class Stock extends Asset {
    private double dividend;  // Annual dividend payment
    private double dividendGrowthRate;  // Expected annual growth rate of the dividend

    /**
     * Constructs a Stock instance with specified financial parameters.
     * @param amount The market value of the stock, must not be negative.
     * @param interestRate The discount rate, used for present value calculations, must not be negative.
     * @param volatility The volatility of the stock's price, must not be negative.
     * @param dividend The annual dividend paid by the stock, must not be negative.
     * @param description A description of the stock.
     * @throws IllegalArgumentException if any parameter is invalid.
     */
    public Stock(double amount, double interestRate, double volatility, double dividend,double dividendGrowth, String description) {
        super(amount, interestRate, volatility, description);
        if (dividend < 0) {
            throw new IllegalArgumentException("Dividend cannot be negative.");
        }
        this.dividend = dividend;
        this.dividendGrowthRate = dividendGrowth;
    }

    /**
     * Calculates the expected return of the stock using the Dividend Discount Model.
     * @return The calculated intrinsic value based on dividends or the current amount, whichever is higher.
     */
    @Override
    public double calculateReturn() {
        if (getInterestRate() <= dividendGrowthRate) {
            throw new IllegalArgumentException("Discount rate must be greater than the dividend growth rate to avoid infinite intrinsic value.");
        }
        // Calculate the present value of infinite future dividends
        double intrinsicValue = dividend / (getInterestRate() - dividendGrowthRate);

        // Return the higher of the intrinsic value or the current market value (amount)
        return Math.max(intrinsicValue, getAmount());
    }

    public double getDividend() {
        return dividend;
    }

    public double getDividendGrowthRate() {
        return dividendGrowthRate;
    }
}

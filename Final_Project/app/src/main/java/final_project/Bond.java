package final_project;

/**
 * Represents a bond as a financial asset.
 * This class calculates the net present value of the bond based on annual coupon payments
 * and the principal amount repayment at maturity.
 */
public class Bond extends Asset {
    private int maturity; // The maturity year of the bond
    private final double coupon; // The fixed coupon rate of the bond

    /**
     * Constructs a Bond instance with specified financial parameters.
     * @param amount The principal or face value of the bond, must not be negative.
     * @param interestRate The discount rate used for present value calculations, must not be negative.
     * @param volatility The volatility of the bond's price, must not be negative.
     * @param maturity The year in which the bond matures.
     * @param coupon The fixed annual coupon payment, must not be negative.
     * @param description A description of the bond.
     * @throws IllegalArgumentException if any parameter is invalid.
     */
    public Bond(double amount, double interestRate, double volatility, int maturity, double coupon, String description) {
        super(amount, interestRate, volatility, description);
        if (maturity < 2024) {
            throw new IllegalArgumentException("Maturity year must not be earlier than 2024.");
        }
        if (coupon < 0) {
            throw new IllegalArgumentException("Coupon cannot be negative.");
        }
        this.maturity = maturity;
        this.coupon = coupon;
    }

    /**
     * Calculates the expected return of the bond as the net present value (NPV) of all future cash flows.
     * @return The calculated net present value.
     */
    @Override
    public double calculateReturn() {
        double npv = 0.0;
        int currentYear = 2024;
        int yearsToMaturity = maturity - currentYear;

        // Calculate the present value of each year's coupon payment
        for (int year = 1; year <= yearsToMaturity; year++) {
            double discountFactor = Math.pow(1 + getInterestRate(), year);
            npv += coupon / discountFactor;
        }

        // Add the present value of the principal amount plus the final coupon payment at maturity
        double finalYearDiscount = Math.pow(1 + getInterestRate(), yearsToMaturity);
        npv += (coupon + getAmount()) / finalYearDiscount;

        return npv;
    }

    public int getMaturity() {
        return maturity;
    }

    public double getCoupon() {
        return coupon;
    }
}

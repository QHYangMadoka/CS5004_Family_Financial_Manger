package final_project;

/**
 * Represents a debt record with details about the amount, interest rate, type, and payment time.
 */
public class Debt {
    private double amount;
    private double interestRate;
    private boolean isOneTime;
    private int time;  // Time is now an integer representing the year, must not be less than 2024.
    private DebtCategory category;
    private final String description;  // Description of the debt, not changeable after being set.

    /**
     * Constructs a new Debt instance with specified parameters.
     * @param amount The principal amount of the debt, must not be negative.
     * @param interestRate The interest rate of the debt, must not be negative.
     * @param isOneTime A boolean value indicating if the debt is a one-time payment.
     * @param time The year associated with the debt, must not be less than 2024.
     * @param category The category of the debt.
     * @param description The description of the debt, must not be null or empty.
     * @throws IllegalArgumentException if any parameter is invalid or description is null or empty.
     */
    public Debt(double amount, double interestRate, boolean isOneTime, int time, DebtCategory category, String description) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        if (interestRate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative.");
        }
        if (time < 2024) {
            throw new IllegalArgumentException("Time must not be earlier than 2024.");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        this.amount = amount;
        this.interestRate = interestRate;
        this.isOneTime = isOneTime;
        this.time = time;
        this.category = category;
        this.description = description;
    }


    /**
     * Returns the amount of the debt.
     * @return The amount of the debt.
     */
    public double getAmount() {
        return amount;
    }


    /**
     * Returns the interest rate of the debt.
     * @return The interest rate of the debt.
     */
    public double getInterestRate() {
        return interestRate;
    }


    /**
     * Returns the year of the debt.
     * @return The year associated with the debt.
     */
    public int getTime() {
        return time;
    }

    /**
     * Returns whether the debt is a one-time payment.
     * @return true if the debt is one-time, false otherwise.
     */
    public boolean isOneTime() {
        return isOneTime;
    }


    /**
     * Returns the category of the debt.
     * @return The category of the debt.
     */
    public DebtCategory getCategory() {
        return category;
    }

    /**
     * Returns the description of the debt.
     * @return The description.
     */
    public String getDescription() {
        return description;
    }
}
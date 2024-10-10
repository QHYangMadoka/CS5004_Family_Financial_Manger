package final_project;

/**
 * Represents a financial goal with a target amount, due time, and the nature of the goal (one-time or recurring).
 */
public class Goal {
    private double amount;          // The target amount of money needed for the goal.
    private int time;               // The time at which the goal needs to be met, measured in years from now.
    private boolean isOneTime;      // Flag indicating whether the goal is a one-time event or recurring.
    private double interestRate;    // The discount rate used for present value calculations.
    private final String description; // Description of the goal, immutable after being set.

    /**
     * Constructs a Goal with specified parameters.
     * @param amount The target amount for the goal, must not be negative.
     * @param time The year by which the goal must be achieved, must not be earlier than 2024.
     * @param isOneTime Whether the goal requires a one-time payment or recurring payments.
     * @param interestRate The interest rate used to discount the future values, must not be negative.
     * @param description A brief description of the goal, must not be null or empty.
     * @throws IllegalArgumentException if any parameter is invalid or description is null or empty.
     */
    public Goal(double amount, int time, boolean isOneTime, double interestRate, String description) {
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
        this.time = time;
        this.isOneTime = isOneTime;
        this.interestRate = interestRate;
        this.description = description;
    }



    // Getters and setters
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the target amount for the goal.
     * @param amount The new target amount, must not be negative.
     */
    public void setAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        this.amount = amount;
    }

    public int getTime() {
        return time;
    }

     /**
     * Sets the year by which the goal must be achieved.
     * @param time The new target year, must not be earlier than 2024.
     */
    public void setTime(int time) {
        if (time < 2024) {
            throw new IllegalArgumentException("Time must not be earlier than 2024.");
        }
        this.time = time;
    }

    public boolean isOneTime() {
        return isOneTime;
    }

    public void setOneTime(boolean oneTime) {
        isOneTime = oneTime;
    }

    public double getInterestRate() {
        return interestRate;
    }

    /**
     * Sets the discount rate used for present value calculations.
     * @param interestRate The new interest rate, must not be negative.
     */
    public void setInterestRate(double interestRate) {
        if (interestRate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative.");
        }
        this.interestRate = interestRate;
    }

    public String getDescription() {
        return description;
    }
}
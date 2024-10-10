package final_project;


/**
 * Represents a person with personal financial details.
 * The class ensures that all financial metrics and personal details meet specific conditions for validity.
 */
public class Person {
    private String name;
    private int birthYear;
    private double monthlyIncome;
    private double monthlyExpenditure;
    private double insurance;
    private int lifeSpan;

    /**
     * Constructor for creating a new person with initial values.
     * @param name The person's name, cannot be null or empty.
     * @param birthYear The birth year of the person, must be between 1900 and 2024 inclusive.
     * @param monthlyIncome The person's monthly income, must be non-negative.
     * @param monthlyExpenditure The person's monthly expenditure, must be non-negative.
     * @param insurance The amount of the person's insurance, must be non-negative.
     * @param lifeSpan The expected lifespan of the person, must be greater than zero.
     */
    public Person(String name, int birthYear, double monthlyIncome, double monthlyExpenditure, double insurance, int lifeSpan) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (birthYear < 1900 || birthYear > 2024) {
            throw new IllegalArgumentException("Birth year must be between 1900 and 2024.");
        }
        if (monthlyIncome < 0) {
            throw new IllegalArgumentException("Monthly income cannot be negative.");
        }
        if (monthlyExpenditure < 0) {
            throw new IllegalArgumentException("Monthly expenditure cannot be negative.");
        }
        if (insurance < 0) {
            throw new IllegalArgumentException("Insurance amount cannot be negative.");
        }
        if (lifeSpan <= 0) {
            throw new IllegalArgumentException("Life span must be greater than zero.");
        }
        
        this.name = name;
        this.birthYear = birthYear;
        this.monthlyIncome = monthlyIncome;
        this.monthlyExpenditure = monthlyExpenditure;
        this.insurance = insurance;
        this.lifeSpan = lifeSpan;
    }

    // Getters and Setters with validation
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBirthYear(int birthYear) {
        if (birthYear < 1900 || birthYear > 2024) {
            throw new IllegalArgumentException("Birth year must be between 1900 and 2024.");
        }
        this.birthYear = birthYear;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        if (monthlyIncome < 0) {
            throw new IllegalArgumentException("Monthly income cannot be negative.");
        }
        this.monthlyIncome = monthlyIncome;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyExpenditure(double monthlyExpenditure) {
        if (monthlyExpenditure < 0) {
            throw new IllegalArgumentException("Monthly expenditure cannot be negative.");
        }
        this.monthlyExpenditure = monthlyExpenditure;
    }

    public double getMonthlyExpenditure() {
        return monthlyExpenditure;
    }

    public void setInsurance(double insurance) {
        if (insurance < 0) {
            throw new IllegalArgumentException("Insurance amount cannot be negative.");
        }
        this.insurance = insurance;
    }

    public double getInsurance() {
        return insurance;
    }

    public void setLifeSpan(int lifeSpan) {
        if (lifeSpan <= 0) {
            throw new IllegalArgumentException("Life span must be greater than zero.");
        }
        this.lifeSpan = lifeSpan;
    }

    public int getLifeSpan() {
        return lifeSpan;
    }
}
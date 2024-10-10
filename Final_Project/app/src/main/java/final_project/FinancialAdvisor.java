package final_project;

/**
 * Provides financial advice based on the current financial situation of a family.
 * This class uses a strategy pattern to dynamically select the financial strategy based on the evaluation of family assets, liabilities, and goals.
 */
public class FinancialAdvisor {
    private Strategy strategy;
    private GoalManager goalManager;
    private BalanceSheet balanceSheet;

    /**
     * Constructs a FinancialAdvisor with references to GoalManager and BalanceSheet for accessing family goals and financial data.
     * @param goalManager the manager responsible for handling goals
     * @param balanceSheet the balance sheet providing financial data of the family
     */
    public FinancialAdvisor(GoalManager goalManager, BalanceSheet balanceSheet) {
        this.goalManager = goalManager;
        this.balanceSheet = balanceSheet;
    }

    /**
     * Evaluates the financial situation of a family by comparing the net present value of family assets and liabilities
     * to the total value of all goals. Sets the strategy based on the outcome of this comparison.
     */
    public void evaluateFinancialSituation() {
        double totalGoalsValue = calculateTotalGoalsValue();
        double familyNetValue = balanceSheet.calculateNetPresentValue();
        double shortfall = familyNetValue - totalGoalsValue;

        if (shortfall < 0) {
            setStrategy(new AggressiveStrategy(shortfall));
        } else {
            setStrategy(new ConservativeStrategy(balanceSheet.getFamily().getFamilyMembers(), balanceSheet.calculateTotalIncomePV(), balanceSheet.calculateTotalExpenditurePV()));
        }
    }

    /**
     * Executes the current strategy, providing financial advice based on the family's financial situation.
     */
    public void advise() {
        strategy.execute();
    }

    /**
     * Sets the financial strategy to be used for advising.
     * @param strategy the strategy to be set
     */
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Calculates the total present value of all goals managed by the GoalManager. This includes both one-time and recurring goals.
     * @return the total present value of all goals
     */
    private double calculateTotalGoalsValue() {
        double totalGoalsValue = 0;
        for (Goal goal : goalManager.getGoalList()) {
            int years = goal.getTime() - 2024;
            if (goal.isOneTime()) {
                totalGoalsValue += goal.getAmount() / Math.pow(1 + goal.getInterestRate(), years);
            } else {
                double annualValue = 0;
                for (int i = 1; i <= years; i++) {
                    annualValue += goal.getAmount() / Math.pow(1 + goal.getInterestRate(), i);
                }
                totalGoalsValue += annualValue;
            }
        }
        return totalGoalsValue;
    }
}

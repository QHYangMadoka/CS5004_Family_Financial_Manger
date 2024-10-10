package final_project;

import java.util.List;

public class ConservativeStrategy implements Strategy {
    private List<Person> familyMembers;
    private double totalIncomePV;
    private double totalExpenditurePV;

    public ConservativeStrategy(List<Person> familyMembers, double totalIncomePV, double totalExpenditurePV) {
        this.familyMembers = familyMembers;
        this.totalIncomePV = totalIncomePV;
        this.totalExpenditurePV = totalExpenditurePV;
    }

    public void execute() {
        System.out.println("Analyzing insurance coverage for each family member:");
        for (Person member : familyMembers) {
            if (member.getMonthlyIncome() > 0) {
                double gap = member.getInsurance() - (totalIncomePV - totalExpenditurePV);
                if (gap >= 0) {
                    System.out.println(member.getName() + " is adequately covered by insurance.");
                } else {
                    System.out.println(member.getName() + "'s insurance shortfall: " + -gap);
                }
            }
        }
    }
}

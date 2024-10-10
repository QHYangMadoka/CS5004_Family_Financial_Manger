package final_project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages lists of financial goals and provides functionality to load and save these goals to/from a CSV file named "Goal.csv".
 */
public class GoalManager {
    private static GoalManager instance;
    private List<Goal> goalList = new ArrayList<>();


    private GoalManager() {}


    public static synchronized GoalManager getInstance() {
        if (instance == null) {
            instance = new GoalManager();
        }
        return instance;
    }

    /**
     * Saves the list of goals to a CSV file named "Goal.csv".
     * @throws IOException If there is an error writing to the file.
     */
    public void saveGoalsToCSV() throws IOException {
        File file = new File("Goal.csv");
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
        writer.write("Amount,Time,IsOneTime,InterestRate,Description\n");
        for (Goal goal : goalList) {
            writer.write(String.format("%f,%d,%b,%f,%s\n",
                    goal.getAmount(), goal.getTime(), goal.isOneTime(),
                    goal.getInterestRate(), goal.getDescription()));
        }
        writer.close();
    }
    
    /**
     * Loads goals from a CSV file named "Goal.csv".
     * Each goal is expected to be in the format: Amount,Time,IsOneTime,InterestRate,Description
     * @throws IOException If there is an error reading the file.
     */
    public void loadGoalsFromCSV() throws IOException {
        File file = new File("Goal.csv");
        if (!file.exists()) {
            return; // If file does not exist, exit the method
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine(); // Skip header line
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 5) {
                double amount = Double.parseDouble(parts[0]);
                int time = Integer.parseInt(parts[1]);
                boolean isOneTime = Boolean.parseBoolean(parts[2]);
                double interestRate = Double.parseDouble(parts[3]);
                String description = parts[4];
                Goal goal = new Goal(amount, time, isOneTime, interestRate, description);
                goalList.add(goal);
            }
        }
        reader.close();
    }

    /**
     * Adds a new goal to the list and updates the CSV file.
     * @param goal The goal to add.
     * @throws IOException If there is an error updating the CSV file.
     */
    public void addGoal(Goal goal) throws IOException {
        goalList.add(goal);
        saveGoalsToCSV();
    }

    /**
     * Deletes a goal from the list based on its description and updates the CSV file.
     * @param description The description of the goal to remove.
     * @return true if the goal was found and deleted, false otherwise.
     * @throws IOException If there is an error updating the CSV file.
     */
    public boolean deleteGoal(String description) throws IOException {
        boolean exists = goalList.stream().anyMatch(goal -> goal.getDescription().equals(description));
        if (exists) {
            goalList = goalList.stream()
                            .filter(goal -> !goal.getDescription().equals(description))
                            .collect(Collectors.toList());
            saveGoalsToCSV();
            return true;
        }
        return false;
    }

    /**
     * Creates a new goal, adds it to the list, and updates the CSV file.
     * @param amount The target amount for the goal.
     * @param time The year by which the goal must be achieved.
     * @param isOneTime Whether the goal requires a one-time payment or recurring payments.
     * @param interestRate The interest rate used to discount the future values.
     * @param description A brief description of the goal.
     * @throws IOException If there is an error updating the CSV file.
     */
    public void createGoal(double amount, int time, boolean isOneTime, double interestRate, String description) throws IOException {
        Goal newGoal = new Goal(amount, time, isOneTime, interestRate, description);
        addGoal(newGoal);
    }

    // Getter method for the list of goals
    public List<Goal> getGoalList() {
        return goalList;
    }
}

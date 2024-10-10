package final_project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Singleton class that manages a family's list of members and their data storage in CSV format.
 * Utilizes PersonFactory for creating new family members and manages them through a central list.
 */
public class Family {
    private static Family instance;
    private List<Person> familyMembers;
    private final String csvFileName = "Person.csv";  // Default CSV file name
    private PersonFactory factory;

    /**
     * Private constructor to prevent instantiation from outside the class.
     * @param factory The PersonFactory to use for creating new Person instances.
     */
    private Family(PersonFactory factory) {
        this.familyMembers = new ArrayList<>(factory.getPersonList());
        this.factory = factory;
    }

    /**
     * Provides the global access point to the single instance of the Family class.
     * If the instance does not exist, it initializes it using the provided factory.
     * Subsequent calls ignore the factory parameter.
     * @param factory The PersonFactory to use if the instance needs to be initialized.
     * @return The single instance of the Family class.
     */
    public static synchronized Family getInstance(PersonFactory factory) {
        if (instance == null) {
            instance = new Family(factory);
        }
        return instance;
    }

    /**
     * Provides the global access point to the single instance of the Family class.
     * Throws IllegalStateException if called before the instance is initialized.
     * @return The single instance of the Family class.
     */
    public static synchronized Family getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Family class is not initialized. Please call getInstance(PersonFactory) first.");
        }
        return instance;
    }

    /**
     * Loads people from a CSV file into the family member list.
     * @throws IOException If there is an error reading the file.
     */
    public void loadPeopleFromCSV() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(csvFileName));
        String line = reader.readLine();  // Skip header line
        familyMembers.clear();
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length >= 6) {
                Person person = new Person(data[0], Integer.parseInt(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3]), Double.parseDouble(data[4]), Integer.parseInt(data[5]));
                familyMembers.add(person);
            }
        }
        reader.close();
    }

    /**
     * Saves the list of family members to a CSV file.
     * @throws IOException If there is an error writing to the file.
     */
    public void savePeopleToCSV() throws IOException {
        File file = new File(csvFileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
        writer.write("Name,BirthYear,MonthlyIncome,MonthlyExpenditure,Insurance,LifeSpan\n");
        for (Person person : familyMembers) {
            writer.write(String.format("%s,%d,%.2f,%.2f,%.2f,%d\n",
                person.getName(), person.getBirthYear(), person.getMonthlyIncome(),
                person.getMonthlyExpenditure(), person.getInsurance(), person.getLifeSpan()));
        }
        writer.close();
    }

    /**
     * Adds a new family member to the list and updates the CSV file.
     * @param name The name of the person.
     * @param birthYear The birth year of the person.
     * @param monthlyIncome The monthly income of the person.
     * @param monthlyExpenditure The monthly expenditure of the person.
     * @param insurance The insurance amount of the person.
     * @param lifeSpan The life span of the person.
     * @throws IOException If there is an error updating the CSV file.
     */
    public void addFamilyMember(String name, int birthYear, double monthlyIncome, double monthlyExpenditure, double insurance, int lifeSpan) throws IOException {
        Person newPerson = factory.createPerson(name, birthYear, monthlyIncome, monthlyExpenditure, insurance, lifeSpan);
        familyMembers.add(newPerson);
        savePeopleToCSV();  // Update CSV file after adding a new member
    }

    /**
     * Deletes a family member from the list based on the name and updates the CSV file.
     * @param name The name of the person to remove.
     * @throws IOException If there is an error updating the CSV file.
     */
    public boolean deleteFamilyMember(String name) throws IOException {
        Optional<Person> personToRemove = familyMembers.stream()
            .filter(person -> person.getName().equals(name))
            .findFirst();
    
        if (personToRemove.isPresent()) {
            familyMembers.remove(personToRemove.get());
            savePeopleToCSV();  // Update CSV file after removing a member
            return true;
        }
        return false;
    }

    /**
     * Returns the list of family members.
     * @return the list of Person objects representing the family members.
     */
    public List<Person> getFamilyMembers() {
        return familyMembers;
    }
}

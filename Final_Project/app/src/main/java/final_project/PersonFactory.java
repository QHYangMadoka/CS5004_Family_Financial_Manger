package final_project;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory class for creating and managing Person objects.
 * Uses the Factory Method design pattern to encapsulate the creation of Person objects.
 */
public class PersonFactory {
    private List<Person> personList;  // List to store created Person objects

    /**
     * Constructs a new PersonFactory with an empty list of Person objects.
     */
    public PersonFactory() {
        personList = new ArrayList<>();
    }

    /**
     * Creates a new Person object and adds it to the person list.
     * @param name The person's name, cannot be null or empty.
     * @param birthYear The birth year of the person, must be between 1900 and 2024 inclusive.
     * @param monthlyIncome The person's monthly income, must be non-negative.
     * @param monthlyExpenditure The person's monthly expenditure, must be non-negative.
     * @param insurance The amount of the person's insurance, must be non-negative.
     * @param lifeSpan The expected lifespan of the person, must be greater than zero.
     * @return The newly created Person object.
     */
    public Person createPerson(String name, int birthYear, double monthlyIncome, double monthlyExpenditure, double insurance, int lifeSpan) {
        Person newPerson = new Person(name, birthYear, monthlyIncome, monthlyExpenditure, insurance, lifeSpan);
        personList.add(newPerson);
        return newPerson;
    }

    /**
     * Gets the list of all created Person objects.
     * @return The list of Person objects.
     */
    public List<Person> getPersonList() {
        return personList;
    }
}

package final_project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonFactoryTest {
    private PersonFactory factory;

    @BeforeEach
    void setUp() {
        factory = new PersonFactory();
    }

    @Test
    void testCreatePerson() {
        // Test construct normally
        Person person = factory.createPerson("John Doe", 1990, 5000, 2000, 100000, 85);
        assertNotNull(person, "Person should not be null");
        assertEquals("John Doe", person.getName(), "Name should match");
        assertEquals(1990, person.getBirthYear(), "Birth year should match");
        assertEquals(5000, person.getMonthlyIncome(), "Monthly income should match");
        assertEquals(2000, person.getMonthlyExpenditure(), "Monthly expenditure should match");
        assertEquals(100000, person.getInsurance(), "Insurance should match");
        assertEquals(85, person.getLifeSpan(), "Life span should match");

        assertEquals(1, factory.getPersonList().size(), "Person list should contain one person");
        assertTrue(factory.getPersonList().contains(person), "Person list should include the created person");
    }

    @Test
    void testCreatePersonWithInvalidParameters() {
        // Test Invalid Parameter
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createPerson(null, 1990, 5000, 2000, 100000, 85);
        }, "Should throw an exception due to null name");

        assertThrows(IllegalArgumentException.class, () -> {
            factory.createPerson("", 1990, 5000, 2000, 100000, 85);
        }, "Should throw an exception due to empty name");

        assertThrows(IllegalArgumentException.class, () -> {
            factory.createPerson("John Doe", 1890, 5000, 2000, 100000, 85);
        }, "Should throw an exception due to invalid birth year");

        assertThrows(IllegalArgumentException.class, () -> {
            factory.createPerson("John Doe", 2025, 5000, 2000, 100000, 85);
        }, "Should throw an exception due to future birth year");

        assertThrows(IllegalArgumentException.class, () -> {
            factory.createPerson("John Doe", 1990, -1, 2000, 100000, 85);
        }, "Should throw an exception due to negative monthly income");

        assertThrows(IllegalArgumentException.class, () -> {
            factory.createPerson("John Doe", 1990, 5000, -1, 100000, 85);
        }, "Should throw an exception due to negative monthly expenditure");

        assertThrows(IllegalArgumentException.class, () -> {
            factory.createPerson("John Doe", 1990, 5000, 2000, -1, 85);
        }, "Should throw an exception due to negative insurance");

        assertThrows(IllegalArgumentException.class, () -> {
            factory.createPerson("John Doe", 1990, 5000, 2000, 100000, 0);
        }, "Should throw an exception due to non-positive life span");
    }

    @Test
    void testGetPersonList() {
        assertEquals(0, factory.getPersonList().size(), "Initially, person list should be empty");
        factory.createPerson("John Doe", 1990, 5000, 2000, 100000, 85);
        factory.createPerson("Jane Doe", 1992, 6000, 2500, 200000, 90);
        assertEquals(2, factory.getPersonList().size(), "Person list should contain two persons after creation");
    }
}
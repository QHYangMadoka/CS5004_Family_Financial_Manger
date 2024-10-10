package final_project;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void validPersonCreation() {
        Person person = new Person("John Doe", 1985, 5000.0, 1500.0, 200000.0, 85);
        assertNotNull(person);
        assertEquals("John Doe", person.getName());
        assertEquals(1985, person.getBirthYear());
        assertEquals(5000.0, person.getMonthlyIncome());
        assertEquals(1500.0, person.getMonthlyExpenditure());
        assertEquals(200000.0, person.getInsurance());
        assertEquals(85, person.getLifeSpan());
    }

    @Test
    void nameCannotBeNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Person(null, 1985, 5000.0, 1500.0, 200000.0, 85);
        });
        assertEquals("Name cannot be null or empty.", exception.getMessage());
    }

    @Test
    void nameCannotBeEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Person("", 1985, 5000.0, 1500.0, 200000.0, 85);
        });
        assertEquals("Name cannot be null or empty.", exception.getMessage());
    }

    @Test
    void birthYearMustBeValid() {
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
            new Person("John Doe", 1899, 5000.0, 1500.0, 200000.0, 85);
        });
        assertEquals("Birth year must be between 1900 and 2024.", exception1.getMessage());

        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            new Person("John Doe", 2025, 5000.0, 1500.0, 200000.0, 85);
        });
        assertEquals("Birth year must be between 1900 and 2024.", exception2.getMessage());
    }

    @Test
    void monthlyIncomeCannotBeNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Person("John Doe", 2000, -1, 1500.0, 200000.0, 85);
        });
        assertEquals("Monthly income cannot be negative.", exception.getMessage());
    }

    @Test
    void monthlyExpenditureCannotBeNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Person("John Doe", 2000, 5000.0, -1, 200000.0, 85);
        });
        assertEquals("Monthly expenditure cannot be negative.", exception.getMessage());
    }

    @Test
    void insuranceCannotBeNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Person("John Doe", 2000, 5000.0, 1500.0, -1, 85);
        });
        assertEquals("Insurance amount cannot be negative.", exception.getMessage());
    }

    @Test
    void lifeSpanMustBeGreaterThanZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Person("John Doe", 2000, 5000.0, 1500.0, 200000.0, 0);
        });
        assertEquals("Life span must be greater than zero.", exception.getMessage());
    }

    @Test
    void settersShouldThrowExceptionsForInvalidValues() {
        Person person = new Person("John Doe", 1985, 5000.0, 1500.0, 200000.0, 85);
        assertThrows(IllegalArgumentException.class, () -> person.setName(null));
        assertThrows(IllegalArgumentException.class, () -> person.setBirthYear(1899));
        assertThrows(IllegalArgumentException.class, () -> person.setMonthlyIncome(-100.0));
        assertThrows(IllegalArgumentException.class, () -> person.setMonthlyExpenditure(-100.0));
        assertThrows(IllegalArgumentException.class, () -> person.setInsurance(-100.0));
        assertThrows(IllegalArgumentException.class, () -> person.setLifeSpan(0));
    }

    @Test
    void testValidSetName() {
        Person person = new Person("John Doe", 1990, 3000, 1500, 100000, 85);
        person.setName("Jane Doe");
        assertEquals("Jane Doe", person.getName());
    }

    @Test
    void testValidSetBirthYear() {
        Person person = new Person("John Doe", 1990, 3000, 1500, 100000, 85);
        person.setBirthYear(2000);
        assertEquals(2000, person.getBirthYear());
    }

    @Test
    void testValidSetMonthlyIncome() {
        Person person = new Person("John Doe", 1990, 3000, 1500, 100000, 85);
        person.setMonthlyIncome(4000);
        assertEquals(4000, person.getMonthlyIncome());
    }

    @Test
    void testValidSetMonthlyExpenditure() {
        Person person = new Person("John Doe", 1990, 3000, 1500, 100000, 85);
        person.setMonthlyExpenditure(2000);
        assertEquals(2000, person.getMonthlyExpenditure());
    }

    @Test
    void testValidSetInsurance() {
        Person person = new Person("John Doe", 1990, 3000, 1500, 100000, 85);
        person.setInsurance(120000);
        assertEquals(120000, person.getInsurance());
    }

    @Test
    void testValidSetLifeSpan() {
        Person person = new Person("John Doe", 1990, 3000, 1500, 100000, 85);
        person.setLifeSpan(90);
        assertEquals(90, person.getLifeSpan());
    }
}
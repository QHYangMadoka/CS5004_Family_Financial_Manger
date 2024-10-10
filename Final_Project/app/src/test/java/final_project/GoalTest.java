package final_project;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class GoalTest {
    @Test
    void constructorValidParameters() {
        Goal goal = new Goal(5000, 2025, true, 0.05, "Save for a car");
        assertEquals(5000, goal.getAmount(), "Amount should be correct.");
        assertEquals(2025, goal.getTime(), "Time should be correct.");
        assertTrue(goal.isOneTime(), "Goal should be one-time.");
        assertEquals(0.05, goal.getInterestRate(), "Interest rate should be correct.");
        assertEquals("Save for a car", goal.getDescription(), "Description should be correct.");
    }

    @Test
    void constructorNegativeAmountThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Goal(-1000, 2025, true, 0.05, "Save for a car");
        });
        assertTrue(exception.getMessage().contains("Amount cannot be negative"));
    }

    @Test
    void constructorNegativeInterestRateThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Goal(5000, 2025, true, -0.05, "Save for a car");
        });
        assertTrue(exception.getMessage().contains("Interest rate cannot be negative"));
    }

    @Test
    void constructorTimeBefore2024ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Goal(5000, 2023, true, 0.05, "Save for a car");
        });
        assertTrue(exception.getMessage().contains("Time must not be earlier than 2024"));
    }

    @Test
    void constructorEmptyDescriptionThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Goal(5000, 2025, true, 0.05, "");
        });
        assertTrue(exception.getMessage().contains("Description cannot be null or empty"));
    }

    @Test
    void constructorNullDescriptionThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Goal(5000, 2025, true, 0.05, null);
        });
        assertTrue(exception.getMessage().contains("Description cannot be null or empty"));
    }

    @Test
    void getAmountReturnsCorrectValue() {
        Goal goal = new Goal(5000, 2025, true, 0.05, "Save for a car");
        assertEquals(5000, goal.getAmount(), "getAmount should return the correct value.");
    }

    @Test
    void getInterestRateReturnsCorrectValue() {
        Goal goal = new Goal(5000, 2025, true, 0.05, "Save for a car");
        assertEquals(0.05, goal.getInterestRate(), "getInterestRate should return the correct value.");
    }

    @Test
    void getTimeReturnsCorrectValue() {
        Goal goal = new Goal(5000, 2025, true, 0.05, "Save for a car");
        assertEquals(2025, goal.getTime(), "getTime should return the correct year.");
    }

    @Test
    void isOneTimeReturnsCorrectValue() {
        Goal goal = new Goal(5000, 2025, true, 0.05, "Save for a car");
        assertTrue(goal.isOneTime(), "isOneTime should return true for one-time goals.");
    }

    @Test
    void getDescriptionReturnsCorrectValue() {
        Goal goal = new Goal(5000, 2025, true, 0.05, "Save for a car");
        assertEquals("Save for a car", goal.getDescription(), "getDescription should return the correct description.");
    }

    @Test
    void setAmountWithValidValue() {
        Goal goal = new Goal(5000, 2025, true, 0.05, "Save for a car");
        goal.setAmount(6000);
        assertEquals(6000, goal.getAmount(), "setAmount should update the amount correctly.");
    }

    @Test
    void setAmountWithNegativeValueThrowsException() {
        Goal goal = new Goal(5000, 2025, true, 0.05, "Save for a car");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> goal.setAmount(-100));
        assertTrue(exception.getMessage().contains("Amount cannot be negative"));
    }

    @Test
    void setTimeWithValidValue() {
        Goal goal = new Goal(5000, 2025, true, 0.05, "Save for a car");
        goal.setTime(2026);
        assertEquals(2026, goal.getTime(), "setTime should update the time correctly.");
    }

    @Test
    void setTimeWithInvalidValueThrowsException() {
        Goal goal = new Goal(5000, 2025, true, 0.05, "Save for a car");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> goal.setTime(2023));
        assertTrue(exception.getMessage().contains("Time must not be earlier than 2024"));
    }

    @Test
    void setInterestRateWithValidValue() {
        Goal goal = new Goal(5000, 2025, true, 0.05, "Save for a car");
        goal.setInterestRate(0.06);
        assertEquals(0.06, goal.getInterestRate(), "setInterestRate should update the interest rate correctly.");
    }

    @Test
    void setInterestRateWithNegativeValueThrowsException() {
        Goal goal = new Goal(5000, 2025, true, 0.05, "Save for a car");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> goal.setInterestRate(-0.01));
        assertTrue(exception.getMessage().contains("Interest rate cannot be negative"));
    }

    @Test
    void setIsOneTimeWithValidValue() {
        Goal goal = new Goal(5000, 2025, true, 0.05, "Save for a car");
        goal.setOneTime(false);
        assertFalse(goal.isOneTime(), "setOneTime should update the one-time flag correctly.");
    }
}

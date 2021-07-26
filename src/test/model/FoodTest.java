package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodTest {
    Food food;

    @BeforeEach
    public void runBefore() {
        food = new Food("Apple", 2.99, "room temperature", 1, true);
    }

    @Test
    public void testReduceDaysLeftByOneTrue() {
        assertTrue(food.reduceDaysLeftByOne());
        assertEquals(0, food.getDaysLeft());
    }

    @Test
    public void testReduceDaysLeftByOneFalse() {
        Food food2 = new Food("Broccoli", 1.99, "refrigerated", 0, true);

         assertFalse(food2.reduceDaysLeftByOne());
    }
}

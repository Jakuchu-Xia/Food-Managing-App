package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodTest {
    Food food;

    @BeforeEach
    public void runBefore() {
        food = new Food("Apple", 2.99, "room temperature", 1);
    }

    @Test
    public void testReduceDaysLeftByOneNotZero() {
        food.reduceDaysLeftByOne();

        assertEquals(0, food.getDaysLeft());
    }

    @Test
    public void testReduceDaysLeftByOneZero() {
        food.reduceDaysLeftByOne();
        food.reduceDaysLeftByOne();

        assertTrue(food.getIsPassedExpDate());
    }
}

package model;

import exceptions.NegativeValueException;
import exceptions.UnitMismatchException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoodTest {
    Food food;

    @BeforeEach
    public void runBefore() {
        food = new Food("Apple", 2.99, 23, Unit.NONE, "room_temperature", 1);
    }

    @Test
    public void testSetPriceAndDaysLeftPass() {
        try {
            food.setPriceAndDaysLeft(1, 2);
            assertEquals(1, food.getPrice());
            assertEquals(2, food.getDaysLeft());
        } catch (NegativeValueException e) {
            fail();
        }
    }

    @Test
    public void testSetPriceAndDaysLeftNegativePrice() {
        try {
            food.setPriceAndDaysLeft(-1, 2);
            fail();
        } catch (NegativeValueException e) {
            // pass
        }
    }

    @Test
    public void testSetPriceAndDaysLeftNegativeDaysLeft() {
        try {
            food.setPriceAndDaysLeft(1, -2);
            fail();
        } catch (NegativeValueException e) {
            // pass
        }
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

    @Test
    public void testReduceAmountPartial() {
        try {
            food.reduceAmount(22, Unit.NONE);

            assertEquals(1, food.getAmount());
        } catch (NegativeValueException | UnitMismatchException e) {
            fail();
        }
    }

    @Test
    public void testReduceAmountAll() {
        try {
            food.reduceAmount(23, Unit.NONE);

            assertEquals(0, food.getAmount());
        } catch (NegativeValueException | UnitMismatchException e) {
            fail();
        }
    }

    @Test
    public void testReduceAmountNegative() {
        try {
            food.reduceAmount(24, Unit.NONE);
            fail();
        } catch (NegativeValueException ignored) {
        } catch (UnitMismatchException e) {
            fail();
        }
    }

    @Test
    public void testReduceAmountWrongUnit() {
        try {
            food.reduceAmount(22, Unit.L);
            fail();
        } catch (NegativeValueException e) {
            fail();
        } catch (UnitMismatchException ignored) {
        }
    }

    @Test
    public void testGetStorageCond() {
        assertEquals("room_temperature", food.getStorageCond());
    }

    @Test
    public void testInitializeAmountWithUnit() {
        Food food1 = new Food("rice", 7.2, 500, Unit.G, "r.t.", 30);
        assertEquals(500, food1.getAmount());
        assertEquals(Unit.G, food1.getUnit());

        Food food2 = new Food("rice", 7.2, 2, Unit.KG, "r.t.", 30);
        assertEquals(2000, food2.getAmount());
        assertEquals(Unit.G, food2.getUnit());
    }
}

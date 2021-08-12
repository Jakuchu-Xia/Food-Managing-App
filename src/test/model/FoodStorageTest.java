package model;

import exceptions.NegativeAmountException;
import exceptions.UnitMismatchException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class FoodStorageTest {
    private FoodStorage storage;
    private Food food;

    @BeforeEach
    public void runBefore() {
        storage = new FoodStorage();
        food = new Food("Apple", 2.99, 23, Unit.NONE, "room_temperature", 1);
    }

    @Test
    public void testStoreFood() {
        storage.storeFood(food);

        assertEquals(1, storage.totalAmount());
        assertEquals(food, storage.position(1));
    }

    @Test
    public void testRemoveFoodByAmountPartial() {
        storage.storeFood(food);

        try {
            storage.reduceFoodByAmount(food, 10, Unit.NONE);
            assertEquals(13, storage.position(1).getAmount());
        } catch (NegativeAmountException | UnitMismatchException e) {
            fail();
        }
    }

    @Test
    public void testRemoveFoodByAmountAll() {
        storage.storeFood(food);
        try {
            storage.reduceFoodByAmount(food, 23, Unit.NONE);
            assertEquals(0, storage.totalAmount());
        } catch (NegativeAmountException | UnitMismatchException e) {
            fail();
        }
    }

    @Test
    public void testRemoveFoodByNegativeAmount() {
        storage.storeFood(food);

        try {
            storage.reduceFoodByAmount(food, 50, Unit.NONE);
            fail();
        } catch (NegativeAmountException e) {
        } catch (UnitMismatchException e) {
            fail();
        }
    }

    @Test
    public void testRemoveFoodByAmountWrongUnit() {
        storage.storeFood(food);

        try {
            storage.reduceFoodByAmount(food, 11, Unit.KG);
            fail();
        } catch (NegativeAmountException e) {
            fail();
        } catch (UnitMismatchException e) {
        }
    }

    @Test
    public void testRemoveFood() {
        storage.storeFood(food);
        storage.removeFood(food);

        assertEquals(0, storage.totalAmount());
    }

    @Test
    public void testDisplayAllFood() {
        Food food2 = new Food("Broccoli", 1.99, 23, Unit.NONE, "refrigerated", 0);
        ArrayList<String> foodList = new ArrayList<>();

        storage.storeFood(food);
        storage.storeFood(food2);
        foodList.add("Apple");
        foodList.add("Broccoli");

        assertEquals(foodList, storage.displayAllFood());
    }

    @Test
    public void testDisplayFoodByDaysLeft() {
        Food food2 = new Food("Broccoli", 1.99, 23, Unit.NONE, "refrigerated", 0);
        ArrayList<String> foodList = new ArrayList<>();

        storage.storeFood(food);
        storage.storeFood(food2);
        foodList.add("Broccoli");

        assertEquals(foodList, storage.displayFoodByDaysLeft(0));
    }

    @Test
    public void testGetTotalPrice() {
        Food food2 = new Food("Broccoli", 1.99, 23, Unit.NONE, "refrigerated", 0);

        storage.storeFood(food);
        storage.storeFood(food2);

        assertEquals(4.98, storage.getTotalPrice());
    }

    @Test
    public void testPosition() {
        storage.storeFood(food);

        assertEquals(food, storage.position(1));
    }

    @Test
    public void testIsContainedTrue() {
        storage.storeFood(food);

        assertTrue(storage.isContain(food));
    }

    @Test
    public void testIsContainedFalse() {

        assertFalse(storage.isContain(food));
    }

    @Test
    public void testFindFoodByNameTrue() {
        storage.storeFood(food);

        assertTrue(storage.findFoodByName("Apple"));
        assertEquals(food, storage.getFoundFood());

        Food food2 = new Food("Broccoli", 1.99, 23, Unit.NONE, "refrigerated", 0);
        storage.storeFood(food2);

        assertTrue(storage.findFoodByName("Broccoli"));
        assertEquals(food2, storage.getFoundFood());
    }

    @Test
    public void testFindFoodByNameFalse() {
        assertFalse(storage.findFoodByName("Apple"));
    }

    @Test
    public void testGetFoodList() {
        storage.storeFood(food);

        LinkedList<Food> foodList = new LinkedList<>();
        foodList.add(food);

        assertEquals(foodList, storage.getFoodList());
    }
}
package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FoodStorageTest {
    FoodStorage storage;
    Food food;

    @BeforeEach
    public void runBefore() {
        storage = new FoodStorage();
        food = new Food("Apple", 2.99, "room temperature", 1, true);
    }

    @Test
    public void testStoreFood() {
        storage.storeFood(food);

        assertEquals(1, storage.totalAmount());
        assertEquals(food, storage.position(1));
    }

    @Test
    public void testRemoveFood() {
        storage.storeFood(food);
        storage.removeFood(food);

        assertEquals(0, storage.totalAmount());
    }

    @Test
    public void testDisplayAllFood() {
        Food food2 = new Food("Broccoli", 1.99, "refrigerated", 0, true);
        ArrayList<String> foodList = new ArrayList<String>();

        storage.storeFood(food);
        storage.storeFood(food2);
        foodList.add("Apple");
        foodList.add("Broccoli");

        assertEquals(foodList, storage.displayAllFood());
    }

    @Test
    public void testDisplayFoodByDaysLeft() {
        Food food2 = new Food("Broccoli", 1.99, "refrigerated", 0, true);
        ArrayList<String> foodList = new ArrayList<String>();

        storage.storeFood(food);
        storage.storeFood(food2);
        foodList.add("Broccoli");

        assertEquals(foodList, storage.displayFoodByDaysLeft(0));
    }

    @Test
    public void testGetTotalPrice() {
        Food food2 = new Food("Broccoli", 1.99, "refrigerated", 0, true);

        storage.storeFood(food);
        storage.storeFood(food2);

        assertEquals(4.98, storage.getTotalPrice());
    }
}
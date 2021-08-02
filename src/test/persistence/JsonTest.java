package persistence;

import model.Food;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkFood(String name, double price, String storageCond, int daysLeft, Food food) {
        assertEquals(name, food.getName());
        assertEquals(price, food.getPrice());
        assertEquals(storageCond, food.getStorageCond());
        assertEquals(daysLeft, food.getDaysLeft());
    }
}

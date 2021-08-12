package persistence;

import model.Food;
import model.Unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkFood(String name, double price, double amount, Unit unit, String storageCond, int daysLeft,
                             Food food) {
        assertEquals(name, food.getName());
        assertEquals(price, food.getPrice());
        assertEquals(amount, food.getAmount());
        assertEquals(unit, food.getUnit());
        assertEquals(storageCond, food.getStorageCond());
        assertEquals(daysLeft, food.getDaysLeft());
    }
}

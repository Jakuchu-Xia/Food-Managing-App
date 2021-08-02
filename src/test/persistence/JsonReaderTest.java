package persistence;

import model.Food;
import model.FoodStorage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            FoodStorage fs = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyFoodStorage.json");
        try {
            FoodStorage fs = reader.read();
            assertEquals(0, fs.getFoodList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralFoodStorage.json");
        try {
            FoodStorage wr = reader.read();
            List<Food> foodList = wr.getFoodList();
            assertEquals(2, foodList.size());
            checkFood("apple", 5, "roomtemp", 3, foodList.get(0));
            checkFood("pear", 3, "none", 1, foodList.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    public void checkFood(String name, double price, String storageCond, int daysLeft, Food food) {
        assertEquals(name, food.getName());
        assertEquals(price, food.getPrice());
        assertEquals(storageCond, food.getStorageCond());
        assertEquals(daysLeft, food.getDaysLeft());
    }
}

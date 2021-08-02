package persistence;

import model.Food;
import model.FoodStorage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            FoodStorage wr = new FoodStorage();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyFoodStorage() {
        try {
            FoodStorage wr = new FoodStorage();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFoodStorage.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFoodStorage.json");
            wr = reader.read();
            assertEquals(0, wr.getFoodList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralFoodStorage() {
        try {
            FoodStorage wr = new FoodStorage();
            wr.storeFood(new Food("apple", 5, "roomtemp", 3));
            wr.storeFood(new Food("pear", 3, "none", 1));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFoodStorage.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralFoodStorage.json");
            wr = reader.read();
            List<Food> foodList = wr.getFoodList();
            assertEquals(2, foodList.size());
            checkFood("apple", 5, "roomtemp", 3, foodList.get(0));
            checkFood("pear", 3, "none", 1, foodList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    public void checkFood(String name, double price, String storageCond, int daysLeft, Food food) {
        assertEquals(name, food.getName());
        assertEquals(price, food.getPrice());
        assertEquals(storageCond, food.getStorageCond());
        assertEquals(daysLeft, food.getDaysLeft());
    }
}

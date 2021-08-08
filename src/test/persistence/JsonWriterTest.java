package persistence;

import model.Food;
import model.FoodStorage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            FoodStorage fs = new FoodStorage();
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
            FoodStorage fs = new FoodStorage();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFoodStorage.json");
            writer.open();
            writer.write(fs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFoodStorage.json");
            fs = reader.read();
            assertEquals(0, fs.getFoodList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralFoodStorage() {
        try {
            FoodStorage fs = new FoodStorage();
            fs.storeFood(new Food("apple", 5, "roomtemp", 3));
            fs.storeFood(new Food("pear", 3, "none", 1));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFoodStorage.json");
            writer.open();
            writer.write(fs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralFoodStorage.json");
            fs = reader.read();
            List<Food> foodList = fs.getFoodList();
            assertEquals(2, foodList.size());
            checkFood("apple", 5, "roomtemp", 3, foodList.get(0));
            checkFood("pear", 3, "none", 1, foodList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

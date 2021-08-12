package persistence;

import model.Food;
import model.FoodStorage;
import model.Unit;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/*
   Persistence functionality and methods are implemented from Work room app. Link below:
   https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
*/

// Reader reads data of food storage from the json file
public class JsonReader {
    private String source;

    // MODIFIES: this
    // EFFECTS: construct a reader of given source
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: return the food storage saved in json file
    //          throw IOException if occur error when read file from the source
    public FoodStorage read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFoodStorage(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parse and return food storage from the json file
    private FoodStorage parseFoodStorage(JSONObject jsonObject) {
        FoodStorage fs = new FoodStorage();
        addFoodList(fs, jsonObject);
        return fs;
    }

    // MODIFIES: fs
    // EFFECTS: parse and add food list from json array to the parsed food storage
    private void addFoodList(FoodStorage fs, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("food list");
        for (Object json : jsonArray) {
            JSONObject nextFood = (JSONObject) json;
            addFood(fs, nextFood);
        }
    }

    // MODIFIES: fs
    // EFFECTS: parse and add food to the parsed food storage
    private void addFood(FoodStorage fs, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double price = jsonObject.getDouble("price");
        String storageCond = jsonObject.getString("storageCond");
        int daysLeft = jsonObject.getInt("daysLeft");
        double amount = jsonObject.getDouble("amount");
        String unit = jsonObject.getString("unit");
        Food food = new Food(name, price, amount, Unit.parseUnit(unit), storageCond, daysLeft);
        fs.storeFood(food);
    }
}

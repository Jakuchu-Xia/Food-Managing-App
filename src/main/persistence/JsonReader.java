package persistence;

import model.Food;
import model.FoodStorage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    public JsonReader(String source) {
        this.source = source;
    }

    public FoodStorage read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFoodStorage(jsonObject);
    }

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private FoodStorage parseFoodStorage(JSONObject jsonObject) {
        FoodStorage fs = new FoodStorage();
        addFoods(fs, jsonObject);
        return fs;
    }

    private void addFoods(FoodStorage fs, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("foods");
        for (Object json : jsonArray) {
            JSONObject nextFood = (JSONObject) json;
            addFood(fs, nextFood);
        }
    }

    private void addFood(FoodStorage fs, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double price = jsonObject.getDouble("price");
        String storageCond = jsonObject.getString("storageCond");
        int daysLeft = jsonObject.getInt("daysLeft");
        Food food = new Food(name, price, storageCond, daysLeft);
        fs.storeFood(food);
    }
}

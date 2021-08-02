package persistence;

import model.FoodStorage;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/*
   Persistence functionality and methods are implemented from Work room app. Link below:
   https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
*/

// Writer writes data of food storage to the json file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // MODIFIES: this
    // EFFECTS: construct a writer of given destination
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: open a writer of given destination
    //          throw exception if the destination not found
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: convert food storage to json and save to the file
    public void write(FoodStorage fs) {
        JSONObject json = fs.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: close the writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: print and save string to json
    private void saveToFile(String json) {
        writer.print(json);
    }
}

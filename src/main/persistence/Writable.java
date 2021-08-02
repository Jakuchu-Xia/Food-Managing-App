package persistence;

import org.json.JSONObject;

/*
   Persistence functionality and methods are implemented from Work room app. Link below:
   https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
*/

public interface Writable {
    // EFFECTS: save information to json file
    JSONObject toJson();
}

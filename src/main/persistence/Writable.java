package persistence;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: returns this as JSON object
    //Code taken from JsonSerializationDemo (CPSC 210)
    JSONObject toJson();
}

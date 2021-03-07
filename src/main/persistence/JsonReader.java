package persistence;


import model.Menu;
import model.MenuItem;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
//Code taken from JsonSerializationDemo (CPSC 210)
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads menu from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Menu read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMenu(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses menu from JSON object and returns it
    private Menu parseMenu(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Menu menu = new Menu(name);
        addMenuItems(menu, jsonObject);
        return menu;
    }

    // MODIFIES: menu
    // EFFECTS: parses menu items from JSON object and adds them to menu
    private void addMenuItems(Menu menu, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("menuItems");
        for (Object json : jsonArray) {
            JSONObject nextMenuItem = (JSONObject) json;
            addMenuItem(menu, nextMenuItem);
        }
    }

    // MODIFIES: menu
    // EFFECTS: parses menu item from JSON object and adds it to menu
    private void addMenuItem(Menu menu, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Double price = jsonObject.getDouble("price");
        MenuItem menuItem = new MenuItem(name, price);
        menu.addMenuItem(menuItem);
    }
}

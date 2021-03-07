package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class Menu implements Writable {
    //Represents the menu of the restaurant. Has a name and holds a collection of individual MenuItem(s).
    private String name;

    ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>(); // data structure to store menu items.

    public Menu(String name) {
        this.name = name;

    }

    public Menu() {
    }


    public boolean contains(String newItemName) {
        boolean found = false;
        for (MenuItem m : menuItems) {
            if (m.getName().equalsIgnoreCase(newItemName)) {
                found = true;
            }
        }
        return found;
    }


    /*
     * MODIFIES: this
     * EFFECTS: Adds menuItem to menu.
     */
    public void addMenuItem(MenuItem menuItem) {
        if (!this.contains(menuItem.getName())) {
            this.menuItems.add(menuItem);
        }
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }



    @Override
    // EFFECTS: returns this as JSON object
    //Code taken from JsonSerializationDemo (CPSC 210)
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("menuItems", menuItemsToJson());
        return json;
    }

    // EFFECTS: returns menu items in this menu as a JSON array
    //Code taken from JsonSerializationDemo (CPSC 210)
    private JSONArray menuItemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (MenuItem m : menuItems) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;
    }

    public String getName() {
        return name;
    }


}

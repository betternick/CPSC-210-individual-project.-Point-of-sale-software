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

    public void loadMenu(String name) {
        this.name = name;
        this.menuItems.clear();
        if (this.name.equals("Breakfast Menu")) {
            addMenuItem(new MenuItem("Eggs", 2));
            addMenuItem(new MenuItem("Orange Juice", 3));
            addMenuItem(new MenuItem("Milk", 2.5));
        } else if (this.name.equals("Lunch Menu")) {
            addMenuItem(new MenuItem("Burger", 5));
            addMenuItem(new MenuItem("Fries", 2));
            addMenuItem(new MenuItem("Drink", 1.5));
        }
    }



    /*
     * MODIFIES: this
     * EFFECTS: Adds menuItem to menu.
     */
    public void addMenuItem(MenuItem menuItem) {
        this.menuItems.add(menuItem);
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



}

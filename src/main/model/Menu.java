package model;

import model.exceptions.DuplicateMenuItemException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class Menu implements Writable {
    //Represents the menu of the restaurant. Has a name and holds a collection of individual MenuItem(s).
    private String name;

    ArrayList<MenuItem> menuItems = new ArrayList<>(); // data structure to store menu items.

    // EFFECTS: constructs a Menu with given name
    public Menu(String name) {
        this.name = name;

    }

    public Menu() {
    }

    /*
     * EFFECTS: Returns true if menu already contains a menu item with newItemName.
     */
    public boolean containsMenuItem(String newItemName) {
        boolean found = false;
        for (MenuItem m : menuItems) {
            if (m.getName().equalsIgnoreCase(newItemName)) {
                found = true;
                break;
            }
        }
        return found;
    }

    /*
     * MODIFIES: this
     * EFFECTS: Removes given Menu Item from menu.
     */
    public boolean removeMenuItem(String menuItemName) {
        MenuItem toRemove = null;
        for (MenuItem m : menuItems) {
            if (m.getName().equalsIgnoreCase(menuItemName)) {
                toRemove = m;
                break;
            }
        }
        if (toRemove == null) {
            return false;
        } else {
            menuItems.remove(toRemove);
            return true;
        }


    }

    /*
     * MODIFIES: this
     * EFFECTS: Adds menuItem to menu. Throws DuplicateMenuItemException if Menu Item
     * already exists.
     */
    public void addMenuItem(MenuItem menuItem) throws DuplicateMenuItemException {
        if (this.containsMenuItem(menuItem.getName())) {
            throw new DuplicateMenuItemException(menuItem);
        }
        this.menuItems.add(menuItem);
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }


    @Override
    // EFFECTS: returns this as JSON object
    //Code for this function taken from JsonSerializationDemo (CPSC 210)
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("menuItems", menuItemsToJson());
        return json;
    }

    // EFFECTS: returns menu items in this menu as a JSON array
    //Code for this function taken from JsonSerializationDemo (CPSC 210)
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

package model;

import org.json.JSONObject;
import persistence.Writable;

public class MenuItem implements Writable {
    //Represents the individual menu items with a name and price in dollars.
    private final String name;
    private final double price;  // In Dollars

    /*
     * MODIFIES: this
     * EFFECTS: Name of menu item is set to name. Price of the menu item is set to price. If entered price
     * is a negative number, then price is set to 0.
     */
    public MenuItem(String name, double price) {
        this.name = name;
        if (price < 0) {
            this.price = 0;
        } else {
            this.price = price;
        }
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }


    /*
     * EFFECTS: Displays name and price of menu item.
     */
    public String displayMenuItem() {
        return "Name = " + name + ", Price = $" + price;
    }


    @Override
    // EFFECTS: returns this as JSON object
    //Code for this function taken from JsonSerializationDemo (CPSC 210)
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("price", price);
        return json;
    }
}

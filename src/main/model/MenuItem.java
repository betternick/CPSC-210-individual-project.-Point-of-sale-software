package model;

public class MenuItem {
    //Represents the individual menu items with a name and price in dollars.
    private String name;
    private double price;  // In Dollars

    /*
     * REQUIRES: menuItem name has a non-zero length.
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
}

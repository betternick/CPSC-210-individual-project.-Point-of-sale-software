package model;

public class MenuItem {
    //Represents the individual menu items with a name and price in dollars.
    private String name;
    private double price;  // In Dollars


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

    public String displayMenuItem() {
        return "Name = " + name + ", Price = $" + price;
    }
}

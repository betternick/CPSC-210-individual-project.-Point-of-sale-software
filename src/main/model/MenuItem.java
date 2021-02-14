package model;

public class MenuItem {
    //Represents the individual menu items with a name and price in dollars.
    private String name;
    private double price;  // In Dollars


    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String displayMenuItem() {
        return "Name = " + name + ", Price = $" + price;
    }
}

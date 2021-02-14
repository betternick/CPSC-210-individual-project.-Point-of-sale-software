package model;

import java.util.Date;

public class Order {
    //Represents an order placed at the restaurant with name of Item ordered and Date of order.

    private MenuItem menuItem;
    private String date;

    /*
     * REQUIRES: menuItem has a non-zero length.
     * MODIFIES: this
     * EFFECTS: Creates an order. Given menuItem is assigned to MenuItem. A string input of date is also entered.
     */
    public Order(MenuItem menuItem, String date) {
        this.menuItem = menuItem;
        this.date = date;
    }


    /*
     * EFFECTS: Displays name, price and date of order.
     */
    public String displayOrder() {
        return "Name = " + menuItem.getName() + ", Price = $" + menuItem.getPrice() + ", Date: " + date;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public String getDate() {
        return date;
    }
}
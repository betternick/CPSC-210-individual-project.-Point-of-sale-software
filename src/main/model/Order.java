package model;

import java.util.Date;

public class Order {
    //Represents an order placed at the restaurant with name of Item ordered and Date of order.

    private MenuItem menuItem;
    private String date;

    public Order(MenuItem menuItem, String date) {
        this.menuItem = menuItem;
        this.date = date;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public String getDate() {
        return date;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String displayOrder() {
        return "Name = " + menuItem.getName() + ", Price = $" + menuItem.getPrice() + ", Date: " + date;
    }

}
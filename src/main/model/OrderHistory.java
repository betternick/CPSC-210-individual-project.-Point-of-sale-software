package model;

import java.util.ArrayList;

public class OrderHistory {
    //Represents the collection of all the Orders placed at the restaurant.

    ArrayList<Order> orderItems = new ArrayList(); // data structure to store orders.

    /*
     * MODIFIES: this
     * EFFECTS: Adds an order to the list of Order history.
     */
    public void addOrder(Order singleOrder) { //to add order to order history

        this.orderItems.add(singleOrder);
    }

    public ArrayList<Order> getOrders() {
        return orderItems;
    }


}

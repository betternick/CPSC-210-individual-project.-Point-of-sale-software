package model;

import java.util.ArrayList;

public class Menu {
    //Represents the menu of the restaurant. Holds a collection of individual MenuItem(s).


    ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>(); // data structure to store menu items.

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



}

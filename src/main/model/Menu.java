package model;

import java.util.ArrayList;

public class Menu {
    //Represents the menu of the restaurant. Holds a collection of individual MenuItem(s).


    ArrayList<MenuItem> menuItems = new ArrayList(); // data structure to store menu items.


    public void addMenuItem(MenuItem menuItem) { //to add menu item to menu
        this.menuItems.add(menuItem);
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }



}

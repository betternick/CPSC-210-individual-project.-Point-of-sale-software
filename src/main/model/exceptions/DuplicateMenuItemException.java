package model.exceptions;

import model.MenuItem;

public class DuplicateMenuItemException extends Exception {

    public DuplicateMenuItemException(MenuItem menuItem) {
        super("Menu Item: " + menuItem.getName() + " already exists");
    }

}

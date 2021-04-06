package ui;

import model.*;
import model.Menu;
import model.MenuItem;
import model.exceptions.DuplicateMenuItemException;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class POS {
    // The POS application.
    private static final String JSON_STORE = "./data/menu.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    Scanner in = new Scanner(System.in); //taken from Teller app example
    private Menu menu;
    private OrderHistory orderHistory;

    /*
     * MODIFIES: this
     * EFFECTS: Creates Menu. Initializes OrderHistory.
     */
    public POS() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        menu = new Menu();
        orderHistory = new OrderHistory();


    }


    // MODIFIES: this
    // EFFECTS: loads menu from file
    //Code taken from JsonSerializationDemo (CPSC 210)
    public void loadMenu() {
        try {
            menu = jsonReader.read();
            new PlaySound("data/loadedsuccess.wav");
            System.out.println("Loaded " + menu.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    // EFFECTS: saves the menu to file
    //Code taken from JsonSerializationDemo (CPSC 210)
    public void saveMenu() {
        try {
            jsonWriter.open();
            jsonWriter.write(menu);
            jsonWriter.close();
            new PlaySound("data/savedsuccess.wav");
            System.out.println("Saved " + menu.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: Displays menu items.
    public void displayMenu(JTextArea displayWindow) {
        displayWindow.setText("");

        for (MenuItem i : menu.getMenuItems()) {
            displayWindow.append(i.displayMenuItem() + "\n");
        }
    }


    // MODIFIES: this
    // EFFECTS: Adds item to menu.
    // https://www.javatpoint.com/java-joptionpane
    // https://stackoverflow.com/questions/39758916/how-do-i-use-a-double-value-with-joptionpane-showinputdialog
    // Add Sound. URL: https://stackoverflow.com/questions/3780406/how-to-play-a-sound-alert-in-a-java-application
    public void addItemToMenuUI(JFrame f) {
        String newItemName = JOptionPane.showInputDialog(f, "Enter Name of Item");
        if (newItemName == null || newItemName.isEmpty()) {
            JOptionPane.showMessageDialog(f, "You must enter at least 1 character");
            return;
        } else {
            try {
                String priceInput = JOptionPane.showInputDialog(f, "Enter Price of Item");
                if (priceInput != null && !priceInput.isEmpty()) {
                    Double newItemPrice = Double.parseDouble(priceInput);
                    menu.addMenuItem(new MenuItem(newItemName, newItemPrice));
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(f, "Menu Item successfully added");
                }
            } catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(f, "Invalid number");
            } catch (DuplicateMenuItemException d) {
                JOptionPane.showMessageDialog(f, d.getMessage());
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: Places an order after Item name is provided. Checks that menu item exists. Checks that
    // amount paid is more than price of item. Provides output of change due to customer. Adds order
    // to OrderHistory. Provides confirmation if order is successful.
    public void placeOrderUI(JFrame f) {
        String orderItemName = JOptionPane.showInputDialog(f, "Enter Name of Item");
        Boolean foundItem = false;
        for (MenuItem i : menu.getMenuItems()) {
            if (i.getName().equalsIgnoreCase(orderItemName)) {
                foundItem = true;
                Double amountPaid = Double.parseDouble(JOptionPane.showInputDialog(f, "Enter Amount Paid By Customer"));
                if (amountPaid < i.getPrice()) {
                    JOptionPane.showMessageDialog(f, "Amount insufficient. Item price is $ " + i.getPrice());
                    break;
                }
                Double changeDue = amountPaid - i.getPrice();
                JOptionPane.showMessageDialog(f, "Change Due $" + changeDue);
                Order newOrder = new Order(i, LocalDate.now().toString());
                orderHistory.addOrder(newOrder);
                JOptionPane.showMessageDialog(f, "Order placed successfully");
            }
        }
        if (!foundItem) {
            JOptionPane.showMessageDialog(f, "No such menu item exists.");
        }
    }


    // MODIFIES: this
    // EFFECTS: Removes given Menu Item from the menu. Provides confirmation if successful.
    public void removeMenuItemUI(JFrame f) {
        String itemToRemove = JOptionPane.showInputDialog(f, "Enter Name of Item To Remove");
        if (menu.removeMenuItem(itemToRemove)) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(f, "Item Removed.");
        } else {
            JOptionPane.showMessageDialog(f, "Item does not exist.");
        }
    }


    // EFFECTS: Displays list of all orders placed. Indicates if no orders have been placed.
    public void viewOrderUI(JTextArea displayWindow) {
        displayWindow.setText("");
        if (orderHistory.getOrders().isEmpty()) {
            displayWindow.append("No orders have been placed. \n");
        } else {
            for (Order i : orderHistory.getOrders()) {
                displayWindow.append(i.displayOrder() + "\n");
            }
        }
    }
}

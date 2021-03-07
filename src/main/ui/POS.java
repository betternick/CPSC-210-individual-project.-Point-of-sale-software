package ui;

import model.Menu;
import model.MenuItem;
import model.Order;
import model.OrderHistory;
import persistence.JsonReader;
import persistence.JsonWriter;

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
        loadMenu();

    }
    //EFFECTS: Runs the POS application. Processes user commands.

    public void startPOS() {
        while (true) {
            String usersInput = promptAndGetUserInput();
            if (usersInput.equals("1")) {
                displayMenu();
            } else if (usersInput.equals("2")) {
                addItemToMenuUI();
            } else if (usersInput.equals("3")) {
                placeOrderUI();
            } else if (usersInput.equals("4")) {
                viewOrderUI();
            } else if (usersInput.equals("5")) {
                loadMenu();
            } else if (usersInput.equals("6")) {
                saveMenu();
            } else if (usersInput.equals("7")) {
                break;
            }
        }
        System.out.println(" GoodBye!");
    }

    // EFFECTS: Displays options menu to user.

    private String promptAndGetUserInput() {
        System.out.println(" "); //Just to add one line space from previous readout.
        System.out.println(" Press 1 to Show Menu");
        System.out.println(" Press 2 to Add Menu Item");
        System.out.println(" Press 3 to Order");
        System.out.println(" Press 4 to View Orders");
        System.out.println(" Press 5 to load menu");
        System.out.println(" Press 6 to save menu");
        System.out.println(" Press 7 to Quit");

        return in.nextLine(); //taken from Teller app example
    }

    // MODIFIES: this
    // EFFECTS: loads menu from file
    //Code taken from JsonSerializationDemo (CPSC 210)
    private void loadMenu() {
        try {
            menu = jsonReader.read();
            System.out.println("Loaded " + menu.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    // EFFECTS: saves the menu to file
    //Code taken from JsonSerializationDemo (CPSC 210)
    private void saveMenu() {
        try {
            jsonWriter.open();
            jsonWriter.write(menu);
            jsonWriter.close();
            System.out.println("Saved " + menu.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: Displays menu items.

    private void displayMenu() {
        for (MenuItem i : menu.getMenuItems()) {
            System.out.println(i.displayMenuItem());
        }
    }

    // REQUIRES: Menu item name must not be blank spaces.
    // MODIFIES: this
    // EFFECTS: Adds item to menu.
    private void addItemToMenuUI() {
        System.out.println(" Enter Name of Item");
        String newItemName = in.nextLine();
        if (newItemName.isEmpty()) {
            System.out.println(" You must enter at least 1 character");
        }
        if (menu.contains(newItemName)) {
            System.out.println(" That item already exists");
        } else {
            System.out.println(" Enter Price of Item");
            Double newItemPrice = in.nextDouble();
            in.nextLine(); //junk next line character.
            menu.addMenuItem(new MenuItem(newItemName, newItemPrice));
            System.out.println(" Menu Item successfully added");
        }

    }




    // MODIFIES: this
    // EFFECTS: Places an order after Item name is provided. Checks that menu item exists. Checks that
    // amount paid is more than price of item. Provides output of change due to customer. Adds order
    // to OrderHistory. Provides confirmation if order is successful.

    private void placeOrderUI() {
        System.out.println(" Enter Name of Item");
        String orderItemName = in.nextLine();
        Boolean foundItem = false;
        for (MenuItem i : menu.getMenuItems()) {
            if (i.getName().equalsIgnoreCase(orderItemName)) {
                foundItem = true;
                System.out.println(" Enter Amount Paid By Customer");
                Double amountPaid = in.nextDouble();
                in.nextLine(); //junk next line character.
                if (amountPaid < i.getPrice()) {
                    System.out.println(" Amount insufficient. Item price is $ " + i.getPrice());
                    break;
                }
                Double changeDue = amountPaid - i.getPrice();
                System.out.println(" Change Due $" + changeDue);
                Order newOrder = new Order(i, LocalDate.now().toString());
                orderHistory.addOrder(newOrder);
                System.out.println(" Order placed successfully");
            }
        }
        if (foundItem == false) {
            System.out.println(" No such menu item exists.");
        }
    }

    // EFFECTS: Displays list of all orders placed. Indicates if no orders have been placed.
    private void viewOrderUI() {
        if (orderHistory.getOrders().isEmpty()) {
            System.out.println(" No orders have been placed.");
        } else {
            for (Order i : orderHistory.getOrders()) {
                System.out.println(i.displayOrder());
            }
        }
    }
}

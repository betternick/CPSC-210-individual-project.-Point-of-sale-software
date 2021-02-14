package ui;

import model.Menu;
import model.MenuItem;
import model.Order;
import model.OrderHistory;

import java.time.LocalDate;
import java.util.Scanner;

public class POS {
    // The POS application.
    Scanner in = new Scanner(System.in); //taken from Teller app example
    private Menu menu;
    private OrderHistory orderHistory;

    public POS() {
        menu = new Menu();
        menu.addMenuItem(new MenuItem("Burger", 5));
        menu.addMenuItem(new MenuItem("Fries", 2));
        menu.addMenuItem(new MenuItem("Drink", 1.5));
        orderHistory = new OrderHistory();


    }

    public void startPOS() {
        while (true) {
            String usersInput = promptAndGetUserInput();

            if (usersInput.equals("1")) {
                displayMenu();
            }
            if (usersInput.equals("2")) {
                addItemToMenuUI();
            }
            if (usersInput.equals("3")) {
                placeOrderUI();
            }
            if (usersInput.equals("4")) {
                viewOrderUI();
            }
        }
    }

    private String promptAndGetUserInput() {
        System.out.println(" Press 1 to Show Menu");
        System.out.println(" Press 2 to Add Menu Item");
        System.out.println(" Press 3 to Order");
        System.out.println(" Press 4 to View Orders");

        return in.nextLine(); //taken from Teller app example
    }

    private void displayMenu() {
        for (MenuItem i : menu.getMenuItems()) {
            System.out.println(i.displayMenuItem());
        }
    }

    private void addItemToMenuUI() {
        System.out.println(" Enter Name of Item");
        String newItemName = in.nextLine();
   //     if (newItemName.isEmpty()) {
   //         System.out.println(" You must enter at least 1 character");
    //        break;
   //     }
        System.out.println(" Enter Price of Item");
        Double newItemPrice = in.nextDouble();
        in.nextLine(); //junk next line character.
        menu.addMenuItem(new MenuItem(newItemName, newItemPrice));
        System.out.println(" Menu Item successfully added");
    }

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

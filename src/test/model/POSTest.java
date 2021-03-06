package model;

import model.exceptions.DuplicateMenuItemException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class POSTest {
    private Menu testMenu = new Menu();
    private Menu testMenu2 = new Menu("Lunch Menu");
    private MenuItem testMenuItem = new MenuItem("Taco", 12);
    private MenuItem testMenuItem2 = new MenuItem("Magic Taco", -2);
    private MenuItem testMenuItem3 = new MenuItem("Chicken Taco", 15);
    private Order testOrder = new Order(testMenuItem, LocalDate.now().toString());
    private OrderHistory testOrderHistory = new OrderHistory();

    // @BeforeEach

    @Test
    void testConstructors() {
        assertEquals(testMenu.getMenuItems().size(), 0);
        assertEquals("Lunch Menu", testMenu2.getName());
        assertEquals(testMenuItem.getName(), "Taco");
        assertEquals(testMenuItem.getPrice(), 12);
        assertEquals(testOrder.getMenuItem(), testMenuItem);
        assertEquals(testOrder.getDate(), LocalDate.now().toString());
        assertTrue(testOrderHistory.getOrders().isEmpty());
    }

    @Test
    void testAddMenuItem() {
        assertEquals(testMenu.getMenuItems().size(), 0);
        try {
            testMenu.addMenuItem(testMenuItem);
            assertEquals(testMenu.getMenuItems().size(), 1);
            assertEquals(testMenuItem.getName(), "Taco");
            assertEquals(testMenuItem.getPrice(), 12);
        } catch (DuplicateMenuItemException d) {
            fail("DuplicateMenuItemException should not have been thrown");
        }
    }

    @Test
    void testRemoveMenuItem() {
        try {
            assertEquals(testMenu.getMenuItems().size(), 0);
            testMenu.addMenuItem(testMenuItem);
            assertEquals(testMenu.getMenuItems().size(), 1);
            assertEquals(testMenu.getMenuItems().get(0).getName(), "Taco");
            assertEquals(testMenu.getMenuItems().get(0).getPrice(), 12);
            testMenu.addMenuItem(testMenuItem3);
            assertEquals(testMenu.getMenuItems().get(1).getName(), "Chicken Taco");
            assertEquals(testMenu.getMenuItems().get(1).getPrice(), 15);
            assertEquals(testMenu.getMenuItems().size(), 2);
            testMenu.removeMenuItem("Tacoy");
            assertEquals(testMenu.getMenuItems().size(), 2);
            testMenu.removeMenuItem("Taco");
            assertEquals(testMenu.getMenuItems().size(), 1);
            assertEquals(testMenu.getMenuItems().get(0).getName(), "Chicken Taco");
            assertEquals(testMenu.getMenuItems().get(0).getPrice(), 15);
        } catch (DuplicateMenuItemException d) {
            fail("DuplicateMenuItemException should not have been thrown");
        }
    }

    @Test
    void testDuplicateMenuItem() {
        try {
            assertEquals(testMenu.getMenuItems().size(), 0);
            testMenu.addMenuItem(testMenuItem);
            assertEquals(testMenu.getMenuItems().size(), 1);
            assertEquals(testMenuItem.getName(), "Taco");
            assertEquals(testMenuItem.getPrice(), 12);
            testMenu.addMenuItem(testMenuItem);
            fail("DuplicateMenuItemException should have been thrown");
        } catch (DuplicateMenuItemException d) {
            //DuplicateMenuItemException was thrown and caught;
        }

    }

    @Test
    void testDisplayMenuItem() {
        assertEquals(testMenuItem.displayMenuItem(), "Name = Taco, Price = $12.0");
    }

    @Test
    void testDisplayOrder() {
        assertEquals(testOrder.displayOrder(), "Name = Taco, Price = $12.0, Date: " + LocalDate.now().toString());
    }

    @Test
    void testAddOrder() {
        assertTrue(testOrderHistory.getOrders().isEmpty());
        testOrderHistory.addOrder(testOrder);
        assertEquals(testOrderHistory.getOrders().size(), 1);
        assertEquals(testOrderHistory.getOrders().get(0).getMenuItem().getName(), "Taco");
        assertEquals(testOrderHistory.getOrders().get(0).getMenuItem().getPrice(), 12);
    }

    @Test
    void testMenuItemConstructorWithNegativeAmount() {
        assertEquals(testMenuItem2.getName(), "Magic Taco");
        assertEquals(testMenuItem2.getPrice(), 0);
    }


}
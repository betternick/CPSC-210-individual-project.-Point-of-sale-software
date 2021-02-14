package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class POStest {
    private Menu testMenu = new Menu();
    private MenuItem testMenuItem = new MenuItem("Taco",12);
    private MenuItem testMenuItem2 = new MenuItem("Magic Taco",-2);
    private Order testOrder = new Order(testMenuItem, LocalDate.now().toString());
    private OrderHistory testOrderHistory = new OrderHistory();

   // @BeforeEach

    @Test
    void testConstructors() {
        assertEquals(testMenu.getMenuItems().size(), 0);
        assertEquals(testMenuItem.getName(), "Taco");
        assertEquals(testMenuItem.getPrice(), 12);
        assertEquals(testOrder.getMenuItem(), testMenuItem);
        assertEquals(testOrder.getDate(), LocalDate.now().toString());
        assertTrue(testOrderHistory.getOrders().isEmpty());
    }

    @Test
    void testAddMenuItem() {
        assertEquals(testMenu.getMenuItems().size(), 0);
        testMenu.addMenuItem(testMenuItem);
        assertEquals(testMenu.getMenuItems().size(), 1);
        assertEquals(testMenuItem.getName(), "Taco");
        assertEquals(testMenuItem.getPrice(), 12);
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
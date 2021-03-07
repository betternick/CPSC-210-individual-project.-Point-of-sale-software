package persistence;


import model.MenuItem;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    //Code for this class taken from JsonSerializationDemo (CPSC 210)
    protected void checkMenuItem(String name, Double price, MenuItem menuItem) {
        assertEquals(name, menuItem.getName());
        assertEquals(price, menuItem.getPrice());
    }
}

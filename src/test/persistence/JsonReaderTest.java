package persistence;

import model.Menu;
import model.MenuItem;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {
    //Code taken from JsonSerializationDemo (CPSC 210)

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Menu menu = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMenu() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMenu.json");
        try {
            Menu menu = reader.read();
            assertEquals("Lunch Menu", menu.getName());
            assertEquals(0, menu.getMenuItems().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderSampleMenu() {
        JsonReader reader = new JsonReader("./data/testReaderSampleMenu.json");
        try {
            Menu menu = reader.read();
            assertEquals("Breakfast Menu", menu.getName());
            List<MenuItem> menuItems = menu.getMenuItems();
            assertEquals(3, menuItems.size());
            checkMenuItem ("Eggs", 2.0, menuItems.get(0));
            checkMenuItem ("Orange Juice", 3.0, menuItems.get(1));
            checkMenuItem ("Toast", 2.0, menuItems.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
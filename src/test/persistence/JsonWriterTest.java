package persistence;


import model.MenuItem;
import model.Menu;
import model.exceptions.DuplicateMenuItemException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    //Code for this class taken from JsonSerializationDemo (CPSC 210)
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Menu menu = new Menu("Dinner Menu");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Menu menu = new Menu("Test Menu");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMenu.json");
            writer.open();
            writer.write(menu);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMenu.json");
            menu = reader.read();
            assertEquals("Test Menu", menu.getName());
            assertEquals(0, menu.getMenuItems().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterGeneralWorkroom() {
        try {
            Menu menu = new Menu("Drinks Menu");
            menu.addMenuItem(new MenuItem("Coke", 2));
            menu.addMenuItem(new MenuItem("Fanta", 1));
            JsonWriter writer = new JsonWriter("./data/testWriterSampleWorkroom.json");
            writer.open();
            writer.write(menu);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterSampleWorkroom.json");
            menu = reader.read();
            assertEquals("Drinks Menu", menu.getName());
            List<MenuItem> menuItems = menu.getMenuItems();
            assertEquals(2, menuItems.size());
            checkMenuItem("Coke", 2.0, menuItems.get(0));
            checkMenuItem("Fanta", 1.0, menuItems.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (DuplicateMenuItemException d) {
            fail("DuplicateMenuItemException should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDuplicateException() {
        try {
            Menu menu = new Menu("Drinks Menu");
            menu.addMenuItem(new MenuItem("Coke", 2));
            menu.addMenuItem(new MenuItem("Coke", 1));
            JsonWriter writer = new JsonWriter("./data/testWriterDuplicateException.json");
            writer.open();
            writer.write(menu);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterDuplicateException.json");
            menu = reader.read();
            assertEquals("Drinks Menu", menu.getName());
            List<MenuItem> menuItems = menu.getMenuItems();
            assertEquals(1, menuItems.size());
            checkMenuItem("Coke", 2.0, menuItems.get(0));
            checkMenuItem("Coke", 1.0, menuItems.get(1));
            fail("DuplicateMenuItemException should have been thrown");

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (DuplicateMenuItemException d) {
            // exception was correctly thrown;
        }
    }

}
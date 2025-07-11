package simulator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    @Test
    void testValidCoordinates() {
        Table table = new Table(5, 5);
        assertTrue(table.isValid(0, 0));
        assertTrue(table.isValid(4, 4));
        assertTrue(table.isValid(2, 3));
    }

    @Test
    void testInvalidCoordinates() {
        Table table = new Table(5, 5);
        assertFalse(table.isValid(-1, 0));
        assertFalse(table.isValid(0, -1));
        assertFalse(table.isValid(5, 2));
        assertFalse(table.isValid(2, 5));
    }
}
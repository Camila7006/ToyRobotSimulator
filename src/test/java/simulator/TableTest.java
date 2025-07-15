package simulator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    @Test
    void testValidCoordinates() {
        Table table = new Table(10, 10);
        assertTrue(table.isValid(0, 0));
        assertTrue(table.isValid(9, 9));
        assertTrue(table.isValid(5, 7));
    }

    @Test
    void testInvalidCoordinates() {
        Table table = new Table(10, 10);
        assertFalse(table.isValid(-1, 0));
        assertFalse(table.isValid(0, -1));
        assertFalse(table.isValid(10, 2));
        assertFalse(table.isValid(2, 10));
    }
}
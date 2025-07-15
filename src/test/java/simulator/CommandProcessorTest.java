package simulator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommandProcessorTest {
    private CommandProcessor proc;

    @BeforeEach
    void setUp() {
        Table table = new Table(10, 10);
        Robot robot = new Robot();
        proc = new CommandProcessor(table, robot);
    }

    @Test
    void testPlaceMoveReportSequence() {
        assertNull(proc.execute("PLACE 0,0,NORTH"));
        assertNull(proc.execute("MOVE"));
        String out = proc.execute("REPORT");
        assertEquals("0,1,NORTH", out);
    }

    @Test
    void testIgnoreBeforePlace() {
        assertNull(proc.execute("MOVE"));
        assertNull(proc.execute("LEFT"));
        assertNull(proc.execute("REPORT"));
    }

    @Test
    void testInvalidPlaceIgnored() {
        assertNull(proc.execute("PLACE 10,10,EAST"));
        assertNull(proc.execute("REPORT"));
    }

    @Test
    void testUnknownCommandIgnored() {
        assertNull(proc.execute("JUMP"));
        assertNull(proc.execute("REPORT"));
    }

    @Test
    void testRotationViaProcessor() {
        proc.execute("PLACE 1,1,EAST");
        proc.execute("LEFT");
        assertEquals("1,1,NORTH", proc.execute("REPORT"));
    }

    @Test
    void testBadFormatIgnored() {
        assertNull(proc.execute("PLACE abc"));
        assertNull(proc.execute("PLACE 1,2"));
        assertNull(proc.execute("PLACE 1,2,NORTH,EXTRA"));
        assertNull(proc.execute("REPORT now"));
    }

    @Test
    void testIgnoreEmptyAndWhitespaceLines() {
        assertNull(proc.execute(""));
        assertNull(proc.execute("  "));
        assertNull(proc.execute("\t"));

        assertNull(proc.execute("REPORT"));
    }

    @Test
    void testMoveAtBoundary() {
        proc.execute("PLACE 0,0,SOUTH");
        assertNull(proc.execute("MOVE"));
        assertEquals("0,0,SOUTH", proc.execute("REPORT"));

        proc.execute("PLACE 0,9,NORTH");
        assertNull(proc.execute("MOVE"));
        assertEquals("0,9,NORTH", proc.execute("REPORT"));

        proc.execute("PLACE 9,0, EAST");
        assertNull(proc.execute("MOVE"));
        assertEquals("9,0,EAST", proc.execute("REPORT"));

        proc.execute("PLACE 0,5,WEST");
        assertNull(proc.execute("MOVE"));
        assertEquals("0,5,WEST", proc.execute("REPORT"));

    }

    @Test
    void testMalformedPlaceCommands() {
        assertNull(proc.execute("PLACE")); // no argument
        assertNull(proc.execute("PLACE 1"));
        assertNull(proc.execute("PLACE 1,2"));
        assertNull(proc.execute("PLACE 1,2,NORTH,EX"));
        assertNull(proc.execute("PLACE x,y,NORTH"));
        assertNull(proc.execute("PLACE 1,1,UP"));

        proc.execute("PLACE 2,2,EAST");
        assertEquals("2,2,EAST", proc.execute("REPORT"));

    }

    @Test
    void testDispersedUnknownCommands() {
        proc.execute("PLACE 1,1,NORTH");
        proc.execute("JUMP");
        proc.execute("FLY 1,2");
        proc.execute("MOVE");
        assertEquals("1,2,NORTH", proc.execute("REPORT"));
    }

    @Test
    void testResetPlaceholder() {
        proc.execute("PLACE 1,1,SOUTH");
        proc.execute("MOVE");

        assertNull(proc.execute("RESET"));
        assertEquals("1,0,SOUTH", proc.execute("REPORT"));
    }

}
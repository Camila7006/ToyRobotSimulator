package simulator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommandProcessorTest {
    private CommandProcessor proc;

    @BeforeEach
    void setUp() {
        Table table = new Table(5, 5);
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
        assertNull(proc.execute("PLACE 5,5,EAST"));
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

}
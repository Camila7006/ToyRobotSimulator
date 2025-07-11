package simulator; 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RobotTest {

    private Robot robot; 
    void setUp() {
        robot = new Robot();
    }

    @Testvoid testPlaceAndReport(){
        assertNull(robot.report());
        robot.place(1,2,Direction.NORTH);
        assertEquals("1,2,NORTH",robot,report());
    }

    @Test
    void testMoveAndDirection() {
        robot.place(0, 0, Direction.EAST);
        Position next = robot.getNextPosition();
        assertEquals(new Position(1, 0), next);
        robot.move(next);
        assertEquals("1,0,EAST", robot.report());
    }

    @Test
    void testRotation() {
        robot.place(0, 0, Direction.NORTH);
        robot.left();
        assertEquals("0,0,WEST", robot.report());
        robot.right();
        robot.right();
        assertEquals("0,0,EAST", robot.report());
    }

    @Test
    void testIgnoreCommandsBeforePlace() {
        robot.move(new Position(0, 1));
        assertNull(robot.report());
        robot.left();
        assertNull(robot.report());
    }

}
package simulator;

public class Robot {
    private Position pos; // Current position of robot
    private Direction dir; // Current direction of robot

    public void place(int x, int y, Direction d) {
        if (x < 0 || x > 9 || y < 0 || y > 9) {
            return; // return nothing if outside playground
        }
        pos = new Position(x, y);
        dir = d;

    }

    public Position getNextPosition() {
        int x = pos.getX();
        int y = pos.getY();
        switch (dir) {
            case NORTH:
                y++;
                break;
            case SOUTH:
                y--;
                break;
            case EAST:
                x++;
                break;
            case WEST:
                x--;
                break;

        }

        return new Position(x, y);
    }

}

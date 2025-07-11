package simulator;

public class Robot {
    private Position pos; // Current position of robot
    private Direction dir; // Current direction of robot
    private boolean placed; // False until place method is called

    public Robot() {
        this.placed = false;
    }

    public void place(int x, int y, Direction d) {
        pos = new Position(x, y);
        dir = d;
        placed = true;

    }

    private boolean isReady() {
        return placed && pos != null && dir != null;
    }

    public Position getNextPosition() {
        if (!isReady())
            return null;
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

    // method moves one step ahead if placed=true and within limit
    public void move(Position next) {
        if (!isReady() || next == null)
            return;
        pos = next;
    }

    // rotetes robot 90° to the right
    public void right() {
        if (isReady()) {
            dir = dir.right();
        }
    }

    // rotates robot 90° to the left
    public void left() {
        if (isReady()) {
            dir = dir.left();
        }
    }

    public boolean isPlaced() {
        return placed;
    }

    // Prints out X, Y position and direction in terminal
    public String report() {
        if (!isReady())
            return null;

        return pos + "," + dir;

    }
}
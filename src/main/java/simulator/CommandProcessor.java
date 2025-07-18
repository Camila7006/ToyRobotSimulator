package simulator;

import java.util.Locale;

public class CommandProcessor {
    private final Table table;
    private final Robot robot;

    public CommandProcessor(Table table, Robot robot) {
        this.table = table;
        this.robot = robot;
    }

    private boolean validLine(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public String execute(String input) {
        if (!validLine(input))
            return null;
        String line = input.trim();

        String[] parts = line.split("\\s+", 2);
        String cmd = parts[0].toUpperCase(Locale.ROOT);

        if ("PLACE".equals(cmd)) {
            handlePlace(parts);
            return null;
        }

        if (!robot.isPlaced())
            return null;

        switch (cmd) {
            case "MOVE":
                handleMove();
                break;
            case "LEFT":
                robot.left();
                break;
            case "RIGHT":
                robot.right();
                break;
            case "REPORT":
                return robot.report();
            default:
                // ignore unknown commando
                break;
        }
        return null;
    }

    private void handlePlace(String[] parts) {
        if (parts.length < 2) {
            return;
        }
        String[] args = parts[1].split(",");
        if (args.length != 3) {
            return;
        }
        try {
            int x = Integer.parseInt(args[0].trim());
            int y = Integer.parseInt(args[1].trim());
            Direction d = Direction.valueOf(args[2].trim().toUpperCase(Locale.ROOT));
            if (table.isValid(x, y)) {
                robot.place(x, y, d);
            }
        } catch (IllegalArgumentException e) {

        }

    }

    private void handleMove() {
        Position next = robot.getNextPosition();
        if (next != null && table.isValid(next.getX(), next.getY())) {
            robot.move(next);
        }
    }

}
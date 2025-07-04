package simulator;

import java.util.Locale;

//klassen borde kanske döpas om till commandProcessor 
public class Simulator {
    private final Table table;
    private final Robot robot;

    public Simulator(Table table, Robot robot) {
        this.table = table;
        this.robot = robot;
    }

    public String execute(String input) {
        if (input == null)
            return null;
        String line = input.trim();
        if (line.isEmpty())
            return null;

        String[] parts = line.split("\\s+", 2);
        String cmd = parts[0].toUpperCase(Locale.ROOT);

        if ("PLACE".equals(cmd)) {
            if (parts.length < 2)
                return null;
            String[] args = parts[1].split(",");
            if (args.length != 3)
                return null;
            try {
                int x = Integer.parseInt(args[0].trim());
                int y = Integer.parseInt(args[1].trim());
                Direction d = Direction.valueOf(args[2].trim().toUpperCase(Locale.ROOT));
                if (table.isValid(x, y))
                    robot.place(x, y, d);
            } catch (Exception e) {

            }
            return null;
        }

        if (!robot.isPlaced())
            return null;

        switch (cmd) {
            case "MOVE":
                Position next = robot.getNextPosition();
                if (next != null && table.isValid(next.getX(), next.getY()))
                    robot.move(next);
                return null;
            case "LEFT":
                robot.left();
                return null;
            case "RIGHT":
                robot.right();
                return null;
            case "REPORT":
                return robot.report();
            default:
                return null;
        }
    }
}
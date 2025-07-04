package simulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Missing input file");
        }

        Table table = new Table(10, 10);
        Robot robot = new Robot();
        Simulator sim = new Simulator(table, robot);

        run(args[0], sim);
    }

    static void run(String filename, Simulator simulator) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String output = simulator.execute(line);

                if (output != null) {
                    System.out.println(output);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error reading file" + e.getMessage());
        }
    }
}
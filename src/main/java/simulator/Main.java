package simulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Missing input file");
        }
        String filename = args[0];
        Table table = new Table(10, 10);
        Robot robot = new Robot();
        CommandProcessor processor = new CommandProcessor(table, robot);

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String output = processor.execute(line);

                if (output != null) {
                    System.out.println(output);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file" + e.getMessage());
        }
    }
}
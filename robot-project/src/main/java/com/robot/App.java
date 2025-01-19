package com.robot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.robot.model.Robot;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        String[] bounds = scanner.nextLine().split(" ");
        int maxX = Integer.parseInt(bounds[0]);
        int maxY = Integer.parseInt(bounds[1]);

        List<Robot> results = new ArrayList<>();

        while (scanner.hasNextLine()) {

            String[] initialPosition = scanner.nextLine().split(" ");
            if (initialPosition.length != 3)
                break;
            int initial_x = Integer.parseInt(initialPosition[0]);
            int initial_y = Integer.parseInt(initialPosition[1]);
            char initial_orientation = initialPosition[2].charAt(0);

            Robot robot = new Robot(maxX, maxY, initial_x, initial_y, initial_orientation);

            if (!scanner.hasNextLine())
                break;
            String instructions = scanner.nextLine();
            if (instructions.isEmpty())
                break;
            robot.processInstructions(instructions);

            results.add(robot);

        }

        for (Robot result : results) {
            System.out.println(result.getPrintResult());
        }
        scanner.close();

    }
}

package com.robot.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Robot {
 private int maxX;
    private int maxY;
    private int x_coordinate;
    private int y_coordinate;
    private char orientation;

    private static final Map<Character, Character> leftTurnMap = new HashMap<>();
    private static final Map<Character, Character> rightTurnMap = new HashMap<>();

    static {
        leftTurnMap.put('N', 'W');
        leftTurnMap.put('W', 'S');
        leftTurnMap.put('S', 'E');
        leftTurnMap.put('E', 'N');

        rightTurnMap.put('N', 'E');
        rightTurnMap.put('E', 'S');
        rightTurnMap.put('S', 'W');
        rightTurnMap.put('W', 'N');
    }

    public Robot(int maxX, int maxY, int x_coordinate, int y_coordinate, char orientation) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
        this.orientation = orientation;
    }

    public void processInstructions(String instructions) {
        for (char instruction : instructions.toCharArray()) {
            switch (instruction) {
                case 'L': 
                    orientation = leftTurnMap.get(orientation);
                    break;
                case 'R': 
                    orientation = rightTurnMap.get(orientation);
                    break;
                case 'M': 
                    moveForward();
                    break;
            }
        }
    }

    private void moveForward() {
        switch (orientation) {
            case 'N': 
                if (y_coordinate + 1 <= maxY) {
                    y_coordinate++;
                }
                break;
            case 'E': 
                if (x_coordinate + 1 <= maxX) {
                    x_coordinate++;
                }
                break;
            case 'S': 
                if (y_coordinate - 1 >= 0) {
                    y_coordinate--;
                }
                break;
            case 'W': 
                if (x_coordinate - 1 >= 0) {
                    x_coordinate--;
                }
                break;
        }
      }


  public String getPrintResult() {
    return "(" + x_coordinate + ";" + y_coordinate + "), orientation: " + orientation;
  }
}

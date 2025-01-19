package com.robot;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.robot.model.Robot;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
       @Test
    public void testSimpleMovementNorth() {
        Robot robot = new Robot(5, 5, 1, 2, 'N');
        robot.processInstructions("M");
        assertEquals("(1;3), orientation: N", robot.getPrintResult());
    }

    @Test
    public void testTurningLeft() {
        Robot robot = new Robot(5, 5, 1, 2, 'N');
        robot.processInstructions("L");
        assertEquals("(1;2), orientation: W", robot.getPrintResult());
    }

    @Test
    public void testTurningRight() {
        Robot robot = new Robot(5, 5, 1, 2, 'N');
        robot.processInstructions("R");
        assertEquals("(1;2), orientation: E", robot.getPrintResult());
    }

    @Test
    public void testMovementWithTurn() {
        Robot robot = new Robot(5, 5, 1, 2, 'N');
        robot.processInstructions("LMLMLMLMM");
        assertEquals("(1;3), orientation: N", robot.getPrintResult());
    }

    @Test
    public void testSequentialRobotExecution() {
        Robot robot1 = new Robot(5, 5, 1, 2, 'N');
        robot1.processInstructions("LMLMLMLMM");
        assertEquals("(1;3), orientation: N", robot1.getPrintResult());

        Robot robot2 = new Robot(5, 5, 3, 3, 'E');
        robot2.processInstructions("MMRMMRMRRM");
        assertEquals("(5;1), orientation: E", robot2.getPrintResult());
    }

    @Test
    public void testBoundaryConditions() {
        Robot robot = new Robot(5, 5, 5, 5, 'N');
        robot.processInstructions("M"); 
        assertEquals("(5;5), orientation: N", robot.getPrintResult());

        robot = new Robot(5, 5, 0, 0, 'S');
        robot.processInstructions("M"); 
        assertEquals("(0;0), orientation: S", robot.getPrintResult());

        robot = new Robot(5, 5, 0, 0, 'W');
        robot.processInstructions("M"); 
        assertEquals("(0;0), orientation: W", robot.getPrintResult());
    }

    @Test
    public void testInvalidInstructions() {
        Robot robot = new Robot(5, 5, 1, 2, 'N');
        robot.processInstructions("MXLR"); 
        assertEquals("(1;3), orientation: N", robot.getPrintResult());
    }

    @Test
    public void testEdgeMovement() {
        Robot robot = new Robot(5, 5, 5, 0, 'E');
        robot.processInstructions("M"); 
        assertEquals("(5;0), orientation: E", robot.getPrintResult());
    }

    @Test
    public void testComplexMovement() {
        Robot robot = new Robot(10, 10, 5, 5, 'N');
        robot.processInstructions("MMRMMRMRRMLM");
        assertEquals("(6;7), orientation: W", robot.getPrintResult());
    }
}

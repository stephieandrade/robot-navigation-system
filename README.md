# Robot Navigation System

## Overview

This project implements a simple robot navigation system where a robot operates on a grid workspace. The robot can receive a series of movement and turning instructions to traverse the grid while staying within its boundaries. The system calculates the robot's final position and orientation after processing the given instructions.

## Problem Statement

The robot's initial position and orientation are defined by:

- **X-coordinate**: Horizontal position (0-indexed).
- **Y-coordinate**: Vertical position (0-indexed).
- **Orientation**: One of the four cardinal directions (‘N’ for North, ‘E’ for East, ‘S’ for South, ‘W’ for West).

The robot can receive the following instructions:

- `L`: Turn 90 degrees left.
- `R`: Turn 90 degrees right.
- `M`: Move forward by one unit in the current orientation.

### Constraints

- The grid is defined by its upper-right corner, with the bottom-left corner assumed to be `(0, 0)`.
- The robot cannot move outside the grid boundaries.

## Input Format

1. The first line specifies the grid boundaries as two integers: `maxX maxY`.
2. For each robot:
   - The first line specifies the initial position and orientation as `x_coordinate y_coordinate orientation`.
   - The second line specifies a string of movement instructions (a sequence of `L`, `R`, and `M`).

### Example Input

```
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
```

## Output Format

For each robot, the final position and orientation are printed.

### Example Output

```
(1;3), orientation: N
(5;1), orientation: E
```

## Implementation

The solution is implemented in Java, consisting of the following components:

### 1. `Robot` Class

This class encapsulates the robot's behavior and attributes, including:

- `maxX` and `maxY`: The grid boundaries.
- `x_coordinate` and `y_coordinate`: The robot's current position.
- `orientation`: The robot's current facing direction.
- Methods for processing instructions and updating the robot's state.

#### Key Methods

1. **Constructor**: Initializes the robot with its starting position, orientation, and grid boundaries.
2. **`processInstructions(String instructions)`**:
   - Iterates through each character in the instruction string.
   - Updates the robot's orientation (`L` or `R`) or position (`M`).
3. **`moveForward()`**:
   - Updates the robot's position based on its current orientation.
   - Ensures the robot does not move out of bounds.
4. **`getPrintResult()`**:
   - Returns the robot's final position and orientation in the required format.

#### Example Usage

```java
Robot robot = new Robot(5, 5, 1, 2, 'N');
robot.processInstructions("LMLMLMLMM");
System.out.println(robot.getPrintResult());
```

### 2. Orientation Management

The turning logic is implemented using `HashMap`s for efficiency:

- `leftTurnMap`: Maps the current orientation to the orientation after a left turn.
- `rightTurnMap`: Maps the current orientation to the orientation after a right turn.

#### Example:

```java
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
```

### 3. Input Processing

The program reads the input, initializes the robots, and processes their instructions sequentially.

#### Example Input Processing:

```java
Scanner scanner = new Scanner(System.in);
int maxX = scanner.nextInt();
int maxY = scanner.nextInt();
scanner.nextLine();

List<String> results = new ArrayList<>();
while (scanner.hasNextLine()) {
    String[] position = scanner.nextLine().split(" ");
    int x = Integer.parseInt(position[0]);
    int y = Integer.parseInt(position[1]);
    char orientation = position[2].charAt(0);

    Robot robot = new Robot(maxX, maxY, x, y, orientation);
    String instructions = scanner.nextLine();
    robot.processInstructions(instructions);

    results.add(robot.getPrintResult());
}
for (String result : results) {
    System.out.println(result);
}
```

## Design Highlights

1. **Efficiency**:

   - The use of `HashMap` for orientation changes ensures constant-time lookup for turns.
   - Movement logic is separate, making the code modular and easier to debug.

2. **Scalability**:

   - The design can be extended to handle additional commands or larger grids.
   - Adding diagonal movement, for example, would involve extending the `moveForward` method.

3. **Robustness**:
   - Boundary checks prevent the robot from moving outside the grid.
   - Input parsing ensures that invalid data is handled gracefully.

## Testing Data

### Unit Tests

The `RobotTest` class provides comprehensive unit tests for various scenarios. Below are some of the test cases used:

## Testing Data

The following test cases are used to validate the functionality of the `Robot` class:

### Test Case 1: Simple Movement North

- **Description**: Tests basic forward movement in the North direction.
- **Input**:
  - Grid size: (5, 5)
  - Initial position: (1, 2, N)
  - Instructions: `M`
- **Expected Output**: `(1;3), orientation: N`

### Test Case 2: Turning Left

- **Description**: Tests turning left from the initial position.
- **Input**:
  - Grid size: (5, 5)
  - Initial position: (1, 2, N)
  - Instructions: `L`
- **Expected Output**: `(1;2), orientation: W`

### Test Case 3: Turning Right

- **Description**: Tests turning right from the initial position.
- **Input**:
  - Grid size: (5, 5)
  - Initial position: (1, 2, N)
  - Instructions: `R`
- **Expected Output**: `(1;2), orientation: E`

### Test Case 4: Movement With Turn

- **Description**: Tests a combination of movement and turns.
- **Input**:
  - Grid size: (5, 5)
  - Initial position: (1, 2, N)
  - Instructions: `LMLMLMLMM`
- **Expected Output**: `(1;3), orientation: N`

### Test Case 5: Sequential Robot Execution

- **Description**: Tests the execution of multiple robots sequentially.
- **Input**:
  - Robot 1:
    - Grid size: (5, 5)
    - Initial position: (1, 2, N)
    - Instructions: `LMLMLMLMM`
  - Robot 2:
    - Grid size: (5, 5)
    - Initial position: (3, 3, E)
    - Instructions: `MMRMMRMRRM`
- **Expected Output**:
  - Robot 1: `(1;3), orientation: N`
  - Robot 2: `(5;1), orientation: E`

### Test Case 6: Boundary Conditions

- **Description**: Ensures that the robot does not move out of the grid boundaries.
- **Input**:
  - Robot 1:
    - Grid size: (5, 5)
    - Initial position: (5, 5, N)
    - Instructions: `M`
    - **Expected Output**: `(5;5), orientation: N`
  - Robot 2:
    - Grid size: (5, 5)
    - Initial position: (0, 0, S)
    - Instructions: `M`
    - **Expected Output**: `(0;0), orientation: S`
  - Robot 3:
    - Grid size: (5, 5)
    - Initial position: (0, 0, W)
    - Instructions: `M`
    - **Expected Output**: `(0;0), orientation: W`

### Test Case 7: Invalid Instructions

- **Description**: Tests how the system handles invalid instructions (e.g., unsupported characters).
- **Input**:
  - Grid size: (5, 5)
  - Initial position: (1, 2, N)
  - Instructions: `MXLR`
- **Expected Output**: `(1;3), orientation: N`

### Test Case 8: Edge Movement

- **Description**: Tests movement at the edge of the grid without exceeding the boundary.
- **Input**:
  - Grid size: (5, 5)
  - Initial position: (5, 0, E)
  - Instructions: `M`
- **Expected Output**: `(5;0), orientation: E`

### Test Case 9: Complex Movement

- **Description**: Tests a more complex series of instructions.
- **Input**:
  - Grid size: (10, 10)
  - Initial position: (5, 5, N)
  - Instructions: `MMRMMRMRRMLM`
- **Expected Output**: `(6;7), orientation: W`

## Example Execution

```java
public static void main(String[] args) {
    Robot robot1 = new Robot(5, 5, 1, 2, 'N');
    robot1.processInstructions("LMLMLMLMM");
    System.out.println(robot1.getPrintResult());

    Robot robot2 = new Robot(5, 5, 3, 3, 'E');
    robot2.processInstructions("MMRMMRMRRM");
    System.out.println(robot2.getPrintResult());
}
```

**Output**:

```
(1;3), orientation: N
(5;1), orientation: E
```

## Future Improvements

1. **Error Handling**:
   - Validate input to ensure instructions are within the expected format.
   - Handle invalid orientation characters gracefully.
2. **Dynamic Grid Expansion**:
   - Allow the grid size to dynamically expand based on the robot's movement.
3. **Visualization**:
   - Add a graphical representation of the robot's movement on the grid.

## Conclusion

The Robot Navigation System is a robust and efficient implementation that handles sequential robot movements on a grid. Its modular design and use of efficient data structures ensure scalability and maintainability.

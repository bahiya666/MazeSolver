import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {
    private String[] map;

    public Maze(String filename) {
        File file = new File(filename);

        // Check if the file exists
        if (!file.exists()) 
        {
            map = new String[0]; // Create an empty maze
        } 
        else 
        {
            try (Scanner scanner = new Scanner(file)) 
            {
                // Read the number of rows from the first line
                int numRows = Integer.parseInt(scanner.nextLine());
                map = new String[numRows];

                // Read subsequent lines and save them in the map array
                readMapFromFile(scanner, 0);
            } catch (FileNotFoundException e)
            {
                map = new String[0]; // Create an empty maze
            } catch (NumberFormatException e) 
            {
                map = new String[0]; // Create an empty maze
            }
        }
    }

    private void readMapFromFile(Scanner scanner, int row) 
    {
        if (row >= map.length) 
        {
            return;
        }
        if (scanner.hasNextLine()) 
        {
            map[row] = scanner.nextLine();
        }
        readMapFromFile(scanner, row + 1);
    }

    public Maze(Maze other) {
        map = new String[other.map.length];
        copyMap(other, 0);
    }

    private void copyMap(Maze other, int row) {
        if (row >= map.length) {
            return;
        }
        map[row] = other.map[row];
        copyMap(other, row + 1);
    }


    @Override
    public String toString() 
    {
        if (map.length == 0) {
            return "Empty Map";
        } else {
            return toStringHelper(0, new StringBuilder());
        }
    }
    
    private String toStringHelper(int index, StringBuilder result) {
        if (index >= map.length) {
            return result.toString();
        }
        result.append(map[index]);
        if (index < map.length - 1) {
            result.append("\n");
        }
        return toStringHelper(index + 1, result);
    }


    

    public boolean validSolution(int startX, int startY, int goalX, int goalY, LinkedList path) {
        // Check if the path is empty
        if (path == null || path.head == null || path.length() < 2)
            return false;

        if (path.head.x != startX || path.head.y != startY || path.head.x != goalX || path.head.y != goalY)
            return false;
    
        // Recursively check each coordinate in the path
        return validSolutionHelper(startX, startY, path.head, new LinkedList());
    }
    
    private boolean validSolutionHelper(int startX, int startY, CoordinateNode node, LinkedList visited) {
        // Base case: if node is null, all coordinates have been checked
        if (node == null) {
            return true;
        }
    
        // Check if the current coordinate is within the maze bounds and not a wall
        if (!isValidCoordinate(node.x, node.y) || isWall(node.x, node.y)) {
            return false;
        }
    
        // Check if the current coordinate has been visited before
        if (visited.contains(node.x, node.y)) {
            return false;
        }
    
        // Check if the current coordinate is adjacent to the previous coordinate
        if (!isValidMove(startX, startY, node.x, node.y)) {
            return false;
        }
    
        // Mark the current coordinate as visited
        visited.append(node.x, node.y);
    
        // Recursively check the next coordinate in the path
        return validSolutionHelper(node.x, node.y, node.next, visited);
    }
    
    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < map.length && y >= 0 && y < map[0].length();
    }
    
    private boolean isWall(int x, int y) {
        return map[x].charAt(y) == '#';
    }
    
    private boolean isValidMove(int startX, int startY, int newX, int newY) {
        return Math.abs(newX - startX) <= 1 && Math.abs(newY - startY) <= 1 && !(newX == startX && newY == startY);
    }
    
    
    public String solve(int x, int y, int goalX, int goalY) {
        // Create a new StringBuilder to store the solution
        StringBuilder solution = new StringBuilder();
    
        // Create a new LinkedList to store the path
        LinkedList path = new LinkedList();
        
        // If a valid solution is found, return the solution
        // Check if a valid solution exists using validSolution method
        if (!validSolution(x, y, goalX, goalY, path)) {
            return "No valid solution exists";
        }

        // Find the solution recursively
        findSolution(x, y, goalX, goalY, solution, path);
        solution.insert(0, "Solution\n");

        return solution.toString();
    }
    
    private boolean findSolution(int x, int y, int goalX, int goalY, StringBuilder solution, LinkedList path) 
    {
        // Check if the current position is outside the maze boundaries or a wall
        if (x < 0 || y < 0 || x >= map.length || y >= map[0].length() || map[x].charAt(y) == 'X') {
            return false;
        }

        // Check if the current position is the goal
        if (x == goalX && y == goalY) {
            // Construct the solution maze and path
            constructSolution(map, path, solution);
            return true;
        }

        // Mark the current position as visited
        map[x] = map[x].substring(0, y) + "@" + map[x].substring(y + 1);

        // Add the current position to the path
        path.append(x, y);

        // Move in the order: Left, Up, Down, Right
        if (findSolution(x, y - 1, goalX, goalY, solution, path) ||
            findSolution(x - 1, y, goalX, goalY, solution, path) ||
            findSolution(x + 1, y, goalX, goalY, solution, path) ||
            findSolution(x, y + 1, goalX, goalY, solution, path)) {
            return true;
        }

        // If no valid solution is found from the current position, backtrack
        map[x] = map[x].substring(0, y) + "." + map[x].substring(y + 1);
        deleteTail(path);
        
            return false;
    }
    
    private void constructSolution(String[] map, LinkedList path, StringBuilder solution) {
        // Replace the start and end points in the maze
        CoordinateNode start = path.head;
        map[start.x] = map[start.x].substring(0, start.y) + "S" + map[start.x].substring(start.y + 1);
    
        // Replace the end point with "E"
        CoordinateNode end = getTail(path);
        map[end.x] = map[end.x].substring(0, end.y) + "E" + map[end.x].substring(end.y + 1);
    
        // Call the recursive helper function to traverse the path and replace visited positions
        traverseAndReplace(map, path.head.next, solution);
    
        // Append the path to the solution
        solution.append(path.toString());
    }
    

    
    
    private void traverseAndReplace(String[] map, CoordinateNode current, StringBuilder solution) {
        // Base case: If current node is null, return
        if (current == null) {
            return;
        }
        
        // Replace visited positions with "@"
        map[current.x] = map[current.x].substring(0, current.y) + "@" + map[current.x].substring(current.y + 1);
        
        // Recursive call to traverse the next node in the path
        traverseAndReplace(map, current.next, solution);
    }
    
    
    public static void deleteTail(LinkedList list) {
        if (list == null || list.head == null) {
            return;
        }
        if (list.head.next == null) {
            list.head = null;
            return;
        }
        deleteTailHelper(list.head);
    }

    private static void deleteTailHelper(CoordinateNode current) {
        if (current.next.next == null) {
            current.next = null;
            return;
        }
        deleteTailHelper(current.next);
    }

    public static CoordinateNode getTail(LinkedList list) {
        if (list == null || list.head == null) {
            return null;
        }
        return getTailHelper(list.head);
    }

    private static CoordinateNode getTailHelper(CoordinateNode current) {
        if (current.next == null) {
            return current;
        }
        return getTailHelper(current.next);
    }


    public LinkedList validStarts(int goalX, int goalY) {
        LinkedList validCoordinates = new LinkedList();
        boolean[][] visited = new boolean[map.length][map[0].length()];
        findValidStarts(0, 0, goalX, goalY, validCoordinates, visited);
        return validCoordinates;
    }
    
    private void findValidStarts(int x, int y, int goalX, int goalY, LinkedList validCoordinates, boolean[][] visited) {
        // Base case: if current coordinate is outside maze boundaries or is a wall or already visited
        if (x < 0 || y < 0 || x >= map.length || y >= map[x].length() || map[x].charAt(y) == 'X' || visited[x][y]) {
            return;
        }
    
        // Mark the current coordinate as visited
        visited[x][y] = true;
    
        // Check if a valid path exists from current coordinate to the goal
        if (validSolution(x, y, goalX, goalY, new LinkedList())) {
            // Add the current coordinate to the list of valid start coordinates
            validCoordinates.append(x, y);
        }
    
        // Recursive calls to explore neighboring coordinates
        findValidStarts(x + 1, y, goalX, goalY, validCoordinates, visited); // Right
        findValidStarts(x, y + 1, goalX, goalY, validCoordinates, visited); // Down
        findValidStarts(x - 1, y, goalX, goalY, validCoordinates, visited); // Left
        findValidStarts(x, y - 1, goalX, goalY, validCoordinates, visited); // Up
    }
    
    
}


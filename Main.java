public class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.append(5, 2);
        list.append(2, 3);
        list.append(4, 6);
        System.out.println("Original list: " + list.toString());
        System.out.println("Length of list: " + list.length());

        LinkedList reversedList = list.reversed();
        System.out.println("Reversed list: " + reversedList.toString());
        System.out.println("Does the list contain (3, 4)? " + list.contains(3, 4));
        System.out.println("Does the list contain (7, 8)? " + list.contains(7, 8));

        LinkedList anotherList = new LinkedList();
        anotherList.append(7, 8);
        anotherList.append(9, 10);
        list.appendList(anotherList);
        System.out.println("List after appending another List: " + list.toString());
        System.out.println("Length of list after appending another List: " + list.length());


        Maze maze = new Maze("input.txt");
        System.out.println(maze.toString());

        LinkedList path = new LinkedList();
        path.append(0, 0);
        path.append(0, 1);
        path.append(0, 2);
        path.append(1, 2);
        path.append(2, 2);
        path.append(2, 3);

        int startX = 0;
        int startY = 0;
        int goalX = 2;
        int goalY = 3;
        boolean isValid = maze.validSolution(startX, startY, goalX, goalY, path);
        System.out.println("Is the path valid? " + isValid);

        LinkedList validStartsList = maze.validStarts(goalX, goalY);

        System.out.println("Valid starting coordinates for reaching goal at (" + goalX + ", " + goalY + "):");
        System.out.println(validStartsList.toString());

        System.out.println("Valid Solution: " + maze.solve(0, 0, 4, 4));
        System.out.println("Valid Solution/shorter: " + maze.solve(0, 0, 1, 1));
        System.out.println("Same: " + maze.solve(0, 0, 0, 0));
        System.out.println("No Valid Solution: " + maze.solve(0, 0, 2, 3));

        System.out.println(maze.solve(3, 3, 1, 0));
        System.out.println(maze.validStarts(0, 0));
    }
}
Maze Solver Program
This project contains classes for solving maze problems using a linked list to represent coordinates and various algorithms for pathfinding within a maze. The classes are:

CoordinateNode: Represents a node containing coordinates (x, y) in the linked list.
LinkedList: A custom linked list implementation to manage a sequence of CoordinateNodes.
Main: The entry point of the program that demonstrates the functionality of the maze solver.
Maze: Handles reading a maze from a file, validating paths, and solving the maze.

Description
This program is designed to read a maze from a file, validate paths in the maze, and solve the maze using recursive backtracking. The maze is represented as a 2D grid, where walls are marked with #, open paths are marked with ., and start/end points are designated with S and E.

The core logic of the program revolves around managing and manipulating coordinates stored in a custom LinkedList class. The Maze class solves the maze by recursively finding paths, ensuring that each path is valid according to certain rules.

Classes and Methods

CoordinateNode
The CoordinateNode class represents a node in the linked list, storing a pair of coordinates (x, y), and a reference to the next node in the list.
  
  Constructor:
  CoordinateNode(int x, int y): Initializes a new node with coordinates x and y.
  Methods:
  toString(): Returns the string representation of the node in the format [x, y].


LinkedList
The LinkedList class represents a singly linked list of CoordinateNode objects. It provides various methods for managing the list of coordinates.
  
  Constructor:
  LinkedList(): Initializes an empty linked list.
  LinkedList(int x, int y): Initializes a linked list with a single node containing the coordinates x and y.
  Methods:
  append(int x, int y): Appends a new node with the given coordinates to the end of the list.
  appendList(LinkedList other): Appends all nodes from another linked list to the current list.
  contains(int x, int y): Checks if a node with the given coordinates exists in the list.
  toString(): Returns a string representation of the entire list in the format [x1,y1] -> [x2,y2] -> ....
  length(): Returns the number of nodes in the list.
  reversed(): Returns a new linked list with the nodes in reverse order.
  copy(): Returns a new copy of the current linked list.
  
Maze
The Maze class represents a maze read from a file. It provides methods to validate solutions, find valid start points, and solve the maze.

  Constructor:
  Maze(String filename): Initializes the maze by reading the map from a file.
  Methods:
  validSolution(int startX, int startY, int goalX, int goalY, LinkedList path): Validates if the given path is a valid solution from the start to the goal.
  solve(int x, int y, int goalX, int goalY): Attempts to find a solution from the start (x, y) to the goal (goalX, goalY) using backtracking.
  validStarts(int goalX, int goalY): Finds all valid start coordinates that can lead to the goal.
  toString(): Returns the string representation of the maze.
  Main
  The Main class demonstrates how to use the LinkedList and Maze classes. It performs the following tasks:

Creates a linked list and appends coordinates.
Demonstrates appending one list to another and reversing the list.
Solves a maze and checks for valid solutions.
Finds all valid start points for a given goal.
Methods:
main(String[] args): The entry point of the program, where the demonstration occurs.

Usage
Prepare a Maze File (input.txt):

The maze file should be a text file with the following format:
The first line contains the number of rows in the maze.
Each subsequent line represents a row of the maze.
The maze can contain # for walls, . for open paths, and S for the start point and E for the end point.
Example:

5
#####
#S..#
#.###
#...#
####E

Running the Program:
javac *.java
java Main

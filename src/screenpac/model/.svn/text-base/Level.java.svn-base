package screenpac.model;

import games.pacman.maze.MazeFour;
import games.pacman.maze.MazeOne;
import games.pacman.maze.MazeThree;
import games.pacman.maze.MazeTwo;

import java.util.ArrayList;

public class Level {
    public static void main(String[] args) {
        System.out.println(mazes.size());
        System.out.println("Made the mazes");
        for (int i=0; i<20; i++) {
            System.out.println(i + "\t " + edibleTime(i));
        }
    }
    
    static ArrayList<Maze> mazes;
    // load the mazes
    static {
        mazes = new ArrayList<Maze>();

        Maze maze1 = new Maze();
        maze1.processOldMaze(MazeOne.getMaze());
        mazes.add(maze1);
        System.out.println("Processed maze 1");

        Maze maze2 = new Maze();
        maze2.processOldMaze(MazeTwo.getMaze());
        mazes.add(maze2);
        System.out.println("Processed maze 2");

        Maze maze3 = new Maze();
        maze3.processOldMaze(MazeThree.getMaze());
        mazes.add(maze3);
        System.out.println("Processed maze 3");

        Maze maze4 = new Maze();
        maze4.processOldMaze(MazeFour.getMaze());
        mazes.add(maze4);
        System.out.println("Processed maze 4");
    }

    static Maze getMaze(int level) {
        int index = level % mazes.size();
        return mazes.get(index);
    }

    static int maxEdibleTime = 150;
    static double edibleTimeReductionFactor = 0.67;
    static int edibleTime(int level) {
        return (int) (maxEdibleTime *
                Math.pow(edibleTimeReductionFactor, level));
    }
}

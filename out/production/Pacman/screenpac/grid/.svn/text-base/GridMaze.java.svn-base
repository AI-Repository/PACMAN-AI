package screenpac.grid;

import utilities.JEasyFrame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GridMaze implements GridConstants {
    int[][] grid;
    int w, h;
    int size = 21;
    static Map<Character,Integer> charMap = new HashMap<Character,Integer>();
    static {
        charMap.put('#',wall);
        charMap.put('.',pill);
        charMap.put(' ',empty);
        charMap.put('o',power);
        charMap.put('G',ghost);
        charMap.put('c',agent);
    }

    public static void main(String[] args) throws Exception {
        GridMaze gm = new GridMaze();
        gm.read("screenpac/data/mazes/SmallTest2.txt");
        GridView view = new GridView(gm);
        new JEasyFrame(view, "Maze View", true);
    }

    public void read(String file) throws Exception {
        ArrayList<String> lines = new ArrayList<String>();
        String line;
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (!(line.startsWith("//") || line.length() == 0)) {
                lines.add(line);
            }
        }
        w = lines.get(0).length();
        h = lines.size();
        System.out.println(w + " w h " + h);
        grid = new int[w][h];
        int y=0;
        for(String s : lines) {
            int x = 0;
            for (char c : s.toCharArray()) {
                // System.out.println("c = " + c);
                Integer col = charMap.get(c);
                if (col == null) col = pill;
                // System.out.println("col: " + col);
                grid[x][y] = col;
                x++;
            }
            y++;
        }
    }

    public void flipWall(int x, int y) {
        if (grid[x][y] == empty) {
            grid[x][y] = wall;
        } else {
            grid[x][y] = empty;
        }
    }

    public void flipPill(int x, int y) {
        if (grid[x][y] == pill) {
            grid[x][y] = power;
        } else {
            grid[x][y] = pill;
        }

    }
}

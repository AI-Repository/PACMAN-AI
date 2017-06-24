package screenpac.grid;

import javax.swing.*;
import java.awt.*;

public class GridView extends JComponent implements GridConstants {

    // todo: chamfer the corners
    // done: add a mouse listener to enable maze editing
    // todo: add a key listener to enable cursor-key based editing
    GridMaze gm;
    int size;
    int offset;
    static Color bg = Color.magenta;
    static Color corridor = Color.black;
    static Color[] colors = new Color[GridMaze.charMap.size()];
    static int ln = 8;
    static int pn = 6;

    static {
        colors[pill] = corridor;
        colors[wall] = Color.pink;
        colors[empty] = corridor;
        colors[agent] = corridor;
        colors[power] = corridor;
        colors[ghost] = corridor;
    }

    public GridView(GridMaze gm) {
        this.gm = gm;
        size = gm.size;
        offset = size / 2;
    }

    public void paintComponent(Graphics g) {
        g.setColor(bg);
        g.fillRect(0, 0, getWidth(), getHeight());
        // System.out.println(gm.w + "\t gm " + gm.h);
        for (int i = 0; i < gm.w; i++) {
            for (int j = 0; j < gm.h; j++) {
                if (gm.grid[i][j] != wall) {
                    g.setColor(Color.blue);
                    g.fillRect(i * size - ln, j * size - ln, size+ln*2, size+ln*2);
                    chamfer(g, i, j);
                }

            }
        }
        g.setColor(Color.black);
        for (int i = 0; i < gm.w; i++) {
            for (int j = 0; j < gm.h; j++) {
                if (gm.grid[i][j] != wall) {
                    // g.fillRect(i * size-pn, j * size-pn, size+pn*2, size+pn*2);
                }
            }
        }
        for (int i = 0; i < gm.w; i++) {
            for (int j = 0; j < gm.h; j++) {
                if (gm.grid[i][j] != wall) {
                    drawLines(g, i, j);
                }
                g.setColor(Color.white);
                g.fillRect(i * size + size / 2, j * size + size / 2, 1, 1);
            }
        }
        System.out.println("Repainted");
    }

    public void drawLines(Graphics g, int x, int y) {
        // look at neighbours to the right and below
        g.setColor(Color.blue);
        int sx = size * x + offset;
        int sy = size * y + offset;
        if (x+1 == gm.w || gm.grid[x + 1][y] != wall) {
            g.drawLine(sx, sy, sx + size, sy);
        }
        if (y+1 == gm.h || gm.grid[x][y + 1] != wall) {
            g.drawLine(sx, sy, sx, sy + size);
        }
    }

    public void chamfer(Graphics g, int x, int y) {
        // look at diagonal neighbours below
        g.setColor(Color.green);
        int sx = size * x + offset+25;
        int sy = size * y + offset+25;
        if (x+1 == gm.w || y+1 == gm.h || gm.grid[x + 1][y+1] != wall) {
            System.out.println("Diagonal");
            g.drawLine(sx, sy, sx + size, sy+size);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(gm.w * gm.size, gm.h * gm.size);
    }

}

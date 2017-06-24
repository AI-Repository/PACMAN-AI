package screenpac.grid;

import utilities.JEasyFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by IntelliJ IDEA.
 * User: sml
 * Date: 27-Mar-2010
 * Time: 15:15:49
 * To change this template use File | Settings | File Templates.
 */
public class MazeEditor {
    GridMaze gm;
    GridView gv;

    public static void main(String[] args) throws Exception {
        GridMaze gm = new GridMaze();
        gm.read("screenpac/data/mazes/SmallTest2.txt");
        GridView view = new GridView(gm);
        new JEasyFrame(view, "Maze View", true);
        MazeEditor me = new MazeEditor(gm, view);
    }

    public MazeEditor(GridMaze gm, GridView gv) {
        this.gm = gm;
        this.gv = gv;
        MouseHandler mh = new MouseHandler(gm, gv);
        gv.addMouseListener(mh);
    }
    

    static class MouseHandler extends MouseAdapter {
        GridMaze gm;
        GridView gv;

        MouseHandler(GridMaze gm, GridView gv) {
            this.gm = gm;
            this.gv = gv;
        }

        public void mouseClicked(MouseEvent e) {
            int x = e.getX() / gm.size;
            int y = e.getY() / gm.size;

//            System.out.println(e.getButton());
//            System.out.println(MouseEvent.BUTTON1);
            if (e.getButton() == MouseEvent.BUTTON1) {
//                System.out.println("Flip wall");
                gm.flipWall(x, y);
            } else {
                gm.flipPill(x,y);
//                System.out.println("Flip pill");
            }
            gv.repaint();
            System.out.println(x + " : " + y);
        }
    }
}

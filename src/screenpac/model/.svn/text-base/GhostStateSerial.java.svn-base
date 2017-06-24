package screenpac.model;

public class GhostStateSerial {

    public int edibleTime;
    int current, previous;
    int curDir;
    int delay;
    int delayCounter;
    int returnNode;

    public GhostStateSerial(GhostState gs) {
        edibleTime = gs.edibleTime;
        current = gs.current.nodeIndex;
        previous = safeIndex(gs.previous);
        curDir = gs.curDir;
        delay = gs.delay;
        delayCounter = gs.delayCounter;
        returnNode = safeIndex(gs.returnNode);
    }

    private int safeIndex(Node node) {
        if (node == null) {
            return -1;
        } else {
            return node.nodeIndex;
        }
    }

    private Node getNode(Maze maze, int index) {
        if (index == -1) {
            return null;
        } else{
            return maze.getNode(index);
        }
    }

    public GhostState getGhostState(Maze maze) {
        // this may need access to the maze - in fact, for sure it will
        GhostState gs = new GhostState();
        gs.edibleTime = edibleTime;
        gs.curDir = curDir;
        gs.current = getNode(maze, current);
        gs.previous = getNode(maze, previous);
        gs.returnNode = getNode(maze, returnNode);
        gs.delay = delay;
        gs.delayCounter = delayCounter;
        return gs;
    }

    public static GhostStateSerial[] getGhostStateSerial(GhostState[] gs) {
        GhostStateSerial[] gss = new GhostStateSerial[gs.length];
        for (int i = 0; i < gs.length; i++) {
            System.out.println("Ghost: " + i);
            gss[i] = new GhostStateSerial(gs[i]);
        }
        return gss;
    }

    public static GhostState[] getGhostState(GhostStateSerial[] gss, int level) {
        GhostState[] gs = new GhostState[gss.length];
        for (int i = 0; i < gss.length; i++) {
            System.out.println("Ghost: " + i);
            gs[i] = gss[i].getGhostState(level);
        }
        return gs;
    }

    public GhostState getGhostState(int level) {
        Maze maze = Level.getMaze(level);
        GhostState gs = new GhostState();
        gs.edibleTime = edibleTime;
        gs.current = getNode(maze, current);
        gs.previous = getNode(maze, previous);
        gs.curDir = curDir;
        gs.delay = delay;
        gs.delayCounter = delayCounter;
        gs.returnNode = getNode(maze, returnNode);
        return gs;
    }
}

package screenpac.model;

import bits.MyBitSet;

import java.util.BitSet;

public class GameStateSerial {
    // represents the game state in a way that's more
    // efficient for serialisation but less convenient
    // the design is deliberately rather flat
    // Nodes are stored as ints (using the nodeIndex property of a Node)

    int agentPos;
    GhostStateSerial[] ghosts;
    // maze etc is recoverable from this
    int level;
    int score;
    BitSet powers;
    BitSet pills;

    public GameStateSerial(GameState gs) {
        score = gs.getScore();
        pills = gs.getPills();
        powers = gs.getPowers();
        level = gs.getLevel();
        agentPos = gs.getPacman().current.nodeIndex;
        ghosts = GhostStateSerial.getGhostStateSerial(gs.getGhosts());
    }

    public GameState getGameState() {
        GameState gs = new GameState();
        gs.score = score;
        gs.pills = pills;
        gs.powers = powers;
        gs.level = level;
        Maze maze = Level.getMaze(level);
        gs.maze = maze;
        gs.ghosts = GhostStateSerial.getGhostState(ghosts, level);
        gs.pacMan.current = maze.getNode(agentPos);
        return gs;
    }

    public String toString() {
        return new MyBitSet(200).toString();
    }
}

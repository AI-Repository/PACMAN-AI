package screenpac.model;

import java.util.BitSet;

public interface GameStateInterface {
    GameStateInterface copy();
    void next(int pacDir, int[] ghostDirs);
    Agent getPacman();
    MazeInterface getMaze();
    int getLevel(); // the maze should be recoverable from this
    BitSet getPills();
    BitSet getPowers();
    GhostState[] getGhosts();
    int getScore();
    int getGameTick();
    int getEdibleGhostScore();
    int getLivesRemaining();
    boolean agentDeath();
    boolean terminal();
    void reset();
}

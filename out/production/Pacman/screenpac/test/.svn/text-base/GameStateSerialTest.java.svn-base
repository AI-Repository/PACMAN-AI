package screenpac.test;

import games.pacman.maze.MazeOne;
import screenpac.controllers.AgentInterface;
import screenpac.controllers.RandomNonReverseAgent;
import screenpac.controllers.SimplePillEater;
import screenpac.ghosts.GhostTeamController;
import screenpac.ghosts.LegacyTeam;
import screenpac.ghosts.RandTeam;
import screenpac.model.*;
import screenpac.sound.PlaySound;
import utilities.ElapsedTimer;
import utilities.JEasyFrame;
import wox.serial.Easy;

public class GameStateSerialTest {
    static boolean visual = true;

    // done: reference the WOX library from this project
    // todo: and test its ability to serialise game state objects

    public static void main(String[] args) throws Exception {
        // idea is to set up a game
        // run it for a number of steps
        // then save it

        PlaySound.enable();
        AgentInterface agent = new SimplePillEater();
        agent = new RandomNonReverseAgent();
        //
        GhostTeamController ghostTeam;
        ghostTeam = new RandTeam();
        ghostTeam = new LegacyTeam();
        // ghostTeam = new PincerTeam();
        runVisual(agent, ghostTeam);
    }

    public static void runVisual(AgentInterface agentController, GhostTeamController ghostTeam) throws Exception {
        Maze maze = new Maze();
        maze.processOldMaze(MazeOne.getMaze());
        GameState gs = new GameState(maze);
        gs.reset();
        GameStateView gsv = new GameStateView(gs);
        // JEasyFrame fr = new JEasyFrame(gsv, "Pac-Man vs Ghosts", true);
        GameThread game = new GameThread(gs, gsv, agentController, ghostTeam);
        // game.frame = fr;
        game.run(10);
        Thread.sleep(1000);

        GameStateSerial gss = new GameStateSerial(gs);
        // Easy.save(gss, "data/gs2.xml");

        // System.out.println(gss);
        Easy.save(gss, "data/gs1.xml");

        System.out.println("Saved");
        System.out.println(Easy.saveString(gss, 3000));

        Thread.sleep(100);
        System.out.println("Timing it!");
        ElapsedTimer t = new ElapsedTimer();

        String s = Easy.saveString(gss, 3000);
        System.out.println(s.length());
        System.out.println(t);


        System.out.println("Reading it back in...");
        GameStateSerial gssIn1 = (GameStateSerial) Easy.loadString(s);
        t.reset();
        GameStateSerial gssIn2 = (GameStateSerial) Easy.loadString(s);
        System.out.println("Done.");
        System.out.println(t);
        System.out.println(gssIn1);
        System.out.println(gssIn2);
    }
}

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

public class GameStateLoadTest {
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
        ElapsedTimer t = new ElapsedTimer();
        GameStateSerial gss = (GameStateSerial) Easy.load("data/gs1.xml");
        System.out.println("Loaded in: " + t);
        System.out.println(t);
        GameState gs = gss.getGameState();
        GameStateView gsv = new GameStateView(gs);
        JEasyFrame fr = new JEasyFrame(gsv, "Pac-Man vs Ghosts", true);
        GameThread game = new GameThread(gs, gsv, agentController, ghostTeam);
        game.frame = fr;
        PlaySound.enable();
        game.run(500);
        System.out.println("Final score: " + game.gs.score);
    }
}
package screenpac.model;

import games.pacman.controllers.ReactiveController;
import games.pacman.maze.MazeOne;

import java.applet.Applet;

import screenpac.controllers.AgentInterface;
import screenpac.controllers.SimplePillEater;
import screenpac.extract.Constants;
import screenpac.ghosts.GhostTeamController;
import screenpac.ghosts.LegacyTeam;
import screenpac.ghosts.PincerTeam;
import screenpac.ghosts.RandTeam;
import screenpac.sound.PlaySound;
import utilities.ElapsedTimer;
import utilities.JEasyFrame;
import utilities.StatSummary;

public class Game extends Applet implements Constants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int delay = 40;
	static boolean visual = true;
	GameState gs;
	GameStateView gsv;
	AgentInterface agentController;
	GhostTeamController ghostTeam;
	JEasyFrame frame;

	public Game() {
	}

	public void init() {
		super.init();

		AgentInterface agent = new SimplePillEater();

		GhostTeamController ghostTeam = new RandTeam();
		ghostTeam = new LegacyTeam();
		ghostTeam = new PincerTeam();
		agent = new ReactiveController();
		if (visual) {
			try {
				runVisual(agent, ghostTeam);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				runDark(agent, ghostTeam);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		AgentInterface agent = new SimplePillEater();

		GhostTeamController ghostTeam = new RandTeam();
		ghostTeam = new LegacyTeam();
		ghostTeam = new PincerTeam();

		agent = new ReactiveController();
		if (visual) {
			runVisual(agent, ghostTeam);
		} else {
			runDark(agent, ghostTeam);
		}
	}

	public static void runDark(AgentInterface agentController,
			GhostTeamController ghostTeam) throws Exception {
		Maze maze = new Maze();
		maze.processOldMaze(MazeOne.getMaze());
		GameState gs = new GameState(maze);
		gs.reset();
		Game game = new Game(gs, null, agentController, ghostTeam);
		ElapsedTimer t = new ElapsedTimer();
		int nRuns = gs.getLivesRemaining();
		StatSummary ss = new StatSummary();
		for (int i = 0; i < nRuns; i++) {
			game.gs.reset();
			game.run();
			ss.add(game.gs.score);
//			System.out.println("Final score: " + game.gs.score + ", ticks = "
//					+ game.gs.gameTick);
		}
//		System.out.println(t);
//		System.out.println(ss);
	}

	public static void runVisual(AgentInterface agentController,
			GhostTeamController ghostTeam) throws Exception {
		GameState gs = new GameState();
		gs.nextLevel();

		gs.nLivesRemaining = gs.getLivesRemaining();

		GameStateView gsv = new GameStateView(gs);
		PlaySound.enable();
		JEasyFrame fr = new JEasyFrame(gsv, "Pac-Man vs Ghosts", true);
		KeyController kc = new KeyController();
		fr.addKeyListener(kc);
		if (agentController == null) {
			agentController = kc;
		}
		Game game = new Game(gs, gsv, agentController, ghostTeam);
		game.frame = fr;
		game.run();

//		System.out.println("Final score: " + game.gs.score);
	}

	public void run() throws Exception {
		while (!this.gs.terminal()) {
			cycle();
		}
//		System.out.println("nLives left: " + this.gs.nLivesRemaining);
	}

	public void run(int n) throws Exception {
		int i = 0;
		while ((i++ < n) && (!this.gs.terminal())) {
			cycle();
		}
	}

	public void cycle() throws Exception {
		this.gs.next(this.agentController.action(this.gs),
				this.ghostTeam.getActions(this.gs));
		if (this.gsv != null) {
			this.gsv.repaint();
			if (this.frame != null) {
				this.frame.setTitle("Score: " + this.gs.score);
			}
			Thread.sleep(delay);
		}
	}

	public Game(GameState gs, GameStateView gsv,
			AgentInterface agentController, GhostTeamController ghostTeam) {
		this.gs = gs;
		this.gsv = gsv;
		this.agentController = agentController;
		this.ghostTeam = ghostTeam;
	}
}

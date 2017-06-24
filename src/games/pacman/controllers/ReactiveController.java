package games.pacman.controllers;


import java.awt.Color;
import java.util.ArrayList;

import screenpac.model.GhostState;
import screenpac.model.Node;
import screenpac.model.GameStateInterface;
import screenpac.features.NearestPillDistance;
import screenpac.features.Utilities;
import screenpac.controllers.AgentInterface;
import screenpac.extract.Constants;

/**
 * @author Andre Santos 41931 
 * Grupo 02 IA 2012/2013
 */
public class ReactiveController implements AgentInterface, Constants {
	static int max = Integer.MAX_VALUE;
	static int min = Integer.MIN_VALUE;
	// direcao actual
	int dir;
	Node next;

	NearestPillDistance npd;

	public ReactiveController() {
		// Pill mais proxima
		npd = new NearestPillDistance();
	}

	public int action(GameStateInterface gs) {
		gs = gs.copy();
		// no actual
		Node current = gs.getPacman().current;

		// nos de jogo
		Node nextPowerPill = NextPowerPill(gs, current);
		Node nextEdibleGhost = NextEdibleGhost(gs, current);
		Node nextNonEdibleGhost = NextNonEdibleGhost(gs, current);
		Node nextPill = NextPill(gs, current, nextNonEdibleGhost,
				nextEdibleGhost);

		// regras
		rule4(gs, current, nextPill, nextNonEdibleGhost);
		rule3(gs, current, nextNonEdibleGhost, nextEdibleGhost);
		rule2(gs, current, nextPowerPill, nextNonEdibleGhost);
		rule1(gs, current, nextPowerPill, nextNonEdibleGhost);
		run(gs, current, nextNonEdibleGhost, nextPowerPill);

		dir = Utilities.getWrappedDirection(current, next, gs.getMaze());
		return dir;
	}

	// primeira regra
	private void rule1(GameStateInterface gs, Node node, Node nextPowerPill,
			Node nextNonEdibleGhost) {
		if (DistToNextPowerPill(gs, node, nextPowerPill) != -1
				&& nextPowerPill != null
				&& DistToNextPowerPill(gs, node, nextPowerPill) <= 5
				&& DistToNonEdibleGhost(gs, node, nextNonEdibleGhost) >= 30) {
			stopMotion(dir, node);
		}
	}

	// segunda regra
	private void rule2(GameStateInterface gs, Node node, Node nextPowerPill,
			Node nextNonEdibleGhost) {
		if (nPowerPill(gs) > 0
				&& DistToNextPowerPill(gs, node, nextPowerPill) <= 10
				&& (DistToNextPowerPill(gs, node, nextPowerPill) <= DistNodes(
						gs, nextNonEdibleGhost, nextPowerPill))) {
			next = Utilities.getClosest(node.adj, nextPowerPill, gs.getMaze());
		}
	}

	// terceira regra
	private void rule3(GameStateInterface gs, Node node,
			Node nextNonEdibleGhost, Node nextEdibleGhost) {
		if (NEdibleGhostCount(gs) > 0
				&& DistToEdibleGhost(gs, node, nextEdibleGhost) < 120
				&& nextNonEdibleGhost == null) {
			next = Utilities
					.getClosest(node.adj, nextEdibleGhost, gs.getMaze());
		} else if (NEdibleGhostCount(gs) > 0
				&& DistToEdibleGhost(gs, node, nextEdibleGhost) < 120
				&& DistToNonEdibleGhost(gs, node, nextNonEdibleGhost) >= 15) {
			next = Utilities
					.getClosest(node.adj, nextEdibleGhost, gs.getMaze());
		}
	}

	// quarta regra
	private void rule4(GameStateInterface gs, Node node, Node nextPill,
			Node nextNonEdibleGhost) {
		if (DistToNonEdibleGhost(gs, node, nextNonEdibleGhost) >= 15
				&& nextPill != null) {
			next = Utilities.getClosest(node.adj, nextPill, gs.getMaze());
		}
	}

	// fugir fantasmas
	private void run(GameStateInterface gs, Node node, Node nextNonEdibleGhost,	Node nextPowerPill) {
		Node sel = null;
		double best = min;
		double best2 = max;
		GhostState[] arrayGhost = gs.getGhosts();
		ArrayList<Node> adj = new ArrayList<Node>(); adj = node.adj;
		
		if (DistToNonEdibleGhost(gs, node, nextNonEdibleGhost) <= 20 && nextNonEdibleGhost != null) {
			if(nextPowerPill!=null){
				// se agente esta longe de power piil entao foge
				if (DistToNextPowerPill(gs, node, nextPowerPill) >= 20) {// POWER
					// dos nos adjacentes
					for (Node n : adj) {
						int d = gs.getMaze().dist(n, nextNonEdibleGhost);
						if (d > best) {
							best = d;
							sel = n;
						}
					}
					sel.col = Color.red;
					next = Utilities.getClosest(node.adj, sel, gs.getMaze());
				} else {// se agente esta perto de power pill entao tenta apanhala
					int distGhostMax = max;
					Node nearestGhost = null;
					// fantasma mais perto power pill
					for (GhostState ghostState : arrayGhost) {
						int d = gs.getMaze().dist(ghostState.current,
								nextPowerPill);
						if (d < distGhostMax) {
							distGhostMax = d;
							nearestGhost = ghostState.current;
						}
					}
					if (distGhostMax > DistToNextPowerPill(gs, node, nextPowerPill)) {
						sel = nextPowerPill;
						sel.col = Color.red;
						next = Utilities.getClosest(node.adj, sel, gs.getMaze());
					}
				}
			} else {//nao á power pills
				for (Node n : adj) {
					int d = gs.getMaze().dist(n, nextNonEdibleGhost);
					if (d > best) {
						best = d;
						sel = n;
					}
				}
				sel.col = Color.red;
				next = Utilities.getClosest(node.adj, sel, gs.getMaze());
			}
		}
	}

	// distancia entre 2 nos
	private double DistNodes(GameStateInterface gs, Node a, Node b) {
		if (a != null && b != null) {
			return gs.getMaze().dist(a, b);
		} else {
			return max;
		}
	}

	// distancia a pill mais proxima
	private double DistToNextPill(GameStateInterface gs, Node node,
			Node nextPill) {
		return gs.getMaze().dist(node, nextPill);
	}

	// proxima pill
	private Node NextPill(GameStateInterface gs, Node node,
			Node nextNonEdibleGhost, Node nextEdibleGhost) {
		double minDist = max;
		double maxDist = min;
		Node sel = null;
		for (Node n : gs.getMaze().getPills()) {
			// pills longe de fantasmas e perto de min
			if (gs.getPills().get(n.pillIndex) && nextNonEdibleGhost != null) {
				GhostState[] ghosts = gs.getGhosts();
				for (GhostState ghostState : ghosts) {
					int d = gs.getMaze().dist(node, n);
					int d1 = gs.getMaze().dist(n, ghostState.current);
					if (d < minDist && d1 > 80) {
						minDist = d;
						sel = n;
					}
				}
			}
		}
		// sel.col=Color.green;
		return sel;
	}

	// numero pills restantes
	private int nPills(GameStateInterface gs) {
		int count = 0;
		for (Node n : gs.getMaze().getPills()) {
			if (gs.getPills().get(n.pillIndex)) {
				count++;
			}
		}
		return count;
	}

	// distancia proxima power pill
	private double DistToNextPowerPill(GameStateInterface gs, Node node,
			Node nextPowerPill) {
		if (nextPowerPill != null) {
			return gs.getMaze().dist(node, nextPowerPill);
		} else {
			return -1;
		}
	}

	// proxima power pill
	private Node NextPowerPill(GameStateInterface gs, Node node) {
		double minDist = max;
		Node sel = null;
		for (Node n : gs.getMaze().getPowers()) {
			if (gs.getPowers().get(n.powerIndex)) {
				int d = gs.getMaze().dist(node, n);
				if (d < minDist) {
					minDist = d;
					sel = n;
				}
			}
		}
		return sel;
	}

	// distancia ao proximo fantasma comestivel
	private double DistToEdibleGhost(GameStateInterface gs, Node node,
			Node nextEdibleGhost) {
		double minDist = max;
		if (nextEdibleGhost != null) {
			minDist = gs.getMaze().dist(node, nextEdibleGhost);
		}
		if (minDist == max) {
			minDist = -1;
		}
		return minDist;
	}

	// proximo fanatsma comestivel
	private Node NextEdibleGhost(GameStateInterface gs, Node node) {
		double minDist = max;
		Node sel = null;
		GhostState[] ghosts = gs.getGhosts();
		for (GhostState ghostState : ghosts) {
			if (ghostState.edible()) {
				int d = gs.getMaze().dist(ghostState.current, node);
				if (d < minDist) {
					minDist = d;
					sel = ghostState.current;
				}
			}
		}
		return sel;
	}

	// distancia ao fantasma nao comestivel
	private double DistToNonEdibleGhost(GameStateInterface gs, Node node,
			Node nextNonEdibleGhost) {
		double minDist = max;
		if (nextNonEdibleGhost != null) {
			minDist = gs.getMaze().dist(node, nextNonEdibleGhost);
		}
		if (minDist == max) {
			minDist = -1;
		}
		return minDist;
	}

	// proximo fantasma nao comestivel
	private Node NextNonEdibleGhost(GameStateInterface gs, Node node) {
		double minDist = max;
		Node sel = null;
		GhostState[] ghosts = gs.getGhosts();
		for (int i = 0; i < ghosts.length; i++) {
			if (!ghosts[i].edible()) {
				int d = gs.getMaze().dist(ghosts[i].current, node);
				if (d < minDist) {
					minDist = d;
					sel = ghosts[i].current;
				}
			}
		}
		return sel;
	}

	// numero fantasmas comestiveis
	private int NEdibleGhostCount(GameStateInterface gs) {
		int count = 0;
		GhostState[] ghosts = gs.getGhosts();
		for (GhostState ghostState : ghosts) {
			if (ghostState.edible()) {
				count++;
			}
		}
		return count;
	}

	// parar movimento
	private void stopMotion(int dir, Node node) {
		ArrayList<Node> nodes = new ArrayList();
		nodes = node.adj;
		if (dir == 0) {
			for (Node n : nodes) {
				if (n.x == node.x && n.y > node.y) {
					next = n;
				}
			}
			// dir = 2;
		} else if (dir == 1) {
			for (Node n : nodes) {
				if (node.y == n.y && n.x < node.x) {
					next = n;
				}
			}
			// dir = 3;
		} else if (dir == 2) {
			for (Node n : nodes) {
				if (n.x == node.x && n.y < node.y) {
					next = n;
				}
			}
			// dir = 0;
		} else if (dir == 3) {
			for (Node n : nodes) {
				if (n.y == node.y && n.x > node.x) {
					next = n;
				}
			}
			// dir = 1;
		}
	}

	// numero power pills
	private int nPowerPill(GameStateInterface gs) {
		int count = 0;
		for (Node n : gs.getMaze().getPowers()) {
			if (gs.getPowers().get(n.powerIndex)) {
				count++;
			}
		}
		return count;
	}

}
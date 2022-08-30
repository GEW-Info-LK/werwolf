import java.util.*;

public class Action {
	List<Spieler> kills = new ArrayList<>();
	List<Spieler> heals = new ArrayList<>();
	Map<Spieler, Integer> votes = new HashMap<>();

	public Action() { }

	public Action combine(Action other) {
		this.kills.addAll(other.kills);
		this.heals.addAll(other.heals);
		this.votes.putAll(other.votes);
		return this;
	}

	public Action kill(Spieler spieler) {
		kills.add(spieler);
		return this;
	}

	public Action heal(Spieler spieler) {
		heals.add(spieler);
		return this;
	}

	public Action voteKill(Spieler spieler) {
		votes.put(spieler, votes.getOrDefault(spieler, 0) + 1);
		return this;
	}

	public void playerExec(List<Spieler> prevDeaths) {
		prevDeaths.addAll(kills);
		prevDeaths.removeAll(heals);
	}

	public void computeVoteKill() {
		System.out.println("votes = " + votes);
		int max = 0;
		int maxEq = 0;
		Spieler maxSpieler = null;
		for (Spieler spieler: votes.keySet()) {
			if (votes.get(spieler) == max) {
				maxEq = max;
			} else if (votes.get(spieler) > max) {
				max = votes.get(spieler);
				maxSpieler = spieler;
			}
		}
		votes = new HashMap<>();
		if (maxSpieler == null || maxEq == max) return;
		kill(maxSpieler);
	}
}

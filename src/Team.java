import java.util.ArrayList;
import java.util.Iterator;

public class Team {
	private ArrayList<Player> team;
	private String state;
	public Team() {
		team = new ArrayList<Player>();
		state = "missing";
	}
	public Team(Player player1,Player player2,Player player3,Player player4,Player player5) {
		team = new ArrayList<Player>();
		team.set(0, player1);
		team.set(1, player2);
		team.set(2, player3);
		team.set(3, player4);
		team.set(4, player5);
	}
	public boolean isFull() {
		boolean full = true;
		for (int i = 0; i<5; i++) {
			if (team.get(i).getKey() == null) {
				full = false;
			}
		}
		if (full) {
			state = "ready";
		}
		return full;
	}
	public int missingPosition() {
		for (int i = 0; i<5; i++) {
			if (team.get(i) == null) {
				return i;
			}
		}
		return 6;
	}
	public void setPlayer(int index, Player player) {
		team.set(index, player);
		if (isFull()) {
			state = "ready";
		}
	}
	public Player getPlayer(int index) {
		Player player;
		player = team.get(index);
		return player;
	}
	public int avgMMR() {
		int avgMMR = 0; 
		if (isFull()) {
			for (int i = 0; i<5; i++) {
				avgMMR += team.get(i).getMMR();
			}
		}
		avgMMR = avgMMR/5;
		return avgMMR;
	}

}

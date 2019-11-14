
public class PlayerInfo {
	private String summonerID;
	private int fillPriority;
	private int position1, position2;
	private int MMR;
	
	public PlayerInfo(String summonerID, int MMR, int fillPriority, int position1, int position2) {
		this.summonerID = summonerID;
		this.fillPriority = fillPriority;
		this.MMR = MMR;
		this.position1 = position1;
		this.position2 = position2;
	}
	public PlayerInfo() {
		this.summonerID = null;
		this.fillPriority = -1;
		this.MMR =-1;
		this.position1 = -1;
		this.position2 = -1;
	}
	public String getKey() {
		return this.summonerID;
	}
	public int getMMR() {
		return MMR;
	}
	public int getFillPriority() {
		return fillPriority;
	}
	public int getPrimary() {
		return position1;
	}
	public int getSecondary() {
		return position2;
	}
	public String toString() {
		return String.format("The player info is(summonerID, MMR, fill priority, Primary Position, Secondary Position): %s, %d, %d, %d, %d\n", summonerID, MMR, fillPriority, 
				position1, position2);
	}
	
}

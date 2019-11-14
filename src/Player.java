import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;
	
public class Player extends PlayerInfo{
	private String summonerID;
	private int fillPriority;
	private int position1, position2;
	private int MMR;
	private Stopwatch timer;
	private int queueKey, topKey, jgKey, midKey, adcKey, supKey;
	final int topPCT =25, jgPCT=20, midPCT=30, adcPCT=20, supPCT=5, primary=2000, secondary = 1000, onlyPrimary=2500, fill = 250;
	// top = 0, jg = 1, mid = 2, adc = 3, sup = 4, 
	public Player(String summonerID, int MMR, int fillPriority, int position1, int position2) {
		this.summonerID = summonerID;
		this.fillPriority = fillPriority;
		this.MMR = MMR;
		this.position1 = position1;
		this.position2 = position2;
	    timer = Stopwatch.createStarted();
		calcPrioKey();
	}
	public Player(PlayerInfo p) {
		summonerID = p.getKey();
		MMR = p.getMMR();
		fillPriority = p.getFillPriority();
		position1 = p.getPrimary();
		position2 = p.getSecondary();
		timer = Stopwatch.createStarted();
		calcPrioKey();
	}
	public Player() {
		summonerID = "Test Name";
		MMR = -1; 
		fillPriority = -1;
		position1 = -1;
		position2 = -1;
	}

	public void calcPrioKey() {
		switch (position1) {
			case 0 : switch(position2) {
					case 1 : 
						topKey = primary/topPCT;
						jgKey = secondary/jgPCT;
					    break;
					case 2:
						topKey = primary/topPCT;
						midKey = secondary/midPCT;
						break;
					case 3:
						topKey = primary/topPCT;
						adcKey = secondary/adcPCT;
						break;
					case 4 :
						topKey = primary/topPCT;
						supKey = (topPCT/supPCT)/supPCT;
					case 5 :
						topKey = primary/topPCT;
						jgKey =  fill/jgPCT;
						midKey = fill/midPCT;
						adcKey = fill/adcPCT;
						supKey = (fill/supPCT)/supPCT;
						break;
				    default:
				    	topKey = onlyPrimary/topPCT;
				}
			    break;
			case 1 : switch(position2) {
					case 0 : 
						jgKey = primary/jgPCT;
						topKey = secondary/topPCT;
					    break;
					case 2:
						jgKey = primary/jgPCT;
						midKey = secondary/midPCT;
						break;
					case 3:
						jgKey = primary/jgPCT;
						adcKey = secondary/adcPCT;
						break;
					case 4 :
						jgKey = primary/jgPCT;
						supKey = (jgPCT/supPCT)/supPCT;
					case 5 :
						jgKey = primary/jgPCT;
						topKey = fill/topPCT;
						midKey = fill/midPCT;
						adcKey = fill/adcPCT;
						supKey = (fill/supPCT)/supPCT;
						break;
				    default:
				    	jgKey = onlyPrimary/jgPCT;
			
			    }
				break;
			case 2 : switch(position2) {
					case 1 : 
						midKey = primary/midPCT;
						jgKey = secondary/jgPCT;
					    break;
					case 0 :
						midKey = primary/midPCT;
						topKey = secondary/topPCT;
						break;
					case 3 :
						midKey = primary/midPCT;
						adcKey = secondary/adcPCT;
						break;
					case 4  :
						midKey = primary/midPCT;
						supKey = (midPCT/supPCT)/supPCT;
					case 5 :
						midKey = primary/midPCT;
						jgKey =  fill/jgPCT;
						topKey = fill/topPCT;
						adcKey = fill/adcPCT;
						supKey = (fill/supPCT)/supPCT;
						break;
				    default:
				    	midKey = onlyPrimary/midPCT;
				}
		    	break;
			case 3 : switch(position2) {
					case 1 : 
						adcKey = primary/adcPCT;
						jgKey = secondary/jgPCT;
					    break;
					case 0:
						adcKey = primary/adcPCT;
						topKey = secondary/topPCT;
						break;
					case 2 :
						adcKey = primary/adcPCT;
						midKey = secondary/midPCT;
						break;
					case 4 :
						adcKey = primary/adcPCT;
						supKey = (adcPCT/supPCT)/supPCT;
					case 5 :
						adcKey = primary/adcPCT;
						jgKey =  fill/jgPCT;
						topKey = fill/topPCT;
						midKey = fill/midPCT;
						supKey = (fill/supPCT)/supPCT;
						break;
				    default:
				    	adcKey = onlyPrimary/adcPCT;
				}
		    	break;
			case 4 : switch(position2) {
					case 1 : 
						supKey = primary/supPCT;
						jgKey = secondary/jgPCT;
					    break;
					case 0 :
						supKey = primary/supPCT;
						topKey = secondary/topPCT;
						break;
					case 2 :
						supKey = primary/supPCT;
						midKey = secondary/midPCT;
						break;
					case 3 :
						supKey = primary/supPCT;
						adcKey = secondary/adcPCT;
					case 5 :
						supKey = primary/supPCT;
						jgKey =  fill/jgPCT;
						topKey = fill/topPCT;
						midKey = fill/midPCT;
						adcKey = fill/adcPCT;
						break;
				    default:
				    	supKey = onlyPrimary/supPCT;
				}
				 break;
			default:
				supKey = primary/supPCT;
				adcKey = primary/adcPCT;
				jgKey = primary/jgPCT;
				topKey = primary/topPCT;
				midKey = primary/midPCT;
		}
		queueKey = topKey + jgKey + midKey + adcKey + supKey;
	}

	public int getPositionKey(int index) {
		if(index == 0) {
			return topKey;
		} 
		else if(index == 1) {
			return jgKey;
		}
		else if (index == 2) {
			return midKey;
		}
		else if (index == 3) {
			return adcKey;
		}
		else if (index == 4) {
			return supKey;
		} 
		else if(index == 5) {
			return queueKey;
		} else {
			return 0;
		}
	}
	public int getMMR() {
		return this.MMR;
	}
	public String getKey() {
		return this.summonerID;
	}
	public int getPositionOne() {
		return position1;
	}
	public int getPositionTwo() {
		return position2;
	}
	public void setQueueKey(int key) {
		queueKey = key;
	}
	public long checkTimer() {
		long time;
		time = timer.elapsed(TimeUnit.NANOSECONDS);
		return time;
	}
	public void stopTimer() {
		timer.stop();
	}
	public String toString() {
		return String.format("The player info is(summonerID, MMR, fill priority, Primary Position, Secondary Position): %s, %d, %d, %d, %d\n", summonerID, MMR, fillPriority, 
				position1, position2);
	}


}

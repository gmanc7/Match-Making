import java.util.ArrayList;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
public class Main {
	public static void main(String[] args) throws IOException {
		hashTable dict = new hashTable(100000);
		PriorityQueue[] allPrioQueues = new PriorityQueue[27];
		boolean[] test = new boolean[11];
		FileWriter writer = new FileWriter("PlayerBase.txt");
		int i,j;
		for(i = 0; i < allPrioQueues.length; i++) { //loop that initializes all the priorityQueues
			allPrioQueues[i] = new PriorityQueue();
		}
		if (args.length == 0){ 
			for (i = 0; i < 11; ++i) test[i] = true;  // Perform all tests
		}
		else {

			if (args[0].equals("?")) {
				System.out.println("Usage: java TestDict, or java TestDict n1 n2 n3 ... ");
				System.out.println("where each ni has value 1 - 10; with the second invocation");
				System.out.println("only the specified tests will be run");
				System.exit(0);
			}

			for (i = 0; i < 11; ++i) test[i] = false;
			for (i = 0; i < args.length; ++i) {
				j = Integer.parseInt(args[i]);
				if (j >= 1 && j <= 10) test[j] = true; // Perform only specified tests
			}
		}
		int collisions = 0, fillPriority, position1, position2, MMR;
		String summoner = "";
		Random randomGenerator = new Random();
		int numPlayers = 100000;
		int[] numperElo = new int[27];     //integer array containing the number of players per each division. Will be numPlayers * pct for that division.	https://www.leagueofgraphs.com/rankings/rank-distribution	
		numperElo[0] = (int) (numPlayers * (.0013)); //iron 4           MMR (750 - 775)
		numperElo[1] = (int) (numPlayers * (.0066)); //iron 3           MMR (775 - 810)
		numperElo[2] = (int) (numPlayers *  (.0133));  // iron 2        MMR (810 - 860)
		numperElo[3] = (int) (numPlayers * (.0170)); // iron 1          MMR (860 - 910)
		numperElo[4] = (int) (numPlayers * (.0358)); // bronze 4        MMR (910 - 960)
		numperElo[5] = (int) (numPlayers * (.0446)); // bronze 3        MMR (960 - 1015)
		numperElo[6] = (int) (numPlayers * (.0585)); // bronze 2        MMR (1015 - 1080)
		numperElo[7] = (int) (numPlayers * (.0711)); // bronze 1        MMR (1080 - 1170)
		numperElo[8] = (int) (numPlayers *  (.0977)); // silver 4       MMR (1170 - 1285)
		numperElo[9] = (int) (numPlayers *  (.0958)); // silver 3       MMR (1285 - 1400) 
		numperElo[10] = (int) (numPlayers * (.1000)); // silver 2       MMR (1400 - 1530)
		numperElo[11] = (int) (numPlayers * (.0721)); // silver 1       MMR (1530 - 1610)
		numperElo[12] = (int) (numPlayers * (.0902)); // gold 4         MMR (1610 - 1725)
		numperElo[13] = (int) (numPlayers * (.0625)); // gold 3         MMR (1725 - 1795)
		numperElo[14] = (int) (numPlayers * (.0554)); // gold 2         MMR (1795 - 1860)
		numperElo[15] = (int) (numPlayers * (.0358)); // gold 1         MMR (1860 - 1900)
		numperElo[16] = (int) (numPlayers * (.0446)); // platinum 4     MMR (1900 - 1945)
		numperElo[17] = (int) (numPlayers * (.0226)); // platinum 3     MMR (1945 - 1975)
		numperElo[18] = (int) (numPlayers * (.0193)); // platinum 2     MMR (1975 - 2005)
		numperElo[19] = (int) (numPlayers * (.0135)); // platinum 1     MMR (2005 - 2035)
		numperElo[20] = (int) (numPlayers * (.0307)); // diamond 4      MMR (2035 - 2080)
		numperElo[21] = (int) (numPlayers * (.0041)); // diamond 3      MMR (2080 - 2105)
		numperElo[22] = (int) (numPlayers * (.0029)); // diamond 2      MMR (2100 - 2125)
		numperElo[23] = (int) (numPlayers * (.00110)); // diamond 1     MMR (2125 - 2145)
		numperElo[24] = (int) (numPlayers * (.0021));  // master        MMR (2145 - 2160)
		numperElo[25] = (int) (numPlayers * (.0010)); // grand master   MMR (2160 - 2175)
		numperElo[26] = (int) (numPlayers * (.0004)); // challenger     MMR (2175 - 2200)
		
		// Test 1: insert 100 000 players into the hashTable.
	
		if (test[1]){
			
			for(j = 0;j < 27; j++) {
				collisions +=numperElo[j];
			}
			for(i = 0; i<numperElo.length; i++) {
				System.out.println(numperElo[i]);
			}
			System.out.println("Sum is: "+ collisions);
			
		    for( i = 0; i < numPlayers; i++) {
					fillPriority = randomGenerator.nextInt(25);   // fill priority is basically the number of games since you were filled.
				    summoner = getSummoner(i);
				    MMR = getMMR(summonerRange(i, numperElo));
				    position1 = getRandomPositionPrimary();
				    if (position1 != 5) {
				    	position2 = getRandomPositionSecondary();
				    } else {
				    	position2 = -1;
				    }
				    try {
				    	collisions += dict.put(new PlayerInfo(summoner, MMR, fillPriority, position1, position2));
				    } catch (DictionaryException e) {
						System.out.println("***Test 1 failed");
					}
			}
		    System.out.println("Test 1 succeeded. Put 100 000 players in the dictionairy.");
			  
		}
		boolean pass = true;
		if (test[2]) {  //  checks that the rest are in the dictionary	
				for (i = 0; i < numPlayers; ++i) {
					summoner = getSummoner(i);
					if (dict.get(summoner) == null) {
						System.out.println("It broke at this player");
						System.out.println("***Test 2 failed");
						pass = false;
						break;
					}
				}
				if (pass) System.out.println("Test 2 succeeded");
		
		}
		if (test[3]) { //this test removes 10 random players and prints their info then puts them back in.
			PlayerInfo p = new PlayerInfo();
			int name; 
			String checkName;
			for(i = 0; i<10;i++) {
				name = randomGenerator.nextInt(numPlayers)+1;
				checkName = getSummoner(name);
				try {
					p = dict.remove(checkName);
					System.out.print(p.toString());
					try {
						dict.put(p);
					} catch (DictionaryException e) {
					}
				} catch (NoKeyException e) {
					System.out.println("***Test 3 failed");
				}
		    }
		    System.out.println("Test 3 succeded. Removed and Printed 10 random players.");
		}
		if (test[4]) { 
			/**
			 * This test is the first test that deals with the priority queue ADT. Will start by grabbing 1000 random players from dict and 
			 * turning each playerInfo object into a player object and then insert into the appropriate PrioQeueue. Lastly, this test
			 * picks ten random elo and removes the max player object stored for that elo. 
			 */
			PriorityQueue testPrio = new PriorityQueue();
			PlayerInfo p = new PlayerInfo();
			Player player = new Player();
			int name;
			int elo;
			String checkName;
			int count = 0;
			while(count < 1000) {
				name = randomGenerator.nextInt(numPlayers)+1;
				checkName = getSummoner(name);
				p = dict.get(checkName);
				elo = summonerRange(name, numperElo);
				player = new Player(p);
				//System.out.println(player.getKey());
				//System.out.println(player.toString());
				boolean passed = true;
				passed = testPrio.insert(player);
				passed = allPrioQueues[elo].insert(player);
				count ++;
				if (!passed) {
					System.out.println("***Test 4 failed");
				}
		    }
			System.out.println("Test 4 succeded. Inserted 1000 players into prospective Prio Queue ");
		    count = 0;
			System.out.println("These players were the max of ten random prio queues");
			while(count <10) {
				int randomNum = randomGenerator.nextInt(27);
					try {
						player = allPrioQueues[randomNum].getMaximum(5);
						count ++;
						System.out.println("Elo: " + randomNum +" " + player.toString());
					} catch (HeapUnderflowException e) {
				
					}	
			}
			
		}
		if(test[5]) {   // first populate test prio queue with twenty players. then sort the prio queue for a position(mid). Then finally, print the prio queue in original form. 
			PriorityQueue testPrio = new PriorityQueue();
			PlayerInfo p = new PlayerInfo();
			Player player = new Player();
			int name;
			String checkName;
			for (i = 0; i < 10; i++) {
				name = randomGenerator.nextInt(numPlayers)+1;
				checkName = getSummoner(name);
				p = dict.get(checkName);
				player = new Player(p);
				testPrio.insert(player);
			}
			System.out.println("This is what the prio queue looks like after inserting 10 random players based off queueKey");
			System.out.print(testPrio.toString(5));
			testPrio.buildMaxHeap(5);
			System.out.println("This is what the prio queue looks like after building for queue key");
			System.out.print(testPrio.toString(5));
			testPrio.buildMaxHeap(2);    //build max heap for mid
			System.out.println("This is what the prio queue looks like after building for mid");
			System.out.print(testPrio.toString(2));
			System.out.println("Switching back to overall queue key");
			System.out.print(testPrio.toString(5));
		}
	}
	
	
	// These are all helper methods for main  to test.
	public static int summonerRange(int checkRange, int[] array) {
		int elo = 0;
		int lowerRange=0, upperRange=0;
		for (int i = 0; i < 27; i++) {
			int j = i-1;
			if (j >= 0) {
				lowerRange += array[j];
				upperRange += array[i];
				if(checkRange > lowerRange && checkRange <= upperRange) elo=i;
			} else {
				lowerRange = 0;
				upperRange = array[0];
				if(checkRange > lowerRange && checkRange <= upperRange) elo = 0;
			}
		}
		return elo;
	}
	
			
	public static String getSummoner(int s) {
		String summoner;
		summoner = new Integer(s).toString();                        // this creates a summoner name based on the Integer value. 
		for (int j = 0; j < 3; ++j) summoner += summoner;
		return summoner;
	}

	public static int getMMR(int elo) {
		Random randomGenerator = new Random();                                // rand.nextInt((max - min) + 1) + min;
		int MMR =0;
		if(elo == 0) {
			MMR = randomGenerator.nextInt(26) + 750;
		} else if (elo == 1) {
			MMR = randomGenerator.nextInt(36) + 775;
		} else if (elo == 2) {
			MMR = randomGenerator.nextInt(51) + 810;
		} else if (elo == 3) {
			MMR = randomGenerator.nextInt(51) + 860;
		} else if (elo == 4) {
			MMR = randomGenerator.nextInt(51) + 910;
		} else if (elo == 5) {
			MMR = randomGenerator.nextInt(56) + 960;
		} else if (elo == 6) {
			MMR = randomGenerator.nextInt(66) + 1015;
		} else if (elo == 7) {
			MMR = randomGenerator.nextInt(91) + 1080;
		} else if (elo == 8) {
			MMR = randomGenerator.nextInt(116) + 1170;
		} else if (elo == 9) {
			MMR = randomGenerator.nextInt(116) + 1285;
		} else if (elo == 10) {
			MMR = randomGenerator.nextInt(131) + 1400;
		} else if (elo == 11) {
			MMR = randomGenerator.nextInt(81) + 1530;
		} else if (elo == 12) {
			MMR = randomGenerator.nextInt(116) + 1610;
		} else if (elo == 13) {
			MMR = randomGenerator.nextInt(71) + 1725;
		} else if (elo == 14) {
			MMR = randomGenerator.nextInt(66) + 1795;
		} else if (elo == 15) {
			MMR = randomGenerator.nextInt(41) + 1860;
		} else if (elo == 16) {
			MMR = randomGenerator.nextInt(46) + 1900;
		} else if (elo == 17) {
			MMR = randomGenerator.nextInt(31) + 1945;
		} else if (elo == 18) {
			MMR = randomGenerator.nextInt(31) + 1975;
		} else if (elo == 19) {
			MMR = randomGenerator.nextInt(31) + 2005;
		} else if (elo == 20) {
			MMR = randomGenerator.nextInt(46) + 2035;
		} else if (elo == 21) {
			MMR = randomGenerator.nextInt(26) + 2080;
		} else if (elo == 22) {
			MMR = randomGenerator.nextInt(26) + 2105;
		} else if (elo == 23) {
			MMR = randomGenerator.nextInt(21) + 2130;
		} else if (elo == 24) {
			MMR = randomGenerator.nextInt(21) + 2150;
		} else if (elo == 25) {
			MMR = randomGenerator.nextInt(21) + 2170;
		} else if (elo == 26) {
			MMR = randomGenerator.nextInt(21) + 2190;
		}
		return MMR;
	}
	
	public static int getRandomPositionPrimary() {                                     // this method simulates an unfair dice that rolls what position a player is going to have
		Random randomGenerator = new Random();                           // i based the percentages of achieving a certain role based off the player percentages 
		int position = 0, randomNum;                                                // in the player class
		randomNum = randomGenerator.nextInt(100);
		if (randomNum < 25) {
			position = 0;
		} else if (randomNum >= 25 && randomNum < 45 ) {
			position = 1;
		} else if (randomNum >=45 && randomNum < 70) {
			position = 2;
		} else if (randomNum >= 75 && randomNum < 95 ) {
			position = 3;
		} else if (randomNum >= 95 && randomNum < 100) {
			position = 4;
		} else {                                                                 // player put fill as primary
			position = 5;
		}
		return position; 
	}
	public static int getRandomPositionSecondary() {                                     // this method simulates an unfair dice that rolls what position a player is going to have
		Random randomGenerator = new Random();                                  // i based the percentages of achieving a certain role based off the player percentages 
		int position = 0, randomNum;                                                // in the player class
		randomNum = randomGenerator.nextInt(100);
		if (randomNum < 20) {
			position = 0;
		} else if (randomNum >= 25 && randomNum < 45 ) {
			position = 1;
		} else if (randomNum >=45 && randomNum < 70) {
			position = 2;
		} else if (randomNum >= 75 && randomNum < 95 ) {
			position = 3;
		} else if (randomNum >= 95 && randomNum < 100) {
			position = 4;
		} else {                                                                 // player put fill as secondary
			position = 5;
		}
		
		return position; 
	}

}

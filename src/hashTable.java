import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class hashTable implements DictionaryADT {

	private LinkedList[] hashTable;

	
	public hashTable(int size) {
		hashTable = new LinkedList[size];
	}
	
	private int myHashCode(String key) {
		long hashvalue = 0;
		char c[] = key.toCharArray();
		int n = key.length();
		final int hashprime = 71;
		if (hashvalue == 0 && key.length() >= 0) {
			hashvalue = (int)c[n-1];
			for (int i =n-2; i >= 0; i--) {
				hashvalue = hashprime * hashvalue + (int)c[i] ;
			}
		}
		int result = (int)hashvalue;
		//custom scaling factor
		result =  (int)( (double)result / Integer.MAX_VALUE * hashTable.length);
		if( result < 0) result = result * -1;

		return result;
	}

	@Override
	public PlayerInfo get(String summonerID) {
		int hashtableindex = myHashCode(summonerID);
		List horizontalEntry = hashTable[hashtableindex];
		//iterates through each array element then iterates through each linked list and if it finds the word returns it
		if( horizontalEntry != null) {	
			Iterator i = horizontalEntry.iterator();
			while (i.hasNext()) {
				PlayerInfo p = (PlayerInfo) i.next();
				if(p.getKey().equals(summonerID)) {
					return p;
				}
			}
		}
		return null;
	}

	public int put(PlayerInfo player) throws DictionaryException {
		int hashtableindex = myHashCode(player.getKey());
		LinkedList horizontalEntry = hashTable[hashtableindex];
		if(horizontalEntry == null) {
		    horizontalEntry = new LinkedList<PlayerInfo>();
		    horizontalEntry.add(player);
			hashTable[hashtableindex] =  horizontalEntry;
			return 0;
		}
		else
		{
			ListIterator iterLinkedList = horizontalEntry.listIterator();
			while(iterLinkedList.hasNext()) {
				PlayerInfo checkWord = (PlayerInfo) iterLinkedList.next();
				if(checkWord.getKey().equals(player.getKey())) {
					throw new DictionaryException(player.getKey());
				}
			}
			horizontalEntry.add(player);
			return 1;
		}
	}

	public PlayerInfo remove(String summonerID) throws NoKeyException {
		int hashtableindex = myHashCode(summonerID);
		List<PlayerInfo> horizontalEntry = hashTable[hashtableindex];
		if(horizontalEntry == null) {
			throw new NoKeyException(summonerID);
		}
		else
		{
			for( PlayerInfo p : horizontalEntry) {
				if( p.getKey().equals(summonerID)) {
					horizontalEntry.remove(p);
					return p;
				}
			}
			throw new NoKeyException(summonerID);
		}
	}

	public int size() {
		int size = 0;
		for( List<PlayerInfo> horizontalEntry : hashTable) {
			if( horizontalEntry != null) {
				size += horizontalEntry.size();
			}
		}	
		return size;
	}
	
}


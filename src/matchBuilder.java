
import java.util.*;

public class matchBuilder implements CustomListADT
{
	private PriorityQueue eloPool;
	private TeamNode<Team> head, tail;
	public matchBuilder() {
		head = new TeamNode<Team>();
		tail = mbPolymerase(head);
		eloPool = new PriorityQueue();
	}
	
	public Team removeFirst() throws EmptyCollectionException, ArrayUnderflowException {
		Team readyforMatch;
		if (!head.getElement().isFull())
			throw new EmptyCollectionException ("Tried to put not full team into game");
		else  {
			readyforMatch = head.getElement();
			head = head.getNext();
		} 
		return readyforMatch;
	}
	
	private int chainmissing;
	public TeamNode<Team> mbPolymerase(TeamNode<Team> current) {
		/**
		 *  base case: first element of linked list (a.k.a head) is empty or not full. Grab 5 players from prioQueue(according to queuekey).
		 *  else, traverse horizontally every time checking if the team is ready for a game or not. everytime you go to the next team you check
		 *  the state flag and update the chain missing counter; this is used to ensure the recursive calls for fixing the team (aka retrieving players
		 *  from the priority queue) don't just keep going there is a limit to how far ahead it looks. at the end of the recursive call it should update
		 *  the current team it looked at  with hopefully a new team that is full or has more players, move on to next team as long as chainmissing isnt too long. 
		 */
		if (current == head) {          //base case
			if(head.getElement().isFull()) {
				current = head.getNext();
				return mbPolymerase(current);
			} else {
				LinkedList<Player> players = grabPlayers(5);
				ListIterator i = players.listIterator();
				while (i.hasNext() && !current.getElement().isFull()) {
					Player player = (Player) i.next();
					if (player.getPositionOne() == current.getElement().missingPosition()) {
						current.getElement().setPlayer(player.getPositionOne(), player);
					} else if (player.getPositionTwo() == current.getElement().missingPosition() || player.getPositionTwo() == 5 ) {
						current.getElement().setPlayer(player.getPositionTwo(), player);
					} else if (player.getPositionOne() == 5) {
						current.getElement().setPlayer(current.getElement().missingPosition(), player);
					} 
				}
				current = current.getNext();
				chainmissing ++;
				mbPolymerase(current);
			}
			
		} else {
			while(chainmissing < 5) {
				LinkedList<Player> players = grabPlayers(5);
				ListIterator i = players.listIterator();
				while (i.hasNext() && !current.getElement().isFull()) {
					Player player = (Player) i.next();
					if (player.getPositionOne() == current.getElement().missingPosition()) {
						current.getElement().setPlayer(player.getPositionOne(), player);
					} else if (player.getPositionTwo() == current.getElement().missingPosition() || player.getPositionTwo() == 5 ) {
						current.getElement().setPlayer(player.getPositionTwo(), player);
					} else if (player.getPositionOne() == 5) {
						current.getElement().setPlayer(current.getElement().missingPosition(), player);
					} 
				}
				current = current.getNext();
				chainmissing ++;
				mbPolymerase(current);
			}	
		}
		return current;
	}
	public LinkedList<Player> grabPlayers(int type){
		LinkedList<Player> players = new LinkedList<Player>();
		eloPool.buildMaxHeap(type);
		for (int i = 0; i < 5; i ++) {
			try {
				players.add(eloPool.getMaximum(type));
			} catch (HeapUnderflowException e) {
			    }
		}
		 
		return players;
	}

}

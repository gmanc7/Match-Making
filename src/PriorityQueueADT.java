import java.util.ArrayList;

/**
 * PriorityQueue ADT
 * --------------
 * An interface that sets up the primary operations required for a Priority Queue that uses a custom key factor for LOL match making.
 * @author Goran Curguz
 * @date Winter 2019
 */
public interface PriorityQueueADT {
	/**
	 * inserts the element PlayerInfo into the set S.
	 * @param playerinfo player
	 */
	public Boolean insert(Player player);
	/**
	 * Check's what is the highest priority element (root) and returns the player value  
	 * @return Player 
	 */
	public Boolean delete(Player player);
	/**
	 * s
	 * @return
	 */
	public Player checkMaximum(int type);
	/**
	 * Check's what is the highest priority player(root) and removes it from the heap.
	 * @return value V
	 */
	public Player getMaximum(int type) throws HeapUnderflowException;
	/**
	 * finds the player in the heap and updates the key if it finds the player.
	 * returns 0 if successful, 1 if not
	 */
	public void changeKey(int key, int index, int positionKey) throws SmallerKeyException;
	
	/**
	 * resets the tempPrioQueue. will be used after check operations so we dont need to heapify after removing. 
	 * Just need to attach marker to player objects in the hash table so when they reach the top of the main
	 * prioQueue we just skip over them. 
	 */
	public void eraseQueue() throws HeapUnderflowException;
	/**
	 * prints the desired queue
	 */
	public String toString(int type);
}

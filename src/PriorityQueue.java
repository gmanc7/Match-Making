import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;
public class PriorityQueue implements PriorityQueueADT {
	
	private ArrayList<Player> prioQueue;
	private ArrayList<Player> tempPrioQueue;
	
	private int tempState;
	
	public PriorityQueue() {
		prioQueue = new ArrayList<Player>();
		tempPrioQueue = new ArrayList<Player>();
		tempState = 5;
	}
	private int parent(int index) {
		return index/2;
	}
	private int left(int index) {
		return 2*index;
	}
	private int right(int index) {
		return 2*index + 1;
	}
	public void maxHeapify(int index, int positionNeeded, int n) { //n is size
		ArrayList<Player> Queue;
		int l = left(index);
		int r = right(index);
		int largest = index;
		int position = positionNeeded;
		int l_key = 0, r_key = 0, largest_key, index_key;
		if (position == 5) {
			Queue = prioQueue;
		} else {
			Queue = tempPrioQueue;
		}
		if (l < n && r<n) {
			l_key = Queue.get(l).getPositionKey(position);
			r_key = Queue.get(r).getPositionKey(position);
		}
		
	    index_key = Queue.get(index).getPositionKey(position);
		if ( (l < n) && (l_key > index_key)) {
			largest = l;
		} 
		largest_key = Queue.get(largest).getPositionKey(position);
		if ( (r < n) && (r_key > largest_key)) {
			largest = r;
		}
		if (largest != index){
			Collections.swap(Queue, index, largest);
			maxHeapify(largest, position, n);
		}
		
	}
	public int buildMaxHeap(int positionNeeded) {
		int position = positionNeeded;
		int n;
		if (position != 5) {
			tempPrioQueue = prioQueue;
			n = tempPrioQueue.size();
			for(int i = (n/2)-1; i >=0; i--) {
				maxHeapify(i, position, n);
			}
		} else {
			n = prioQueue.size(); 
			for(int i = (n/2)-1; i >=0; i--) {
				maxHeapify(i, position, n);
			}
		}
		if (tempState != position){
			tempState = position;
		}
		return n;
	}
	public void heapSort(int positionNeeded) { //just need to make sure passing 5 every time with prioQueue
		ArrayList<Player> Queue;
		int position = positionNeeded;
		if (position == 5) {
			Queue = prioQueue;
		} else {
			Queue = tempPrioQueue;
		}
		int n = buildMaxHeap(position);
		for (int i = n-1; i >=1; i--) {
			Collections.swap(Queue, 0, i);
		    maxHeapify(i, position, n);
		}
	}

	public Boolean insert(Player player) {
		prioQueue.add(player);
		int position = 5; // always insert players into the main prioQueue;
		int index = prioQueue.size() -1;
	    try {
	    	int key = player.getPositionKey(position);
	    	changeKey(key, index, position);
	    	return true;
	    } catch(SmallerKeyException e) {
	    	return false;
	    }
	}


	public Player checkMaximum(int type) {
		if (type !=5) {
			return tempPrioQueue.get(0);
		}
		return prioQueue.get(0);
	}

	
	public Player getMaximum(int type) throws HeapUnderflowException{
		ArrayList<Player> Queue;
		if (type == 5) {
			Queue = prioQueue;
		} else {
			Queue = tempPrioQueue;
		}
		if (Queue.size() < 1) {
			throw new HeapUnderflowException();
		}
		int n = Queue.size() -1;
		Player max = Queue.get(0);
		Player temp = Queue.get(n);
		Queue.set(0, temp);
		Queue.remove(n);
		if (!Queue.isEmpty()){
			maxHeapify(0, type, n);
		}
		
		return max;
	}
	public void changeKey(int key, int index, int positionKey) throws SmallerKeyException {
		int currentKey = prioQueue.get(index).getPositionKey(positionKey);
		if(key < currentKey) {
			throw new SmallerKeyException(key);
		}
		prioQueue.get(index).setQueueKey(key);
		int parentKey = prioQueue.get(parent(index)).getPositionKey(5);
		while ((index > 0) && (parentKey < currentKey)) {
			tempPrioQueue = prioQueue;
			Collections.swap(prioQueue, index, parent(index));
			tempPrioQueue = prioQueue;
			index = parent(index);
			parentKey = prioQueue.get(parent(index)).getPositionKey(5);
		}
		
	}
	

	public Boolean delete(Player player) {
		Iterator i = prioQueue.iterator();
		while(i.hasNext()) {
			if(i.equals(player)) {
				i.remove();
				return true;
			}
		}
		return false;
	}
	public String toString(int type) {
		ArrayList<Player> Queue;
		if (type == 5) {
			Queue = prioQueue;
			heapSort(type);
		} else {
			Queue = tempPrioQueue;
			heapSort(type);
		}
		
		String s = "";
		for(int i = 0; i< Queue.size(); i++) {
			int queukey = Queue.get(i).getPositionKey(type);
			s += String.format("%d: %s: Queue Key: %d\n",i, Queue.get(i).toString(), Queue.get(i).getPositionKey(type));
		}
		return s;
	}

	public void eraseQueue() throws HeapUnderflowException {
		// TODO Auto-generated method stub
		
	}
	
}

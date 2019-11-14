/**
 *Heap underflow Exception
 * ---------------------
 * The exception thrown if the heap is empty when we try to extract max.
 * @author Goran Curguz
 * @date Winter 2019
 */
public class HeapUnderflowException extends Exception {

	/**
	 * Constructor
	 */
	public HeapUnderflowException(){
		super("Heap Underflow!");
	}
}

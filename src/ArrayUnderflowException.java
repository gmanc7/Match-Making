/**
 *Heap underflow Exception
 * ---------------------
 * The exception thrown if the heap is empty when we try to extract max.
 * @author Goran Curguz
 * @date Winter 2019
 */
public class ArrayUnderflowException extends Exception {

	/**
	 * Constructor
	 */
	public ArrayUnderflowException(){
		super("Array Underflow!");
	}
}
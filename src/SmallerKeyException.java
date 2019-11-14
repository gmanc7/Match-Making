/**
 * Smaller Key Excpetion
 * ---------------------
 * The exception thrown if the key requested is smaller than the key of the player being updated.
 * @author Goran Curguz
 * @date Winter 2019
 */
public class SmallerKeyException extends Exception {

	/**
	 * Constructor
	 */
	public SmallerKeyException(long key){
		super("Key " + key + "is smaller than the key of the player you wish to change.");
	}
}

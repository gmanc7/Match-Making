/**
 * Dictionary ADT
 * --------------
 * An interface that sets up the primary operations required for a dictionary.
 * @author Daniel Page
 * @date Winter 2019
 */
public interface DictionaryADT 
{
	/**
	 * get the value V associated with key K, return -1 if not in the dictionary.
	 * @param key
	 * @return value V
	 */
	public PlayerInfo get(String summonerID);
	
	/**
	 * @param PlayerInfo - a word that has a string and score associated with it.
	 * @return int - 1 if a collision occurs, 0 otherwise.
	 * @throws DictionaryException - if two keys (words) of two Word objects are the same.
	 */
    public int put(PlayerInfo player) throws DictionaryException;

    /**
     * remove key-value pair with key K from the dictionary and return the value of that record.
     * @param key - a key for the key-value pair
     * @return Word - the Word object associated with the key for this dictionary.
     * @throws NoKeyException - if attempting to remove, there is no record with the key.
     */
    public PlayerInfo remove(String summonerID) throws NoKeyException;
    
    /**
     * get the number of key-value pairs currently in the dictionary.
     * @return number of key-value pairs in dictionary.
     */
    public int size();

}
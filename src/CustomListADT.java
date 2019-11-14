/**
 * 
 * 
 */
public interface CustomListADT 
{
   /**  
    * Removes and returns the first element from this list. The element will be a linked list containing 5 players for a game
    * will not remove if it doesn't contain 5 players.  
    * 
    * @return  the first element from this list
    */
   public Team removeFirst () throws ArrayUnderflowException;

   /**  
    * Returns true if this list contains no elements. 
    *
   */
   public TeamNode<Team> mbPolymerase(TeamNode<Team> current);
   
}
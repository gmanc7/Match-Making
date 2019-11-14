/**
 * This class will be more condensed version of the PLayerInfo class. It will store summoner ID, and fill priority.
 * the constructor creates a modified player object that is used to put into the priority queue and then the matchbuilder. 
 * when the player object is created it is because the player has begun queuing for a game so we start a timer. the timer is 
 * used to measure both how long players stay in queue but also to make sure the priority queue also prioritizes based on length spent in queue.  
 * @author Goran Curguz
 *
 */


public class TeamNode<Team> {
	private TeamNode<Team> next;
	private TeamNode<Team> previous;
	private Team element;
	// creates empty node
	public TeamNode() {
		next = null;
		element = null;
		previous = null;
	}
	//creates a node storing the specified element
	public TeamNode(Team team) {
		next = null;
		element = team;
		previous = null;
	}
	public TeamNode<Team> getNext(){
		return next;
	}
	public TeamNode<Team> getPrevious(){
		return previous;
	}
	public void setNext(TeamNode<Team> node) {
		next = node;
	}
	public void setPrevious (TeamNode<Team> node) {
		previous = node;
	}
	public Team getElement() {
		return element;
	}
	public void setElement(Team team) {
		element = team;
	}
	
}

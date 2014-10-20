package game;

/**
 * Container that has a reference to the player's color and opponent
 */
public class Player {
	
	// "White" or "Black"
	public String color;
	public Player opponent = null;
	
	public String toString() {
		return color;
	}

}

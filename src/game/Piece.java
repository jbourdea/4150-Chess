package game;

/**
 * Generic Piece class that is extended by each individual game piece (Bishop, King, Knight, Pawn, Queen, Rook).
 * Contains a reference to the player and a variable for if it has moved.
 */
public class Piece {
	
	public Player owner = null;
	public Boolean hasMoved = false;
	
	public Piece(Player owner) {
		this.owner = owner;
	}
	

}
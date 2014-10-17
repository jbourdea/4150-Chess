package game;

public class Piece {
	
	public Player owner = null;
	public Boolean hasMoved = false;
	
	public Piece(Player owner) {
		this.owner = owner;
	}
	

}
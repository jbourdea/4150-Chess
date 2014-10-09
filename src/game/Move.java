package game;

public class Move {
	
	public Player activePlayer = null;
	public Piece piece = null;
	public Tile endPosition = null;
	
	public Move(Player activePlayer, String move, Board board) {
		this.activePlayer = activePlayer;
		//TODO: parse move string and convert to piece and endPosition.
	}

}

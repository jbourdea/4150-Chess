package game;

public class Move {
	
	public Player activePlayer = null;
	public Piece piece = null;
	public Tile endPosition = null;
	
	public Move(Player activePlayer, String move) {
		this.activePlayer = activePlayer;
		//TODO: parse move string and convert to piece and endPosition.
	}

	public boolean IsValid() {
		
		//ask the moving piece if it is allowed to make this move
		//return piece.ValidMoves.Contains(endPosition);
		
		return true;
	}
}

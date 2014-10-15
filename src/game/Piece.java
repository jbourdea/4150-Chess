package game;

public class Piece {
	
	public Player owner = null;
	public Move[] validMoves;
	public Boolean hasMoved = false;
	
	public Piece(Player owner) {
		this.owner = owner;
	}
	
	
	public boolean IsValid(Move move) {
		updateValidMoves();
		
		for (Move validMove : validMoves) {
			if (validMove.endPosition == move.endPosition) {
				return true;
			}
		}
		
		return false;
	}
	
	private void updateValidMoves(){
		
	}
	
	

}
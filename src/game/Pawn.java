package game;

public class Pawn extends Piece {
	public Boolean lastMoveJump = false;
	public Pawn (Player owner) {
		super(owner);
	}
	
	private void updateValidMoves(){
		if (owner.color.equals("White") ) {
			
		}
		else {
			
		}
	}

	public String toString() {
		if (owner.color.equals("White")) {
			return "P";
		}
		return "p";
		
	}
}
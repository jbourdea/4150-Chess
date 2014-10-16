package game;

public class Queen extends Piece {
	public Queen (Player owner) {
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
			return "Q";
		}
		return "q";
		
	}
}
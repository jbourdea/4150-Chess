package game;

public class Queen extends Piece {
	public Queen (Player owner) {
		super(owner);
	}

	public String toString() {
		if (owner.color.equals("White")) {
			return "Q";
		}
		return "q";
		
	}
}
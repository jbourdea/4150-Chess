package game;

public class Rook extends Piece {
	public Rook (Player owner) {
		super(owner);
	}

	public String toString() {
		if (owner.color.equals("White")) {
			return "R";
		}
		return "r";
		
	}
}
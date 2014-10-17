package game;

public class King extends Piece {
	public King (Player owner) {
		super(owner);
	}

	public String toString() {
		if (owner.color.equals("White")) {
			return "K";
		}
		return "k";
		
	}
}
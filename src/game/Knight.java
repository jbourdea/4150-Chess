package game;

public class Knight extends Piece {
	public Knight (Player owner) {
		super(owner);
	}

	public String toString() {
		if (owner.color.equals("White")) {
			return "N";
		}
		return "n";
		
	}
}
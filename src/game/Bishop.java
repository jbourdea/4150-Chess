package game;

public class Bishop extends Piece {
	public Bishop (Player owner) {
		super(owner);
	}

	public String toString() {
		if (owner.color.equals("White")) {
			return "B";
		}
		return "b";
		
	}
}
package game;

/**
 * Extends Piece and uses a letter to represent the value
 */
public class King extends Piece {
	public King (Player owner) {
		super(owner);
	}

	/**
	 * returns the letter that represents the piece for the color they are on
	 */
	public String toString() {
		if (owner.color.equals("White")) {
			return "K";
		}
		return "k";
		
	}
}
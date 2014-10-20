package game;

/**
 * Extends Piece and uses a letter to represent the value
 */
public class Queen extends Piece {
	public Queen (Player owner) {
		super(owner);
	}

	/**
	 * returns the letter that represents the piece for the color they are on
	 */
	public String toString() {
		if (owner.color.equals("White")) {
			return "Q";
		}
		return "q";
		
	}
}
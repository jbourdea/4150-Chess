package game;

/**
 * Extends Piece and uses a letter to represent the value
 */
public class Rook extends Piece {
	public Rook (Player owner) {
		super(owner);
	}

	/**
	 * returns the letter that represents the piece for the color they are on
	 */
	public String toString() {
		if (owner.color.equals("White")) {
			return "R";
		}
		return "r";
		
	}
}
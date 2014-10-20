package game;

/**
 * Extends Piece and uses a letter to represent the value
 */
public class Bishop extends Piece {
	public Bishop (Player owner) {
		super(owner);
	}

	/**
	 * returns the letter that represents the piece for the color they are on
	 */
	public String toString() {
		if (owner.color.equals("White")) {
			return "B";
		}
		return "b";
		
	}
}
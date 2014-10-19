package game;

public class Tile {
	public Piece piece;
	public int xCord = -1;
	public int yCord = -1;
	
	public Tile() {
		piece = null;
	}
	
	public Tile(Piece piece) {
		this.piece = piece;
	}

	public void addPiece(Piece piece) {
		if(piece != null) {
			this.piece = piece;
		}
	}

	public Piece getPiece() {
		return this.piece;
	}
	
	public String toString() {
		if(this.piece != null) {
			return "xCord => " + this.xCord
					+ " yCord => " + this.yCord
					+ " piece => " + this.piece.toString();
		} else {
			return "xCord => " + this.xCord
					+ " yCord => " + this.yCord
					+ " piece => N/A";
		}
	}
	
}
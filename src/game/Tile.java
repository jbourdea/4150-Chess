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
	
}
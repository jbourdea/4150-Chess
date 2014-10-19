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
	
	private String xCordToChar() {
		String returnVal = "";
		returnVal = (this.xCord == 0) ? "A" : returnVal;
		returnVal = (this.xCord == 1) ? "B" : returnVal;
		returnVal = (this.xCord == 2) ? "C" : returnVal;
		returnVal = (this.xCord == 3) ? "D" : returnVal;
		returnVal = (this.xCord == 4) ? "E" : returnVal;
		returnVal = (this.xCord == 5) ? "F" : returnVal;
		returnVal = (this.xCord == 6) ? "G" : returnVal;
		returnVal = (this.xCord == 7) ? "H" : returnVal;
		
		return returnVal;
	}
	
	public String toString() {
		if(this.piece != null) {
			return "xCord => " + xCordToChar()
					+ " yCord => " + this.yCord
					+ " piece => " + this.piece.toString();
		} else {
			return "xCord => " + xCordToChar()
					+ " yCord => " + this.yCord
					+ " piece => N/A";
		}
	}
	
}
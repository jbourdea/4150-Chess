package game;

public class Move {
	
	public Player activePlayer = null;
	public Piece piece = null;
	public Tile endPosition = null;
	public Tile startPosition = null;
	
	public Move() {}
	
	public Move(Move move) {
		this.activePlayer = move.activePlayer;
		this.startPosition = move.startPosition;
		this.endPosition = move.endPosition;
		this.piece = move.piece;
	}

	public Move(Player activePlayer, Tile startTile, Tile endTile) {
		this.activePlayer = activePlayer;
		this.startPosition = startTile;
		this.piece = startTile.piece;
		this.endPosition = endTile;
	
	}
	
	public Move(Player activePlayer, String move, Board board) {
		this.activePlayer = activePlayer;

		String start = move.substring(0, move.indexOf('-')).toLowerCase();
		String end = move.substring(move.indexOf('-')+1).toLowerCase();

		int startX = 0;
		int startY = Character.getNumericValue(start.charAt(1));
		
		startX = (start.charAt(0) == 'a') ? 0 : startX;
		startX = (start.charAt(0) == 'b') ? 1 : startX;
		startX = (start.charAt(0) == 'c') ? 2 : startX;
		startX = (start.charAt(0) == 'd') ? 3 : startX;
		startX = (start.charAt(0) == 'e') ? 4 : startX;
		startX = (start.charAt(0) == 'f') ? 5 : startX;
		startX = (start.charAt(0) == 'g') ? 6 : startX;
		startX = (start.charAt(0) == 'h') ? 7 : startX;
		startX = (start.charAt(0) == 'i') ? 8 : startX;
		startX = (start.charAt(0) == 'j') ? 9 : startX;

		Tile tile = board.tiles[startX][startY];
		
		if (tile.piece != null)
		{
			piece = tile.piece;
			startPosition = tile;
			tile.xCord = startX;
			tile.yCord = startY;
		}
		
		int endX = 0;
		int endY = Character.getNumericValue(end.charAt(1));
		
		endX = (end.charAt(0) == 'a') ? 0 : endX;
		endX = (end.charAt(0) == 'b') ? 1 : endX;
		endX = (end.charAt(0) == 'c') ? 2 : endX;
		endX = (end.charAt(0) == 'd') ? 3 : endX;
		endX = (end.charAt(0) == 'e') ? 4 : endX;
		endX = (end.charAt(0) == 'f') ? 5 : endX;
		endX = (end.charAt(0) == 'g') ? 6 : endX;
		endX = (end.charAt(0) == 'h') ? 7 : endX;
		endX = (end.charAt(0) == 'i') ? 8 : endX;
		endX = (end.charAt(0) == 'j') ? 9 : endX;
		
		endPosition = board.tiles[endX][endY];
		endPosition.xCord = endX;
		endPosition.yCord = endY;
	}
	
	public boolean IsValid()
	{
		if (piece != null && startPosition != null && endPosition != null && activePlayer != null && piece.owner == activePlayer)
			return true;
		return false;
	}
	
	public String toString() {
		return "<= Move => \n Player => " + this.activePlayer.toString()
				+ "\n Piece => " + this.piece.toString() 
				+ "\n Start Position => " + this.startPosition.toString() 
				+ "\n End Position => " + this.endPosition.toString();
	
	}

}

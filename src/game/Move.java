package game;

public class Move {
	
	public Player activePlayer = null;
	public Piece piece = null;
	public Tile endPosition = null;
	public Tile startPosition = null;
	
	public Move(Player activePlayer, String move, Board board) {
		this.activePlayer = activePlayer;

		String start = move.substring(0, move.indexOf('-'));
		String end = move.substring(move.indexOf('-')+1);

		int startX = 0;
		int startY = Character.getNumericValue(start.charAt(1));
		
		startX = (start.charAt(0) == 'a' || start.charAt(0) == 'A') ? 0 : startX;
		startX = (start.charAt(0) == 'b' || start.charAt(0) == 'B') ? 1 : startX;
		startX = (start.charAt(0) == 'c' || start.charAt(0) == 'C') ? 2 : startX;
		startX = (start.charAt(0) == 'd' || start.charAt(0) == 'D') ? 3 : startX;
		startX = (start.charAt(0) == 'e' || start.charAt(0) == 'E') ? 4 : startX;
		startX = (start.charAt(0) == 'f' || start.charAt(0) == 'F') ? 5 : startX;
		startX = (start.charAt(0) == 'g' || start.charAt(0) == 'G') ? 6 : startX;
		startX = (start.charAt(0) == 'h' || start.charAt(0) == 'H') ? 7 : startX;
		startX = (start.charAt(0) == 'i' || start.charAt(0) == 'I') ? 8 : startX;
		startX = (start.charAt(0) == 'j' || start.charAt(0) == 'J') ? 9 : startX;

		Tile tile = board.tiles[startX][startY];
		
		if (tile.piece != null)
		{
			piece = tile.piece;
			startPosition = tile;
		}
		
		int endX = 0;
		int endY = Character.getNumericValue(end.charAt(1));
		
		endX = (end.charAt(0) == 'a' || end.charAt(0) == 'A') ? 0 : endX;
		endX = (end.charAt(0) == 'b' || end.charAt(0) == 'B') ? 1 : endX;
		endX = (end.charAt(0) == 'c' || end.charAt(0) == 'C') ? 2 : endX;
		endX = (end.charAt(0) == 'd' || end.charAt(0) == 'D') ? 3 : endX;
		endX = (end.charAt(0) == 'e' || end.charAt(0) == 'E') ? 4 : endX;
		endX = (end.charAt(0) == 'f' || end.charAt(0) == 'F') ? 5 : endX;
		endX = (end.charAt(0) == 'g' || end.charAt(0) == 'G') ? 6 : endX;
		endX = (end.charAt(0) == 'h' || end.charAt(0) == 'H') ? 7 : endX;
		endX = (end.charAt(0) == 'i' || end.charAt(0) == 'I') ? 8 : endX;
		endX = (end.charAt(0) == 'j' || end.charAt(0) == 'J') ? 9 : endX;
		
		endPosition = board.tiles[endX][endY];
	}
	
	public boolean IsValid()
	{
		if (piece != null && startPosition != null && endPosition != null && activePlayer != null && piece.owner == activePlayer)
			return true;
		return false;
	}

}

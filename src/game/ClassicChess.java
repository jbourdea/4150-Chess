package game;

public class ClassicChess extends Rules {

	public ClassicChess() {
		super();
		
		
	}
	
	public Board SetStartingPositions(Player white, Player black) {
		
		Board board = new Board(8,8);
		
		board.tiles[0][1].addPiece(new Pawn(white));
		board.tiles[1][1].addPiece(new Pawn(white));
		board.tiles[2][1].addPiece(new Pawn(white));
		board.tiles[3][1].addPiece(new Pawn(white));
		board.tiles[4][1].addPiece(new Pawn(white));
		board.tiles[5][1].addPiece(new Pawn(white));
		board.tiles[6][1].addPiece(new Pawn(white));
		board.tiles[7][1].addPiece(new Pawn(white));
		
		board.tiles[0][6].addPiece(new Pawn(black));
		board.tiles[1][6].addPiece(new Pawn(black));
		board.tiles[2][6].addPiece(new Pawn(black));
		board.tiles[3][6].addPiece(new Pawn(black));
		board.tiles[4][6].addPiece(new Pawn(black));
		board.tiles[5][6].addPiece(new Pawn(black));
		board.tiles[6][6].addPiece(new Pawn(black));
		board.tiles[7][6].addPiece(new Pawn(black));
		
		return board;
	}
	
	public boolean ValidateMove(Move move)
	{
		//TODO:	- ensure that the moving piece can legally get to the move end point.
		// 		- ensure that the requested move does not put the active player in check
		//		- return true if the move is valid, false otherwise
		
		return true;
	}
}

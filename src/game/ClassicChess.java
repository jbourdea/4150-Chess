package game;

public class ClassicChess extends Rules {

	public ClassicChess() {
		super();
		
		this.board = new Board(8,8);
		
	}
	
	public void SetStartingPositions(Player white, Player black) {
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
		
	}
}

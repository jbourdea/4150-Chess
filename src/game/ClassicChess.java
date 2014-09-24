package game;

public class ClassicChess extends Rules {

	public ClassicChess() {
		super();
		
		this.board = new Board(8,8);
		
		setStartingPositions();	
	}
	
	private void setStartingPositions() {
		/*
		for(int x = 0; x < this.boardWidth; x++) {
			for(int y = 0; y < this.boardHeight; y++) {
				boardPieces[x][y] = new Tile();
			}
		}
		
		boardPieces[0][0].addPiece(new Piece(Piece.PIECE_ROOK, Piece.PLAYER_ONE));
		boardPieces[1][0].addPiece(new Piece(Piece.PIECE_KNIGHT, Piece.PLAYER_ONE));
		boardPieces[2][0].addPiece(new Piece(Piece.PIECE_BISHOP, Piece.PLAYER_ONE));
		boardPieces[3][0].addPiece(new Piece(Piece.PIECE_KING, Piece.PLAYER_ONE));
		boardPieces[4][0].addPiece(new Piece(Piece.PIECE_QUEEN, Piece.PLAYER_ONE));
		boardPieces[5][0].addPiece(new Piece(Piece.PIECE_BISHOP, Piece.PLAYER_ONE));
		boardPieces[6][0].addPiece(new Piece(Piece.PIECE_KNIGHT, Piece.PLAYER_ONE));
		boardPieces[7][0].addPiece(new Piece(Piece.PIECE_ROOK, Piece.PLAYER_ONE));
		
		boardPieces[0][this.boardHeight - 1].addPiece(new Piece(Piece.PIECE_ROOK, Piece.PLAYER_TWO));
		boardPieces[1][this.boardHeight - 1].addPiece(new Piece(Piece.PIECE_KNIGHT, Piece.PLAYER_TWO));
		boardPieces[2][this.boardHeight - 1].addPiece(new Piece(Piece.PIECE_BISHOP, Piece.PLAYER_TWO));
		boardPieces[3][this.boardHeight - 1].addPiece(new Piece(Piece.PIECE_KING, Piece.PLAYER_TWO));
		boardPieces[4][this.boardHeight - 1].addPiece(new Piece(Piece.PIECE_QUEEN, Piece.PLAYER_TWO));
		boardPieces[5][this.boardHeight - 1].addPiece(new Piece(Piece.PIECE_BISHOP, Piece.PLAYER_TWO));
		boardPieces[6][this.boardHeight - 1].addPiece(new Piece(Piece.PIECE_KNIGHT, Piece.PLAYER_TWO));
		boardPieces[7][this.boardHeight - 1].addPiece(new Piece(Piece.PIECE_ROOK, Piece.PLAYER_TWO));
		
		for(int x = 0; x < this.boardWidth; x++) {
			boardPieces[x][1].addPiece(new Piece(Piece.PIECE_PAWN, Piece.PLAYER_ONE));
			boardPieces[x][this.boardHeight - 2].addPiece(new Piece(Piece.PIECE_PAWN, Piece.PLAYER_TWO));
		}
		*/
		
	}
}

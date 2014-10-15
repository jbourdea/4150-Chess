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
	
	public boolean ValidateMove(Move move, Board board)
	{
		//TODO:	- ensure that the moving piece can legally get to the move end point.
		// 		- ensure that the requested move does not put the active player in check
		//		- return true if the move is valid, false otherwise
		
		if(move.piece.getClass() == Pawn.class) {
			// get direction it can go in
			int direction = -1;
			
			// white on top
			if(move.activePlayer.color.equals("White")) {
				direction = 1;
			}
			
			int yDifference = move.endPosition.yCord - move.startPosition.yCord;
			int xDifference = Math.abs(move.endPosition.xCord - move.startPosition.xCord);
			if(yDifference == direction) {
				// pawn is moving straight by 1 space
				if(xDifference == 0) {
					// if there is no piece ahead, it's valid
					if(move.endPosition.piece == null) {
						return true;
					}
					else {
						return false;
					}
				}
				// pawn is moving diagonal by 1 space
				else if(xDifference == 1) {
					// can't move diagonal without a piece
					if(move.endPosition.piece == null) {
						return false;
					}
					// can only move diagonal if the piece is owned by the other player
					else if(!move.endPosition.piece.owner.color.equals(move.activePlayer.color)) {
						return true;
					}
				}
			}
			else if(move.piece.hasMoved == false && yDifference == direction * 2) {
				// double jump for first move
				//checking that the final position is empty (does not contain a piece) and that the space in between is also empty.
				if(move.endPosition.piece == null && board.tiles[move.startPosition.xCord][move.startPosition.yCord - direction].piece == null) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		
		
		return false;
	}
}

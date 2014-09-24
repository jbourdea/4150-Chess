package game;

public class Piece {
	public static final int PIECE_NONE = 0;
	public static final int PIECE_PAWN = 1;
	public static final int PIECE_ROOK = 2;
	public static final int PIECE_KNIGHT = 3;
	public static final int PIECE_BISHOP = 4;
	public static final int PIECE_QUEEN = 5;
	public static final int PIECE_KING = 6;
	
	public static final int PLAYER_NONE = 0;
	public static final int PLAYER_ONE = 1;
	public static final int PLAYER_TWO = 2;
	
	public int pieceType = PIECE_NONE;
	public int piecePlayer = PLAYER_NONE;
	
	public Piece() {
		this.pieceType = PIECE_NONE;
		this.piecePlayer = PLAYER_NONE;
	}
	
	public Piece(int pieceType, int piecePlayer) {
		this.pieceType = pieceType;
		this.piecePlayer = piecePlayer;
	}
	
	public String toString() {
		switch(this.pieceType) {
		case 1:
			return this.piecePlayer == PLAYER_ONE ? "P" : "p";
		case 2:
			return this.piecePlayer == PLAYER_ONE ? "R" : "r";
		case 3:
			return this.piecePlayer == PLAYER_ONE ? "K" : "k";
		case 4:
			return this.piecePlayer == PLAYER_ONE ? "B" : "b";
		case 5:
			return this.piecePlayer == PLAYER_ONE ? "Q" : "q";
		case 6:
			return this.piecePlayer == PLAYER_ONE ? "K" : "k";
		default:
			return "O";
		}
	}

}
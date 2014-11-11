package game;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClassicChessTest {

	@Test
	public void testSetStartingPositions() {
		ClassicChess classicChess = new ClassicChess();
		Player white = new Player();
		white.color = "white";
		Player black = new Player();
		black.color = "black";
		Board board = classicChess.SetStartingPositions(white, black);
		assertEquals("board height must be 8 for classic chess", 8, board.height);
		assertEquals("board width must be 8 for classic chess", 8, board.width);
		
		// white board positions
		assertEquals("White Rook must be placed in the correct position", Rook.class, board.tiles[0][0].piece.getClass());
		assertEquals("White Knight must be placed in the correct position", Knight.class, board.tiles[1][0].piece.getClass());
		assertEquals("White Bishop must be placed in the correct position", Bishop.class, board.tiles[2][0].piece.getClass());
		assertEquals("White King must be placed in the correct position", King.class, board.tiles[3][0].piece.getClass());
		assertEquals("White Queen must be placed in the correct position", Queen.class, board.tiles[4][0].piece.getClass());
		assertEquals("White Bishop must be placed in the correct position", Bishop.class, board.tiles[5][0].piece.getClass());
		assertEquals("White Knight must be placed in the correct position", Knight.class, board.tiles[6][0].piece.getClass());
		assertEquals("White Rook must be placed in the correct position", Rook.class, board.tiles[7][0].piece.getClass());
		for(int i=0; i <= 7; i++) {
			assertEquals("White Pawn must be placed in the correct position", Pawn.class, board.tiles[i][1].piece.getClass());
		}
		
		// black board positions
		assertEquals("Black Rook must be placed in the correct position", Rook.class, board.tiles[0][7].piece.getClass());
		assertEquals("Black Knight must be placed in the correct position", Knight.class, board.tiles[1][7].piece.getClass());
		assertEquals("Black Bishop must be placed in the correct position", Bishop.class, board.tiles[2][7].piece.getClass());
		assertEquals("Black King must be placed in the correct position", King.class, board.tiles[3][7].piece.getClass());
		assertEquals("Black Queen must be placed in the correct position", Queen.class, board.tiles[4][7].piece.getClass());
		assertEquals("Black Bishop must be placed in the correct position", Bishop.class, board.tiles[5][7].piece.getClass());
		assertEquals("Black Knight must be placed in the correct position", Knight.class, board.tiles[6][7].piece.getClass());
		assertEquals("Black Rook must be placed in the correct position", Rook.class, board.tiles[7][7].piece.getClass());
		for(int i=0; i <= 7; i++) {
			assertEquals("Black Pawn must be placed in the correct position", Pawn.class, board.tiles[i][6].piece.getClass());
		}
	}

	@Test
	public void testGetStalemateMessage() {
		ClassicChess classicChess = new ClassicChess();
		assertEquals("Stalement message must be initialized","No valid moves available, game ends in a stalemate.", classicChess.stalemateMessage);
	}

	@Test
	public void testSetLastMoveJump() {
		ClassicChess classicChess = new ClassicChess();
		Player white = new Player();
		white.color = "white";
		Player black = new Player();
		black.color = "black";
		Board board = classicChess.SetStartingPositions(white, black);
		classicChess.setLastMoveJump(white, board);
		
		for(Tile tile : board.listOfTiles) {
			if(tile.piece != null) {
				if(tile.piece.getClass() == Pawn.class && tile.piece.owner == white){
					Pawn p = (Pawn)tile.piece;
					assertEquals("Every white pawn piece should have lastMoveJump set to false", false, p.lastMoveJump);
				}
			}
		}
		
		classicChess.setLastMoveJump(black, board);
		
		for(Tile tile : board.listOfTiles) {
			if(tile.piece != null) {
				if(tile.piece.getClass() == Pawn.class && tile.piece.owner == black){
					Pawn p = (Pawn)tile.piece;
					assertEquals("Every black pawn piece should have lastMoveJump set to false", false, p.lastMoveJump);
				}
			}
		}
		
	}

}

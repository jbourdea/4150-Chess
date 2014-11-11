package game;

import static org.junit.Assert.*;

import org.junit.Test;

public class MoveTest {

	@Test
	public void testMove() {
		Move move = new Move();
		
		assertNotNull(move);
		assertNull(move.activePlayer);
		assertNull(move.startPosition);
		assertNull(move.endPosition);
		assertNull(move.piece);
	}

	@Test
	public void testMoveMove() {
		Move move = new Move();
		Player white = new Player();
		white.color = "White";
		move.activePlayer = white;
		move.startPosition = new Tile();
		move.endPosition = new Tile();
		move.piece = new Pawn(white);
		Move moveCopy = new Move(move);
		
		assertNotNull(moveCopy);
		assertNotNull(move.activePlayer);
		assertTrue(moveCopy.activePlayer.color.equals(white.color));
		assertNotNull(moveCopy.startPosition);
		assertNotNull(moveCopy.endPosition);
		assertNotNull(moveCopy.piece);
		assertTrue(moveCopy.piece.getClass() == Pawn.class);
	}

	@Test
	public void testMovePlayerTileTile() {
		Player white = new Player();
		white.color = "White";
		Move move = new Move(white, new Tile(new Pawn(white)), new Tile());
		
		assertNotNull(move);
		assertNotNull(move.activePlayer);
		assertTrue(move.activePlayer.color.equals(white.color));
		assertNotNull(move.startPosition);
		assertNotNull(move.endPosition);
		assertNotNull(move.piece);
		assertTrue(move.piece.getClass() == Pawn.class);
	}

	@Test
	public void testMovePlayerStringBoard() {
		Board board = new Board(8, 8);
		Player white = new Player();
		white.color = "White";
		Player black = new Player();
		black.color = "Black";
		
		board.AddPiece(new King(white), 1, 1);
		board.AddPiece(new King(black), 6, 6);
		
		Move move = new Move(white, "b1-b2", board);
		
		assertNotNull(move);
		assertNotNull(move.activePlayer);
		assertTrue(move.activePlayer.color.equals(white.color));
		assertNotNull(move.startPosition);
		assertTrue(move.startPosition.xCord == 1);
		assertTrue(move.startPosition.yCord == 1);
		assertNotNull(move.endPosition);
		assertTrue(move.endPosition.xCord == 1);
		assertTrue(move.endPosition.yCord == 2);
		assertNotNull(move.piece);
		assertTrue(move.piece.getClass() == King.class);
	}

	@Test
	public void testIsValid() {
		Board board = new Board(8, 8);
		Player white = new Player();
		white.color = "White";
		Player black = new Player();
		black.color = "Black";
		
		board.AddPiece(new King(white), 1, 1);
		board.AddPiece(new King(black), 6, 6);
		
		Move move = new Move(white, "b1-b2", board);
		
		assertTrue(move.IsValid());
	}

	@Test
	public void testToString() {
		Board board = new Board(8, 8);
		Player white = new Player();
		white.color = "White";
		Player black = new Player();
		black.color = "Black";
		
		board.AddPiece(new King(white), 1, 1);
		board.AddPiece(new King(black), 6, 6);
		
		Move move = new Move(white, "b1-b2", board);
		
		assertEquals(move.toString(), "== Move == \n Start Position => xCord => B yCord => 1 piece => K\n End Position => xCord => B yCord => 2 piece => N/A");
	}

	@Test
	public void testConvertToDifferentBoard() {
		Board board = new Board(8, 8);
		Player white = new Player();
		white.color = "White";
		Player black = new Player();
		black.color = "Black";
		
		board.AddPiece(new King(white), 1, 1);
		board.AddPiece(new King(black), 6, 6);
		
		Move move = new Move(white, "b1-b2", board);
		
		Board newBoard = new Board(board);
		Move newMove = new Move(move);
		
		newMove.ConvertToDifferentBoard(newBoard);
		
		//section of "Game.CompleteMove" that would simulate the move being valid and the piece being moved
		newMove.startPosition.piece = null;
		newMove.endPosition.piece = newMove.piece;
		
		assertNotNull(board);
		assertNotNull(board.tiles[1][1].piece);
		assertTrue(board.tiles[1][1].piece.getClass() == King.class);
		assertSame(board.tiles[1][1].piece.owner, white);
		assertNotNull(board.tiles[6][6].piece);
		assertTrue(board.tiles[6][6].piece.getClass() == King.class);
		assertSame(board.tiles[6][6].piece.owner, black);
		
		assertNotNull(newBoard);
		assertNotNull(newBoard.tiles[1][2].piece);
		assertTrue(newBoard.tiles[1][2].piece.getClass() == King.class);
		assertSame(newBoard.tiles[1][2].piece.owner, white);
		assertNotNull(newBoard.tiles[6][6].piece);
		assertTrue(newBoard.tiles[6][6].piece.getClass() == King.class);
		assertSame(newBoard.tiles[6][6].piece.owner, black);
	}

}

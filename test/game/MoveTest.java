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
		Player player1 = new Player();
		player1.color = "White";
		move.activePlayer = player1;
		move.startPosition = new Tile();
		move.endPosition = new Tile();
		move.piece = new Pawn(player1);
		Move moveCopy = new Move(move);
		
		assertNotNull(moveCopy);
		assertTrue(moveCopy.activePlayer.color.equals(player1.color));
		assertNotNull(moveCopy.startPosition);
		assertNotNull(moveCopy.endPosition);
		assertNotNull(moveCopy.piece);
		assertTrue(move.piece.getClass() == Pawn.class);
	}

	@Test
	public void testMovePlayerTileTile() {
		fail("Not yet implemented");
	}

	@Test
	public void testMovePlayerStringBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsValid() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testConvertToDifferentBoard() {
		fail("Not yet implemented");
	}

}

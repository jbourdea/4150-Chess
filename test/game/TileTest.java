package game;

import static org.junit.Assert.*;

import org.junit.Test;

public class TileTest {

	@Test
	public final void testTile() {
		Tile tile = new Tile();
		assertNotNull(tile);
		assertNull(tile.piece);
	}

	@Test
	public final void testTilePiece() {
		Player white = new Player();
		white.color = "White";
		Queen whiteQueen = new Queen(white);
		Tile tile = new Tile(whiteQueen);
		assertNotNull(tile);
		assertNotNull(tile.piece);
		assertTrue(tile.piece.getClass() == Queen.class);
	}

	@Test
	public final void testAddPiece() {
		Player white = new Player();
		white.color = "White";
		Queen whiteQueen = new Queen(white);
		Tile tile = new Tile();
		assertNotNull(tile);
		tile.addPiece(whiteQueen);
		assertNotNull(tile.piece);
		assertTrue(tile.piece.getClass() == Queen.class);
	}

	@Test
	public final void testGetPiece() {
		Player white = new Player();
		white.color = "White";
		Queen whiteQueen = new Queen(white);
		Tile tile = new Tile(whiteQueen);
		assertNotNull(tile);
		assertNotNull(tile.piece);
		assertSame(tile.getPiece(), whiteQueen);
	}

	@Test
	public final void testToString() {
		Player white = new Player();
		white.color = "White";
		Queen whiteQueen = new Queen(white);
		Tile tile = new Tile(whiteQueen);
		assertNotNull(tile);
		tile.xCord = 3;
		tile.yCord = 5;
		assertNotNull(tile.piece);
		assertSame(tile.getPiece(), whiteQueen);
		assertEquals(tile.toString(),"xCord => D yCord => 5 piece => Q");
	}

}

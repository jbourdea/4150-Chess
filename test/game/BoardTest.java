package game;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {
	
	public int width = 8;
	public int height = 8;

	@Test
	public final void testBoardIntInt() {
		Board board = new Board(width, height);
		assertNotNull(board);
		assertTrue(board.height == 8);
		assertTrue(board.width == 8);
		assertNotNull(board.listOfTiles);
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				assertNotNull(board.tiles[x][y]);
				assertTrue(board.tiles[x][y].getClass() == Tile.class);
				assertTrue(board.tiles[x][y].xCord == x && board.tiles[x][y].yCord == y);
			}
		}
		for (Tile tile: board.listOfTiles) {
			assertNotNull(tile);
		}
	}

	@Test
	public final void testBoardBoard() {
		Board board = new Board(width, height);
		assertNotNull(board);
		Board newBoard = new Board(board);
		assertNotNull(newBoard);
		assertTrue(newBoard.height == 8);
		assertTrue(newBoard.width == 8);
		assertNotNull(newBoard.listOfTiles);
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				assertNotNull(newBoard.tiles[x][y]);
				assertTrue(newBoard.tiles[x][y].getClass() == Tile.class);
				assertTrue(newBoard.tiles[x][y].xCord == x && newBoard.tiles[x][y].yCord == y);
			}
		}
		for (Tile tile: newBoard.listOfTiles) {
			assertNotNull(tile);
		}
	}

	@Test
	public final void testAddPiece() {
		Board board = new Board(width, height);
		assertNotNull(board);
		Player white = new Player();
		white.color = "White";
		Queen whiteQueen = new Queen(white);
		board.AddPiece(whiteQueen, 3, 5);
		assertNotNull(board.tiles[3][5]);
		assertTrue(board.tiles[3][5].piece.getClass() == Queen.class);
	}

}

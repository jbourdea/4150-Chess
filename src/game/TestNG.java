package game;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG {
	
	/**
	 * This method is used to handle each move that the testing suite wants to make.
	 * @param move The move that is going to be made
	 */
	public void CompleteMove(Move move, Game game) {
		if (move.IsValid() && game.rules.ValidateMove(move, game.board) == 0) {
			MoveCompleteResult moveResult = game.CompleteMove(move);
			game.NextTurn();
			
			// check for game ending condition
			if (moveResult.gameOver) {
				game.GameOver = true;
			}
		}

	}
	
	@Test
	public void testGameInstantiation() {
		
		Game game = new Game();
		
		Assert.assertNotNull(game.white);
		Assert.assertNotNull(game.black);
		Assert.assertEquals(game.turnNumber, 0);
		Assert.assertEquals(game.activePlayer, game.black);
	}
	
	@Test
	public void testBoardInstantiation() {
		
		Board board = new Board(8,8);
		
		Assert.assertEquals(board.width, 8);
		Assert.assertEquals(board.height, 8);
		Assert.assertNotNull(board.tiles);
		
		Board newBoard = new Board(board);
		
		Assert.assertEquals(newBoard.width, 8);
		Assert.assertEquals(newBoard.height, 8);
		Assert.assertNotNull(newBoard.tiles);
	}
  
	@Test
	public void testPieceInstantiation() {
		
		Player white = new Player();
		white.color = "White";
		Piece piece = new King(white);
		
		Assert.assertEquals(white, piece.owner);
		Assert.assertEquals("K", piece.toString());
	}
	
	@Test
	public void testCheck() {
		View view = new View();
		Game game = new Game();
		game.rules = new ClassicChess();
		game.board = new Board(8,8);
		game.board.AddPiece(new King(game.white), 2, 2);
		game.board.AddPiece(new Knight(game.white), 3, 3);
		game.board.AddPiece(new Knight(game.black), 5, 5);
		game.board.AddPiece(new King(game.black), 6, 6);
		game.NextTurn();
		CompleteMove(new Move(game.activePlayer, "d3-e5", game.board), game);
		
		Assert.assertEquals(game.rules.CheckForCheck(game.activePlayer.opponent, game.board), true);
		Assert.assertEquals(game.GameOver, false);
	}
	
	@Test
	public void testCheckMate() {
		View view = new View();
		Game game = new Game();
		game.rules = new ClassicChess();
		game.board = new Board(8,8);
		game.board.AddPiece(new King(game.white), 2, 2);
		game.board.AddPiece(new Rook(game.white), 7, 2);
		game.board.AddPiece(new Rook(game.white), 0, 4);
		game.board.AddPiece(new Rook(game.white), 0, 6);
		game.board.AddPiece(new King(game.black), 5, 5);
		game.NextTurn();
		CompleteMove(new Move(game.activePlayer, "h2-h5", game.board), game);
		
		Assert.assertEquals(game.rules.CheckForCheck(game.activePlayer.opponent, game.board), true);
		Assert.assertEquals(game.GameOver, true);
	}
}

package game;

public class TestingSuite {
	
	public View view = null;
	public Game game = null;
	
	public TestingSuite(View view) {
		this.view = view;
	}
	
	public void Start() {
		//do testing
		
		game = null;
		
		
		//classic chess
		System.out.println("Testing Checkmate");
		
		game = new Game();
		game.rules = new ClassicChess();
		game.board = game.rules.SetStartingPositions(this.game.white, this.game.black);
		view.DisplayBoard(game.board);
		game.NextTurn();
		
		try {
			CompleteMove(new Move(game.activePlayer, "c1-c2", game.board));
			CompleteMove(new Move(game.activePlayer, "d6-d4", game.board));
			CompleteMove(new Move(game.activePlayer, "b1-b3", game.board));
			CompleteMove(new Move(game.activePlayer, "e7-a3", game.board));
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
		//take all chess
		game = new Game();
		game.rules = new TakeAllChess();
		game.board = game.rules.SetStartingPositions(this.game.white, this.game.black);
		//view.DisplayBoard(game.board);
		game.NextTurn();
		
		//movement
		
		
		System.out.println("Testing Complete");
	}
	
	private void CompleteMove(Move move) {
		System.out.println(move.activePlayer + " moves " + move.text);
		game.NextTurn();
		if (move.IsValid() && game.rules.ValidateMove(move, game.board) == 0) {
			MoveCompleteResult moveResult = game.CompleteMove(move);
			view.DisplayBoard(game.board);
			// check for game ending condition
			
			if(moveResult.gameOver) {
				view.DisplayWinMessage(moveResult.winner);
			}
		}
		else {
			view.DisplayErrorMessage();
		}
		
	}

}

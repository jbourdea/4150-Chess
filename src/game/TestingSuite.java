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
		System.out.println("**Testing Classic Chess**");
		
		if (FoolsMate()) { System.out.println("Fool's Mate test was successful."); }
		else { System.out.println("Fool's Mate test failed."); }
		
		if (ScholarsMate()) { System.out.println("Scholar's Mate test was successful."); }
		else { System.out.println("Scholar's Mate test failed."); }
		
		
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
	
	private boolean FoolsMate() {
		boolean success = true;
		
		System.out.println("Beginning fools mate test..");
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
			success = false;
			System.out.println(e.getMessage());
		}
		return success;
	}
	
	private boolean ScholarsMate() {
		boolean success = true;
		
		System.out.println("Beginning Scholar's mate test..");
		game = new Game();
		game.rules = new ClassicChess();
		game.board = game.rules.SetStartingPositions(this.game.white, this.game.black);
		view.DisplayBoard(game.board);
		game.NextTurn();
		
		try {
			CompleteMove(new Move(game.activePlayer, "d1-d3", game.board)); //w
			CompleteMove(new Move(game.activePlayer, "d6-d4", game.board)); //b
			CompleteMove(new Move(game.activePlayer, "c0-f3", game.board)); //w
			CompleteMove(new Move(game.activePlayer, "c7-f4", game.board)); //b
			CompleteMove(new Move(game.activePlayer, "e0-a4", game.board)); //w
			CompleteMove(new Move(game.activePlayer, "b7-c5", game.board)); //b
			CompleteMove(new Move(game.activePlayer, "a4-c6", game.board)); //w
		}
		catch(Exception e)
		{
			success = false;
			System.out.println(e.getMessage());
		}
		return success;
	}

}

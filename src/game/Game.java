package game;

public class Game {
	
	public Rules rules = null;
	
	public Board board = null;
	
	public int turnNumber = 0;
	public Player activePlayer = null;
	
	public Player white = null;
	public Player black = null;
	
	public Game() {
		white = new Player();
		black = new Player();
		
		white.color = "White";
		black.color = "Black";
		
		turnNumber = 0;
		activePlayer = black;
	}
	
	public void NextTurn() {
		turnNumber++;
		
		if (activePlayer == black) {
			activePlayer = white;
		}
		else {
			activePlayer = black;
		}
		
		//TODO: update view with new turn information
	}
	
	
	/**
	 * 
	 * @param move
	 * @return 	0 - normal move was made, not a game ending condition
	 * 			1 - white wins, game ends
	 * 			2 - black wins, game ends
	 * 			3 - stalemate, game ends
	 */
	public int CompleteMove(Move move) {
		Piece piece = move.piece;
		
		move.startPosition.piece = null;
		move.endPosition.piece = piece;
		piece.hasMoved = true;
		int gameResult = rules.ruleCompleteMove(activePlayer, board, move);
		return gameResult;
	}
	
	public static void main(String[] args) {
	    
		Controller controller = new Controller(new View());
		
		controller.play();
		
	}

}
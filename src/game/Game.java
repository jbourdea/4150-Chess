package game;

/**
 * The Game class is essentially the starting point for the game. It creates the controller that holds the functionality of playing, and instantiates the game.
 */
public class Game {
	
	public Rules rules = null;
	public Board board = null;
	
	public int turnNumber = 0;
	public Player activePlayer = null;
	
	public Player white = null;
	public Player black = null;
	
	public boolean GameOver = false;
	
	public Game() {
		white = new Player();
		black = new Player();
		
		white.color = "White";
		black.color = "Black";
		
		white.opponent = black;
		black.opponent = white;
		
		turnNumber = 0;
		activePlayer = black;
	}
	
	/**
	 * Called after a move has been inputed and the rules have checked the board for a win/tie condition. Changes the active player
	 */
	public void NextTurn() {
		turnNumber++;
		
		if (activePlayer == black) {
			activePlayer = white;
		}
		else {
			activePlayer = black;
		}
	}
	
	
	/**
	 * This method is called when the move is finally validated and is used to move the piece and call the rule specific 
	 * @param move The validated move inputed by the player.
	 * @return 	MoveCompleteResult;
	 */
	public MoveCompleteResult CompleteMove(Move move) {
		Piece piece = move.piece;
		
		move.startPosition.piece = null;
		move.endPosition.piece = piece;
		piece.hasMoved = true;
		MoveCompleteResult gameResult = rules.ruleCompleteMove(board, move);
		return gameResult;
	}
	
	/**
	 * Main class, creates a game controller and initiates a play.
	 * @param args Any arguments passed into the program.
	 */
	public static void main(String[] args) {
	    
		Controller controller = new Controller(new View());
		
		controller.play();
		
	}

}
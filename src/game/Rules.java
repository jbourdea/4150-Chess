 package game;

 
 /**
  * Rules contains custom starting positions and custom move validation for each piece 
  */
public class Rules {
	public String stalemateMessage;
		
	public Rules() {
		
	}
	
	/**
	 * Creates a newly set up board
	 * @return The game board with pieces in starting positions
	 */
	public Board SetStartingPositions(Player white, Player black) {
		return null;
	}
	
	/**
	 * Returns the validity of a move
	 * @return:
	 * 0 - move is valid
	 * 1 - Move doesn't align with the rules for that piece types allowed movement
	 * 2 - Movement puts the user in check
	 * 3 - A kill is possible at another spot on the board but the user is moving to not kill
	*/
	public int ValidateMove(Move move, Board board)
	{
		return 1;
	}
	
	/**
	 * Allows rules to have control over what happens after a move
	 * @param activePlayer 			- reference to the player whos turn it is
	 * @param board 				- reference to the game board
	 * @param move					- reference to the move project
	 * @return MoveCompleteResult	- result of the last move, whether it ends the game and who wins
	 */
	public MoveCompleteResult ruleCompleteMove(Player activePlayer, Board board, Move move){
		return new MoveCompleteResult(false);
	}
	
	/**
	 * Allows rules to have control over what happens after a move
	 * @param board 				- reference to the game board
	 * @param move					- reference to the move project
	 * @return MoveCompleteResult	- result of the last move, whether it ends the game and who wins
	 */
	public MoveCompleteResult ruleCompleteMove(Board board, Move move){
		return new MoveCompleteResult(false);
	}
	
	/**
	 * Called after every move in classic and peasants revolt. Checks if the move a player just submitted puts them self into check
	 * @return false - move given puts the active player in check, true - the move is valid, it doesn't put the active player in check.
	 */
	private boolean validateBoardState(Move move, Board board) {
		return false;
	}
	
	/**
	 * Called when a pawn gets to the far side of the board. Gets the choice of piece from teh user and changes the piece accordingly.
	 */
	public void getPawnPromotionInput(Board board, Move move) {
		
	}
	
	/**
	 * Called before every move, checks for stalemate and ends the game accordingly.
	 */
	public boolean checkForStalemate(Player activePlayer, Board board) {
		return false;
	}
	
	/**
	 * Gives the stalemate message based on which player was put into stalemate.
	 * @param activePlayer - The player who's turn it currently is.
	 * @return true if the current player was just put into stalemate, false if they weren't
	 */
	public String getStalemateMessage(Player activePlayer) {
		return this.stalemateMessage;
	}
	
	public boolean CheckForCheck(Player activePlayer, Board board) {
		return false;
	}
	
}

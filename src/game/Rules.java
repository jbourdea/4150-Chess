 package game;

public class Rules {
		
	public Rules() {
		
	}
	
	public Board SetStartingPositions(Player white, Player black) {
		return null;
	}
	
	/*
	 * Return Values:
	 * 0 - move is valid
	 * 1 - Move doesn't align with the rules for that piece types allowed movement
	 * 2 - Movement puts the user in check
	 * 3 - A kill is possible at another spot on the board but the user is moving to not kill
	 * 	 
	*/
	public int ValidateMove(Move move, Board board)
	{
		return 1;
	}
	
	
	/**
	 * 
	 * @param activePlayer
	 * @param board
	 * @param move
	 * @return 	0 - normal move was made, not a game ending condition
	 * 			1 - white wins, game ends
	 * 			2 - black wins, game ends
	 * 			3 - stalemate, game ends
	 */
	public int ruleCompleteMove(Player activePlayer, Board board, Move move){
		return 0;
	}
	
	
}
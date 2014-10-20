package game;

/**
 * Container class for the result of a game state. Specifying if the game is ended or not. If the game is ended, 
 * and no winner is provided it is assumed to be a stalemate.
 *
 */
public class MoveCompleteResult {
	public boolean gameOver;
	public Player winner;
	
	public MoveCompleteResult(boolean endGame)
	{
		gameOver = endGame;
	}
}

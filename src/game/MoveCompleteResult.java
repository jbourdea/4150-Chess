package game;

public class MoveCompleteResult {
	public boolean gameOver;
	public Player winner;
	
	public MoveCompleteResult(boolean endGame)
	{
		gameOver = endGame;
	}
}

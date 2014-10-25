package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestingSuite {
	
	public View view = null;
	public Game game = null;
	
	public TestingSuite(View view) {
		this.view = view;
	}
	
	public void Start() {
		
		game = null;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		while (input == null) {
			
			System.out.println("Welcome to the Automatic Testing Suite");
			System.out.println("Select a test to run:");
			System.out.println("0. Return to main menu.");
			System.out.println("1. Classic Chess: Fool's Mate");
			System.out.println("2. Classic Chess: Scholar's Mate");
			
			
			try {
				 input = br.readLine();
			} catch (IOException e) {
				input = null;
				View.setErrorMessage("Input Error: Unable to read input.");
				view.DisplayErrorMessage();
			}
			
			if (input != null) {
				try {
					if(input.equalsIgnoreCase("exit") ||
							input.equalsIgnoreCase("quit") ||
							input.equalsIgnoreCase("q")) {
						view.DisplayExitMessage();
						System.exit(0);
					}
					
					int option = Integer.parseInt(input);
					
					if (option == 0) { 
						break;
					}
					else if (option == 1) { 
						if (FoolsMate()) { System.out.println("Fool's Mate test was successful.\n"); }
						else { System.out.println("Fool's Mate test failed.\n"); }
						input = null;
					}
					else if (option == 2) { 
						if (ScholarsMate()) { System.out.println("Scholar's Mate test was successful.\n"); }
						else { System.out.println("Scholar's Mate test failed.\n"); }
						input = null;
					}
					else {
						input = null;
						System.out.println("Input Error: Not a valid test index.");
					}
				}
				catch (Exception e)
				{
					input = null;
					System.out.println("Error loading test.");
				}
			}
			
		}
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
				game.GameOver = true;
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
		if (success && game.GameOver)
		{
			return success;
		}
		return false;
		
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
		
		if (success && game.GameOver)
		{
			return success;
		}
		return false;
	}

}

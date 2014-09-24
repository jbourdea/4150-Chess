package game;
import java.io.*;

public class Controller {
	public View view = null;
	public Rules rules = null;
	public Game game = null;
	
	public Controller(View view) {
		this.view = view;
	}
	
	public void play() {
		
		while (true) {
			
			if (rules == null) {
				//request rules
				//TODO: move this output to view
				System.out.println("What variation of Chess do you want to play?");
				System.out.println("1. Classic Chess");
				System.out.println("Enter the index of the variation.");
				
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String input = null;
				while (input == null) {
					try {
						 input = br.readLine();
					} catch (IOException e) {
						input = null;
						System.out.println("Input Error: Unable to read input.");
					}
					
					if (input != null) {
						try {
							int variation = Integer.parseInt(input);
							if (variation == 1) {

								this.rules = new ClassicChess();
							}
							else {
								input = null;
								System.out.println("Input Error: Not a valid variation index.");
							}
						}
						catch (Exception e)
						{
							input = null;
							System.out.println("Error loading variation.");
						}
					}
					
				}
			}
			
			if (game == null) {
				//start new game
				this.game = new Game();
				this.rules.SetStartingPositions(this.game.white, this.game.black);
				
				//TODO: move this output to the view
				System.out.println("New game of chess started!");
				
			}
			else {
				view.DisplayBoard(rules.board);
				game.NextTurn();
				
				//TODO: move this output to view
				System.out.println("It is " + game.activePlayer.color + "'s turn.");
				System.out.println("Enter a move. (eg: a2-a3)");
				
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String input = null;
				while (input == null) {
					try {
						 input = br.readLine();
					} catch (IOException e) {
						input = null;
						System.out.println("Input Error: Unable to read input.");
					}
					
					if (input != null) {
						try {
							Move move = new Move(game.activePlayer, input);
							
							if (move.IsValid()) {
								CompleteMove(move);
							}
							else {
								input = null;
								System.out.println("Input Error: Not a valid move.");
							}
						}
						catch (Exception e)
						{
							input = null;
							System.out.println("Input Error: Unable to parse input.");
						}
					}
					
				}
			}

		}
			
		
	}
	
	public void CompleteMove(Move move) {
		//TODO: move specified piece to specified position then check boardstate
	}
}
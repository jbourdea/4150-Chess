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

	public void start() {

		game = null;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		while (input == null) {

			System.out.println("Welcome to the Automatic Testing Suite");
			System.out.println("Select a test to run:");
			System.out.println("0. Return to main menu.");
			System.out.println("1. Run All Test Cases.");
			System.out.println("2. Classic Chess: Fool's Mate");
			System.out.println("3. Classic Chess: Scholar's Mate");
			System.out.println("4. Take All Chess: Taking all pieces");
			System.out.println("5. Piece Movement: Take own piece");
			System.out.println("6. Piece Movement: Move onto self");

			try {
				input = br.readLine();
			} catch (IOException e) {
				input = null;
				View.setErrorMessage("Input Error: Unable to read input.");
				view.DisplayErrorMessage();
			}

			if (input != null) {
				try {
					if (input.equalsIgnoreCase("exit")
							|| input.equalsIgnoreCase("quit")
							|| input.equalsIgnoreCase("q")) {
						view.DisplayExitMessage();
						System.exit(0);
					}

					int option = Integer.parseInt(input);

					if (option == 0) {
						break;
					} else if (option == 1) {
						runAllTestCases();
						input = null;
					} else if (option == 2) {
						foolsMate();
						input = null;
					} else if (option == 3) {
						scholarsMate();
						input = null;
					} else if (option == 4) {
						takeAll();
						input = null;
					} else if (option == 5) {
						takeOwnPiece();
						input = null;
					} else if (option == 6) {
						moveOntoSelf();
						input = null;
					} else if (option == 7) {
						takeOwnPiece();
						input = null;
					} else if (option == 8) {
						takeOwnPiece();
						input = null;
					} else {
						input = null;
						System.out.println("Input Error: Not a valid test index.");
					}
				} catch (Exception e) {
					input = null;
					System.out.println("Error loading test.");
				}
			}

		}
	}

	private void completeMove(Move move) {
		System.out.println(move.activePlayer + " moves " + move.text);
		game.NextTurn();
		if (move.IsValid() && game.rules.ValidateMove(move, game.board) == 0) {
			MoveCompleteResult moveResult = game.CompleteMove(move);
			view.DisplayBoard(game.board);
			// check for game ending condition

			if (moveResult.gameOver) {
				view.DisplayWinMessage(moveResult.winner);
				game.GameOver = true;
			}
		} else {
			view.DisplayErrorMessage();
		}

	}

	private void runAllTestCases() {
		foolsMate();
		scholarsMate();
		takeAll();
		takeOwnPiece();
		moveOntoSelf();
	}
	
	private void foolsMate() {
		boolean success = true;

		System.out.println("Beginning Classic Chess: Fool's Mate test..");
		game = new Game();
		game.rules = new ClassicChess();
		game.board = game.rules.SetStartingPositions(this.game.white,
				this.game.black);
		view.DisplayBoard(game.board);
		game.NextTurn();

		try {
			completeMove(new Move(game.activePlayer, "c1-c2", game.board)); // w
			completeMove(new Move(game.activePlayer, "d6-d4", game.board)); // b
			completeMove(new Move(game.activePlayer, "b1-b3", game.board)); // w
			completeMove(new Move(game.activePlayer, "e7-a3", game.board)); // b

		} catch (Exception e) {
			success = false;
			System.out.println(e.getMessage());
		}
		if (success && game.GameOver) {
			System.out.println("Classic Chess: Fool's Mate test was successful.\n");
			return;
		}
		System.out.println("Classic Chess: Fool's Mate test failed.\n");
		return;

	}

	private void scholarsMate() {
		boolean success = true;

		System.out.println("Beginning Classic Chess: Scholar's Mate test..");
		game = new Game();
		game.rules = new ClassicChess();
		game.board = game.rules.SetStartingPositions(this.game.white,
				this.game.black);
		view.DisplayBoard(game.board);
		game.NextTurn();

		try {
			completeMove(new Move(game.activePlayer, "d1-d3", game.board)); // w
			completeMove(new Move(game.activePlayer, "d6-d4", game.board)); // b
			completeMove(new Move(game.activePlayer, "c0-f3", game.board)); // w
			completeMove(new Move(game.activePlayer, "c7-f4", game.board)); // b
			completeMove(new Move(game.activePlayer, "e0-a4", game.board)); // w
			completeMove(new Move(game.activePlayer, "b7-c5", game.board)); // b
			completeMove(new Move(game.activePlayer, "a4-c6", game.board)); // w
		} catch (Exception e) {
			success = false;
			System.out.println(e.getMessage());
		}

		if (success && game.GameOver) {
			System.out.println("Classic Chess: Scholar's Mate test was successful.\n");
			return;
		}
		System.out.println("Classic Chess: Scholar's Mate test failed.\n");
		return;
	}

	private void takeAll() {
		boolean success = true;

		System.out.println("Beginning Take All Chess: Taking all pieces test..");
		game = new Game();
		game.rules = new TakeAllChess();
		Board board = new Board(8, 8);
		Player white = game.white;
		Player black = game.black;

		board.AddPiece(new Pawn(white), 0, 1);
		board.AddPiece(new Pawn(black), 1, 2);

		game.board = board;
		view.DisplayBoard(game.board);
		game.NextTurn();

		try {
			completeMove(new Move(game.activePlayer, "a1-b2", game.board)); // w
		} catch (Exception e) {
			success = false;
			System.out.println(e.getMessage());
		}
		if (success && game.GameOver) {
			System.out.println("Take All Chess: Taking all pieces was successful.\n");
			return;
		}
		System.out.println("Take All Chess: Taking all pieces failed.\n");
		return;

	}

	private void takeOwnPiece() {
		boolean success = true;

		System.out.println("Beginning Piece Movement: Take own piece test..");
		game = new Game();
		game.rules = new ClassicChess();
		game.board = game.rules.SetStartingPositions(this.game.white,
				this.game.black);
		view.DisplayBoard(game.board);
		game.NextTurn();

		try {
			completeMove(new Move(game.activePlayer, "e0-f1", game.board)); // w
		} catch (Exception e) {
			success = false;
			System.out.println(e.getMessage());
		}
		if (success && game.board.tiles[4][0].piece.getClass() == Queen.class && game.board.tiles[5][1].piece.getClass() == Pawn.class) {
			System.out.println("Piece Movement: Take own piece test was successful.\n");
			return;
		}
		System.out.println("Piece Movement: Take own piece test failed.\n");
		return;

	}
	
	private void moveOntoSelf() {
		boolean success = true;

		System.out.println("Beginning Piece Movement: Move onto self test..");
		game = new Game();
		game.rules = new ClassicChess();
		game.board = game.rules.SetStartingPositions(this.game.white,
				this.game.black);
		view.DisplayBoard(game.board);
		game.NextTurn();

		try {
			completeMove(new Move(game.activePlayer, "e0-f1", game.board)); // w
		} catch (Exception e) {
			success = false;
			System.out.println(e.getMessage());
		}
		if (success && game.board.tiles[4][0].piece.getClass() == Queen.class && game.board.tiles[5][1].piece.getClass() == Pawn.class) {
			System.out.println("Piece Movement: Move onto self test was successful.\n");
			return;
		}
		System.out.println("Piece Movement: Move onto self test failed.\n");
		return;

	}

}

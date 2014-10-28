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
			System.out.println("7. Piece Movement: En Passant Positive");
			System.out.println("8. Piece Movement: En Passant Negative");
			System.out.println("9. Piece Movement: Pawns");
			System.out.println("10. Piece Movement: Rook");

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
						enPassantPositiveTest();
						input = null;
					} else if (option == 8) {
						enPassantNegativeTest();
						input = null;
					} else if (option == 9) {
						pawnMovementTest();
						input = null;
					} else if (option ==10) {
						rookMovementTest();
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

		if (move.IsValid() && game.rules.ValidateMove(move, game.board) == 0) {
			MoveCompleteResult moveResult = game.CompleteMove(move);
			game.NextTurn();
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
		enPassantPositiveTest();
		enPassantNegativeTest();
		
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
			System.out.println("| PASS | Classic Chess: Fool's Mate test was successful.\n");
			return;
		}
		System.out.println("| FAIL | Classic Chess: Fool's Mate test failed.\n");
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
			System.out.println("| PASS | Classic Chess: Scholar's Mate test was successful.\n");
			return;
		}
		System.out.println("| FAIL | Classic Chess: Scholar's Mate test failed.\n");
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
			System.out.println("| PASS | Take All Chess: Taking all pieces was successful.\n");
			return;
		}
		System.out.println("| FAIL | Take All Chess: Taking all pieces failed.\n");
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
			System.out.println("| PASS | Piece Movement: Take own piece test was successful.\n");
			return;
		}
		System.out.println("| FAIL | Piece Movement: Take own piece test failed.\n");
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
			completeMove(new Move(game.activePlayer, "e0-e0", game.board)); // w
		} catch (Exception e) {
			success = false;
			System.out.println(e.getMessage());
		}
		if (success && game.board.tiles[4][0].piece.getClass() == Queen.class) {
			System.out.println("| PASS | Piece Movement: Move onto self test was successful.\n");
			return;
		}
		System.out.println("| FAIL | Piece Movement: Move onto self test failed.\n");
		return;

	}
	
	private void enPassantPositiveTest() {
		boolean success = true;

		System.out.println("Beginning Piece Movement: En Passant Positive test..");
		game = new Game();
		game.rules = new ClassicChess();
		game.board = game.rules.SetStartingPositions(this.game.white,
				this.game.black);
		view.DisplayBoard(game.board);
		game.NextTurn();

		try {
			completeMove(new Move(game.activePlayer, "d1-d3", game.board)); // w
			completeMove(new Move(game.activePlayer, "e6-e4", game.board)); // b
			completeMove(new Move(game.activePlayer, "d3-e4", game.board)); // w
			completeMove(new Move(game.activePlayer, "d6-d4", game.board)); // b
			completeMove(new Move(game.activePlayer, "e4-d5", game.board)); // w
		} catch (Exception e) {
			success = false;
			System.out.println(e.getMessage());
		}
		if (success && game.board.tiles[3][5].piece.getClass() == Pawn.class && game.board.tiles[3][4].piece == null) {
			System.out.println("| PASS | Piece Movement: En Passant Positive test was successful.\n");
			return;
		}
		System.out.println("| FAIL | Piece Movement: En Passant Positive test failed.\n");
		return;
	}
	
	private void enPassantNegativeTest() {
		boolean success = true;

		System.out.println("Beginning Piece Movement: En Passant Negative test..");
		game = new Game();
		game.rules = new ClassicChess();
		game.board = game.rules.SetStartingPositions(this.game.white,
				this.game.black);
		view.DisplayBoard(game.board);
		game.NextTurn();

		try {
			completeMove(new Move(game.activePlayer, "d1-d3", game.board)); // w
			completeMove(new Move(game.activePlayer, "e6-e4", game.board)); // b
			completeMove(new Move(game.activePlayer, "d3-e4", game.board)); // w
			completeMove(new Move(game.activePlayer, "d6-d4", game.board)); // b
			completeMove(new Move(game.activePlayer, "a1-a2", game.board)); // w
			completeMove(new Move(game.activePlayer, "h6-h4", game.board)); // b
			completeMove(new Move(game.activePlayer, "e4-d5", game.board)); // w
		} catch (Exception e) {
			success = false;
			System.out.println(e.getMessage());
		}
		if (success && game.board.tiles[4][4].piece.getClass() == Pawn.class && game.board.tiles[3][4].piece.getClass() == Pawn.class) {
			System.out.println("| PASS | Piece Movement: En Passant Negative test was successful.\n");
			return;
		}
		System.out.println("| FAIL | Piece Movement: En Passant Negative test failed.\n");
		return;

	}
	
	

	private void rookMovementTest() {
		boolean success = true;

		System.out.println("Beginning Piece Movement: Rook test..");
		game = new Game();
		game.rules = new ClassicChess();
		game.board = game.rules.SetStartingPositions(this.game.white,
				this.game.black);
		view.DisplayBoard(game.board);
		game.NextTurn();

		try {
			completeMove(new Move(game.activePlayer, "a1-a3", game.board)); // w
			
			completeMove(new Move(game.activePlayer, "b6-b5", game.board)); // b
			// negative test: move rook past pawn
			completeMove(new Move(game.activePlayer, "a0-a4", game.board)); // w
			
			if (game.board.tiles[0][0].piece.getClass() != Rook.class || game.board.tiles[0][4].piece != null) {
				System.out.println("| FAIL | Piece Movement: Rook failed the piece infront of it test.");
				success = false;
			}
			
			// normal vertical movement
			completeMove(new Move(game.activePlayer, "a0-a2", game.board)); // w
			if (game.board.tiles[0][2].piece.getClass() != Rook.class || game.board.tiles[0][0].piece != null) {
				System.out.println("| FAIL | Piece Movement: Rook failed the vertical movement test.");
				success = false;
			}
			
			completeMove(new Move(game.activePlayer, "b5-b4", game.board)); // b
			
			// normal horizontal movement
			completeMove(new Move(game.activePlayer, "a2-b2", game.board)); // w
			if (game.board.tiles[1][2].piece.getClass() != Rook.class || game.board.tiles[0][2].piece != null) {
				System.out.println("| FAIL | Piece Movement: Rook failed the horizontal movement test.");
				success = false;
			}
			
			completeMove(new Move(game.activePlayer, "b4-b3", game.board)); // b
			
			// taking an enemy piece
			completeMove(new Move(game.activePlayer, "b2-b3", game.board)); // w
			if (game.board.tiles[1][3].piece.getClass() != Rook.class || game.board.tiles[1][2].piece != null) {
				System.out.println("| FAIL | Piece Movement: Rook failed the taking enemy piece test.");
				success = false;
			}
			
			
		} catch (Exception e) {
			success = false;
			System.out.println(e.getMessage());
		}
		if (success) {
			System.out.println("| PASS | Piece Movement: Rook test was successful.\n");
			return;
		}
		System.out.println("| FAIL | Piece Movement: Rook test failed.\n");
		return;
	}
	
	
	private void pawnMovementTest() {
		boolean success = true;

		System.out.println("Beginning Piece Movement: Pawn test..");
		game = new Game();
		game.rules = new ClassicChess();
		game.board = game.rules.SetStartingPositions(this.game.white,
				this.game.black);
		view.DisplayBoard(game.board);
		game.NextTurn();

		try {
			// move 1 piece forward
			completeMove(new Move(game.activePlayer, "a1-a2", game.board)); // w
			
			if (game.board.tiles[0][2].piece.getClass() != Pawn.class || game.board.tiles[0][1].piece != null) {
				System.out.println("| FAIL | Piece Movement: Pawn failed to move one tile forward.");
				success = false;
			}
			
			// move knight infront of black pawn
			completeMove(new Move(game.activePlayer, "b7-a5", game.board)); // b
			// double jump
			completeMove(new Move(game.activePlayer, "b1-b3", game.board)); // w
			
			if (game.board.tiles[1][3].piece.getClass() != Pawn.class || game.board.tiles[1][1].piece != null) {
				System.out.println("| FAIL | Piece Movement: Pawn failed to double jump.");
				success = false;
			}
			
			// negative test - double jump with piece infront
			completeMove(new Move(game.activePlayer, "a6-a4", game.board)); // b
			
			if (game.board.tiles[0][6].piece.getClass() != Pawn.class || game.board.tiles[0][4].piece != null || game.board.tiles[0][5].piece.getClass() != Knight.class ) {
				System.out.println("| FAIL | Piece Movement: Pawn failed the test for double jump with a piece inbetween.");
				success = false;
			}
			
			completeMove(new Move(game.activePlayer, "c6-c4", game.board)); // b
			// take other piece
			completeMove(new Move(game.activePlayer, "b3-c4", game.board)); // w
			
			if (game.board.tiles[2][4].piece.getClass() != Pawn.class || game.board.tiles[1][3].piece != null) {
				System.out.println("| FAIL | Piece Movement: Pawn failed to take the other piece.");
				success = false;
			}
			
			completeMove(new Move(game.activePlayer, "d6-d4", game.board)); // b
			// fail double jump
			completeMove(new Move(game.activePlayer, "c4-c6", game.board)); // w
			
			if (game.board.tiles[2][4].piece.getClass() != Pawn.class) {
				System.out.println("| FAIL | Piece Movement: Pawn failed the double jump test after already double jumping.");
				success = false;
			}
			
			// negative movement
			completeMove(new Move(game.activePlayer, "a2-a1", game.board)); // w
			
			if (game.board.tiles[0][2].piece.getClass() != Pawn.class) {
				System.out.println("| FAIL | Piece Movement: Pawn failed the negative direction test.");
				success = false;
			}
			
			// movement outside moveset
			completeMove(new Move(game.activePlayer, "a2-g5", game.board)); // w
			
			if (game.board.tiles[0][2].piece.getClass() != Pawn.class) {
				System.out.println("| FAIL | Piece Movement: Pawn failed the moveset movement test.");
				success = false;
			}
			
			completeMove(new Move(game.activePlayer, "h1-h3", game.board)); // w
			completeMove(new Move(game.activePlayer, "h6-h4", game.board)); // b
			
			// enemy piece infront of pawn
			completeMove(new Move(game.activePlayer, "h3-h4", game.board)); // w
			
			if (game.board.tiles[7][3].piece.getClass() != Pawn.class || game.board.tiles[7][4].piece.getClass() != Pawn.class) {
				System.out.println("| FAIL | Piece Movement: Pawn failed the piece infront of it test.");
				success = false;
			}
			
			
		} catch (Exception e) {
			success = false;
			System.out.println(e.getMessage());
		}
		if (success) {
			System.out.println("| PASS | Piece Movement: Pawn test was successful.\n");
			return;
		}
		System.out.println("| FAIL | Piece Movement: Pawn test failed.\n");
		return;
	}

}

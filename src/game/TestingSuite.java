package game;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 
 * This class is the testing suite class and is used to run a testing suite in order to allow our code to be tested using a framework custom created by us.
 *
 */
public class TestingSuite {

	public View view = null;
	public Game game = null;
	public InputStream stdin = System.in;
	
	public TestingSuite(View view) {
		this.view = view;
	}

	/**
	 * This method is called when the test suite is called from the main menu, and is used to display the testing menu to the users, as well as gather their input in regards to which tests to run.
	 */
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
			System.out.println("4. Classic Chess: Castling");
			System.out.println("5. Classic Chess: Check");
			System.out.println("6. Classic Chess: Stalemate");
			System.out.println("7. Take All Chess: Taking all pieces");
			System.out.println("8. Take All Chess: Stalemate");
			System.out.println("9. Piece Movement: Take own piece");
			System.out.println("10. Piece Movement: Move onto self");
			System.out.println("11. Piece Movement: En Passant Positive");
			System.out.println("12. Piece Movement: En Passant Negative");
			System.out.println("13. Piece Movement: Pawn Promotion");
			System.out.println("14. Piece Movement: Pawn");
			System.out.println("15. Piece Movement: Rook");
			System.out.println("16. Piece Movement: Knight");
			System.out.println("17. Piece Movement: Bishop");
			System.out.println("18. Piece Movement: Queen");
			System.out.println("19. Piece Movement: King");
			System.out.println("20. Move Instantiation");
			System.out.println("21. Stalemate Message");
			System.out.println("22. View Test");
			System.out.println("23. Castling Without A Rook");
			System.out.println("24. Castling After Rook Movement");

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
						classicCastling();
						input = null;
					} else if (option == 5) {
						classicCheck();
						input = null;
					} else if (option == 6) {
						classicStalemate();
						input = null;
					} else if (option == 7) {
						takeAll();
						input = null;
					} else if (option == 8) {
						takeAllStalemate();
						input = null;
					} else if (option == 9) {
						takeOwnPiece();
						input = null;
					} else if (option == 10) {
						moveOntoSelf();
						input = null;
					} else if (option == 11) {
						enPassantPositiveTest();
						input = null;
					} else if (option == 12) {
						enPassantNegativeTest();
						input = null;
					} else if (option == 13) {
						pawnPromotion();
						input = null;
					} else if (option == 14) {
						pawnMovementTest();
						input = null;
					} else if (option == 15) {
						rookMovementTest();
						input = null;
					} else if (option == 16) {
						knightMovementTest();
						input = null;
					} else if (option == 17) {
						bishopMovementTest();
						input = null;
					} else if (option == 18) {
						queenMovementTest();
						input = null;
					} else if (option == 19) {
						kingMovementTest();
						input = null;
					} else if (option == 20) {
						moveInstantiationTest();
						input = null;
					} else if (option == 21) {
						stalemateMessageTest();
						input = null;
					} else if (option == 22) {
						viewTest();
						input = null;
					} else if (option == 23) {
						castlingWithoutRook();
						input = null;
					} else if (option == 24) {
						castlingAfterRookMove();
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

	/**
	 * This method is used to handle each move that the testing suite wants to make.
	 * @param move The move that is going to be made
	 */
	public boolean completeMove(Move move) {
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
			return true;
		} else {
			return false;
		}

	}

	/**
	 * This method is called when the user wants to run all the test cases. 
	 */
	private void runAllTestCases() {
		foolsMate();
		scholarsMate();
		classicCastling();
		classicCheck();
		classicStalemate();
		takeAll();
		takeAllStalemate();
		takeOwnPiece();
		moveOntoSelf();
		enPassantPositiveTest();
		enPassantNegativeTest();
		pawnPromotion();
		pawnMovementTest();
		rookMovementTest();
		knightMovementTest();
		bishopMovementTest();
		queenMovementTest();
		kingMovementTest();
		moveInstantiationTest();
		stalemateMessageTest();
		viewTest();
		castlingWithoutRook();
		castlingAfterRookMove();
	}
	
	/**
	 * This test is used to test the Fool's Mate scenario in classic chess.
	 */
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

	/**
	 * This test is used to test the Scholar's Mate scenario in classic chess.
	 */
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
	
	private void classicCastling() {
		boolean success = true;

		System.out.println("Beginning Classic Chess: Castling test..");
		game = new Game();
		game.rules = new ClassicChess();
		Board board = new Board(8, 8);
		Player white = game.white;
		Player black = game.black;

		board.AddPiece(new King(white), 3, 0);
        board.AddPiece(new Rook(white), 0, 0);
		board.AddPiece(new King(black), 3, 7);
        board.AddPiece(new Rook(black), 7, 7);

		game.board = board;
		view.DisplayBoard(game.board);
		game.NextTurn();

		try {
			completeMove(new Move(game.activePlayer, "d0-b0", game.board)); // w
            
            if (game.board.tiles[1][0].piece.getClass() != King.class || game.board.tiles[2][0].piece.getClass() != Rook.class) {
				System.out.println("| FAIL | Castling: King failed castling left.");
				success = false;
			}
            
            completeMove(new Move(game.activePlayer, "d7-f7", game.board)); // b
            
            if (game.board.tiles[5][7].piece.getClass() != King.class || game.board.tiles[4][7].piece.getClass() != Rook.class) {
				System.out.println("| FAIL | Castling: King failed castling right.");
				success = false;
			}
            
		} catch (Exception e) {
			success = false;
			System.out.println(e.getMessage());
		}
		
		if (success) {
			System.out.println("| PASS | Classic Chess: Castling was successful.\n");
			return;
		}
		System.out.println("| FAIL | Classic Chess: Castling failed.\n");
		return;
	}
	
	private void classicCheck() {
		boolean success = true;

		System.out.println("Beginning Classic Chess: Check test..");
		game = new Game();
		game.rules = new ClassicChess();
		game.board = game.rules.SetStartingPositions(this.game.white,
				this.game.black);
		view.DisplayBoard(game.board);
		game.NextTurn();

		try {
			completeMove(new Move(game.activePlayer, "c0-c2", game.board)); // w
			completeMove(new Move(game.activePlayer, "b1-b2", game.board)); // w
			completeMove(new Move(game.activePlayer, "b6-b5", game.board)); // b
			completeMove(new Move(game.activePlayer, "c0-a2", game.board)); // w
			completeMove(new Move(game.activePlayer, "c7-a5", game.board)); // b
			// place black in check
			completeMove(new Move(game.activePlayer, "a2-e6", game.board)); // w
			
			// attempt to make a move while in check
			completeMove(new Move(game.activePlayer, "c6-c5", game.board)); // b
			
			if (game.board.tiles[2][6].piece.getClass() != Pawn.class || game.board.tiles[2][5].piece != null) {
				System.out.println("| FAIL | Piece Movement: Pawn moved while in checkmate.");
				success = false;
			}
			
			// get out of check
			completeMove(new Move(game.activePlayer, "f7-e6", game.board)); // b
			
		} catch (Exception e) {
			success = false;
			System.out.println(e.getMessage());
		}

		if (success) {
			System.out.println("| PASS | Classic Chess: Check test was successful.\n");
			return;
		}
		System.out.println("| FAIL | Classic Chess: Check test failed.\n");
		return;
	}
	
	
	/**
	 * This method is used to test if the stalemate check is working properly in a classic chess game. 
	 */
	private void classicStalemate() {
		boolean success = true;

		System.out.println("Beginning Classic Chess: Stalemate test..");
		game = new Game();
		game.rules = new ClassicChess();
		Board board = new Board(8, 8);
		Player white = game.white;
		Player black = game.black;

		board.AddPiece(new King(white), 1, 1);
		board.AddPiece(new King(black), 6, 6);
		board.AddPiece(new Pawn(black), 7, 5);
		board.AddPiece(new Pawn(white), 7, 4);

		board.AddPiece(new Rook(black), 0, 6);
		board.AddPiece(new Rook(black), 2, 6);
		board.AddPiece(new Rook(black), 6, 3);	

		game.board = board;
		view.DisplayBoard(game.board);
		game.NextTurn();

		try {
			completeMove(new Move(game.activePlayer, "b1-b0", game.board)); // w
			completeMove(new Move(game.activePlayer, "g3-g1", game.board)); // b
			if(game.rules.checkForStalemate(game.activePlayer, game.board)){
				view.DisplayStalemateMessage(game.rules.getStalemateMessage(game.activePlayer));
				game.GameOver = true;
			}
		} catch (Exception e) {
			success = false;
			System.out.println(e.getMessage());
		}
		if (success && game.GameOver) {
			System.out.println("| PASS | Classic Chess: Stalemate test was successful.\n");
			return;
		}
		System.out.println("| FAIL | Classic Chess: Stalemate test failed.\n");
		return;
	}

	/**
	 * This method is used to test taking all pieces in a take all chess game.
	 */
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
	
	
	/**
	 * This method is used to test if the stalemate check is working properly in a take all game. 
	 */
	private void takeAllStalemate() {
		boolean success = true;

		System.out.println("Beginning Take All Chess: Stalemate test..");
		game = new Game();
		game.rules = new TakeAllChess();
		Board board = new Board(8, 8);
		Player white = game.white;
		Player black = game.black;

		board.AddPiece(new Pawn(white), 3, 3);
		board.AddPiece(new Pawn(black), 3, 5);	

		game.board = board;
		view.DisplayBoard(game.board);
		game.NextTurn();

		try {
			completeMove(new Move(game.activePlayer, "d3-d4", game.board)); // w
			if(game.rules.checkForStalemate(game.activePlayer, game.board)){
				view.DisplayStalemateMessage(game.rules.getStalemateMessage(game.activePlayer));
				game.GameOver = true;
			}
		} catch (Exception e) {
			success = false;
			System.out.println(e.getMessage());
		}
		if (success && game.GameOver) {
			System.out.println("| PASS | Take All Chess: Stalemate was successful.\n");
			return;
		}
		System.out.println("| FAIL | Take All Chess: Stalemate failed.\n");
		return;

	}

	/**
	 * This method is used to test if a piece is able to take a piece of the same colour as the active player.
	 * This is expected to have an error message printed.
	 */
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
	
	/**
	 * This method is used to test if a piece is able to move onto the same tile where the piece currently is.
	 * This is expected to have an error message printed.
	 */
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
	
	/**
	 * This method is used to test if En Passant can properly be performed when the conditions are right.
	 */
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
	
	/**
	 * This method is used to test if En Passant can not be performed when the conditions are wrong.
	 * This is expected to have an error message printed.
	 */
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
	
	
	/**
	 * This method is used to test if all the possible scenarios for Pawn Promotion are working.
	 */
	private void pawnPromotion() {
		boolean success = true;

		System.out.println("Beginning Piece Movement: Pawn Promotion test..");
		game = new Game();
		game.rules = new ClassicChess();
		Board board = new Board(8, 8);
		Player white = game.white;
		Player black = game.black;

		board.AddPiece(new King(white), 6, 6);
		board.AddPiece(new King(black), 6, 1);
		
		board.AddPiece(new Pawn(black), 0, 1);
		board.AddPiece(new Pawn(black), 1, 1);
		board.AddPiece(new Pawn(black), 2, 1);
		
		board.AddPiece(new Pawn(white), 0, 6);
		board.AddPiece(new Pawn(white), 1, 6);
		board.AddPiece(new Pawn(white), 2, 6);

		game.board = board;
		view.DisplayBoard(game.board);
		game.NextTurn();

		try {
			//promote a white Pawn into a Rook
			String data = "R\r\n";
			try {
				//changes standard input, reads in data from above.
				System.setIn(new ByteArrayInputStream(data.getBytes()));
				completeMove(new Move(game.activePlayer, "a6-a7", game.board)); // w
			} finally {
				System.setIn(stdin);
			}
			if (game.board.tiles[0][7].piece.getClass() != Rook.class) {
				System.out.println("| FAIL | Piece Movement: Pawn Promotion failed to promote a pawn to a Rook");
				success = false;
			}
			
			//promote a black Pawn into a Knight
			data = "N\r\n";
			try {
				System.setIn(new ByteArrayInputStream(data.getBytes()));
				completeMove(new Move(game.activePlayer, "a1-a0", game.board)); // b
			} finally {
				System.setIn(stdin);
			}
			if (game.board.tiles[0][0].piece.getClass() != Knight.class) {
				System.out.println("| FAIL | Piece Movement: Pawn Promotion failed to promote a pawn to a Knight");
				success = false;
			}
			
			//promote a white Pawn into a Queen
			data = "Q\r\n";
			try {
				System.setIn(new ByteArrayInputStream(data.getBytes()));
				completeMove(new Move(game.activePlayer, "b6-b7", game.board)); // w
			} finally {
				System.setIn(stdin);
			}
			if (game.board.tiles[1][7].piece.getClass() != Queen.class) {
				System.out.println("| FAIL | Piece Movement: Pawn Promotion failed to promote a pawn to a Queen");
				success = false;
			}
			
			//promote a black Pawn into a Bishop
			data = "B\r\n";
			try {
				System.setIn(new ByteArrayInputStream(data.getBytes()));
				completeMove(new Move(game.activePlayer, "b1-b0", game.board)); // b
			} finally {
				System.setIn(stdin);
			}
			if (game.board.tiles[1][0].piece.getClass() != Bishop.class) {
				System.out.println("| FAIL | Piece Movement: Pawn Promotion failed to promote a pawn to a Bishop");
				success = false;
			}
			
		} catch (Exception e) {
			success = false;
			System.out.println(e.getMessage());
		}
		if (success) {
			System.out.println("| PASS | Piece Movement: Pawn Promotion was successful.\n");
			return;
		}
		System.out.println("| FAIL | Piece Movement: Pawn Promotion failed.\n");
		return;

	}
	
	
	/**
	 * This method is used to test all of the pawn movement scenarios.
	 */
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
	
	/**
	 * This method is used to test all of the rook movement scenarios.
	 */
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
	
	
	/**
	 * This method is used to test all of the knight movement scenarios.
	 */
	private void knightMovementTest() {
		boolean success = true;

		System.out.println("Beginning Piece Movement: Knight test..");
		game = new Game();
		game.rules = new ClassicChess();
		game.board = game.rules.SetStartingPositions(this.game.white,
				this.game.black);
		view.DisplayBoard(game.board);
		game.NextTurn();

		try {
			completeMove(new Move(game.activePlayer, "b0-a2", game.board)); // w
			
			if (game.board.tiles[0][2].piece.getClass() != Knight.class || game.board.tiles[1][0].piece != null) {
				System.out.println("| FAIL | Piece Movement: Knight failed passing over piece and 2 down, 1 left movement test.");
				success = false;
			}
			
			completeMove(new Move(game.activePlayer, "b7-a5", game.board)); // b
			
			if (game.board.tiles[0][5].piece.getClass() != Knight.class || game.board.tiles[1][7].piece != null) {
				System.out.println("| FAIL | Piece Movement: Knight failed passing over piece and 2 up, 1 left movement test.");
				success = false;
			}

			completeMove(new Move(game.activePlayer, "a2-b4", game.board)); // w
			
			if (game.board.tiles[1][4].piece.getClass() != Knight.class || game.board.tiles[0][2].piece != null) {
				System.out.println("| FAIL | Piece Movement: Knight failed 2 down, 1 right movement test.");
				success = false;
			}
			
			completeMove(new Move(game.activePlayer, "a5-b3", game.board)); // b
			
			if (game.board.tiles[1][3].piece.getClass() != Knight.class || game.board.tiles[0][5].piece != null) {
				System.out.println("| FAIL | Piece Movement: Knight failed 2 up, 1 right movement test.");
				success = false;
			}
			
			completeMove(new Move(game.activePlayer, "b4-d5", game.board)); // w
			
			if (game.board.tiles[3][5].piece.getClass() != Knight.class || game.board.tiles[1][4].piece != null) {
				System.out.println("| FAIL | Piece Movement: Knight failed 1 down, 2 right movement test.");
				success = false;
			}
			
			completeMove(new Move(game.activePlayer, "b3-d2", game.board)); // b
			
			if (game.board.tiles[3][2].piece.getClass() != Knight.class || game.board.tiles[1][3].piece != null) {
				System.out.println("| FAIL | Piece Movement: Knight failed 1 up, 2 right movement test.");
				success = false;
			}
			
			completeMove(new Move(game.activePlayer, "d5-b6", game.board)); // w
			
			if (game.board.tiles[1][6].piece.getClass() != Knight.class || game.board.tiles[3][5].piece != null) {
				System.out.println("| FAIL | Piece Movement: Knight failed taking enemy piece and 1 down, 2 left movement test.");
				success = false;
			}
			
			
		} catch (Exception e) {
			success = false;
			System.out.println(e.getMessage());
		}
		if (success) {
			System.out.println("| PASS | Piece Movement: Knight test was successful.\n");
			return;
		}
		System.out.println("| FAIL | Piece Movement: Knight test failed.\n");
		return;
	}
	
	
	/**
	 * This method is used to test all of the bishop movement scenarios.
	 */
	private void bishopMovementTest() {
		boolean success = true;

		System.out.println("Beginning Piece Movement: Bishop test..");
		game = new Game();
		game.rules = new ClassicChess();
		game.board = game.rules.SetStartingPositions(this.game.white,
				this.game.black);
		view.DisplayBoard(game.board);
		game.NextTurn();

		try {
			completeMove(new Move(game.activePlayer, "c0-c2", game.board)); // w
			
			if (game.board.tiles[2][0].piece.getClass() != Bishop.class || game.board.tiles[2][2].piece != null) {
				System.out.println("| FAIL | Piece Movement: Bishop failed passing over piece and moveset test.");
				success = false;
			}
			
			// move pawns out of the way
			completeMove(new Move(game.activePlayer, "b1-b2", game.board)); // w
			completeMove(new Move(game.activePlayer, "b6-b5", game.board)); // b
			
			completeMove(new Move(game.activePlayer, "c0-a2", game.board)); // w
			
			if (game.board.tiles[0][2].piece.getClass() != Bishop.class || game.board.tiles[2][0].piece != null) {
				System.out.println("| FAIL | Piece Movement: Bishop failed diagonal down left test.");
				success = false;
			}
			
			completeMove(new Move(game.activePlayer, "c7-a5", game.board)); // b
			
			if (game.board.tiles[0][5].piece.getClass() != Bishop.class || game.board.tiles[2][7].piece != null) {
				System.out.println("| FAIL | Piece Movement: Bishop failed diagonal up left test.");
				success = false;
			}
			
			completeMove(new Move(game.activePlayer, "a2-e6", game.board)); // w
			
			if (game.board.tiles[4][6].piece.getClass() != Bishop.class || game.board.tiles[0][2].piece != null) {
				System.out.println("| FAIL | Piece Movement: Bishop failed taking enemy piece and diagonal down right test.");
				success = false;
			}
			
			// take other bishop to get out of check
			completeMove(new Move(game.activePlayer, "f7-e6", game.board)); // b
			completeMove(new Move(game.activePlayer, "a1-a2", game.board)); // w
			
			completeMove(new Move(game.activePlayer, "a5-e1", game.board)); // b
			
			if (game.board.tiles[4][1].piece.getClass() != Bishop.class || game.board.tiles[0][5].piece != null) {
				System.out.println("| FAIL | Piece Movement: Bishop failed taking enemy piece and diagonal up right test.");
				success = false;
			}
			
			
		} catch (Exception e) {
			success = false;
			System.out.println(e.getMessage());
		}
		if (success) {
			System.out.println("| PASS | Piece Movement: Bishop test was successful.\n");
			return;
		}
		System.out.println("| FAIL | Piece Movement: Bishop test failed.\n");
		return;
	}
	
	
	/**
	 * This method is used to test all of the queen movement scenarios.
	 */
	private void queenMovementTest() {
		boolean success = true;

		System.out.println("Beginning Piece Movement: Queen test..");
		game = new Game();
		game.rules = new ClassicChess();
		game.board = game.rules.SetStartingPositions(this.game.white,
				this.game.black);
		view.DisplayBoard(game.board);
		game.NextTurn();

		try {
			// negative test: move queen vertically past pawn
			completeMove(new Move(game.activePlayer, "e0-e2", game.board)); // w
			
			// negative test: move queen diagonal past pawn
			completeMove(new Move(game.activePlayer, "e0-g2", game.board)); // w
			
			// move pawn out of the way
			completeMove(new Move(game.activePlayer, "e1-e3", game.board)); // w
			
			// move pawn out of the way
			completeMove(new Move(game.activePlayer, "e6-e4", game.board)); // b

			// move queen behind pawn
			completeMove(new Move(game.activePlayer, "e0-e2", game.board)); // w
			if (game.board.tiles[4][2].piece.getClass() != Queen.class || game.board.tiles[4][0].piece != null) {
				System.out.println("| FAIL | Piece Movement: Queen failed vertical movement test.");
				success = false;
			}
			
			// move queen behind pawn
			completeMove(new Move(game.activePlayer, "e7-e5", game.board)); // b
			if (game.board.tiles[4][5].piece.getClass() != Queen.class || game.board.tiles[4][7].piece != null) {
				System.out.println("| FAIL | Piece Movement: Queen failed vertical movement test.");
				success = false;
			}
			
			// move queen diagonal
			completeMove(new Move(game.activePlayer, "e2-c4", game.board)); // w
			if (game.board.tiles[2][4].piece.getClass() != Queen.class || game.board.tiles[4][2].piece != null) {
				System.out.println("| FAIL | Piece Movement: Queen failed diagonal movement test.");
				success = false;
			}
			
			// move queen horizontal
			completeMove(new Move(game.activePlayer, "e5-a5", game.board)); // b
			if (game.board.tiles[0][5].piece.getClass() != Queen.class || game.board.tiles[4][5].piece != null) {
				System.out.println("| FAIL | Piece Movement: Queen failed horizontal movement test.");
				success = false;
			}
			
			// negative test: move queen to a spot outside their move set
			completeMove(new Move(game.activePlayer, "c4-h5", game.board)); // w
			if (game.board.tiles[2][4].piece.getClass() != Queen.class || game.board.tiles[7][5].piece != null) {
				System.out.println("| FAIL | Piece Movement: Queen failed moveset movement test.");
				success = false;
			}
			
		} catch (Exception e) {
			success = false;
			System.out.println(e.getMessage());
		}
		if (success) {
			System.out.println("| PASS | Piece Movement: Queen test was successful.\n");
			return;
		}
		System.out.println("| FAIL | Piece Movement: Queen test failed.\n");
		return;
	}
	
	
	/**
	 * This method is used to test all of the queen movement scenarios.
	 */
	private void kingMovementTest() {
		boolean success = true;

		System.out.println("Beginning Piece Movement: King test..");
		game = new Game();
		game.rules = new ClassicChess();
		game.board = game.rules.SetStartingPositions(this.game.white,
				this.game.black);
		view.DisplayBoard(game.board);
		game.NextTurn();

		try {
			// negative test: move king vertically past pawn
			completeMove(new Move(game.activePlayer, "d0-d2", game.board)); // w
			
			// negative test: move king diagonal past pawn
			completeMove(new Move(game.activePlayer, "d0-g2", game.board)); // w
			
			// move pawn out of the way
			completeMove(new Move(game.activePlayer, "d1-d3", game.board)); // w
			
			// move pawn out of the way
			completeMove(new Move(game.activePlayer, "d6-d4", game.board)); // b

			// move king vertically
			completeMove(new Move(game.activePlayer, "d0-d1", game.board)); // w
			if (game.board.tiles[3][1].piece.getClass() != King.class || game.board.tiles[3][0].piece != null) {
				System.out.println("| FAIL | Piece Movement: King failed vertical movement test white side.");
				success = false;
			}
			
			// move king vertically
			completeMove(new Move(game.activePlayer, "d7-d6", game.board)); // b
			if (game.board.tiles[3][6].piece.getClass() != King.class || game.board.tiles[3][7].piece != null) {
				System.out.println("| FAIL | Piece Movement: King failed vertical movement test black side.");
				success = false;
			}
			
			// move king diagonal
			completeMove(new Move(game.activePlayer, "d1-c2", game.board)); // w
			if (game.board.tiles[2][2].piece.getClass() != King.class || game.board.tiles[3][1].piece != null) {
				System.out.println("| FAIL | Piece Movement: King failed diagonal movement test white side.");
				success = false;
			}
			
			// move king diagonal
			completeMove(new Move(game.activePlayer, "d6-e5", game.board)); // b
			if (game.board.tiles[4][5].piece.getClass() != King.class || game.board.tiles[3][6].piece != null) {
				System.out.println("| FAIL | Piece Movement: King failed diagonal movement test black side.");
				success = false;
			}
			
			// move king horizontal
			completeMove(new Move(game.activePlayer, "c2-b2", game.board)); // w
			if (game.board.tiles[1][2].piece.getClass() != King.class || game.board.tiles[2][2].piece != null) {
				System.out.println("| FAIL | Piece Movement: King failed horizontal movement test white side.");
				success = false;
			}
			
			// move king horizontal
			completeMove(new Move(game.activePlayer, "e5-f5", game.board)); // b
			if (game.board.tiles[5][5].piece.getClass() != King.class || game.board.tiles[4][5].piece != null) {
				System.out.println("| FAIL | Piece Movement: King failed horizontal movement test black side.");
				success = false;
			}
			
			// negative test: move queen to a spot outside their move set
			completeMove(new Move(game.activePlayer, "b2-h5", game.board)); // w
			if (game.board.tiles[1][2].piece.getClass() != King.class || game.board.tiles[7][5].piece != null) {
				System.out.println("| FAIL | Piece Movement: King failed moveset movement test.");
				success = false;
			}
			
		} catch (Exception e) {
			success = false;
			System.out.println(e.getMessage());
		}
		if (success) {
			System.out.println("| PASS | Piece Movement: King test was successful.\n");
			return;
		}
		System.out.println("| FAIL | Piece Movement: King test failed.\n");
		return;
	}
	
	private void moveInstantiationTest() {
		
		boolean success = true;

		System.out.println("Beginning Move instantiation test..");
		game = new Game();
		game.rules = new ClassicChess();
		game.board = game.rules.SetStartingPositions(this.game.white,
				this.game.black);
		game.NextTurn();

		try {
			Move move = new Move(game.activePlayer, "d0-d2", game.board);
			move = new Move(move);
			move = new Move(game.activePlayer, game.board.tiles[0][0], game.board.tiles[1][1]);
			
		} catch (Exception e) {
			success = false;
			System.out.println(e.getMessage());
		}
		if (success) {
			System.out.println("| PASS | Move Instantiation test was successful.\n");
			return;
		}
		System.out.println("| FAIL | Move Instantiation test failed.\n");
		return;
	}
	
	private void viewTest() {
		boolean success = true;
		game = new Game();
		game.rules = new ClassicChess();
		game.board = game.rules.SetStartingPositions(this.game.white,
				this.game.black);
		game.NextTurn();
		
		try {
		View view = new View();

		view.DisplayBoard(game.board);
		view.DisplayTurnNotification(game.turnNumber, game.activePlayer.color);
		view.setErrorMessage("Test Message");
		view.DisplayErrorMessage();
		view.DisplayErrorMessage(view.getErrorMessage());
		view.DisplayStalemateMessage(game.activePlayer.color);
		view.DisplaySurrenderMessage(game.activePlayer.color);
		view.DisplayWinMessage(game.activePlayer);
		view.DisplayExitMessage();
		}
		catch (Exception e) {
			success = false;
		}
		if (success) {
			System.out.println("| PASS | View test was successful.\n");
			return;
		}
		System.out.println("| FAIL | View test failed.\n");
		return;
	}
	
	public void stalemateMessageTest() {
		boolean success = true;
		
		try {
		Rules rules = new ClassicChess();
		rules.getStalemateMessage(new Player());

		}
		catch (Exception e) {
			success = false;
		}
		if (success) {
			System.out.println("| PASS | Stalemate Message test was successful.\n");
			return;
		}
		System.out.println("| FAIL | Stalemate Message test failed.\n");
		return;
	}
	
	private void castlingWithoutRook() {
		boolean success = true;

		game = new Game();
		game.rules = new ClassicChess();
		Board board = new Board(8, 8);
		Player white = game.white;
		Player black = game.black;

		board.AddPiece(new King(white), 3, 0);
        board.AddPiece(new Bishop(white), 0, 0);
		board.AddPiece(new King(black), 3, 7);
        board.AddPiece(new Bishop(black), 7, 7);

		game.board = board;
		view.DisplayBoard(game.board);
		game.NextTurn();

		if (completeMove(new Move(game.activePlayer, "d0-b0", game.board))) {
			System.out.println("| FAIL | Castling failed.\n");
			return;
		}
		completeMove(new Move(game.activePlayer, "d0-d1", game.board));
		
		if (completeMove(new Move(game.activePlayer, "e7-h7", game.board))) {
			System.out.println("| FAIL | Castling failed.\n");
			return;
		}
            		
		System.out.println("| PASS | Castling test was successful.\n");
	}
	
	private void castlingAfterRookMove() {
		boolean success = false;

		game = new Game();
		game.rules = new ClassicChess();
		Board board = new Board(8, 8);
		Player white = game.white;
		Player black = game.black;

		board.AddPiece(new King(white), 3, 0);
        board.AddPiece(new Rook(white), 0, 0);
		board.AddPiece(new King(black), 3, 7);
        board.AddPiece(new Rook(black), 7, 7);

		game.board = board;
		view.DisplayBoard(game.board);
		game.NextTurn();
		
		completeMove(new Move(game.activePlayer, "a0-a1", game.board));
		completeMove(new Move(game.activePlayer, "h7-h6", game.board));
		completeMove(new Move(game.activePlayer, "a1-a0", game.board));
		completeMove(new Move(game.activePlayer, "h6-h5", game.board));

		if (!completeMove(new Move(game.activePlayer, "d0-b0", game.board))) {
			success = true;
		}
            		
		if (success) {
			System.out.println("| PASS | Castling test was successful.\n");
			return;
		}
		System.out.println("| FAIL | Castling failed.\n");
		return;
	}

}

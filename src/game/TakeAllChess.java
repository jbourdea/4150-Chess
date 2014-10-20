package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Take all chess variant rules class.
 */
public class TakeAllChess extends Rules {
	
	public TakeAllChess() {
		super();
	}
	
	/*
	 * (non-Javadoc)
	 * @see game.Rules#SetStartingPositions(game.Player, game.Player)
	 */
	public Board SetStartingPositions(Player white, Player black) {
		
		Board board = new Board(8,8);
		
		board.AddPiece(new Pawn(white), 0, 1);
		board.AddPiece(new Pawn(white), 1, 1);
		board.AddPiece(new Pawn(white), 2, 1);
		board.AddPiece(new Pawn(white), 3, 1);
		board.AddPiece(new Pawn(white), 4, 1);
		board.AddPiece(new Pawn(white), 5, 1);
		board.AddPiece(new Pawn(white), 6, 1);
		board.AddPiece(new Pawn(white), 7, 1);
		board.AddPiece(new Rook(white), 0, 0);
		board.AddPiece(new Knight(white), 1, 0);
		board.AddPiece(new Bishop(white), 2, 0);
		board.AddPiece(new Queen(white), 3, 0);
		board.AddPiece(new King(white), 4, 0);
		board.AddPiece(new Bishop(white), 5, 0);
		board.AddPiece(new Knight(white), 6, 0);
		board.AddPiece(new Rook(white), 7, 0);
		
		board.AddPiece(new Pawn(black), 0, 6);
		board.AddPiece(new Pawn(black), 1, 6);
		board.AddPiece(new Pawn(black), 2, 6);
		board.AddPiece(new Pawn(black), 3, 6);
		board.AddPiece(new Pawn(black), 4, 6);
		board.AddPiece(new Pawn(black), 5, 6);
		board.AddPiece(new Pawn(black), 6, 6);
		board.AddPiece(new Pawn(black), 7, 6);
		board.AddPiece(new Rook(black), 0, 7);
		board.AddPiece(new Knight(black), 1, 7);
		board.AddPiece(new Bishop(black), 2, 7);
		board.AddPiece(new Queen(black), 3, 7);
		board.AddPiece(new King(black), 4, 7);
		board.AddPiece(new Bishop(black), 5, 7);
		board.AddPiece(new Knight(black), 6, 7);
		board.AddPiece(new Rook(black), 7, 7);
		
		return board;
	}
	
	
	/* 
	 * @return true if it's a valid move, false if invalid
	 */
	public boolean validatePawnMove(Move move, Board board)
	{
		// get direction it can go in
		int direction = -1;
		
		// white on top
		if(move.activePlayer.color.equals("White")) {
			direction = 1;
		}
		
		int yDifference = move.endPosition.yCord - move.startPosition.yCord;
		int xDifference = Math.abs(move.endPosition.xCord - move.startPosition.xCord);
		if(yDifference == direction) {
			// pawn is moving straight by 1 space
			if(xDifference == 0) {
				// if there is no piece ahead, it's valid
				if(move.endPosition.piece == null) {
					return true;
				}
				else {
					return false;
				}
			}
			// pawn is moving diagonal by 1 space
			else if(xDifference == 1) {
				// can't move diagonal without a piece
				if(move.endPosition.piece == null) {
					//checking if the piece adjacent to the pawn moving was a pawn and had done a double jump the last turn (checking condition for En Passant)
					if(board.tiles[move.endPosition.xCord][move.startPosition.yCord].piece != null){
						if(board.tiles[move.endPosition.xCord][move.startPosition.yCord].piece.getClass() == Pawn.class){
							Pawn p = (Pawn)board.tiles[move.endPosition.xCord][move.startPosition.yCord].piece;
							if(p.lastMoveJump == true && p.owner != move.activePlayer){
								board.tiles[move.endPosition.xCord][move.startPosition.yCord].piece = null;
								return true;
							}
						}
					}
					return false;
				}
				// can only move diagonal if the piece is owned by the other player
				else if(move.endPosition.piece.owner != move.activePlayer) {
					return true;
				}
			}
		}
		else if(move.piece.hasMoved == false && yDifference == direction * 2) {
			// double jump for first move
			//checking that the final position is empty (does not contain a piece) and that the space in between is also empty.
			if(move.endPosition.piece == null && board.tiles[move.startPosition.xCord][move.startPosition.yCord + direction].piece == null) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}
	
	/* 
	 * @return true if it's a valid move, false if invalid
	 */
	public boolean validateQueenMove(Move move, Board board)
	{
		// check what the move way is, horizontal or diagonal
		int xDiff = Math.abs(move.startPosition.xCord - move.endPosition.xCord);
		int yDiff = Math.abs(move.startPosition.yCord - move.endPosition.yCord);
		
		// if it attacks the same team
		if(move.endPosition.piece != null && move.endPosition.piece.owner == move.activePlayer) {
			return false;
		}
		
		int xDir = 1;
		int yDir = 1;
		
		// moving left
		if(move.startPosition.xCord - move.endPosition.xCord > 0) {
			xDir = -1;
		}
		// moving up
		if(move.startPosition.yCord - move.endPosition.yCord > 0) {
			yDir = -1;
		}
		
		// diagonal movement
		if(xDiff == yDiff) {
			for(int i=1; i < yDiff; i++ ) {
				Tile t = board.tiles[move.startPosition.xCord + (xDir * i)][move.startPosition.yCord + (yDir * i)];
				if(t.piece != null) {
					return false;
				}
			}
			return true;
		}
		// horizontal movement
		else if(yDiff == 0) {
			for(int i=1; i < xDiff; i++ ) {
				Tile t = board.tiles[move.startPosition.xCord + (xDir * i)][move.startPosition.yCord];
				if(t.piece != null) {
					return false;
				}
			}
			return true;
		}
		// vertical movement
		else if(xDiff == 0) {
			for(int i=1; i < yDiff; i++ ) {
				Tile t = board.tiles[move.startPosition.xCord][move.startPosition.yCord + (yDir * i)];
				if(t.piece != null) {
					return false;
				}
			}
			return true;
		}
		
		return false;
	}
	
	/* 
	 * @return true if it's a valid move, false if invalid
	 */
	public boolean validateBishopMove(Move move, Board board)
	{
		// check what the move way is, horizontal or diagonal
		int xDiff = Math.abs(move.startPosition.xCord - move.endPosition.xCord);
		int yDiff = Math.abs(move.startPosition.yCord - move.endPosition.yCord);
		
		// if it attacks the same team
		if(move.endPosition.piece != null && move.endPosition.piece.owner == move.activePlayer) {
			return false;
		}
		
		// diagonal movement
		if(xDiff == yDiff) {
			int xDir = 1;
			int yDir = 1;
			
			// moving left
			if(move.startPosition.xCord - move.endPosition.xCord > 0) {
				xDir = -1;
			}
			// moving up
			if(move.startPosition.yCord - move.endPosition.yCord > 0) {
				yDir = -1;
			}
			
			for(int i=1; i < yDiff; i++ ) {
				Tile t = board.tiles[move.startPosition.xCord + (xDir * i)][move.startPosition.yCord + (yDir * i)];
				if(t.piece != null) {
					return false;
				}
			}
			return true;
		}
		
		return false;
	}
	
	/* 
	 * @return true if it's a valid move, false if invalid
	 */
	public boolean validateRookMove(Move move, Board board)
	{
		// check what the move way is, horizontal or diagonal
		int xDiff = Math.abs(move.startPosition.xCord - move.endPosition.xCord);
		int yDiff = Math.abs(move.startPosition.yCord - move.endPosition.yCord);
		
		// if it attacks the same team
		if(move.endPosition.piece != null && move.endPosition.piece.owner == move.activePlayer) {
			return false;
		}
		
		int xDir = 1;
		int yDir = 1;
		
		// moving left
		if(move.startPosition.xCord - move.endPosition.xCord > 0) {
			xDir = -1;
		}
		// moving up
		if(move.startPosition.yCord - move.endPosition.yCord > 0) {
			yDir = -1;
		}
				
		// vertical movement
		if(xDiff == 0) {
			for(int i=1; i < yDiff; i++ ) {
				
				Tile t = board.tiles[move.startPosition.xCord][move.startPosition.yCord + (yDir * i)];
				if(t.piece != null) {
					return false;
				}
			}
			return true;
		}
		// horizontal movement
		else if(yDiff == 0) {
			for(int i=1; i < xDiff; i++ ) {
				Tile t = board.tiles[move.startPosition.xCord + (xDir * i)][move.startPosition.yCord];
				if(t.piece != null) {
					return false;
				}
			}
			return true;
		}
		
		return false;
	}

	/* 
	 * @return true if it's a valid move, false if invalid
	 */
	public boolean validateKnightMove(Move move, Board board)
	{
		// if it attacks the same team
		if(move.endPosition.piece != null && move.endPosition.piece.owner == move.activePlayer) {
			return false;
		}
		
		// check what the move way is, horizontal or diagonal
		int xDiff = Math.abs(move.startPosition.xCord - move.endPosition.xCord);
		int yDiff = Math.abs(move.startPosition.yCord - move.endPosition.yCord);
		
		
		if(yDiff == 2 && xDiff == 1) {
			return true;
		}
		else if(yDiff == 1 && xDiff == 2) {
			return true;
		}

		return false;
	}
	
	/* 
	 * @return true if it's a valid move, false if invalid
	 */
	public boolean validateKingMove(Move move, Board board)
	{
		// if it attacks the same team
		if(move.endPosition.piece != null && move.endPosition.piece.owner == move.activePlayer) {
			return false;
		}
		
		// check what the move way is, horizontal or diagonal
		int xDiff = Math.abs(move.startPosition.xCord - move.endPosition.xCord);
		int yDiff = Math.abs(move.startPosition.yCord - move.endPosition.yCord);
		
		if(yDiff <= 1 && xDiff <= 1) {
			return true;
		}

		return false;
	}
		
	
	/*
	 * 
	 * (non-Javadoc)
	 * @see game.Rules#ValidateMove(game.Move, game.Board)
	 */
	
	public int ValidateMove(Move move, Board board)
	{
		int isValidMove = -1;
		
		if(move.piece.getClass() == Pawn.class) {
			isValidMove = (validatePawnMove(move, board) == true) ? 0 : 1;
		}
		else if(move.piece.getClass() == Bishop.class) {
			isValidMove = (validateBishopMove(move, board) == true) ? 0 : 1;
		}
		else if(move.piece.getClass() == Queen.class) {
			isValidMove = (validateQueenMove(move, board) == true) ? 0 : 1;
		}
		else if(move.piece.getClass() == Rook.class) {
			isValidMove = (validateRookMove(move, board) == true) ? 0 : 1;
		}
		else if(move.piece.getClass() == Knight.class) {
			isValidMove = (validateKnightMove(move, board) == true) ? 0 : 1;
		}
		else if(move.piece.getClass() == King.class) {
			isValidMove = (validateKingMove(move, board) == true) ? 0 : 1;
		}
		
		if(isValidMove == 1) {
			View.setErrorMessage("Illigally inputted move, piece can't move in the specified manor.");
			return 1;
		}
		
		if(move.endPosition.piece != null && move.endPosition.piece.owner != move.activePlayer) { //Player is killing the opponents piece, no need to check if they have a potential kill move
			return 0;
		}
		
		if(checkIfKillIsPossible(move.activePlayer, board) == true) {
			return 3;
		}
		//isValidMove = validateBoardState(move, board) ? isValidMove : false;

		return 0;
		//return isValidMove;
	}
	
	//Pretty sure this function sets last jumped to 0 for everything, has to do with En Passant
	public void setLastMoveJump(Player activePlayer, Board board){
		for(int y = 0; y < board.height; y++) {
			for(int x = 0; x < board.width; x++) {
				if (board.tiles[x][y].piece != null) {
					if(board.tiles[x][y].piece.getClass() == Pawn.class && board.tiles[x][y].piece.owner == activePlayer){
						Pawn p = (Pawn)board.tiles[x][y].piece;
						p.lastMoveJump = false;
					}
				}
			}
		}
	}
	
	/**
	 * Move has been validated, performing the move
	 * @param activePlayer
	 * @param board
	 * @param move
	 * @return 	MoveCompleteResult
	 */
	public MoveCompleteResult ruleCompleteMove(Player activePlayer, Board board, Move move){
		setLastMoveJump(activePlayer, board);
		if(move.piece.getClass() == Pawn.class && Math.abs(move.startPosition.yCord - move.endPosition.yCord) == 2) {
			Pawn p = (Pawn)move.piece;
			p.lastMoveJump = true;
		}else if(move.piece.getClass() == Pawn.class && move.endPosition.yCord == 7 && move.piece.owner.color.equals("White")) {
			getPawnPromotionInput(board, move);
		}else if(move.piece.getClass() == Pawn.class && move.endPosition.yCord == 0 && move.piece.owner.color.equals("Black")) {
			getPawnPromotionInput(board, move);
		}
		
		boolean victoryStatus = checkWinCondition(activePlayer, board);
		if(victoryStatus) {
			MoveCompleteResult moveResult = new MoveCompleteResult(true);
			moveResult.winner = activePlayer;
			return moveResult;
		}
		
		return new MoveCompleteResult(false);
	}
	
	public void getPawnPromotionInput(Board board, Move move){
		System.out.println("Please enter a valid input for Pawn Promotion. Choose between Q (Queen), R (Rook), N (Knight), and B (Bishop).");
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
					if(input.equalsIgnoreCase("k") || input.equalsIgnoreCase("king")) {
						board.tiles[move.endPosition.xCord][move.endPosition.yCord].addPiece(new King(move.activePlayer));
					}
					else if(input.equalsIgnoreCase("q") || input.equalsIgnoreCase("queen")) {
						board.tiles[move.endPosition.xCord][move.endPosition.yCord].addPiece(new Queen(move.activePlayer));
					}
					else if(input.equalsIgnoreCase("r") || input.equalsIgnoreCase("rook")) {
						board.tiles[move.endPosition.xCord][move.endPosition.yCord].addPiece(new Rook(move.activePlayer));
					}
					else if(input.equalsIgnoreCase("n") || input.equalsIgnoreCase("knight")) {
						board.tiles[move.endPosition.xCord][move.endPosition.yCord].addPiece(new Knight(move.activePlayer));
					}
					else if(input.equalsIgnoreCase("b") || input.equalsIgnoreCase("bishop")) {
						board.tiles[move.endPosition.xCord][move.endPosition.yCord].addPiece(new Bishop(move.activePlayer));
					}
					else{
						input = null;
						System.out.println("Please choose a valid input between K (King), Q (Queen), R (Rook), N (Knight), and B (Bishop).");
					}
				}
				catch (Exception e)
				{
					input = null;
					System.out.println("Please choose a valid input between K (King), Q (Queen), R (Rook), N (Knight), and B (Bishop).");
				}
			}
		}
	}
	
	/*
	 * @return false - no one has won, true - someone wins
	 * should be called after a move has happened, checks if the active player has won (all the opponents pieces are gone)
	 */
	public boolean checkWinCondition(Player activePlayer, Board board) {
		for (Tile tile : board.listOfTiles) {
			if (tile.piece != null) {
				if (tile.piece.owner != activePlayer) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/* Ran after every move is validated if the player isn't killing someone. 
	* Returns true if that player could have killed someone but didn't
	* Returns false otherwise
	*/
	private boolean checkIfKillIsPossible(Player activePlayer, Board board) {
		
		//iterate through the enemy pieces and tell them to try and kill the friendly king
		//if they can (legally) then the active player has put themself in check
		for (Tile tile : board.listOfTiles) {
			if (tile.piece != null) {
				if (tile.piece.owner == activePlayer) {
					//try to make a kill
					Move exMove = new Move();
					exMove.activePlayer = tile.piece.owner;
					exMove.startPosition = tile;
					exMove.piece = tile.piece;
					
					for (Tile endTile : board.listOfTiles) {
						if (endTile.piece != null) {
							if (endTile.piece.owner != activePlayer) {

								exMove.endPosition = endTile;
								boolean isValid = false;
								if(exMove.piece.getClass() == Pawn.class) {
									isValid = validatePawnMove(exMove, board);
								}
								else if(exMove.piece.getClass() == Bishop.class) {
									isValid = validateBishopMove(exMove, board);
								}
								else if(exMove.piece.getClass() == Queen.class) {
									isValid = validateQueenMove(exMove, board);
								}
								else if(exMove.piece.getClass() == Rook.class) {
									isValid = validateRookMove(exMove, board);
								}
								else if(exMove.piece.getClass() == Knight.class) {
									isValid = validateKnightMove(exMove, board);
								}
								else if(exMove.piece.getClass() == King.class) {
									isValid = validateKingMove(exMove, board);
								}
								if (isValid) {
									View.setErrorMessage("A capturing move is available but the move specified captures nothing. See below for details.\n" + exMove.toString());
									return true;
								}
					
							}
						}
					}
					
				}
			}
		}
		return false;
	}
	
	/*
	 * @param activePlayer - what player you want to check if has their opponent in check
	 * @param board - the board you want to check for check on.
	 * @return true if the active player is in check, false if they are not.
	 */
	public boolean CheckForCheck(Player activePlayer, Board board) {
				
		//locate the friendly and enemy kings
		Tile enemyKing = null;
		for (Tile tile: board.listOfTiles) {
			if (tile.piece != null) {
				if (tile.piece.getClass() == King.class && tile.piece.owner != activePlayer) {
					enemyKing = tile;
				}
			}
		}
		
		//if (kingPosition == null) { return false; }
		if (enemyKing == null) { return false; }
		
		//iterate through the active players pieces and tell them to try and kill the friendly king
		//if they can (legally) then the active player is in check
		for (Tile tile : board.listOfTiles) {
			if (tile.piece != null) {
				if (tile.piece.owner == activePlayer) {
					//try to kill the king
					Move regicide = new Move();
					regicide.activePlayer = tile.piece.owner;
					regicide.startPosition = tile;
					regicide.endPosition = enemyKing;
					regicide.piece = tile.piece;
					
					boolean isValid = false;
					if(regicide.piece.getClass() == Pawn.class) {
						isValid = validatePawnMove(regicide, board);
					}
					else if(regicide.piece.getClass() == Bishop.class) {
						isValid = validateBishopMove(regicide, board);
					}
					else if(regicide.piece.getClass() == Queen.class) {
						isValid = validateQueenMove(regicide, board);
					}
					else if(regicide.piece.getClass() == Rook.class) {
						isValid = validateRookMove(regicide, board);
					}
					else if(regicide.piece.getClass() == Knight.class) {
						isValid = validateKnightMove(regicide, board);
					}
					else if(regicide.piece.getClass() == King.class) {
						isValid = validateKingMove(regicide, board);
					}
					if (isValid) {
						return true;
					}
				}
			}
		}
	
		return false;
	}
	
	/*
	 * Called before every move, checks for stalemate and ends the game accordingly.
	 */
	public boolean checkForStalemate(Player activePlayer, Board board) {
		//Check all pieces for a valid move, to start with the player doesn't need to move his King.
		Tile kingTile = null;
		
		for (Tile tile : board.listOfTiles) {
			if (tile.piece != null) {
				if (tile.piece.owner == activePlayer) {
					Move exMove = new Move();
					exMove.activePlayer = tile.piece.owner;
					exMove.startPosition = tile;
					exMove.piece = tile.piece;
					if(tile.piece.getClass() == King.class) {
						kingTile = tile;
					}
					for (Tile endTile : board.listOfTiles) {
						exMove.endPosition = endTile;
						boolean isValid = false;
						if(exMove.piece.getClass() == Pawn.class) {
							isValid = validatePawnMove(exMove, board);
						}
						else if(exMove.piece.getClass() == Bishop.class) {
							isValid = validateBishopMove(exMove, board);
						}
						else if(exMove.piece.getClass() == Queen.class) {
							isValid = validateQueenMove(exMove, board);
						}
						else if(exMove.piece.getClass() == Rook.class) {
							isValid = validateRookMove(exMove, board);
						}
						else if(exMove.piece.getClass() == Knight.class) {
							isValid = validateKnightMove(exMove, board);
						}
						if (isValid) {
							return false;
						}
					}
				}
			}
		}
		
		//Has no valid moves other than potentially the king.
		if(kingTile == null) { //player has no king, no king and no valid moves = stalemate.
			return true;
		}
		
		//Loop through all the potential moved for the king, and check if the player is in check for each of them.
		Move exMove = new Move();
		exMove.activePlayer = activePlayer;
		exMove.startPosition = kingTile;
		exMove.piece = kingTile.piece;
		
		for (Tile endTile : board.listOfTiles) {
			exMove.endPosition = endTile;
			if(!validateKingMove(exMove, board)) {
				continue;
			}
			//is a valid move for the king. Perform the move on a fake board and check for check.
			Board newBoard = new Board(board);	
			newBoard.tiles[endTile.xCord][endTile.yCord].piece = new King(activePlayer);
			newBoard.tiles[kingTile.xCord][kingTile.yCord].piece = null;
			
			if(!CheckForCheck(activePlayer.opponent, newBoard)) { //opponent doesn't have them in check after the move
				return false;
			}
		}
		
		return true;
	}
	
	/*
	 * Gives the stalemate message based on which player was put into stalemate.
	 * @param activePlayer - The player who's turn it currently is.
	 * @return true if the current player was just put into stalemate, false if they weren't
	 */
	public String getStalemateMessage(Player activePlayer) {
		return this.stalemateMessage + " " + activePlayer.color + " wins";
	}
}

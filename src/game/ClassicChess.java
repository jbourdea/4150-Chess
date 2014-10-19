package game;
import java.io.*;

public class ClassicChess extends Rules {

	public ClassicChess() {
		super();
		
		
	}
	
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
	
	
	// return true if it's a valid move, false if invalid
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
	
	// return true if it's a valid move, false if invalid
	public boolean validateQueenMove(Move move, Board board)
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
		// horizontal movement
		else if(xDiff == 0) {
			for(int i=1; i < xDiff; i++ ) {
				Tile t = board.tiles[move.startPosition.xCord + i][move.startPosition.yCord];
				if(t.piece != null) {
					return false;
				}
			}
			return true;
		}
		// vertical movement
		else if(yDiff == 0) {
			for(int i=1; i < yDiff; i++ ) {
				Tile t = board.tiles[move.startPosition.xCord][move.startPosition.yCord + i];
				if(t.piece != null) {
					return false;
				}
			}
			return true;
		}
		
		return false;
	}
	
	// return true if it's a valid move, false if invalid
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
	
	// return true if it's a valid move, false if invalid
		public boolean validateRookMove(Move move, Board board)
		{
			// check what the move way is, horizontal or diagonal
			int xDiff = Math.abs(move.startPosition.xCord - move.endPosition.xCord);
			int yDiff = Math.abs(move.startPosition.yCord - move.endPosition.yCord);
			
			// if it attacks the same team
			if(move.endPosition.piece != null && move.endPosition.piece.owner == move.activePlayer) {
				return false;
			}
			
			// horizontal movement
			if(xDiff == 0) {
				for(int i=1; i < xDiff; i++ ) {
					Tile t = board.tiles[move.startPosition.xCord + i][move.startPosition.yCord];
					if(t.piece != null) {
						return false;
					}
				}
				return true;
			}
			// vertical movement
			else if(yDiff == 0) {
				for(int i=1; i < yDiff; i++ ) {
					Tile t = board.tiles[move.startPosition.xCord][move.startPosition.yCord + i];
					if(t.piece != null) {
						return false;
					}
				}
				return true;
			}
			
			return false;
		}
	
		// return true if it's a valid move, false if invalid
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
		
		// return true if it's a valid move, false if invalid
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
			// the king is attempting to castle
			else if(xDiff == 2) {
				// can't castle if the king has moved
				if(move.piece.hasMoved == true) {
					return false;
				}
				int startXCord = move.startPosition.xCord;
				int startYCord = move.startPosition.yCord;
				
				// check right rook
				if(move.startPosition.xCord < move.endPosition.xCord) {
					// if a piece isn't there, if it has moved, or if it isn't a rook, it doesn't pass
					 if(board.tiles[7][startYCord].piece == null || board.tiles[7][startYCord].piece.hasMoved == true || board.tiles[7][startYCord].piece.getClass() != Rook.class) {
						 return false;
					 }
					 
					 // if there are no pieces inbetween
					 if(board.tiles[startXCord + 1][startYCord].piece == null && board.tiles[startXCord + 2][startYCord].piece == null) {
						 board.tiles[startXCord + 1][startYCord].piece = board.tiles[7][startYCord].piece;
						 board.tiles[7][startYCord].piece = null;
						 return true;
					 }
				}
				// check left rook
				else {
					// if a piece isn't there, if it has moved, or if it isn't a rook, it doesn't pass
					 if(board.tiles[0][startYCord].piece == null || board.tiles[0][startYCord].piece.hasMoved == true || board.tiles[0][startYCord].piece.getClass() != Rook.class) {
						 return false;
					 }
					 
					 // if there are no pieces inbetween
					 if(board.tiles[startXCord - 1][startYCord].piece == null && board.tiles[startXCord - 2][startYCord].piece == null && board.tiles[startXCord - 3][startYCord].piece == null) {
						 board.tiles[startXCord - 1][startYCord].piece = board.tiles[0][startYCord].piece;
						 board.tiles[0][startYCord].piece = null;
						 return true;
					 }
				}
			}

			return false;
		}
		
	
	public boolean ValidateMove(Move move, Board board)
	{
		//TODO:	- ensure that the moving piece can legally get to the move end point.
		// 		- ensure that the requested move does not put the active player in check
		//		- return true if the move is valid, false otherwise
		
		boolean isValid = false;
		
		if(move.piece.getClass() == Pawn.class) {
			isValid = validatePawnMove(move, board);
		}
		else if(move.piece.getClass() == Bishop.class) {
			isValid = validateBishopMove(move, board);
		}
		else if(move.piece.getClass() == Queen.class) {
			isValid = validateQueenMove(move, board);
		}
		else if(move.piece.getClass() == Rook.class) {
			isValid = validateRookMove(move, board);
		}
		else if(move.piece.getClass() == Knight.class) {
			isValid = validateKnightMove(move, board);
		}
		else if(move.piece.getClass() == King.class) {
			isValid = validateKingMove(move, board);
		}
		
		isValid = validateBoardState(move, board) ? isValid : false;
		
		return isValid;
	}
	
	private boolean validateBoardState(Move move, Board board) {
		
		//create deep copies of move and board
		Board newBoard = new Board(board);
		Move newMove = new Move(move);
		
		for (Tile tile: newBoard.listOfTiles) {
			if (newMove.startPosition.xCord == tile.xCord && 
					newMove.startPosition.yCord == tile.yCord) {
				newMove.startPosition = tile;
			}
			else if (newMove.endPosition.xCord == tile.xCord && 
					newMove.endPosition.yCord == tile.yCord) {
				newMove.endPosition = tile;
			}
		}
		
		//make the suggested move on the fake board
		newMove.startPosition.piece = null;
		newMove.endPosition.piece = newMove.piece;
		
		//locate the friendly and enemy kings
		Tile kingPosition = null;
		Tile enemyKing = null;
		for (Tile tile: newBoard.listOfTiles) {
			if (tile.piece != null) {
				if (tile.piece.getClass() == King.class && tile.piece.owner == newMove.activePlayer) {
					kingPosition = tile;
				}
				else if (tile.piece.getClass() == King.class && tile.piece.owner != newMove.activePlayer) {
					enemyKing = tile;
				}
			}
		}
		if (kingPosition == null) { return false; }
		if (enemyKing == null) { return true; }
		
		//iterate through the enemy pieces and tell them to try and kill the friendly king
		//if they can (legally) then the active player has put themself in check
		for (Tile tile : newBoard.listOfTiles) {
			if (tile.piece != null) {
				if (tile.piece.owner != newMove.activePlayer) {
					//try to kill the king
					Move regicide = new Move();
					regicide.activePlayer = tile.piece.owner;
					regicide.startPosition = tile;
					regicide.endPosition = kingPosition;
					regicide.piece = tile.piece;
					
					boolean isValid = false;
					if(regicide.piece.getClass() == Pawn.class) {
						isValid = validatePawnMove(regicide, newBoard);
					}
					else if(regicide.piece.getClass() == Bishop.class) {
						isValid = validateBishopMove(regicide, newBoard);
					}
					else if(regicide.piece.getClass() == Queen.class) {
						isValid = validateQueenMove(regicide, newBoard);
					}
					else if(regicide.piece.getClass() == Rook.class) {
						isValid = validateRookMove(regicide, newBoard);
					}
					else if(regicide.piece.getClass() == Knight.class) {
						isValid = validateKnightMove(regicide, newBoard);
					}
					else if(regicide.piece.getClass() == King.class) {
						isValid = validateKingMove(regicide, newBoard);
					}
					if (isValid) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
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
	
	public void ruleCompleteMove(Player activePlayer, Board board, Move move){
		setLastMoveJump(activePlayer, board);
		if(move.piece.getClass() == Pawn.class && Math.abs(move.startPosition.yCord - move.endPosition.yCord) == 2) {
			Pawn p = (Pawn)move.piece;
			p.lastMoveJump = true;
		}else if(move.piece.getClass() == Pawn.class && move.endPosition.yCord == 7 && move.piece.owner.color.equals("White")) {
			getPawnPromotionInput(board, move);
		}else if(move.piece.getClass() == Pawn.class && move.endPosition.yCord == 0 && move.piece.owner.color.equals("Black")) {
			getPawnPromotionInput(board, move);
		}
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
					if(input.equalsIgnoreCase("q") || input.equalsIgnoreCase("queen")) {
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
						System.out.println("Please choose a valid input between Q (Queen), R (Rook), N (Knight), and B (Bishop).");
					}
				}
				catch (Exception e)
				{
					input = null;
					System.out.println("Please choose a valid input between Q (Queen), R (Rook), N (Knight), and B (Bishop).");
				}
			}
		}
	}
}

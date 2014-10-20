package game;

public class View {
	private static String errorMessage;
	
	public void DisplayMenu() {
		System.out.println("What variation of Chess do you want to play?");
		System.out.println("1. Classic Chess");
		System.out.println("2. Peasants Revolt Chess");
		System.out.println("3. Take All Chess");
		System.out.println("4. Testing Suite");
		System.out.println("Enter the index of the variation.");
	}
	
	public void DisplayBoard(Board gameBoard) {
		
		System.out.print("    A B C D E F G H" + "\n\n");
		
		for(int y = 0; y < gameBoard.height; y++) {
			System.out.print(y + "   ");
			for(int x = 0; x < gameBoard.width; x++) {
				if (gameBoard.tiles[x][y].piece == null) {
					System.out.print("- ");
				}
				else {
					System.out.print(gameBoard.tiles[x][y].piece.toString() + " ");
				}
			}
			System.out.print("  " + y + "\n");
		}
		
		System.out.print("\n    A B C D E F G H" + "\n" );
		
	}
	
	public void DisplayTurnNotification(int turnNumber, String color) {
		System.out.println("Turn #" + turnNumber + ": It's " + color + "'s turn.");
		System.out.println("Please enter a move. (eg: a2-a3)");
	}
	
	public void DisplayErrorMessage() {
		System.out.println("!Error => " + View.getErrorMessage());
		View.setErrorMessage("");
	}
	
	public void DisplayErrorMessage(String errorMessage) {
		View.setErrorMessage(errorMessage);
		DisplayErrorMessage();
	}
	
	public void DisplaySurrenderMessage(String surrenderingColor) {
		String winningColor = "White";
		if(surrenderingColor.equals("White")) {
			winningColor = "Black";
		}
		System.out.println(surrenderingColor + " surrendered. " + winningColor + " is the winner.");
	}
	
	public void DisplayWinMessage(Player winningPlayer) {
		if(winningPlayer == null) {
			System.out.println("Stalement. Game Over");
		}
		else {
			System.out.println(winningPlayer.color + " is the winner.");
		}
	}

	public void DisplayBlackWinsMessage() {
		System.out.println("Black is the winner.");
	}
	
	public void DisplayStalemateMessage() {
		System.out.println("Stalemate. Game Over.");
	}
	
	public void DisplayExitMessage() {
		System.out.println("Thanks for playing!");
	}

	public static String getErrorMessage() {
		return errorMessage;
	}

	public static void setErrorMessage(String errorMessage) {
		View.errorMessage = errorMessage;
	}
}
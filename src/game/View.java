package game;

public class View {
	
	
	
	public void DisplayBoard(Board gameBoard) {
		
		System.out.print("   A B C D E F G H" + "\n");
		
		for(int y = 0; y < gameBoard.height; y++) {
			System.out.print(y + "  ");
			for(int x = 0; x < gameBoard.width; x++) {
				if (gameBoard.tiles[x][y].piece == null) {
					System.out.print("- ");
				}
				else {
					System.out.print(gameBoard.tiles[x][y].piece.toString() + " ");
				}
			}
			System.out.print(" " + y + "\n");
		}
		
		System.out.print("   A B C D E F G H" + "\n" );
		
	}
}
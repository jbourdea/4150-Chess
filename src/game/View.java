package game;

public class View {
	
	
	
	public void DisplayBoard(Board gameBoard) {
		
		for(int y = 0; y < gameBoard.height; y++) {
			for(int x = 0; x < gameBoard.width; x++) {
				if (gameBoard.tiles[x][y].piece == null) {
					System.out.print("0 ");
				}
				else {
					System.out.print(gameBoard.tiles[x][y].piece.toString() + " ");
				}
			}
			System.out.print("\n");
		}
	}
}
package game;

public class Game {
	
	public int turnNumber = 0;
	public Player activePlayer = null;
	
	public Player white = null;
	public Player black = null;
	
	public Game() {
		white = new Player();
		black = new Player();
		
		white.color = "White";
		black.color = "Black";
		
		turnNumber = 0;
		activePlayer = black;
	}
	
	public void NextTurn() {
		turnNumber++;
		
		if (activePlayer == black) {
			activePlayer = white;
		}
		else {
			activePlayer = black;
		}
		
		//TODO: update view with new turn information
	}
	
	public static void main(String[] args) {
	    
		Controller controller = new Controller(new View());
		
		controller.play();
		
	}

}
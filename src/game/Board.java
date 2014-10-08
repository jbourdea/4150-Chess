package game;

public class Board {
	public Tile[][] tiles;
	public int width = 0;
	public int height = 0;
	
	//public Rules gameRules;
	
	
	public Board(int width, int height) {
		
		this.width = width;
		this.height = height;
		
		tiles = new Tile[width][height];
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				tiles[x][y] = new Tile();
			}
		}
	}
}
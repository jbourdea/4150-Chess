package game;
import java.util.*;

public class Board {
	public Tile[][] tiles;
	public int width = 0;
	public int height = 0;
	
	public List<Tile> listOfTiles = new ArrayList<Tile>();
	
	
	public Board(int width, int height) {
		
		this.width = width;
		this.height = height;
		
		tiles = new Tile[width][height];
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				Tile tile = new Tile();
				tile.xCord = x;
				tile.yCord = y;
				tiles[x][y] = tile;
				listOfTiles.add(tile);
			}
		}
	}
	
	//creates a deep copy of board
	public Board(Board board) {
		width = board.width;
		height = board.height;
		
		tiles = new Tile[width][height];
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				Tile tile = new Tile();
				tile.xCord = x;
				tile.yCord = y;
				tiles[x][y] = tile;
				listOfTiles.add(tile);
			}
		}
		for (Tile tile: board.listOfTiles) {
			if (tile.piece != null) {
				AddPiece(tile.piece, tile.xCord, tile.yCord);
			}
		}
	}
	
	public void AddPiece(Piece piece, int xCoord, int yCoord) {
		tiles[xCoord][yCoord].addPiece(piece);
	}
}
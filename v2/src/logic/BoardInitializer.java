package logic;

import Objects.GameObjectBoard;

public class BoardInitializer {
	
	private Level level ;
	private GameObjectBoard board;
	private Game game;
	
	public GameObjectBoard initialize(Game game, Level level) {
		this. level = level;
		this. game = game;
		board = new GameObjectBoard(Game.GetNumRows(), Game.GetNumCols());
		initializeOvni () ;
		initializeRegularAliens () ;
		initializeDestroyerAliens () ;
		return board;
	}
	
	private void initializeOvni () {
		// TODO implement
	}
	
	private void initializeRegularAliens () {
		// TODO implement
	}
	
	private void initializeDestroyerAliens () {
		// TODO implement
	}
}
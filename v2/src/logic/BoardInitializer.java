package logic;

import Objects.DestroyerShip;
import Objects.GameObjectBoard;
import Objects.OVNI;
import Objects.RegularShip;

public class BoardInitializer {

	private Level level ;
	private GameObjectBoard board;
	private Game game;
	private int IniRow;

	public GameObjectBoard initialize(Game game, Level level) {  //por ahora solo inicializa los regShip, desShip
		this. level = level;
		this. game = game;
		board = new GameObjectBoard(game, level);
		return board;
		
	}

	public void initializeEnemy() {
		this.IniRow = game.GetNumRows() -(level.getNumRegularAliens() + level.getNumDestroyerAliens());
		initializeOvni () ;
		initializeDestroyerAliens () ;
		initializeRegularAliens () ;
		
	}
	
	private void initializeOvni () {
		game.addObject(new OVNI(game, 0, 7));
	}
//	  -----TABLERO-----
//	  0 1 2 3 4 5 6 7 8
//	7
//	6
//	5
//	4
//	3
//	2
//	1
//	0

	private void initializeRegularAliens () {
		int total = level.getNumRegularAliens();
		int perRow = level.getNumRegularAliensPerRow();

	
		for(int i = 0; i < total; i++)
		{
			game.addObject(new RegularShip(game, 2 + i, IniRow - i / perRow));
		}
	}

	private void initializeDestroyerAliens () {
		int total = level.getNumDestroyerAliens();
		int perRow = level.getNumDestroyerAliensPerRow();
		
		for(int i = 0; i < total; i++)
		{
			game.addObject(new DestroyerShip(game, 2 + i % perRow, IniRow - i / perRow));
		}
		this.IniRow -= total / perRow;
	}
}

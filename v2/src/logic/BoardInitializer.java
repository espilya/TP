package logic;

import Objects.DestroyerShip;
import Objects.GameObject;
import Objects.GameObjectBoard;
import Objects.OVNI;
import Objects.RegularShip;

public class BoardInitializer {

	private Level level ;
	private GameObjectBoard board;
	private Game game;

	public GameObjectBoard initialize(Game game, Level level) {  //por ahora solo inicializa los regShip, desShip
		this. level = level;
		this. game = game;
		board = new GameObjectBoard(game, level);
		initializeOvni () ;
		initializeRegularAliens () ;
		initializeDestroyerAliens () ;
		return board;
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
		int posX = 2;
		int posY = 6;
		for(int i=0; i<total; i++) {
				game.addObject(new RegularShip(game, i+posX, posY));
				if(i%5==0)
					posY--;
		}
	}

	private void initializeDestroyerAliens () {
		int numOfRowsOfRegular = level.getNumRowsOfRegularAliens();
		int posY = 6- numOfRowsOfRegular;
		for(int i=0; i<level.getNumDestroyerAliens(); i++) {
			game.addObject(new DestroyerShip(game, i+2 , posY));
		}
	}
}

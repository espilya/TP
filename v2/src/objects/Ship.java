package objects;

import logic.Game;

public abstract class Ship extends GameObject{

	public Ship(Game game, int x, int y) {
		super(game, x, y);
	}
	
	public int getPoints()
	{
		return 0;
	}
}

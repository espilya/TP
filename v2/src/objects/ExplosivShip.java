package objects;

import logic.Game;

public class ExplosivShip extends AlienShip{

	public ExplosivShip(Game game, int x, int y) {
		super(game, x, y);
	}
	private final int harm = 1;
	
	public ExplosivShip(Game game, int x, int y, int live) {
		super(game, x, y);
		super.live = live;
	}

	private final int points = 5;
	

	public int getPoints() {
		return points;
	}

	public String toString()
	{
		return "E[" + live + "]";
	}
	
	public int GetHarm()
	{
		return harm;
	}
	
	public boolean die()
	{
		game.explosion(pos[0], pos[1]);
		return true;
	}
}

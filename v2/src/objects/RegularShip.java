package objects;

import logic.Game;

public class RegularShip extends AlienShip{

	public RegularShip(Game game, int x, int y) {
		super(game, x, y);
		live = FinalHP;
	}

	private final int points = 5;
	private final int FinalHP = 2;


	public int getPoints() {
		return points;
	}

	public void reset()
	{
		live = FinalHP;
	}

	public String toString()
	{
		return "R[" + live + "]";
	}
	
	public boolean isRegular() {
		return true;
	}
	
	protected String stringify() {
		return "R;" + pos[0] + "," + pos[1] + ";" + live + ";" + game.cyclesNextAlienMove() + ";" + dir + " ";
	}
}

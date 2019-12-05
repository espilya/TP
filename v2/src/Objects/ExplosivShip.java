package Objects;

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
	protected static final String Detail = "ExplosivShip";
	

	public int getPoints() {
		return points;
	}

	public String toString()
	{
		return "R[" + live + "]";
	}

	protected String getDetail()
	{
		return Detail;
	}
	
	public int GetHarm()
	{
		return harm;
	}
}

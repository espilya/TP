package Objects;

import logic.Game;

public class DestroyerShip extends AlienShip{
	public DestroyerShip(Game game, int x, int y) {
		super(game, x, y);
		super.live = FinalHP;
		bomba = null;
	}

	private final int points = 10;
	private Bomb bomba;
	private final int harm = 0;
	private final int FinalHP = 1;
	protected static final String Detail = "DestroyerShip";

	//rand


	public int getHarm()
	{
		return harm;
	}

	public int getPoints() {
		return points;
	}

	public void reset()
	{
		live = FinalHP;
	}

	public String toString()
	{
		return "D[" + live + "]";
	}

	public boolean shoot()
	{
		if((bomba == null || !bomba.isAlive() || !game.isOnBoard(bomba)) && game.getNextDouble() <= game.getProbShoot())
		{
			bomba = new Bomb(game, super.pos[0], super.pos[1]);
			return true;
		}
		return false;
	}

	protected String getDetail()
	{
		return Detail;
	}

	public GameObject getProyectil()
	{
		return bomba;
	}
}

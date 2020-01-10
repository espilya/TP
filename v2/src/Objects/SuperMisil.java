package Objects;

import logic.Game;

public class SuperMisil extends Weapon{

	public SuperMisil(Game game, int x, int y) {
		super(game, x, y);
		live = 1;
	}

	private final int harm = 2;
	protected final static String Detail = "Misil";

	public int GetHarm()
	{
		return harm;
	}

	public boolean receiveBombAttack(int damage)
	{
		live = 0;
		return true;
	}

	public boolean MoveY() {
		this.pos[1]--;
		if(this.pos[1] < 0)
			return false;

		return true;
	}

	protected String getDetail()
	{
		return Detail;
	}

	public String toString() {
		return "<>";
	}
}

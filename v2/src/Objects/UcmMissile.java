package Objects;

import logic.Game;

public class UcmMissile extends Weapon{

	public UcmMissile(Game game, int x, int y) {
		super(game, x, y);
		live = 1;
	}

	private final int harm = 1;
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
		return "oo";
	}

	public void hit(int harm)
	{
		receiveBombAttack(1);
		live = 0;
	}
}

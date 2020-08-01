package objects;

import logic.Game;

public class UcmMissile extends Weapon{

	public UcmMissile(Game game, int x, int y) {
		super(game, x, y);
		live = 1;
	}

	private final int harm = 1;

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

	public String toString() {
		return "oo";
	}
	
	public boolean isMisil() {
		return true;
	}
	
	protected String stringify() {
		return "M;" + pos[0] + "," + pos[1] + " ";
	}
}

package objects;

import logic.Game;

public class Bomb extends Weapon{

	public Bomb(Game game, int x, int y) {
		super(game, x, y);
		live = 1;
	}

	private final int harm = 1;

	public boolean receiveMissileAttack(int damage)
	{
		live = 0;
		return true;
	}

	public int GetHarm()
	{
		return harm;
	}

	public String toString()
	{
		return "..";
	}

	public boolean MoveY()
	{
		pos[1]++;
		return pos[1] < game.GetNumRows();
	}
	
	public boolean isBomb() {
		return true;
	}

	protected String stringify() {
		return "B:" + pos[0] + "," + pos[1] + " ";
	}
}

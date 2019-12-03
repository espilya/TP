package Objects;

import logic.Game;

public class OVNI extends EnemyShip{

	private int hp;
	private final int points = 25;
	private final int FinalHP = 1;
	public final String Detail = "OVNI";
	private int pos[] = new int[2];

	public OVNI(String t, Game g){
		super(t, g);
		hp = FinalHP;
		row = 0;
	}
	
	public void Hit(int harm) {
		this.hp -= harm;
	}
	
	public void SetPos(int x, int y) {
		pos[0] = x;
		pos[1] = y;
	}
	
	public int GetPosX() {
		return pos[0];
	}
	
	public int GetPosY() {
		return pos[0];
	}
	
	public int GetShipHP() {
		return hp;
	}
	
	public int GetPoints() {
		return this.points;
	}
	
	public int GetFinalHP()
	{
		return FinalHP;
	}
	
	public String toString() {
		return "O[" + hp + "]";
	}
	
	public void reset()
	{
		hp = 1;
	}
}
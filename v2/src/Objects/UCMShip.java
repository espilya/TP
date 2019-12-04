package Objects;

import logic.Game;

public class UCMShip extends Ship{
	public UCMShip(Game game, int x, int y) {
		super(game, x, y);
		live = FinalHP;
		}

	private final int harm = 0;
	private final int FinalHP = 3;
	protected final static String Detail = "UCMShip";
	private UcmMissile misil;

	public String toString() {
		return "^__^";
	}

	public void reset(){
		live = FinalHP;
	}

	protected String getDetail()
	{
		return Detail;
	}

	public boolean receiveBombAttack(int damage)
	{
		live -= damage;
		return true;
	}

	public int getHarm() {
		return harm;
	}

	public boolean shoot()
	{
		if(misil == null || !misil.isAlive())
		{
			misil = new UcmMissile(game, super.pos[0], super.pos[1]);
			return true;
		}
		return false;
	}

	public GameObject getProyectil()
	{
		return misil;
	}

	public boolean moveX(int x)
	{
		boolean aux;
		aux = pos[0] + x < game.GetNumCols() && pos[0] + x >= 0;
		if(aux)
		{
			pos[0] += x;
		}
		return aux;
	}

}

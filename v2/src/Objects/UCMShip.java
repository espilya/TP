package Objects;

import logic.Game;

public class UCMShip extends Ship{
	public UCMShip(Game game, int x, int y) {
		super(game, x, y);
		live = FinalHP;
		}

	private final int harm = 0;
	private final int FinalHP = 3;
	public final String Detail = "UCMShip";
	private UcmMissile misil;
	
	public String toString() {
		return "^__^";
	}
	
	public void reset(){
		live = FinalHP;
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
		aux = pos[0] + 1 < game.GetNumCols();
		if(aux)
		{
			pos[0] += 1;
		}
		return aux;
	}
	
	public String GetDetail() {
		return Detail;
	}
}
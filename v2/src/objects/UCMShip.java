package objects;

import exceptions.MissileInFlightException;
import exceptions.UCMShipMoveException;
import logic.Game;

public class UCMShip extends Ship{
	public UCMShip(Game game, int x, int y) {
		super(game, game.GetNumCols() / 2, game.GetNumRows() - 1);
		super.live = FinalHP;
		}

	private final int harm = 0;
	private final int FinalHP = 3;
	private GameObject misil;

	public String toString() {
		return "^__^";
	}

	public void reset(){
		live = FinalHP;
	}

	public boolean receiveBombAttack(int damage)
	{
		super.live -= damage;
		return true;
	}

	public int getHarm() {
		return harm;
	}

	public boolean shoot(boolean x) throws MissileInFlightException  
	{
		if(misil == null ||  !game.isOnBoard(misil) || !this.misil.isAlive())
		{
			if(!x)
			{
				misil = new UcmMissile(game, super.pos[0], super.pos[1]);
				return true;
			}
			else if(game.getSuperMisil() > 0)
			{
				game.reduceSuperMisil();
				misil = new SuperMisil(game, super.pos[0], super.pos[1]);
				return true;
			}
		}
		throw new MissileInFlightException("missile/SuperMissile already exists on board");
	}

	public GameObject getProyectil()
	{
		return misil;
	}
	
 	public boolean movPossible(int x) throws UCMShipMoveException
	{
		boolean aux;
		aux = pos[0] + x < game.GetNumCols() && pos[0] + x >= 0;
		if(aux)
		{
			pos[0] += x;
			return true;
		}
		throw new UCMShipMoveException("Error while moving the UCMShip");
	}
 	
 	public boolean isUCMShip() {
		return true;
	}

 	protected String stringify() {
		return "P;" + pos[0] + "," + pos[1] + ";" + live + ";" + game.GetPoints() + ";" + game.GetShockWave() + ";" + game.getSuperMisil() + " ";
	}

}

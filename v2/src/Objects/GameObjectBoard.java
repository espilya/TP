package Objects;

import logic.Level;

public class GameObjectBoard
{
	GameObject[] GObject;
	boolean HaveLanded;
	int nOfAliens;

	public GameObjectBoard(Level level)
	{
		init(level);
	}

	private void init(Level level)
	{
		HaveLanded = false;
		nOfAliens = level.getNumDestroyerAliens() + level.getNumRegularAliens();
	}

	//Bien
	private int getCurrentObjects () {
		return GObject.length;
	}

	//Bien
	public void add(GameObject object) {
		GObject[this.getCurrentObjects()] = object;
	}

	//Bien
	private GameObject getObjectInPosition (int row, int col) {
		int i = 0;
		while(i < this.getCurrentObjects())
		{
			if(GObject[i].getRow() == row && GObject[i].getCol() == col)
			{
				return GObject[i];
			}
			i++;
		}
		return null;
	}

	//Bien
	private int getIndex(GameObject x) {
		int i = 0;
		while(i < this.getCurrentObjects())
		{
			if(GObject[i] == x)
			{
				return i;
			}
			i++;
		}
		return -1;
	}

	//Bien
	private void remove (GameObject object) {
		int i = this.getIndex(object);
		while(i < this.getCurrentObjects() - 1)
		{
			GObject[i] = GObject[i + 1];
		}
	}

	//No se que hace
	public void update() {

	}

	//Bien
	private void checkAttacks() {
		int i, j;
		boolean aux, stop;
		i = 0;
		while(i < this.getCurrentObjects() - 1)
		{
			j = 0;
			aux = true;
			stop = false;
			while(j < this.getCurrentObjects())
			{
				if(!stop && this.GObject[i].getRow() == this.GObject[j].getRow() && this.GObject[i].getCol() == this.GObject[j].getCol())
				{
					if(GObject[i].getDetail().equals(UcmMissile.Detail))
						GObject[j].receiveMissileAttack(GObject[i].GetHarm());

					else if(GObject[i].getDetail().equals(Bomb.Detail))
						GObject[j].receiveBombAttack(GObject[i].GetHarm());

					if(GObject[j].getDetail().equals(UcmMissile.Detail))
						GObject[i].receiveMissileAttack(GObject[i].GetHarm());

					else if(GObject[j].getDetail().equals(Bomb.Detail))
						GObject[i].receiveBombAttack(GObject[i].GetHarm());
					stop = true;
					if(!GObject[i].isAlive() && GObject[i].die())
					{
						if(GObject[i].getDetail().equals(DestroyerShip.Detail) || GObject[i].getDetail().equals(RegularShip.Detail))
							this.nOfAliens--;

						remove(GObject[i]);
						aux = false;
					}
					if(!GObject[j].isAlive() && GObject[j].die())
					{
						if(GObject[j].getDetail().equals(DestroyerShip.Detail) || GObject[j].getDetail().equals(RegularShip.Detail))
							this.nOfAliens--;

						remove(GObject[j]);
					}
					else
					{
						j++;
					}
				}
				else
				{
					j++;
				}
			}
			if(aux)
			{
				i++;
			}
		}
	}

	//Bien
	public void computerAction(boolean move) {
		MoveMisil();
		checkAttacks();

		if(move)
			MoveOther();

		shoot();
		MoveBombs();
		checkAttacks();
	}

	public void ShockWave()
	{
		for(int i = 0; i < this.getCurrentObjects(); i++)
		{
			GObject[i].receiveShockWaveAttack(1);
		}
	}

	private void MoveMisil()
	{
		for(int i = 0; i < this.getCurrentObjects(); i++)
		{
			if(GObject[i].getDetail().equals(UcmMissile.Detail) && !GObject[i].MoveY() && GObject[i].die())
			{
				this.remove(GObject[i]);
			}
		}
	}

	private void MoveOther()
	{
		MoveOVNI();
		if(!MoveAlienShipsX(1))
		{
			MoveAlienShipsX(-1);
			MoveAlienShipsY();
		}
	}

	private boolean MoveAlienShipsX(int x)
	{
		boolean aux = false;
		for(int i = 0; i < this.getCurrentObjects(); i++)
		{
			if((GObject[i].getDetail().equals(RegularShip.Detail) || GObject[i].getDetail().equals(DestroyerShip.Detail)) && !GObject[i].MoveX(x))
			{
				aux = true;
			}
		}
		return aux;
	}

	private void MoveBombs()
	{
		for(int i = 0; i < this.getCurrentObjects(); i++)
		{
			if(GObject[i].getDetail().equals(Bomb.Detail) && !GObject[i].MoveY())
			{
				this.remove(GObject[i]);
			}
		}
	}

	private void MoveOVNI()
	{
		for(int i = 0; i < this.getCurrentObjects(); i++)
		{
			if(GObject[i].getDetail().equals(OVNI.Detail))
			{
				GObject[i].MoveY();
			}
		}
	}

	private void MoveAlienShipsY()
	{
		for(int i = 0; i < this.getCurrentObjects(); i++)
		{
			if((GObject[i].getDetail().equals(RegularShip.Detail) || GObject[i].getDetail().equals(DestroyerShip.Detail)) && !GObject[i].MoveY())
			{
				HaveLanded = true;
			}
		}
	}


	//Bien
	private void shoot() {
		for(int i = 0; i < this.getCurrentObjects(); i++)
		{
			if(GObject[i].getDetail().contentEquals(DestroyerShip.Detail) && GObject[i].shoot())
			{
				GObject[this.getCurrentObjects()] = GObject[i].getProyectil();
			}
		}
	}

	//Bien
	public boolean AliensHaveLanded()
	{
		return this.HaveLanded;
	}

	//Bien
	public String toString(int row, int col) {
		GameObject object = getObjectInPosition(row, col);
		if(object == null)
		{
			return null;
		}
		return object.toString();
	}

	//Bien
	public boolean allDead() {
		return this.nOfAliens == 0;
	}

	//Bien
	public int remainingAliens() {
		return nOfAliens;
	}

}

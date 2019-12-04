package Objects;

import logic.Game;
import logic.Level;

public class GameObjectBoard
{
	GameObject[] GObject = new GameObject[30];
	boolean HaveLanded;
	int nOfAliens;
	int contador;
	Game game;

	public GameObjectBoard(Game game, Level level)
	{
		this.game = game;
		init(level);
	}

	private void init(Level level)
	{
		HaveLanded = false;
		contador = 0;
		nOfAliens = level.getNumDestroyerAliens() + level.getNumRegularAliens();
	}

	//Bien
	public void add(GameObject object) {
		GObject[contador] = object;
		contador++;
	}

	//Bien
	private GameObject getObjectInPosition (int col, int row) {
		int i = 0;
		while(i < contador)
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
		while(i < contador)
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
		while(i < contador - 1)
		{
			GObject[i] = GObject[i + 1];
			i++;
		}
		contador--;
	}

	//No se que hace
	public void update() {

	}
	


	//Bien
	private void checkAttacks() {
		int i, j;
		boolean aux, stop;
		i = 0;
		while(i < contador - 1)
		{
			j = i+1;
			aux = true;
			stop = false;
			while(!stop && j < contador)
			{
				if(this.GObject[i].getRow() == this.GObject[j].getRow() && this.GObject[i].getCol() == this.GObject[j].getCol())
				{
					if(GObject[i].getDetail().equals(UcmMissile.Detail))
					{
						GObject[j].receiveMissileAttack(GObject[i].GetHarm());
						remove(GObject[i]);
						game.disableMissile();
						aux = true;
					}

					else if(GObject[i].getDetail().equals(Bomb.Detail))
					{
						aux = true;
						remove(GObject[i]);
						GObject[j].receiveBombAttack(GObject[i].GetHarm());
					}
						
				
					else if(GObject[j].getDetail().equals(UcmMissile.Detail))
					{
						GObject[i].receiveMissileAttack(GObject[i].GetHarm());
						remove(GObject[j]);
						game.disableMissile();
						aux = true;
					}

					else if(GObject[j].getDetail().equals(Bomb.Detail))
					{
						aux = true;
						GObject[j].receiveBombAttack(GObject[i].GetHarm());
						remove(GObject[j]);
					}
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
		int i = 0;
		while(i < contador)
		{
			if(!GObject[i].getDetail().equals(UCMShip.Detail))
				GObject[i].receiveShockWaveAttack(1);
			
			if(!GObject[i].isAlive() && GObject[i].die())
				remove(GObject[i]);
			else
				i++;
		}
	}

	private void MoveMisil()
	{
		for(int i = 0; i < contador; i++)
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
		if(MoveAlienShipsX(1))
		{
			MoveAlienShipsX(-1);
			MoveAlienShipsY();
		}
	}

	private boolean MoveAlienShipsX(int x)
	{
		boolean aux = false;
		for(int i = 0; i < contador; i++)
		{
			if(GObject[i].getDetail().equals(RegularShip.Detail) || GObject[i].getDetail().equals(DestroyerShip.Detail))
			{
				if(!GObject[i].MoveX(x))
					aux = true;
			}
		}
		return aux;
	}

	private void MoveBombs()
	{
		for(int i = 0; i < contador; i++)
		{
			if(GObject[i].getDetail().equals(Bomb.Detail) && !GObject[i].MoveY())
			{
				this.remove(GObject[i]);
			}
		}
	}

	private void MoveOVNI()
	{
		for(int i = 0; i < contador; i++)
		{
			if(GObject[i].getDetail().equals(OVNI.Detail))
			{
				GObject[i].MoveX(i);
			}
		}
	}

	private void MoveAlienShipsY()
	{
		for(int i = 0; i < contador; i++)
		{
			if((GObject[i].getDetail().equals(RegularShip.Detail) || GObject[i].getDetail().equals(DestroyerShip.Detail)) && !GObject[i].MoveY())
			{
				HaveLanded = true;
			}
		}
	}


	//Bien
	private void shoot() {
		for(int i = 0; i < contador; i++)
		{
			if(GObject[i].getDetail().contentEquals(DestroyerShip.Detail) && GObject[i].shoot())
			{
				GObject[contador] = GObject[i].getProyectil();
			}
		}
	}

	//Bien
	public boolean AliensHaveLanded()
	{
		return this.HaveLanded;
	}

	//Bien
	public String toString(int col, int row) {
		GameObject object = getObjectInPosition(col, row);
		if(object == null)
		{
			return "";
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

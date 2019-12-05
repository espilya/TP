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
		
		int points = object.getPoints();
		game.receivePoints(points);
		
		if(object.getDetail().equals(DestroyerShip.Detail) || object.getDetail().equals(RegularShip.Detail) || object.getDetail().equals(ExplosivShip.Detail))
			this.nOfAliens--;
		
		while(i < contador - 1)
		{	
			GObject[i] = GObject[i + 1];
			i++;
		}
		contador--;
		GObject[contador] = null;
	}

	//No se que hace
	public void update() {

	}


	//Bien
	private void checkAttacks() {
		int i, j;
		boolean aux, stop, delete;
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
					aux = true;
					if(GObject[i].getDetail().equals(UcmMissile.Detail) || GObject[i].getDetail().equals(SuperMisil.Detail))
					{
						GObject[j].receiveMissileAttack(GObject[i].GetHarm());
						GObject[i].receiveBombAttack(1);
						if(GObject[j].getDetail().equals(ExplosivShip.Detail))
							game.explosion(GObject[j].getCol(), GObject[j].getRow());
						if(!GObject[j].isAlive() && GObject[j].die())
						{
							remove(GObject[j]);
						}
						remove(GObject[i]);
					}

					else if(GObject[i].getDetail().equals(Bomb.Detail))
					{
						delete = !GObject[j].getClass().equals(DestroyerShip.Detail);
						GObject[j].receiveBombAttack(GObject[i].GetHarm());
						GObject[i].receiveMissileAttack(1);
						if(!GObject[j].isAlive() && GObject[j].die())
						{
							remove(GObject[j]);
						}
						
						if(delete)
							remove(GObject[i]);
						else
							aux = false;
					}
						
				
					else if(GObject[j].getDetail().equals(UcmMissile.Detail) || GObject[i].getDetail().equals(SuperMisil.Detail))
					{
						GObject[j].receiveBombAttack(1);
						GObject[i].receiveMissileAttack(GObject[j].GetHarm());
						if(GObject[i].getDetail().equals(ExplosivShip.Detail))
							game.explosion(GObject[i].getCol(), GObject[i].getRow());
						remove(GObject[j]);
						if(!GObject[i].isAlive() && GObject[i].die())
						{
							remove(GObject[i]);
							aux = false;
						}
					}

					else if(GObject[j].getDetail().equals(Bomb.Detail))
					{
						delete = !GObject[i].getClass().equals(DestroyerShip.Detail);
						GObject[j].receiveMissileAttack(1);
						GObject[i].receiveBombAttack(GObject[j].GetHarm());
						if(delete)
							remove(GObject[j]);
						else
							aux = false;
						if(!GObject[i].isAlive() && GObject[i].die())
						{
							remove(GObject[i]);
							aux = false;
						}
					}
					else
						aux = false;
					stop = true;
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
		{
			makeExplosiv();
			MoveOther();
			checkAttacks();
		}
		
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
		int i = 0;
		while(i < contador)
		{
			if(GObject[i].getDetail().equals(Bomb.Detail) && !GObject[i].MoveY())
			{
				this.remove(GObject[i]);
			}
			else
			{
				i++;
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

	private void makeExplosiv()
	{
		int x, y, live;
		for(int i = 0; i < contador; i++)
		{
			if(GObject[i].getDetail().equals(RegularShip.Detail) && game.getNextDouble() < game.getLevel().getTurnExplodeFrequency())
			{
				x = GObject[i].getCol();
				y = GObject[i].getRow();
				live = GObject[i].getLive();
				GObject[i] = new ExplosivShip(game, x, y, live);
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
				contador++;
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
		if(object == null || !object.isAlive())
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

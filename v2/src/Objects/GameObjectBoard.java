package Objects;

public class GameObjectBoard
{
	GameObject[] GObject;
	boolean HaveLanded;
	int nOfAliens;
	
	//Dar valor inicial a nOfAliens
	public GameObjectBoard()
	{
		HaveLanded = false;
		//nOfAliens = ;
	}
	
	//Creo que bien, no se si hace esto
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
					GObject[i].Hit(GObject[j].GetHarm());
					GObject[j].Hit(GObject[i].GetHarm());
					stop = true;
					if(GObject[i].die())
					{
						remove(GObject[i]);
						aux = false;
					}
					if(GObject[j].die())
					{
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
		if(move)
			MoveMisil();
		checkAttacks();
		shoot();
		if(move)
			MoveOther();
		checkAttacks();
	}
	
//--------------------------------------------------------
//Arreglar moves (tambien en GameObject)	
	
	private void MoveMisil()
	{
		for(int i = 0; i < this.getCurrentObjects(); i++)
		{
			if(!GObject[i].MoveY(-1) && GObject[i].die())
			{
				this.remove(GObject[i]);
			}
		}
	}
	
	private void MoveOther()
	{
		MoveBombs();
		if(MoveAlienShipsX(1))
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
			if(!GObject[i].MoveX(x))
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
			if(!GObject[i].MoveY(0) && GObject[i].die())
			{
				this.remove(GObject[i]);
			}
		}
	}

	private void MoveAlienShipsY()
	{
		for(int i = 0; i < this.getCurrentObjects(); i++)
		{
			if(!GObject[i].MoveY(1))
			{
				HaveLanded = true;
			}
		}
	}
	
//--------------------------------------------------------
	
	//Bien
	private void shoot() {
		for(int i = 0; i < this.getCurrentObjects(); i++)
		{
			if(GObject[i].shoot())
			{
				GObject[this.getCurrentObjects()] = GObject[i].getProyectil();
			}
		}
	}
	
	//No tengo muy claro ni que hace ni en que situacion se usaria
	private void removeDead() {
		// TODO implement
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


	
	//Bien, modificar nOfAliens cuando sea necesario en el resto de funciones
	public boolean allDead() {
		return this.nOfAliens == 0;
	}

	
	//Bien
	public int remainingAliens() {
		return nOfAliens;
	}
	
}
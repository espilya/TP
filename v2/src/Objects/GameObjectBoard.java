package Objects;

public class GameObjectBoard
{
	GameObject[] GObject;
	int contador;
	GameObjectBoard()
	{
		contador = 0;
	}

	public void add(GameObject object) {
		GObject[contador] = object;
		contador++;
	}
	
	public GameObject buscar(int row, int col)
	{
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
	
}
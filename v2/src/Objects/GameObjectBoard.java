package Objects;

public class GameObjectBoard
{
	GameObject[] GObject;
	int contador;
	
	public GameObjectBoard(int numRows, int numCols)
	{
		contador = 0;
		//TODO implement
	}
	
	
	private int getCurrentObjects () {
		// TODO implement
	}

	public void add(GameObject object) {
		GObject[contador] = object;
		contador++;
	}
	
	private GameObject getObjectInPosition ( /* coordinadas */ ) {
		// TODO implement
	}
	
	private int getIndex( /* coordinadas */ ) {
		// TODO implement
	}
	
	/* si vuestra soluci \’on requiere que sea public, se puede cambiar */
	private void remove (GameObject object) {
		// TODO implement
	}
	
	public void update() {
		
	}
	
	private void checkAttacks(GameObject object) {
		// TODO implement
	}
	
	public void computerAction() {
		// TODO implement
	}
	
	private void removeDead() {
		// TODO implement
	}
	
	public String toString( /* coordinadas */ ) {
		// TODO implement
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
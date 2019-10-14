public class DestroyerShipList{
	public static final int DEST_TAM = 4;
	private int contador = 0;
	//DestroyerShip myObject = new DestroyerShip();

	private DestroyerShip[] tableDestroyer = new DestroyerShip[DEST_TAM];
	
	public void shipHitByUCMShip(int i, int harm) {
		tableDestroyer[i].shipHitByUCMShip(harm);
	}
	
	public void SetDestShip(int x, int y, int i) {
		tableDestroyer[i].setShipPos(x, y);
	}
	
	public int GetDestX(int i) {
		return tableDestroyer[i].GetDestX();
	}
	
	public int GetDestY(int i) {
		return tableDestroyer[i].GetDestY();
	}
	
	public int GetDestShipHP(int i) {
		return tableDestroyer[i].GetShipHP();
	}
	
	public void SetContador(int x)
	{
		contador = x;
	}
	
	public int GetContador() {
		return contador;
	}
	
	public String toString(int i) {
		return "O[" + tableDestroyer[i].GetShipHP() +"]";
	}
	
	public void reset()
	{
		for(int i = 0; i < contador; i++)
		{
			tableDestroyer[i].reset();
		}
	}
}
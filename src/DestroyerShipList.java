public class DestroyerShipList{
	public static final int DEST_TAM = 4;
	private int contador = 0;
	DestroyerShip myObject = new DestroyerShip();

	DestroyerShip[] tableDestroyer = new DestroyerShip[DEST_TAM];
	
	public void SetDestShip(int x, int y, int i) {
		tableDestroyer[i].setShipPos(x, y);
	}
	
	public int GetDestShipPos(int x, int y, int i) {
		return tableDestroyer[i].GetShipPos(x, y);
	}
	
	public int GetDestShipHP(int x, int y, int i) {
		return tableDestroyer[i].GetShipHP(x, y);
	}
}
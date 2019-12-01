public class DestroyerShipList{
	public static final int DEST_TAM = 4;
	private int contador = 0;
	private int indice = 0;
	
	//DestroyerShip myObject = new DestroyerShip();

	private DestroyerShip[] tableDestroyer = new DestroyerShip[DEST_TAM];
	
	public void initialize(int n){
		indice = n; contador = n;
		for(int i = 0; i < n; i++) {
			tableDestroyer[i] = new DestroyerShip();
		}
	}
	
	public void shipHitByUCMShip(int i, int harm) {
		tableDestroyer[i].shipHitByUCMShip(harm);
		if(tableDestroyer[i].GetShipHP() == 0) {
			contador--;
			tableDestroyer[i] = null;
		}
	}
	
	public void SetDestShip(int v, int h, int i) {
		tableDestroyer[i].setShipPos(v, h);
	}
	
	public int GetDestV(int i) {
		return tableDestroyer[i].GetDestV();
	}
	
	public int GetDestH(int i) {
		return tableDestroyer[i].GetDestH();
	}
	
	public int GetDestShipHP(int i) {
		return tableDestroyer[i].GetShipHP();
	}
	
	public int GetContador() {
		return contador;
	}
	
	
	public int GetIndice() {
		return indice;
	}
	
	public String toString(int i) {
		return "D[" + tableDestroyer[i].GetShipHP() +"]";
	}
	
	public boolean exists(int i)
	{
		return tableDestroyer[i] != null;
	}
	
	public void reset()
	{
		for(int i = 0; i < indice; i++)
		{
			tableDestroyer[i].reset();
		}
	}
	
	public int buscar(int x, int y)
	{
		int i = -1;
		for(int j = 0; j < indice && i == -1; j++)
		{
			if(tableDestroyer[i].GetDestH() == x && tableDestroyer[i].GetDestV() == y)
			{
				i = j;
			}
		}
		return i;
	}
}
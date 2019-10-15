
public class RegularShipList{
	public static final int REG_TAM = 8;
	private int contador = 0;
	private int indice = 0;
	
	//RegularShip myObject = new RegularShip();

	RegularShip[] tableRegular = new RegularShip[REG_TAM];
	
	public void initialize(int n){
		indice = n; contador = n;
		for(int i = 0; i < n; i++) {
			tableRegular[i] = new RegularShip();
		}
	}
	
	public void SetRegShip(int v, int h, int i) {
		tableRegular[i].setShipPos(v, h);
	}
	
	public int GetRegV(int i) {
		return tableRegular[i].GetRegV();
	}
	
	public int GetRegH(int i) {
		return tableRegular[i].GetRegH();
	}
	
	public int GetRegShipHP(int i) {
		return tableRegular[i].GetShipHP();
	}
	
	public void shipHitByUCMShip(int i, int harm) {
		tableRegular[i].shipHitByUCMShip(harm);
		if(tableRegular[i].GetShipHP() == 0) {
			contador--;
		}
	}
	
	public void SetContador(int x)
	{
		contador = x;
	}
	
	public int GetContador() {
		return contador;
	}
	
	
	public int GetIndice() {
		return indice;
	}
	
	public String toString(int i) {
		return "R[" + tableRegular[i].GetShipHP() +"]";
	}
	
	public void reset()
	{
		for(int i = 0; i < contador; i++)
		{
			tableRegular[i].reset();
		}
	}
}


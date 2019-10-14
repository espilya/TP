
public class RegularShipList{
	public static final int REG_TAM = 8;
	private int contador = 0;
	RegularShip myObject = new RegularShip();

	RegularShip[] tableRegular = new RegularShip[REG_TAM];
	
	public void SetRegShip(int x, int y, int i) {
		tableRegular[i].setShipPos(x, y);
	}
	
	public int GetRegX(int i) {
		return tableRegular[i].GetRegX();
	}
	
	public int GetRegY(int i) {
		return tableRegular[i].GetRegY();
	}
	
	public int GetRegShipHP(int i) {
		return tableRegular[i].GetShipHP();
	}
	
	public void shipHitByUCMShip(int i, int harm) {
		tableRegular[i].shipHitByUCMShip(harm);
	}
	
	public void SetContador(int x)
	{
		contador = x;
	}
	
	public int GetContador() {
		return contador;
	}
	
	public String toString(int i) {
		return "O[" + tableRegular[i].GetShipHP() +"]";
	}
	
	public void reset()
	{
		for(int i = 0; i < contador; i++)
		{
			tableRegular[i].reset();
		}
	}
}


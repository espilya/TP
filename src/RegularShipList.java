
public class RegularShipList{
	public static final int REG_TAM = 8;
	private int contador = 0;
	RegularShip myObject = new RegularShip();

	RegularShip[] tableRegular = new RegularShip[REG_TAM];
	
	public void SetRegShip(int x, int y, int i) {
		tableRegular[i].setShipPos(x, y);
	}
	
	public int GetRegShipPos(int x, int y, int i) {
		return tableRegular[i].GetShipPos(x, y);
	}
	
	public int GetDestShipHP(int x, int y, int i) {
		return tableRegular[i].GetShipHP(x, y);
	}
	
}


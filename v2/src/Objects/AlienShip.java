package Objects;
public abstract class AlienShip extends EnemyShip{

	public AlienShip(int v, int h) {
		super(v, h);
	}


	public static boolean haveLanded() {
		
		if("naves" == "pos[0]") {
			return false;
		}
		else
			return false;
	}

	public static boolean allDead() {
		if("{for in AllAliens.hp} " == "0") {
			return false;
		}
		else
			return false;
	}

}
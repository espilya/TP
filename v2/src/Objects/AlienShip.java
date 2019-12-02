package Objects;
public abstract class AlienShip extends EnemyShip{

	public AlienShip(int v, int h) {
		super(v, h);
	}

	public static boolean haveLanded() {
		return false;
	}

	public static boolean allDead() {
		return false;
	}

}
package Objects;

import logic.Game;

public abstract class AlienShip extends EnemyShip{

	public AlienShip(String t, Game g) {
		super(t, g);
	}


	public static boolean haveLanded() {
		

			return false;
	}

	public static boolean allDead() {

			return false;
	}

}
package Objects;

import logic.Game;

public abstract class AlienShip extends EnemyShip{

	public AlienShip(String t, Game g) {
		super(t, g);
	}


	public static boolean haveLanded() {
		
		if("naves" == "pos[0]") {
			return false;
		}
		else
			return false;
	}

	public static boolean allDead() {
		if("{for in AllAliens.hp} " == "0")
			return false;
		else
			return false;
	}

}
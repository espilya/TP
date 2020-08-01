package printer;

import logic.Game;

public class Stringifier extends GamePrinter {

	public Stringifier() {}

	public String toString(Game game) {
//		

//		La tercera línea dará información sobre el Game: G;totalCycles
//		La cuarta línea dará información sobre el Level: L;level
//		Después, en cada línea, daremos información sobre los objetos de juego que estén en
//		el tablero:
//		• Ovni: O;x;y;live
//		• Regular alien: R;x,y;live;cyclesNextAlienMove;dir
//		• Destroyer alien: D;x,y;live;cyclesNextAlienMove;dir
//		• Explosive alien: E;x,y;live;cyclesNextAlienMove;dir
//		• Bomb: B;x,y
//		• Missile: M;x,y
//		• Supermissile: X;x,y
//		• UCMShip (player): P;x,y;live;points;hasShock;superMissiles4

		String res = "";
		String aux;
		res += "— Space Invaders v2.0 —\n\n";
		res += "G;" + game.getnOfCycles() + '\n';
		res += "L;" + game.getLevel();
		res += '\n';
		for (int i = 0; i < game.GetNumRows(); i++) {
			for (int j = 0; j < game.GetNumCols(); j++) {
				res += game.stringify(i, j);
			}
		}
		return res;
	}

	@Override
	public void setGame(Game game) {
		this.game = game;
	}
}
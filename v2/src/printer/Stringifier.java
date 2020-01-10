package printer;

import logic.Game;
import stringUtils.MyStringUtils;

public class Stringifier extends GamePrinter {

	public Stringifier(Game game) {
		// TODO Auto-generated constructor stub
	}

	public Stringifier() {
		// TODO Auto-generated constructor stub
	}

	public String toString(Game game) {
		String res = "";
		String aux;
//	La primera línea será siempre — Space Invaders v2.0 —
//	La segunda línea es una línea en blanco
//	La tercera línea dará información sobre el Game: G;totalCycles
//	La cuarta línea dará información sobre el Level: L;level
//	Después, en cada línea, daremos información sobre los objetos de juego que estén en
//	el tablero:
//	• Ovni: O;x;y;live
//	• Regular alien: R;x,y;live;cyclesNextAlienMove;dir
//	• Destroyer alien: D;x,y;live;cyclesNextAlienMove;dir
//	• Explosive alien: E;x,y;live;cyclesNextAlienMove;dir
//	• Bomb: B;x,y
//	• Missile: M;x,y
//	• Supermissile: X;x,y
//	• UCMShip (player): P;x,y;live;points;superpower;missiles
		res += "— Space Invaders v2.0 —\n\n";
		res += game.getnOfCycles() + '\n';
		res += game.getLevel();
		res += '\n';
		for (int i = 0; i < game.GetNumRows(); i++) {
			for (int j = 0; j < game.GetNumCols(); j++) {
				aux = game.toString(i, j);
				if(aux != "")
				if (aux.charAt(0) == 'O') {
					res += "O" + ";" + i + "," + j + aux.charAt(2);
				} else if (aux.charAt(0) == '^') {
					res += "^__^;" + i + "," + j + ";" + game.getUCMLife() + ";" + game.GetPoints();
					if (game.shockWave())
						res += "1;";
					else
						res += "0;";
					res += game.getSuperMisil();
				} else if (aux.charAt(0) == 'o') {
					res += "M" + ";" + i + "," + j;
				} else if (aux.charAt(0) == '<') {
					res += "X" + ";" + i + "," + j;
				} else if (aux.charAt(0) == '.') {
					res += "B" + ";" + i + "," + j;
				}

				else {
					res += aux.charAt(0) + ";" + i + "," + j + ";" + aux.charAt(2);
				}
				res += "\n";
			}
		}

		return res;
	}

	@Override
	protected void setGame(Game game) {
		// TODO Auto-generated method stub

	}
}
package printer;

import logic.Game;

public abstract class GamePrinter {

	protected Game game;

	public abstract String toString(Game Game);

	//public static void commandPrinter(String str) {
	//	System.out.println(str);
	//}

	public abstract void setGame(Game game);
	

}
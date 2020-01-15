package printer;

import logic.Game;

public abstract class GamePrinter {

	public abstract String toString(Game Game);

	public static void commandPrinter(String str) {
		System.out.println(str);
	}

	protected abstract void setGame(Game game);

}
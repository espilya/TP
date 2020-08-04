package printer;

import logic.Controller;
import logic.Game;

public class PrinterGenerator {

	static Game game;

	public PrinterGenerator(Game game) {
		this.game = game;
	}

	public enum PrinterTypes {
		BOARDPRINTER("boardprinter", "prints the game formatted as a board of dimension: ",
				new BoardPrinter(),
		STRINGIFIER("stringifier", "prints the game as plain text", new Stringifier());

		private String printerName;
		private String helpText;
		private GamePrinter printerObject;

		private PrinterTypes(String name, String text, GamePrinter printer) {
			printerName = name;
			helpText = text;
			printerObject = printer;
		}

		public static String printerHelp(Game game) {
			String helpString = "";
			for (PrinterTypes printer : PrinterTypes.values())
				helpString += String.format(" %s : %s %s %n", printer.printerName, printer.helpText,
						(printer == BOARDPRINTER ? game.GetNumCols() + " x " + game.GetNumRows() : ""));
			return helpString;
		}

		public GamePrinter getObject(Game game) {
			printerObject.setGame(game);
			return printerObject;
		}
	}
}

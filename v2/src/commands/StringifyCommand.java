package commands;

import logic.Game;
import printer.GamePrinter;
import printer.PrinterTypes;


public class StringifyCommand extends NoParamsCommand {

	private final static String help = "Serialization";
	private final static String name = "stringify";
	private final static String details = "s[T]ringify";
	private final static String shortCut = "t";
	GamePrinter printer;

	public StringifyCommand() {
		super(name, shortCut, details, help);
	}

	public boolean execute(Game game) {
		printer = PrinterTypes.STRINGIFIER.getObject(game);
		System.out.println(printer.toString(game));
		return false;
	}

}
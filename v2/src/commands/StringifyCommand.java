package commands;

import logic.Game;
import printer.GamePrinter;
import printer.PrinterGenerator.PrinterTypes;
import printer.Stringifier;

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

//	public Command parse(String[] commandWords) {
//		if (commandWords.length == 1 && matchCommandName(commandWords[0]))
////			return new StringifyCommand();
//			return this;
//		else
//			return null;
//	}

}
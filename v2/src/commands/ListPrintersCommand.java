package commands;

import logic.Game;
import printer.GamePrinter;
import printer.PrinterGenerator.PrinterTypes;

public class ListPrintersCommand extends Command {

	private final static String help = "Prints the list of available printers.\\n";
	private final static String name = "listPrinters";
	private final static String details = "[P]rinters";
	private final static String shortCut = "p";

	public ListPrintersCommand() {
		super(name, shortCut, details, help);
	}

	@Override
	public boolean execute(Game game) {

		GamePrinter.commandPrinter(PrinterTypes.printerHelp(game));

		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if (commandWords.length == 1 && commandWords[0].equals(name) || commandWords[0].equals(shortCut))
			return new ListCommand();
		else
			return null;
	}

}
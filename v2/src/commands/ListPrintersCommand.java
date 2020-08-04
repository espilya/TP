package commands;

import logic.Game;
import printer.PrinterTypes;

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

		System.out.println(PrinterTypes.printerHelp(game));
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if (commandWords.length == 1 && matchCommandName(commandWords[0]))
//			return new ListCommand();
			return this;
		else
			return null;
	}

}
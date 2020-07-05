package commands;

import exceptions.CommandExecuteException;
import logic.Game;
import printer.GamePrinter;

public class BuySuperMisilCommand extends Command {

	private final static String help = "Buy a supermissile";
	private final static String name = "buysupermisil";
	private final static String details = "[B]uy[S]upermissile";
	private final static String shortCut = "bs";

	public BuySuperMisilCommand() {
		super(name, shortCut, details, help);
	}

	public boolean execute(Game game) throws CommandExecuteException {
		if (game.buySuperMisil())
			GamePrinter.commandPrinter("SuperMisil comprado con exito \n");
		return false;
	}

	public Command parse(String[] commandWords) {
		if (commandWords.length == 1 && (commandWords[0].equals(name) || commandWords[0].equals(shortCut)))
			return new BuySuperMisilCommand();
		else
			return null;
	}

}
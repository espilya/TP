package commands;

import logic.Game;
import printer.GamePrinter;

public class NoneCommand extends Command {

	private final static String help = "None command";
	private final static String name = "none";
	private final static String details = "[N]one";
	private final static String shortCut = "n";

	public NoneCommand() {
		super(name, shortCut, details, help);
	}

	public boolean execute(Game game) {
		GamePrinter.commandPrinter("You've decided to wait..\n\n\n\n");
		game.update();
		return true;
	}

	public Command parse(String[] commandWords) {
		if (commandWords.length == 1 && (commandWords[0].equals(name) || commandWords[0].equals(shortCut)))
			return new NoneCommand();
		else
			return null;
	}

}

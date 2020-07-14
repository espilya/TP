package commands;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import printer.GamePrinter;
import logic.Game;

public abstract class Command {
	protected final String name;
	protected final String shortCut;
	private final String details;
	private final String help;

	protected final String IncorrectNumArgsMsg = "Incorrect number of arguments";
	protected final String IncorrectArgsMsg = "Incorrect argument format";

	public Command(String name, String shortCut, String details, String help) {
		this.name = name;
		this.shortCut = shortCut;
		this.details = details;
		this.help = help;
	}

	public abstract boolean execute(Game game) throws CommandExecuteException;

	public abstract Command parse(String[] commandWords);

	protected boolean matchCommandName(String name) {
		return this.shortCut.equalsIgnoreCase(name) || this.name.equalsIgnoreCase(name);
	}

	public String helpText() {
		return details + " : " + help + "\n";
	}
}
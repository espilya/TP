package commands;

import exceptions.CommandExecuteException;
import logic.Game;

public abstract class NoParamsCommand extends Command {

	public NoParamsCommand(String name, String shortCut, String details, String help) {
		super(name, shortCut, details, help);
	}

	public abstract boolean execute(Game game) throws CommandExecuteException;

	@Override
	public Command parse(String[] commandWords) {
		if (commandWords.length == 1 && matchCommandName(commandWords[0]))
			return this;
		else
			return null;
	}

}

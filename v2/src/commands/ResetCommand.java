package commands;

import logic.Game;

public class ResetCommand extends Command {

	private final static String help = "Starts a new game";
	private final static String name = "reset";
	private final static String details = "[R]eset";
	private final static String shortCut = "r";

	public ResetCommand() {
		super(name, shortCut, details, help);
	}

	@Override
	public boolean execute(Game game) {
		game.reset();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		if (commandWords.length == 1 && matchCommandName(commandWords[0]))
//			return new ResetCommand();
			return this;
		else
			return null;
	}

}
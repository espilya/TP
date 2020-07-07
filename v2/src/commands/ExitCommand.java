package commands;

import logic.Game;

public class ExitCommand extends Command {
	private final static String help = "Terminates the program";
	private final static String name = "exit";
	private final static String details = "[E]xit";
	private final static String shortCut = "e";

	public ExitCommand() {
		super(name, shortCut, details, help);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		game.SetExit(true);
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if (commandWords.length == 1 && matchCommandName(commandWords[0]))
			return new ExitCommand();
		else
			return null;
	}

}
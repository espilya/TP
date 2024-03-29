package commands;

import logic.Game;

public class ListCommand extends Command {

	private final static String help = "Prints the list of available ships";
	private final static String name = "list";
	private final static String details = "[L]ist";
	private final static String shortCut = "l";

	public ListCommand() {
		super(name, shortCut, details, help);
	}

	@Override
	public boolean execute(Game game) {
		System.out.println(game.List());
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
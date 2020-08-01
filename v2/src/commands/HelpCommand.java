package commands;

import logic.Game;

public class HelpCommand extends Command {

	private final static String help = "Prints this help message";
	private final static String name = "help";
	private final static String details = "[H]elp";
	private final static String shortCut = "h";

	public HelpCommand() {
		super(name, shortCut, details, help);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		// GamePrinter.commandPrinter();
		System.out.println(CommandGenerator.commandHelp());
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if (commandWords.length == 1 && matchCommandName(commandWords[0]))
//			return new HelpCommand();
			return this;
		else
			return null;
	}

}
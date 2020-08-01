package commands;

import logic.Game;

public class NoneCommand extends Command {

	private final static String help = "None command";
	private final static String name = "none";
	private final static String details = "[N]one";
	private final static String shortCut = "n";

	public NoneCommand() {
		super(name, shortCut, details, help);
	}

	public boolean execute(Game game) {
		System.out.println("You decided to wait..\n\n\n\n");
		game.update();
		return true;
	}

	public Command parse(String[] commandWords) {
		if (commandWords.length == 1 && matchCommandName(commandWords[0]))
//			return new NoneCommand();
			return this;
		else
			return null;
	}

}

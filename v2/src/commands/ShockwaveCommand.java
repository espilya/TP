package commands;

import exceptions.CommandExecuteException;
import logic.Game;

public class ShockwaveCommand extends Command {

	private final static String help = "UCM-Ship releases a shock wave";
	private final static String name = "shockwave";
	private final static String details = "shock[W]ave";
	private final static String shortCut = "w";

	public ShockwaveCommand() {
		super(name, shortCut, details, help);
		// TODO Auto-generated constructor stub
	}

	public boolean execute(Game game) throws CommandExecuteException {
		boolean shock = game.shockWave();
//		if(!shock)
//			throw new CommandExecuteException("'ShockWave' No dispones de armas tipo ShockWave.");
		return shock;
	}

	public Command parse(String[] commandWords) {
		if (commandWords.length == 1 && matchCommandName(commandWords[0]))
			//return new ShockwaveCommand();
			return this;
		else
			return null;
	}
}
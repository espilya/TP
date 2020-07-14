package commands;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import logic.Game;

public class MoveCommand extends Command {

	private final static String help = "Moves UCM-Ship to the indicated direction";
	private final static String name = "move";
	private final static String details = "[M]ove <left|right><1|2>";
	private final static String shortCut = "m";

	private int dir;
	private int step;

	public MoveCommand() {
		super(name, shortCut, details, help);
	}

	public MoveCommand(int a, int b) {
		super(name, shortCut, details, help);
		dir = a;
		step = b;
	}

	private boolean checkMov(String[] commandWords)  { // move <left|right><1|2>

		boolean ok1 = false;
		boolean ok2 = false;
		if (commandWords[1].equals("left") || commandWords[1].equals("l")) {
			dir = -1;
			ok2 = true;
		} else if (commandWords[1].equals("right") || commandWords[1].equals("r")) {
			dir = 1;
			ok2 = true;
		}
		if (commandWords[2].equalsIgnoreCase("1")) {
			step = 1;
			ok1 = true;
		} else if (commandWords[2].equalsIgnoreCase("2")) {
			step = 2;
			ok1 = true;
		}

		return ok1 && ok2;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		game.move(dir * step);
		return true;
	}

	@Override
	public Command parse(String[] commandWords){
		if (commandWords.length == 3 && matchCommandName(commandWords[0]) && checkMov(commandWords))
			return new MoveCommand(dir, step);
		else
			return null;
	}

}

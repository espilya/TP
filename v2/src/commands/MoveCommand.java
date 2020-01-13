package commands;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import logic.Game;

public class MoveCommand extends Command {

	private final static String help = "Moves UCM-Ship to the indicated direction.\\n";
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

	private boolean checkMov(String[] commandWords) throws CommandParseException { // move <left|right><1|2>

		boolean ok1 = false;
		boolean ok2 = false;

		if (commandWords.length == 1)
			throw new CommandParseException("'Insuficientes argumentos para mover' :=> Falta argumento de dirreccion");

		if (commandWords[1].equals("left") || commandWords[1].equals("l")) {
			dir = -1;
			ok2 = true;
		} else if (commandWords[1].equals("right") || commandWords[1].equals("r")) {
			dir = 1;
			ok2 = true;
		} else
			throw new CommandParseException("'Dirreccion para mover'");

		if (commandWords.length == 2)
			throw new CommandParseException(
					"'Insuficientes argumentos para mover' :=> Falta argumento cantidad de casillas a mover");

		if (commandWords[2].equalsIgnoreCase("1")) {
			step = 1;
			ok1 = true;
		} else if (commandWords[2].equalsIgnoreCase("2")) {
			step = 2;
			ok1 = true;
		} else
			throw new CommandParseException("'Casillas para mover'");

		return ok1 && ok2;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		
		game.move(dir * step);
//			throw new CommandExecuteException("'Movimiento' UCMShip se saldra del zona de ataque de aliens y no podra defender la tierra.");
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		boolean ok = false;
		try {
			ok = (commandWords[0].equals(name) || commandWords[0].equals(shortCut)) && checkMov(commandWords);
		} catch (CommandParseException e) {
			throw new CommandParseException("Exception: " + e);
		}

		if (commandWords.length == 3 && ok)
			return new MoveCommand(dir, step);
		else
			return null;

	}

}
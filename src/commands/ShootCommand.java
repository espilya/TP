package commands;
import exceptions.CommandExecuteException;
import logic.Game;


public class ShootCommand extends Command{

	private final static String help = "UCM-Ship shots a misil.\n";
	private final static String name = "shoot";
	private final static String details = "[S]hoot";
	private final static String shortCut = "s";

		public ShootCommand() {
			super(name, shortCut, details, help);	
		}


	public boolean execute(Game game) throws CommandExecuteException {
		boolean shoot = game.shootMissile(false);
//		if(!shoot)
//			throw new CommandExecuteException("'Disparar misil' Ya existe un misil. Se economico!");
		return shoot;
	}


	public Command parse(String[] commandWords) {
		if(commandWords.length == 1 && (commandWords[0].equals(name) || commandWords[0].equals(shortCut)))
			return new ShootCommand();
		else
			return null;
	}
	
}
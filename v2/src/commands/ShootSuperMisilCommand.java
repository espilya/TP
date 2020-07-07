package commands;
import exceptions.CommandExecuteException;
import logic.Game;


public class ShootSuperMisilCommand extends Command{

	private final static String help = "UCM-Ship shots supermissile";
	private final static String name = "shootsupermisil";
	private final static String details = "[S]hoot[S]upermissile";
	private final static String shortCut = "ss";

		public ShootSuperMisilCommand() {
			super(name, shortCut, details, help);	
		}


	public boolean execute(Game game) throws CommandExecuteException {
		boolean shoot = game.shootMissile(true);
//		if(!shoot)
//			throw new CommandExecuteException("'Disparar supermisil' No dispones de supermissil");
		return shoot;
	}


	public Command parse(String[] commandWords) {
		if (commandWords.length == 1 && matchCommandName(commandWords[0]))
			return new ShootSuperMisilCommand();
		else
			return null;
	}
}
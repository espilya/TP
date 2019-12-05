package commands;
import logic.Game;


public class ShootSuperMisilCommand extends Command{

	private final static String help = "UCM-Ship shots a supermisil.\n";
	private final static String name = "shootsupermisil";
	private final static String details = "[S]hoot[S]upermisil";
	private final static String shortCut = "ss";

		public ShootSuperMisilCommand() {
			super(name, shortCut, details, help);	
		}


	public boolean execute(Game game) {
		return game.shootMissile(true);
	}


	public Command parse(String[] commandWords) {
		if(commandWords.length == 1 && (commandWords[0].equals(name) || commandWords[0].equals(shortCut)))
			return this;
		else
			return null;
	}
	
	public String helpText()
	{
		return details + " : " + help + "\n";
	}
}
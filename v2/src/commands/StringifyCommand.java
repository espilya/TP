package commands;
import logic.Game;


public class StringifyCommand extends Command{
	

	private final static String help = "UCM-Ship shots a misil.\n";
	private final static String name = "shoot";
	private final static String details = "[S]hoot";
	private final static String shortCut = "s";

		public StringifyCommand() {
			super(name, shortCut, details, help);	
		}


	public boolean execute(Game game) {
		return game.shootMissile();
	}


	public Command parse(String[] commandWords) {
		if(commandWords.length == 1 && commandWords[0].equals(name) || commandWords[0].equals(shortCut))
			return this;
		else
			return null;
	}
	
	public String helpText()
	{
		return details + " : " + help + "\n";
	}
}
package commands;
import logic.Game;


public class ShockwaveCommand extends Command{

	private final static String help = "UCM-Ship releases a shock wave.\n";
	private final static String name = "shockwave";
	private final static String details = "shock[W]ave";
	private final static String shortCut = "w";

		public ShockwaveCommand() {
			super(name, shortCut, details, help);	
			// TODO Auto-generated constructor stub
		}

	@Override
	public boolean execute(Game game) {
		game.shockwave();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(commandWords[0].equals(name) || commandWords[0].equals(shortCut))
			return this;
		else
			return null;
	}
	
	public String helpText()
	{
		return details + " : " + help + "\n";
	}
}
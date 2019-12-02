

public class ShockwaveCommand extends Command{

	private final static String help = "shock[W]ave: UCM-Ship releases a shock wave.\n";
	private final static String name = "shockwave";
	private final static String details = "";
	private final static String shortCut = "";

		public ShockwaveCommand() {
			super(name, shortCut, details, help);	
			// TODO Auto-generated constructor stub
		}

	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		// TODO Auto-generated method stub
		if(commandWords[0] == name)
			return this;
		else
			return null;
	}
	
	public static String Help()
	{
		return help;
	}
}
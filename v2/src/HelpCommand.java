

public class HelpCommand extends Command{

	private final static String help = "[H]elp: Prints this help message.\n" ;
	private final static String name = "exit";
	private final static String details = "";
	private final static String shortCut = "";

		public HelpCommand() {
			super(name, shortCut, details, help);	
			// TODO Auto-generated constructor stub
		}

	@Override
	public boolean execute(Game game) {
		CommandGenerator.commandHelp();
		return true;
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
		return "[H]elp: Prints this help message.\n";
	}
}
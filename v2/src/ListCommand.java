

public class ListCommand extends Command{

	private final static String help = "[L]ist: Prints the list of available ships.\\n";
	private final static String name = "list";
	private final static String details = "";
	private final static String shortCut = "";

		public ListCommand() {
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
		return null;
	}
	
	public static String Help()
	{
		return help;
	}
}
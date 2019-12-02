

public class ResetCommand extends Command{
	
private final static String help = "[R]eset: Starts a new game.\n";
private final static String name = "reset";
private final static String details = "";
private final static String shortCut = "";

	public ResetCommand() {
		super(name, shortCut, details, help);	
	}

	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public  Command parse(String[] commandWords) {
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
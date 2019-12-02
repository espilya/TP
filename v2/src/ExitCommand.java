

public class ExitCommand extends Command{
private final static String help = "[E]xit: Terminates the program.\n";
private final static String name = "exit";
private final static String details = "";
private final static String shortCut = "";

	public ExitCommand() {
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
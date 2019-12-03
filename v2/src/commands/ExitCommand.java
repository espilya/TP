package commands;
import logic.Game;


public class ExitCommand extends Command{
private final static String help = "Terminates the program.\n";
private final static String name = "exit";
private final static String details = "[E]xit";
private final static String shortCut = "e";

	public ExitCommand() {
		super(name, shortCut, details, help);	
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		game.SetExit();
		return false;
	}

	@Override
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
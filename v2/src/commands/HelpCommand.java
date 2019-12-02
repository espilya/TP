package commands;
import logic.Game;


public class HelpCommand extends Command{

	private final static String help = "Prints this help message.\n" ;
	private final static String name = "exit";
	private final static String details = "[H]elp";
	private final static String shortCut = "h";

		public HelpCommand() {
			super(name, shortCut, details, help);	
			// TODO Auto-generated constructor stub
		}

	@Override
	public boolean execute(Game game) {
		System.out.print(CommandGenerator.commandHelp());
		return true;
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
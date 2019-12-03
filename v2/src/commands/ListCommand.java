package commands;
import logic.Game;


public class ListCommand extends Command{

	private final static String help = "Prints the list of available ships.\\n";
	private final static String name = "list";
	private final static String details = "[L]ist";
	private final static String shortCut = "l";

		public ListCommand() {
			super(name, shortCut, details, help);	
		}

	@Override
	public boolean execute(Game game) {
		String aux = "[R]egular Ship: Points - 5, Harm - 0, Shield - 3 \n" +
				   "[D]estroyer Ship: Points - 10, Harm - 1, Shield - 1 \n" +
				   "[O]vni: Points - 25, Harm - 0, Shield - 1 \n" + 
				   "^__^: Harm: 1 - Shield: 3\n";
		System.out.print(game.List());
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
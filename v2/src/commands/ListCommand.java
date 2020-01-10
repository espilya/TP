package commands;
import logic.Game;
import printer.GamePrinter;


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
		GamePrinter.commandPrinter(game.List());
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(commandWords.length == 1 && commandWords[0].equals(name) || commandWords[0].equals(shortCut))
			return new ListCommand();
		else
			return null;
	}
	

}
package commands;
import logic.Game;
import printer.GamePrinter;
import printer.Stringifier;


public class StringifyCommand extends Command{
	

	private final static String help = "";
	private final static String name = "";
	private final static String details = "";
	private final static String shortCut = "";
	Stringifier printer;

		public StringifyCommand() {
			super(name, shortCut, details, help);	
		}


	public boolean execute(Game game) {
		printer = new Stringifier(game);
//		GamePrinter.commandPrinter(printer);
		System.out.println(printer);
		return false;
	}


	public Command parse(String[] commandWords) {
		if(commandWords.length == 1 && commandWords[0].equals(name) || commandWords[0].equals(shortCut))
			return new StringifyCommand();
		else
			return null;
	}
	
}
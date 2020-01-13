package commands;
import logic.Game;
import printer.GamePrinter;
import printer.Stringifier;


public class SaveCommand extends Command{
	

	private final static String help = "Save the hame";
	private final static String name = "save";
	private final static String details = "sa[V]e";
	private final static String shortCut = "v";
	Stringifier printer;

		public SaveCommand() {
			super(name, shortCut, details, help);	
		}


	public boolean execute(Game game) {

		return false;
	}


	public Command parse(String[] commandWords) {
		if(commandWords.length == 1 && commandWords[0].equals(name) || commandWords[0].equals(shortCut))
			return new StringifyCommand();
		else
			return null;
	}
	
}
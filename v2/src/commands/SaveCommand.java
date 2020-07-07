package commands;

import files.FileSave;
import logic.Game;
import printer.GamePrinter;
import printer.Stringifier;

public class SaveCommand extends Command {

	private final static String help = "Save the game";
	private final static String name = "save";
	private final static String details = "sa[V]e";
	private final static String shortCut = "v";
	private String archivo = "data"; 
	Stringifier printer;

	public SaveCommand() {
		super(name, shortCut, details, help);
	}
	
	public SaveCommand(String x) {
		super(name, shortCut, details, help);
		archivo = x;
	}

	public boolean execute(Game game) {
		GamePrinter.commandPrinter("Game successfully saved in file '"+ archivo +".dat' \nUse the load command to reload it.");
		printer = new Stringifier(game);
		String fileName = archivo + ".dat";
		FileSave.Save(fileName, printer.toString(game));

		return false;
	}

	public Command parse(String[] commandWords) {
		if (commandWords.length == 2 && matchCommandName(commandWords[0])) {
			if(commandWords.length == 1)
				return new SaveCommand(archivo);
			else
				return new SaveCommand(commandWords[1]);
			
		}
		else
			return null;
	}

}
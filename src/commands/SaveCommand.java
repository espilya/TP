package commands;

import files.FileSave;
import logic.Game;
import printer.Stringifier;

public class SaveCommand extends Command {

	private final static String help = "Save the hame";
	private final static String name = "save";
	private final static String details = "sa[V]e";
	private final static String shortCut = "v";
	private String archivo; 
	Stringifier printer;

	public SaveCommand() {
		super(name, shortCut, details, help);
	}
	
	public SaveCommand(String x) {
		super(name, shortCut, details, help);
		archivo = x;
	}

	public boolean execute(Game game) {
		// "Game successfully saved in file <nombre_proporcionado_por_el_usuario>.dat.
		// Use the load command to reload it"
		printer = new Stringifier(game);
		String fileName = archivo + ".dat";
		FileSave.Save(fileName, printer.toString(game));

		return false;
	}

	public Command parse(String[] commandWords) {
		if (commandWords.length == 2 && commandWords[0].equals(name) || commandWords[0].equals(shortCut))
			return new SaveCommand(commandWords[1]);
		else
			return null;
	}

}
package commands;

public class CommandGenerator {

	private static Command[] availableCommands = { 
			new ListCommand(), 
			new HelpCommand(), 
			new ResetCommand(),
			new ExitCommand(), 
			new UpdateCommand(), 
			new MoveCommand(), 
			new ShockwaveCommand(), 
			new ShootCommand(),
			new BuySuperMisilCommand(), 
			new ShootSuperMisilCommand(), 
			new StringifyCommand(),
			new SaveCommand(),
			new ListPrintersCommand(),
			new NoneCommand()
	};

	public static Command parseCommand(String[] commandWords)  {
		Command aux = null;
		if (commandWords[0].length() != 0) {
			for (int i = 0; i < availableCommands.length && aux == null; i++) {
				aux = availableCommands[i].parse(commandWords);
			}
		} else
			aux = new NoneCommand();
		return aux;
	}

	public static String commandHelp() {
		String aux = "";
		for (int i = 0; i < availableCommands.length; i++) {
			aux += availableCommands[i].helpText();
		}
		return aux;
	}
}

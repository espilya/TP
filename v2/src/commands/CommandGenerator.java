package commands;

import exceptions.CommandParseException;

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
			new NoneCommand()
	};

	public static Command parseCommand(String[] commandWords) throws CommandParseException {
		Command aux = null;
		if (commandWords[0].length() != 0) {
			for (int i = 0; i < availableCommands.length && aux == null; i++) {
				aux = availableCommands[i].parse(commandWords);
			}
				if(aux==availableCommands[5]) {// availableCommands[5] == moveCommand
					if (commandWords.length == 1)
						throw new CommandParseException("'Insuficientes argumentos para mover' :=> Falta argumento de dirreccion");
					
					else if (commandWords.length == 2)
						throw new CommandParseException("'Insuficientes argumentos para mover' :=> Falta argumento cantidad de casillas a mover");
					
					else if (!(commandWords[1].equals("left") || commandWords[1].equals("l") || commandWords[1].equals("right") || commandWords[1].equals("r"))) 
						throw new CommandParseException("'Dirreccion para mover'");
					
					else if (!(commandWords[2].equalsIgnoreCase("1") || commandWords[2].equalsIgnoreCase("2"))) 
						throw new CommandParseException("'Casillas para mover'");
					
				}
			
			else if(aux==null) {
				throw new CommandParseException(
						"Comando invalido.");
			}

		} 
		else
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
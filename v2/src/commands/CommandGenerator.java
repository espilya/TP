package commands;

import exceptions.CommandParseException;

public class CommandGenerator{

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
			new NoneCommand()//,
//			new StringifyCommand(),
//			new FalseCommand()//comando incorrecto
		};
	
	public static Command parseCommand(String[] commandWords) throws CommandParseException //throws CommandParseException
	{
		Command aux = null;
	
		for(int i = 0; i < availableCommands.length && aux == null; i++)
		{
			aux = availableCommands[i].parse(commandWords);
		}
		if(aux == null)
			//aux = availableCommands[11];
			throw new CommandParseException("'Comando incorreto o desconocido'");
		return aux;
	}
	
	public static String commandHelp()
	{
		String aux = "";
		for(int i =0; i < availableCommands.length; i++)
		{
				aux += availableCommands[i].helpText();
		}
		return aux;
	}
}
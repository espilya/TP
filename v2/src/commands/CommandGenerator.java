package commands;
public class CommandGenerator{

	private static Command[] availableCommands = {
			new ListCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new UpdateCommand(),
			new MoveCommand(),
			new ShockwaveCommand()
		};
	
	public static Command parseCommand(String[] commandWords)
	{
		Command aux = null;
		if(commandWords.length >= 1)
		{
			for(int i = 0; i < commandWords.length; i++)
			{
				commandWords[i].toLowerCase();
			}
			
			for(int i = 0; i < availableCommands.length && aux == null; i++)
			{
				aux = availableCommands[i].parse(commandWords);
			}
		}
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
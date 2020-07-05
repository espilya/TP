package exceptions;


//La segunda se utilizará para tratar las situaciones de error al ejecutar el método
//execute de un comando como, por ejemplo, que la nave del usuario no puede desplazarse en la
//dirección introducida por el usuario.
public class CommandExecuteException extends Exception{
	private static final long serialVersionUID = 1L;
	
	private static final String msg = "ATENCION ==> Error al realizar: ";
	
	public CommandExecuteException() {
		super(msg);
	}
	public CommandExecuteException(String message){
		super(msg + message + '\n');
	}
	
	public CommandExecuteException(String message, Throwable cause){
		super(msg + message + '\n', cause );
	}
	
	public CommandExecuteException(Throwable cause){ 
		super(cause); 
	}
	
	
}
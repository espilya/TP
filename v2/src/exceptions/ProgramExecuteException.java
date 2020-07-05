package exceptions;


//La segunda se utilizará para tratar las situaciones de error al ejecutar el método
//execute de un comando como, por ejemplo, que la nave del usuario no puede desplazarse en la
//dirección introducida por el usuario.
public class ProgramExecuteException extends Exception{
	private static final long serialVersionUID = 1L;
	
	private static final String msg = "Usage ==> Main <EASY|HARD|INSANE> [seed]";
	
	public ProgramExecuteException() {
		super(msg);
	}
	public ProgramExecuteException(String message){
		super(msg + ": " + message + '\n');
	}
	
	public ProgramExecuteException(String message, Throwable cause){
		super(msg + message + '\n', cause );
	}
	
	public ProgramExecuteException(Throwable cause){ 
		super(cause); 
	}
	
}
package exceptions;

public class CommandParseException extends Exception{
	private static final long serialVersionUID = 1L;
	
	private static final String msg = "ATENCION  ==>  Se ha producido un error de parseo: ";

	public CommandParseException() {
		super(msg);
	}
	public CommandParseException(String message){
		super(msg + message + '\n');

	}
	
	public CommandParseException(String message, Throwable cause){
		super(msg + message + '\n', cause);
	}
	
	public CommandParseException(Throwable cause){ 
		super(cause); 
	}
	
}
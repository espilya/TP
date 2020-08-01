package exceptions;

public class UCMShipMoveException extends Exception{
private static final long serialVersionUID = 1L;
	
	private static final String msg = "UCMShipMoveException";
	
	public UCMShipMoveException() {
		super(msg);
	}
	public UCMShipMoveException(String message){
		super(msg + ": " + message + '\n');
	}
	
	public UCMShipMoveException(String message, Throwable cause){
		super(msg + message + '\n', cause );
	}
	
	public UCMShipMoveException(Throwable cause){ 
		super(cause); 
	}
}

package exceptions;

public class MissileInFlightException extends Exception{
	private static final long serialVersionUID = 1L;
	
	private static final String msg = "Usage ==> Main <EASY|HARD|INSANE> [seed]";
	
	public MissileInFlightException() {
		super(msg);
	}
	public MissileInFlightException(String message){
		super(msg + ": " + message + '\n');
	}
	
	public MissileInFlightException(String message, Throwable cause){
		super(msg + message + '\n', cause );
	}
	
	public MissileInFlightException(Throwable cause){ 
		super(cause); 
	}
	
}
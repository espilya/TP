package exceptions;





public class FileContentsException extends Exception{
	private static final long serialVersionUID = 1L;
	
	private static final String msg = "ATENCION  ==>  Se ha producido un error de fichero: ";

	public FileContentsException() {
		super(msg);
	}
	public FileContentsException(String message){
		super(msg + message + '\n');

	}
	
	public FileContentsException(String message, Throwable cause){
		super(msg + message + '\n', cause);
	}
	
	public FileContentsException(Throwable cause){ 
		super(cause); 
	}
}

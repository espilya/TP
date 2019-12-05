package exceptions;

//La primera de ellas tratará los errores producidos al parsear un comando, es decir, aquéllos
//producidos durante la ejecución del método parse, tales como “comando desconocido” o “paráme-
//tros incorrectos”.
public class CommandParseException extends Exception{
	private static final String msg = "ATENCION  ==>  Se ha producido un error de parseo: ";
//	private static final String inputErr = "\t\t  +-------------------------------------+\n"
//											+ "\t\t  |\t  <|> Incorrect Input <|>       |\n" 
//											+ "\t\t  |        \tTry again.              |\n"
//											+ "\t\t  +-------------------------------------+\n";
	public CommandParseException() {
		super(msg);
	}
	public CommandParseException(String message){
		super(msg + message + '\n');
//		if(message.equals("InputError")) {
//			super(inputErr + '\n');
//		}
//		else
//			super(msg + message + '\n');
	}
	
	public CommandParseException(String message, Throwable cause){
		super(msg + message + '\n', cause);
	}
	
	public CommandParseException(Throwable cause){ 
		super(cause); 
	}
	
//	--No entendi como implementar este apartado de los apuntes--
//	Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
//		super(message, cause, enableSuppression, writeableStackTrace)
//	}
}
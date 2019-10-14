import java.util.Random;

/*Main: Es la clase que contiene el m�todo main de la aplicaci�n. En este caso el m�todo
main lee los valores de los par�metros de la aplicaci�n (1, quiz� 2), crea una nueva
partida (objeto de la clase Game), crea un controlador (objeto de la clase Controller)
con dicha partida, e invoca al m�todo run del controlador.*/

public class Main{
	int semilla;
	
	public static double[] GenerateNumbers(long seed, int amount) {
	    double[] randomList = new double[amount];
	    for (int i=0;i<amount;i++) {
	        Random generator = new Random(seed);
	        randomList[i] = Math.abs((double) (generator.nextLong() % 0.001) * 10000);
	        seed--;
	    }
	    return randomList;
	}
	
	public static void main(String[] args){
		//Controller G = new Controller();
		//GamePrinter GP = new GamePrinter(G, G.getBattlefieldSize());
			
		
		double random[] = GenerateNumbers(101, 5);
		int randomA = (int)random[0];
		int randomB = (int)random[1];
		int randomC = (int)random[2];
		int randomD = (int)random[3];
		int randomE = (int)random[4];
		System.out.println(randomA);
		System.out.println(randomB);
		System.out.println(randomC);
		System.out.println(randomD);
		System.out.println(randomE);

		
//			En cada ciclo del juego se realizan secuencialemente las siguientes acciones:
//				1. Draw. Se pinta el tablero y se muestra la informaci�n del juego.
//				2. User command. El usuario puede realizar una acci�n, por ejemplo: 	moverse lateralmente o realizar un disparo. El usuario puede no hacer nada en un ciclo y dejar
//					pasar el tiempo.
//				3. Computer action. El ordenador puede decidir si una nave destructora realiza un
//					disparo o si aparece un ovni (ver m�s adelante) en la primera fila del tablero.
//				4. Update. Se actualizan los objetos que est�n en el tablero.
//				3. Computer action. El ordenador puede decidir si una nave destructora realiza un
//					disparo o si aparece un ovni (ver m�s adelante) en la primera fila del tablero.
			
		switch(args.length)
		{
		case 1:
			//jugar(args[0], num random);
			break;
			
		case 2:
			System.out.println("Your first argument is: " + args[0]);  
			//jugar(args[0], args[1].to number);
			break;
			
		default:
			System.out.println("Error" + "\n" + "Insuficientes argumentos para ejecutar el juego.");
		}
	
	}
	
	private static void jugar()
	{
		//GamePrinter print = new GamePrinter();
		//G.initialize();
		//System.out.println(GP.toString());
		//Controller
	
		//Game
		//Game.update
		//Game
	}
}
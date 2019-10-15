/*Main: Es la clase que contiene el m�todo main de la aplicaci�n. En este caso el m�todo
main lee los valores de los par�metros de la aplicaci�n (1, quiz� 2), crea una nueva
partida (objeto de la clase Game), crea un controlador (objeto de la clase Controller)
con dicha partida, e invoca al m�todo run del controlador.*/

public class Main{
	int semilla;
	static  Game G = new Game();
	static Controller controller = new Controller();
	
	public static void main(String[] args){	
		//GamePrinter GP = new GamePrinter(8, 9);
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
			jugar(args[0], -1);
			break;
			
		case 2:
			jugar(args[0], Integer.parseInt(args[1]));
			break;
			
		default:
			System.out.println("Error" + "\n" + "Numero de argumentos no valido.");
		}
	
	}
	
	private static void jugar(String dif, int seed)
	{
		
		
		GamePrinter GP = new GamePrinter(G.GetNumRows(), G.GetNumCols());
		do
		{
			G.initialize(dif, seed, 8, 9);
			G.Print();
			System.out.println(GP.toString(G));
			while(!G.Win() && !G.Exit() && !G.GameOver())
			{
				controller.run(G, dif, seed);
				if(G.update())
				{
					G.Print();
					System.out.println(GP.toString(G));
				}
			}
			if(G.GameOver())
			{
				G.gameOverPrint();
			}
			else
			{
				if(G.Win())
				{
					G.gameWinPrint();
				}
			}
			
		}while(!G.Exit());
		//System.out.println(GP.toString());
		//Controller
	
		//Game
		//Game.update
		//Game
	}
}
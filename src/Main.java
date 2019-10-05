/*Main: Es la clase que contiene el m�todo main de la aplicaci�n. En este caso el m�todo
main lee los valores de los par�metros de la aplicaci�n (1, quiz� 2), crea una nueva
partida (objeto de la clase Game), crea un controlador (objeto de la clase Controller)
con dicha partida, e invoca al m�todo run del controlador.*/

public class Main{
	public static void main(String[] args){
		Game G = new Game();
		GamePrinter GP = new GamePrinter(G, G.getBattlefieldSize());
			
		

//			En cada ciclo del juego se realizan secuencialemente las siguientes acciones:
//				1. Draw. Se pinta el tablero y se muestra la informaci�n del juego.
//				2. User command. El usuario puede realizar una acci�n, por ejemplo: 	moverse lateralmente o realizar un disparo. El usuario puede no hacer nada en un ciclo y dejar
//					pasar el tiempo.
//				3. Computer action. El ordenador puede decidir si una nave destructora realiza un
//					disparo o si aparece un ovni (ver m�s adelante) en la primera fila del tablero.
//				4. Update. Se actualizan los objetos que est�n en el tablero.
//				3. Computer action. El ordenador puede decidir si una nave destructora realiza un
//					disparo o si aparece un ovni (ver m�s adelante) en la primera fila del tablero.
			
			//GamePrinter print = new GamePrinter();
		G.initialize();
		System.out.println(GP.toString());
			//Controller
		
			//Game
			//Game.update
			//Game
		
	
	}
}
//Encapsula la lógica del juego. Tiene, entre otros, el método update que actualiza el 
//estado de todos los elementos del juego. Contiene una instancia de RegularShipList, una 
//DestroyerShipList y una BombList, entre otras instancias de objetos.

//También mantiene el contador de ciclos y la puntuación del jugador. Entre otros,
//tiene un atributo privado Random rand para genera los valores aleatorios.
//También es posible que alguna de estas clases necesite un atributo en el que almacenan 
//el juego, eso es, una instancia de la clase Game (que será la única en el
//programa) y que, como veremos, contiene la lógica del juego. De este modo, estas
//clases podrán usar los métodos de la clase Game para consultar si pueden hacer o
//no una determinada acción.



public class Game{
//	private int contador; 
//	private int puntuacion;
	public int life;
	public int nOfCycles;
	public int points;
	public int remainingAliens;
	public boolean shockWave;
	
	public static  void game(){
		
	}
	
	
	//private  Random rand;
	private static  void update(){
	
	}
	
	
}
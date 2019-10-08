// Es un clase enumerada con la que se encapsulan los tres niveles de juego.

public class Level{
	
//		   #Regular   #Destroyer #frecDisp  Vel       Ovni
//	easy:        2        2        0.1        3        0.5   
//	hard:        8        2        0.3        2        0.2  
//	insane:      8        4        0.5        1        0.1
	
	enum dificultad{
		easy, hard, insane
		}
	
		//usar el 'level' asi????
	public int getNumberRegularShip(int difficulty) {
		int n;
		if(difficulty == 1)
			n = 1;
		else if(difficulty == 2)
			n = 2;
		else
			n = 3;
		return n;
	}
	
	
}
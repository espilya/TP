package logic;
// Es un clase enumerada con la que se encapsulan los tres niveles de juego.

public class Level{
	
//		   #Regular   #Destroyer #frecDisp  Vel       Ovni
//	easy:        4        2        0.1        3        0.5   
//	hard:        8        2        0.3        2        0.2  
//	insane:      8        4        0.5        1        0.1
	
	public enum dificultad{
		easy, hard, insane
		}
	
	private dificultad difficulty;

	public boolean setDifficulty(String d)
	{
		boolean right = false;
		d.toLowerCase();
		
		switch(d)
		{
		case "easy":
			difficulty = dificultad.easy;
			right = true;
			break;
			
		case "hard":
			difficulty = dificultad.hard;
			right = true;
			break;
			
		case "insane":
			difficulty = dificultad.insane;
			right = true;
			break;
		}
		return right;
	}
	
	public dificultad getDifficulty()
	{
		return difficulty;
	}
	
 	public int getNumberRegularShip() {
		int n = 0;
		switch (difficulty)
		{
		case easy:
			n = 4;
			break;
		
		case hard:	
		case insane:
			n = 8;
			break;
		}
		return n;
	}
	
	public int getNumberDestroyerShip()
	{
		int n = 0;
		switch (difficulty)
		{
		case easy:
		case hard:	
			n = 2;
			break;
			
		case insane:
			n = 4;
			break;
		}
		return n;
	}
	
	public double getFrecDisparo()
	{
		double n = 0;
		switch (difficulty)
		{
		case easy:
			n = 0.1;
			break;
			
		case hard:	
			n = 0.3;
			break;
			
		case insane:
			n = 0.5;
			break;
		}
		return n;
	}
	
	public double getProbOvni()
	{
		double n = 0;
		switch (difficulty)
		{
		case easy:
			n = 0.5;
			break;
			
		case hard:	
			n = 0.2;
			break;
			
		case insane:
			n = 0.1;
			break;
		}
		return n;
	}
	
	public int getVelocidad() {
		int n = 0;
		switch (difficulty)
		{
		case easy:
			n = 3;
			break;
		
		case hard:	
			n = 2;
			break;
			
		case insane:
			n = 1;
			break;
		}
		return n;
	}
	
}
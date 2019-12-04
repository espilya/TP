package logic;

import java.util.Random;

public class Main{
	int semilla;
	static Game G;
	static Level L;
	static Random rand = new Random();
	static Controller controller = new Controller();
	
	
	public static void main(String[] args){	
		switch(args.length)
		{
		case 1:
			jugar(args[0], rand.nextInt());
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
	
		Random rand = new Random(seed);
		L = Level.parse(dif);		
		G = new Game(L, rand);
		GamePrinter GP = new GamePrinter(G.GetNumRows(), G.GetNumCols());
			
			while(!G.isFinished())
			{
				System.out.println(GP.toString(G));
				controller.run(G);
			}
			if(G.Lose()){
				G.gameOverPrint();
				
			}
			else{
				if(G.Win())
				{
					G.gameWinPrint();
				}
			}
			G.reset();
	//		System.out.println("Error" + "\n" + "Argumentos de entrada no validos");
	
	}
}
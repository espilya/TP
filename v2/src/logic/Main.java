package logic;

import java.util.Random;

import exceptions.ProgramExecuteException;

public class Main{
	int semilla;

	static Random rand = new Random();
	static Controller controller = new Controller();
	
	
	public static void main(String[] args) throws ProgramExecuteException{	
		try {
			switch(args.length)
			{
			case 1:
				controller.run(args[0], rand.nextInt());
				break;
				
			case 2:
				controller.run(args[0], Integer.parseInt(args[1]));
				break;
				
			default:
				throw new ProgramExecuteException();
			}
		}
		catch(Error | ProgramExecuteException e ) {
			System.err.println("Exception: " + e + "level must be one of: 'EASY, HARD, INSANE'");
		}
		catch(NumberFormatException e) {
			System.err.println("Exception: " + e + ":= It's not a number. The seed must be a number");
			System.err.println("Usage ==> Main <EASY|HARD|INSANE> [seed]");
		}
	}
	
//	private static void jugar(String dif, int seed)
//	{
//	
//		Random rand = new Random(seed);
//		L = Level.parse(dif);		
//		G = new Game(L, rand);
//		GamePrinter GP = new GamePrinter(G.GetNumRows(), G.GetNumCols());
//			
//			while(!G.isFinished())
//			{
//				System.out.println(G.infoToString());
//				System.out.println(GP.toString(G));
//				
//				controller.run(G);
//			}
//			if(G.Lose()){
//				G.gameOverPrint();
//				
//			}
//			else{
//				if(G.Win())
//				{
//					G.gameWinPrint();
//				}
//			}
//			G.reset();
//	}
}
package logic;
public class Main{
	int semilla;
	static  Game G = new Game();
	static Controller controller = new Controller();
	public static void main(String[] args){	
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
		if(G.initialize(dif, seed, 8, 9))
		{
			while(!controller.Exit())
			{
				G.reset();
				G.Print();
				System.out.println(GP.toString(G));
				while(!G.Win() && !controller.Exit() && !G.GameOver())
				{
					controller.run(G);
					if(controller.Print())
					{
						G.update();
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
			}
		}
		else
		{
			System.out.println("Error" + "\n" + "Argumentos de entrada no validos");
		}
	}
}
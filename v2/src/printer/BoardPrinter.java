package logic;

import stringUtils.MyStringUtils;

public class GamePrinter{
	
	int numRows; 
	int numCols;
	String[][] board;
	final String space = " ";
	
	
	public GamePrinter (int sizeY, int sizeX) {
		this.numRows = sizeY;
		this.numCols = sizeX;	
	}
	
	public void serialPrint(Game game) {
//		La primera línea será siempre — Space Invaders v2.0 —
//		La segunda línea es una línea en blanco
//		La tercera línea dará información sobre el Game: G;totalCycles
//		La cuarta línea dará información sobre el Level: L;level
//		Después, en cada línea, daremos información sobre los objetos de juego que estén en
//		el tablero:
//		• Ovni: O;x;y;live
//		• Regular alien: R;x,y;live;cyclesNextAlienMove;dir
//		• Destroyer alien: D;x,y;live;cyclesNextAlienMove;dir
//		• Explosive alien: E;x,y;live;cyclesNextAlienMove;dir
//		• Bomb: B;x,y
//		• Missile: M;x,y
//		• Supermissile: X;x,y
//		• UCMShip (player): P;x,y;live;points;superpower;missiles
		System.out.println("— Space Invaders v2.0 —");
		System.out.println("");
		System.out.println(game.getnOfCycles());
		System.out.println(game.getLevel());
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numCols; j++) {
				if(game.toString(i, j).charAt(0)=='R') {
					System.out.println("R" + ";" + i+j+game.toString(i, j).charAt(2));
				}
				else if(game.toString(i, j).charAt(0)=='D') {
					System.out.println("D" + ";" + i+j+game.toString(i, j).charAt(2));
				}
				else if(game.toString(i, j).charAt(0)=='E') {
					System.out.println("E" + ";" + i+j+game.toString(i, j).charAt(2));
				}
				else if(game.toString(i, j).charAt(0)=='O') {
					System.out.println("O" + ";" + i+j+game.toString(i, j).charAt(2));
				}
		// 		...
			}
		}

		
		
	}
	

	
	private void encodeGame(Game game) { 
		board = new String[numRows][numCols];
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numCols; j++) {
				board[i][j] = game.toString(i, j);
			}
		}
	}
	
	public String toString(Game game) {
		encodeGame(game);
		int cellSize = 7;
		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";
		String intersect = space;
		String vIntersect = space;
		String hIntersect = "-";
		String corner = space;
		
		String cellDelimiter = MyStringUtils.repeat(hDelimiter, cellSize);
		
		String rowDelimiter = vIntersect + MyStringUtils.repeat(cellDelimiter + intersect, numCols-1) + cellDelimiter + vIntersect;
		String hEdge =  corner + MyStringUtils.repeat(cellDelimiter + hIntersect, numCols-1) + cellDelimiter + corner;
		
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineEdge = String.format("%n%s%s%n", margin, hEdge);
		String lineDelimiter = String.format("%n%s%s%n", margin, rowDelimiter);
		
		StringBuilder str = new StringBuilder();
		
		str.append(lineEdge);
		for(int i = 0; i < numRows; i++) {
				str.append(margin).append(vDelimiter);
				for (int j = 0; j < numCols; j++)
				{
					str.append(MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
				}
				if (i != numRows - 1) str.append(lineDelimiter);
				else str.append(lineEdge);	
		}
		
		return str.toString();
	}
}
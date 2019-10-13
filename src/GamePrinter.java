//package tp.p1.//nombre_de_paquete//;

import util.MyStringUtils;

public class GamePrinter {
	
	int numRows; 
	int numCols;
	String[][] board;
	final String space = " ";
	private Game game = new Game();
	
	
	public GamePrinter (int sizeX, int sizeY) {
		this.numRows = sizeX;
		this.numCols = sizeY;	
		encodeGame();
	}

	
	public void encodeGame() { // asi esta bien, no?
		board = new String[numRows][numCols];
		//for(int i = 0; i < numRows; i++) {
			//for(int j = 0; j < numCols; j++) {
				System.out.println(toString());
			//}
		//}
	}
	
	public String toString() {

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
					board[i][j] = game.toString(i, j);
					str.append(MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
				}
				if (i != numRows - 1) str.append(lineDelimiter);
				else str.append(lineEdge);	
		}
		
		return str.toString();
	}
}
package logic;

import java.util.Random;

import Objects.AlienShip;
import Objects.DestroyerShip;
import Objects.GameObject;
import Objects.GameObjectBoard;
import Objects.Misil;
import Objects.OVNI;
import Objects.RegularShip;
import Objects.Ship;
import Objects.UCMShip;
import Objects.Weapon;
import interfaces.IPlayerController;

public class Game implements IPlayerController{

	private static int numRows = 8; 
	private static int numCols = 9;
	private static double frecDisp;
	private static int vel;
	private static int nOfCycles;
	private static int points;
	private static int remainingAliens = 0;
	
	private static BoardInitializer initializer; 
	private static GameObjectBoard board; 
	private static UCMShip player;
	
	private static Level level = new Level();
	private static Misil misil;
	private static Random rand;
	
	private static boolean gameOver;
	private static boolean shipsDir;
	private static boolean shockWave; //Comprobar si es necesario 
	private static boolean HayOvni;
	private static boolean exit;
	
	
	
//  =============================================
//				<Copiado del pdf>
//  =============================================

	//Bien
	public Game (Level level, Random random){
		this.rand = random;
		this.level = level;
		initializer = new BoardInitializer();
		initGame();
		}
	
	//Bien
	public void initGame () {
		nOfCycles = 0;
		board = initializer.initialize(this, level);
		player = new UCMShip(this, numCols / 2, numRows - 1);
		board.add(player);
		}
	
	//Bien, corregir funciones a las que llama
	public boolean Lose() {
		return !player.isAlive () || AlienShip.haveLanded();
	}
	
	//Bien, corregir funciones a las que llama
	private boolean Win () {
		return AlienShip.allDead();
	}
	
	//Bien
	public void update() {
		board.computerAction();
		board.update();
		nOfCycles += 1;
	}

	//Bien
	public String toString(int v, int h){
		GameObject aux = board.buscar(v, h);
		if(aux != null)
			return aux.toString();
		else
			return "";
	}

	//Creo que bien, mirar shockwave
	public void Print()
	{
		String shockwave = "No";
		if(shockWave)
		{
			shockwave = "Si";
		}
		String texto = "Life: " + player.GetHP() + "\nNumber of Cycles: " + nOfCycles + "\nPoints: " + points +
				"\nRemaining Aliens: " + remainingAliens + "\nShockwave: " + shockwave;
		System.out.println(texto);
	}

		
	public void Update()    
	{
		updateMissil();
		killedOrNot(); 
		
		if((nOfCycles) % vel == 0)
		{
			updateBombs();
			killedOrNot(); 
			updateOVNI();
			updateNaves();
			killedOrNot(); 
		}
		nOfCycles++;
	}
	
	

	
 	public void reset()
	{
		regularShips.reset();
		destroyerShips.reset();
		bombs.deleteBombs();
		
		shipsDir = false;
		gameOver = false;
		HayOvni = false;
		misil = null;
		player.SetHP(3);
		points = 0;
		shockWave = true;
		nOfCycles = 1;
		player.setShipPos(7, 4);
		
		remainingAliens = level.getNumberDestroyerShip() + level.getNumberRegularShip();
		
		if(rand.nextInt(10) < level.getProbOvni() * 10)
		{
			remainingAliens++;
			ovni.setShipPos(8);
			HayOvni = true;
		}
		
		switch(level.getDifficulty())
		{
		case easy:
			for(int i = 0; i < level.getNumberRegularShip(); i++)
				regularShips.SetRegShip(1, 3 + i, i);
			
			for(int i = 0; i < level.getNumberDestroyerShip(); i++)
				destroyerShips.SetDestShip(2, 4 + i, i);
			break;
			
		case hard:
			for(int i = 0; i < level.getNumberRegularShip(); i++)
				regularShips.SetRegShip((i / 4) + 1, (i % 4) + 3, i);
			
			for(int i = 0; i < level.getNumberDestroyerShip(); i++)
				destroyerShips.SetDestShip(3, 4 + i, i);
			break;
			
		case insane: 
			for(int i = 0; i < level.getNumberRegularShip(); i++)
				regularShips.SetRegShip((i / 4) + 1, (i % 4) + 3, i);
			
			for(int i = 0; i < level.getNumberDestroyerShip(); i++)
				destroyerShips.SetDestShip(3, 3 + i, i);
			break;
		}	
		
	}
	
	public void moveUCM(int i)
	{
		if(player.GetShipH() + i >= 0 && player.GetShipH() + i < numCols)
		{
			player.setShipPos(player.GetShipV(), player.GetShipH() + i);
		}
		else
		{
			if(i > 0)
			{
				i--;
			}
			else
			{
				i++;
			}
			if(player.GetShipH() + i >= 0 && player.GetShipH() + i < numCols)
			{
				player.setShipPos(player.GetShipV(), player.GetShipH() + i);
			}
		}
		
	}
	
	public boolean shockwave()
	{
		for(int i = 0; i < destroyerShips.GetIndice(); i++)
		{
			if(destroyerShips.GetDestShipHP(i) > 0)
			{
				destroyerShips.shipHitByUCMShip(i, player.GetHarm());
				remainingAliens--;
			}
			
		}
		
		for(int i = 0; i < regularShips.GetIndice(); i++)
		{
			if(regularShips.GetRegShipHP(i) > 0)
			{
				regularShips.shipHitByUCMShip(i, player.GetHarm());
				if(regularShips.GetRegShipHP(i) == 0)
				{
					remainingAliens--;
				}
			}
		}
		
		if(HayOvni)
		{
			HayOvni = false;
			ovni.shipHitByUCMShip();
			remainingAliens--;
		}
		return true;
	}
	
	public boolean shootMissile()
	{
		if(misil == null)
		{
			misil = new Misil();
			misil.SetMisilPos(player.GetShipV(), player.GetShipH());
		}
		return true;
	}
	
	private static void updateBombs(){
		for(int i = 0; i < destroyerShips.GetIndice(); i++) {
				if(bombs.CheckBomb(i)) {
					if(bombs.GetProyectilV(i) + 1 < numRows)
					{
					bombs.SetBombsPos(bombs.GetProyectilV(i) + 1, bombs.GetProyectilH(i), i);
					}
					else
					{
						bombs.deleteBomb(i);
					}
						
				}
				else {
					if(destroyerShips.GetDestShipHP(i) > 0 && (rand.nextInt(10) < (frecDisp * 10))) {
						bombs.Add(i);
						bombs.SetBombsPos(destroyerShips.GetDestV(i) + 1, destroyerShips.GetDestH(i), i);
					}
				}
		}
	}

	private static void updateMissil() {
		if(misil != null) {
			if(misil.GetMisilV() - 1 >= 0)
				misil.SetMisilPos(misil.GetMisilV() - 1, misil.GetMisilH());
			else 
				misil = null;	
		}
	}

	private static void updateNaves() { 
		boolean shipsMoveDown = false;
		int move;
		if(shipsDir) 
			move = 1;
		else
			move = -1;
		for(int i = 0; i < regularShips.GetIndice(); i++) {
			if(regularShips.GetRegShipHP(i) > 0 &&   
					(regularShips.GetRegH(i) + move >= numCols || regularShips.GetRegH(i) + move < 0)) {
				shipsMoveDown = true;
			}
		}
		for(int i = 0; i<destroyerShips.GetIndice(); i++) {
			if(destroyerShips.GetDestShipHP(i) > 0 &&   
					(destroyerShips.GetDestH(i) + move >= numCols || destroyerShips.GetDestH(i) + move < 0) ) {
				shipsMoveDown = true;
			}
		}
		
		
		if(shipsMoveDown) {
			shipsDown();
			shipsDir = !shipsDir;
		}
		else
		{
			shipsMove();
		}
	}

	private static void updateOVNI() {

		if(HayOvni)
		{
			if(ovni.GetShipH() - 1 >= 0)
			{
				ovni.setShipPos(ovni.GetShipH() - 1);
			}
			else
			{
				HayOvni = false;
				remainingAliens --;
			}
			
		}
		else
		{
			if(rand.nextInt(10) < level.getProbOvni() * 10)
			{
				remainingAliens++;
				ovni.reset();
				ovni.setShipPos(8);
				HayOvni = true;
			}
		}
	}

	private static void shipsDown() {
		boolean earth = false;
		for(int i = 0; i < regularShips.GetIndice(); i++) {
			if(regularShips.exists(i) && regularShips.GetRegV(i) == numRows - 2) {
				earth = true;
			}
		}
		for(int i = 0; i<destroyerShips.GetIndice(); i++) {
			if(destroyerShips.exists(i) && destroyerShips.GetDestV(i) == numRows - 2) {
				earth = true;
			}
		}
		if(earth)
			gameOver = true;
		else {
			for(int i = 0; i < regularShips.GetIndice(); i++) {
				regularShips.SetRegShip(regularShips.GetRegV(i)+1, regularShips.GetRegH(i), i);
			}
			for(int i = 0; i<destroyerShips.GetIndice(); i++) {
				destroyerShips.SetDestShip(destroyerShips.GetDestV(i)+1, destroyerShips.GetDestH(i), i);
			}
		}
	}
	
	private static void shipsMove() {
		int move;
		if(shipsDir) 
			move = 1;
		else
			move = -1;
		
		for(int i = 0; i < regularShips.GetIndice(); i++) 
		{
			if(regularShips.exists(i)) 
			{
				regularShips.SetRegShip(regularShips.GetRegV(i), regularShips.GetRegH(i)+ move, i);		
			}
		}
		
		
		for(int i = 0; i<destroyerShips.GetIndice(); i++) 
		{
			if(destroyerShips.exists(i)) 
			{
				destroyerShips.SetDestShip(destroyerShips.GetDestV(i), destroyerShips.GetDestH(i)+ move, i);
			}
		}
	}

	private static void killedOrNot() 
	{
		for(int i = 0; i < bombs.GetIndice(); i++) {
			if(bombs.CheckBomb(i)) {
				if(bombs.GetProyectilV(i) == misil.GetMisilV() && bombs.GetProyectilH(i) == misil.GetMisilH())
				{
					misil = null;
					bombs.deleteBomb(i);
				}
				else
				{
					if(bombs.GetProyectilV(i) == player.GetShipV() && bombs.GetProyectilH(i) == player.GetShipH()) 
					{
						player.shipHitByAlien();
						bombs.deleteBomb(i);
					}
				}
			}
		}
		
		for(int i = 0; i < regularShips.GetIndice(); i++) 
		{
			if(misil != null && regularShips.exists(i) && misil.GetMisilV() == regularShips.GetRegV(i) && misil.GetMisilH() == regularShips.GetRegH(i)) 
			{
				regularShips.shipHitByUCMShip(i, 1);
				misil = null;
			}
		}
		
		for(int i = 0; i<destroyerShips.GetIndice(); i++) 
		{
			if(misil != null && destroyerShips.exists(i) && misil.GetMisilV() == destroyerShips.GetDestV(i) && misil.GetMisilH() == destroyerShips.GetDestH(i)) 
			{
				destroyerShips.shipHitByUCMShip(i, 1);
				misil = null;
			}
		}
		
		if(misil != null && HayOvni && ovni.GetShipHP() > 0 && ovni.GetShipV() == misil.GetMisilV() && ovni.GetShipH() == misil.GetMisilH())
		{
			ovni.shipHitByUCMShip();
			HayOvni = false;
			shockWave = true;
			misil = null;
		}
		remainingAliens = destroyerShips.GetContador() + regularShips.GetContador();
	}
	
	
	//Bien
	public void gameOverPrint() 
	{
		String texto = "\n\n\n\n\n\n HAS PERDIDO \n\n\n\n\n\n";
		System.out.println(texto);
	}
	
	//Bien
	public void gameWinPrint()
	{
		String texto = "\n\n HA GANADO \n\n";
		System.out.println(texto);
	}
	
	//Bien
	public boolean GameOver()
	{
		return Win() || Lose() || exit;
	}
	
	//Bien
	public int GetNumRows()
	{
		return numRows;
	}
	
	//Bien
	public int GetNumCols()
	{
		return numCols;
	}
	
	//Bien
	public void SetExit(boolean x)
	{
		exit = x;
	}

	@Override
	public boolean move(int numCells) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shockWave() {
		// TODO Auto-generated method stub
		return false;
	}

	//Bien
	public void receivePoints(int points) {
		this.points += points;
	}

	@Override
	public void enableShockWave() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enableMissile() {
		// TODO Auto-generated method stub
		
	}

	//Bien
	public String List() {
		String aux;
		UCMShip s = new UCMShip(this, 0, 0);
		RegularShip r = new RegularShip(0, 0);
		DestroyerShip d = new DestroyerShip(0, 0);
		aux = "^__^ : Harm - " + s.getHarm() + ", Shield - " + s.GetFinHP() + "\n"
		+ "[R]egular Ship : Harm - 0, Shield - " + r.GetFinHP() + ", Points - " + r.getPoints() + "\n"
		+ "[D]estroyer Ship : Harm - " + d.getHarm() + ", Shield - " + d.GetFinHP() + ", Points - " + d.getPoints() + "\n"
		+ "[O]vni : Harm - 0, Shield - " + ", Points - " + "\n";
		return aux;
	}
	//Bien
	public boolean Exit() {
		return exit;
	}

}

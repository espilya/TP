package interfaces;

import exceptions.CommandExecuteException;

public interface IPlayerController {
	
	// Player actions
	public boolean move (int numCells) throws CommandExecuteException;
	public boolean shootMissile();
	public boolean shockWave() throws CommandExecuteException;
	
	// Callbacks
	public void receivePoints(int points);
	public void enableShockWave();
	public void enableMissile();
}
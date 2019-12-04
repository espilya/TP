package interfaces;

import logic.Game;



public interface IExecuteRandomActions {
	
	static boolean canGenerateRandomOvni(Game game){
		return game.getNextDouble() < game.getLevel().getOvniFrequency();
	}
	static boolean canGenerateRandomBomb(Game game){
		return game.getNextDouble() < game.getLevel().getShootFrequency();
	}
}
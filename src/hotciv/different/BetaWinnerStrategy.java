package hotciv.different;

import hotciv.framework.*;
import hotciv.standard.*;

public class BetaWinnerStrategy implements WinnerStrategy{
	private GameImpl game;
	
	public Player winner() {
		Player p = this.game.getPlayerInTurn();
		for(int i = 0; i < 16; i++) {
			for(int j = 0; j < 16; j++) {
				p = this.game.getCityAt(new Position(i, j)).getOwner();
				if(p != null) {
					break;
				}
			}
		}
		return Player.RED;
	}
	
	public void setGame(GameImpl game) {
		this.game = game;
	}

}

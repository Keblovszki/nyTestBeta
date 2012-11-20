package hotciv.different;

import hotciv.framework.*;
import hotciv.standard.*;

public class BetaWinnerStrategy implements WinnerStrategy {
	private GameImpl game;
	
	public Player winner() {
		Player p = null;
		Player owner = null;
		Player owner1 = null;
		
		for(CityImpl c : this.game.getAllCities()) {
			if(Player.RED == c.getOwner()) {
				owner = c.getOwner();
			}
			if(Player.BLUE == c.getOwner()) {
				owner1 = c.getOwner();
			}
		}
		if(owner == null) {
			p = Player.BLUE;
		}
		if(owner1 == null) {
			p = Player.RED;
		}
		return p;
	}
	
	public void setGame(GameImpl game) {
		this.game = game;
	}

}

	
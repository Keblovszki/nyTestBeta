package hotciv.different;

import hotciv.framework.*;
import hotciv.standard.*;

public class GammaUnitActionStrategy implements UnitActionStrategy {
	private GameImpl game;
	
	@Override
	public void performUnitActionAt(String type, Position p) {
		Player owner = this.game.getUnitAt(p).getOwner();
		
		//Settler builds the city..
		if(type.equals(GameConstants.SETTLER)) {
			this.game.removeUnit(p);
			this.game.addCity(p, owner);
		}
		//The archer fortifies..
		else if (type.equals(GameConstants.ARCHER)) {
			int defensiveStrength = game.getUnitAt(p).getDefensiveStrength();
			if(defensiveStrength == 3) {
				game.getUnitAt(p).setDefensiveStrength(3);
			}
			if(defensiveStrength == 6) {
				game.getUnitAt(p).setDefensiveStrength(-3);
			}
		}
	}
	
	public void setGame(GameImpl game) {
		this.game = game;
	}
}

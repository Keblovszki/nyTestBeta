package hotciv.different;

import hotciv.framework.*;
import hotciv.standard.*;

public class GammaUnitActionStrategy implements UnitActionStrategy {
	private GameImpl game;
	
	@Override
	public void performUnitActionAt(Unit unit, Position p) {
		Player owner = this.game.getUnitAt(p).getOwner();
		
		//The archer fortifies..
				if(unit.getTypeString().equals(GameConstants.ARCHER)) {
					if(unit.isNotArcherFortify()) {
						unit.increaseBonusDefenseStrength(unit.getDefensiveStrength());
						unit.setIsNotFortify(false);
					}
					else {
						unit.increaseBonusDefenseStrength(- (unit.getDefensiveStrength()/2));
						unit.setIsNotFortify(true);
					}
				}
		
		//Settler builds the city..
		if(unit.getTypeString().equals(GameConstants.SETTLER)) {
			this.game.removeUnit(p);
			this.game.addCity(p, owner);
		}
	}
//Synes det skal sådan her ud men det virker ikke..
/*		else if (type.equals(GameConstants.ARCHER)) {
			if(game.getUnitAt(p).getDefensiveStrength() == 3) {
				game.getUnitAt(p).increaseBonusDefenseStrength(game.getUnitAt(p).getDefensiveStrength());
			}
			if(game.getUnitAt(p).getDefensiveStrength() == 6) {
				game.getUnitAt(p).increaseBonusDefenseStrength(-(game.getUnitAt(p).getDefensiveStrength()/2));
			}
		}
	}*/
		/*
				else if (type.equals(GameConstants.ARCHER)) {
						int defensiveStrength = game.getUnitAt(p).getDefensiveStrength();
						if(defensiveStrength == 3) {
							game.getUnitAt(p).increaseBonusDefenseStrength(3);
						}
						if(defensiveStrength == 6) {
							game.getUnitAt(p).increaseBonusDefenseStrength(-3);
						}
					}
			}
		*/
	
	public void setGame(GameImpl game) {
		this.game = game;
	}
}

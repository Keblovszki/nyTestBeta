package hotciv.standard;

import hotciv.framework.*;


public interface UnitActionStrategy {

	public void performUnitActionAt(Unit unit, Position p);
	public void setGame(GameImpl game);
	
}

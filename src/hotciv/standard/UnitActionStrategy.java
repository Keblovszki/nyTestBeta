package hotciv.standard;

import hotciv.framework.*;


public interface UnitActionStrategy {

	public void performUnitActionAt(String type, Position p);
	public void setGame(GameImpl game);
	
}

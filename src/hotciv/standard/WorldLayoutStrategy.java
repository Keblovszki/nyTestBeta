package hotciv.standard;

import hotciv.framework.Position;

import java.util.HashMap;

public interface WorldLayoutStrategy {
	
	public HashMap<Position, UnitImpl> makeUnitList();
	
	public HashMap<Position, CityImpl> makeCityList();
	
	public HashMap<Position, TileImpl> makeTileList();

}

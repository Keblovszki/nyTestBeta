package hotciv.different;

import java.util.*;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.*;


public class AlphaWorldLayoutStrategy implements WorldLayoutStrategy {
	HashMap<Position, UnitImpl> mapUnit = new HashMap<Position, UnitImpl>();
	HashMap<Position, CityImpl> mapCity = new HashMap<Position, CityImpl>();
	HashMap<Position, TileImpl> mapTile = new HashMap<Position, TileImpl>();
	
	public HashMap<Position, UnitImpl> makeUnitList() {
		mapUnit.put(new Position(2, 0), new UnitImpl(Player.RED, GameConstants.ARCHER) );
		mapUnit.put(new Position(3, 2), new UnitImpl(Player.BLUE, GameConstants.LEGION) );
		mapUnit.put(new Position(4, 3), new UnitImpl(Player.RED, GameConstants.SETTLER) );
		return mapUnit;
	}
	
	public HashMap<Position, CityImpl> makeCityList() {
		mapCity.put(new Position(1, 1), new CityImpl(Player.RED));
		mapCity.put(new Position(4, 1), new CityImpl(Player.BLUE));
		return mapCity;
	}
	
	public HashMap<Position, TileImpl> makeTileList() {
		for(int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				mapTile.put(new Position(i, j), new TileImpl(new Position(i, j), GameConstants.PLAINS));
			}
		}
		mapTile.put(new Position(1, 0), new TileImpl(new Position (1, 0), GameConstants.OCEANS));
		mapTile.put(new Position(0, 1), new TileImpl(new Position (0, 1), GameConstants.HILLS));
		mapTile.put(new Position(2, 2), new TileImpl(new Position (2, 2), GameConstants.MOUNTAINS));
			//return new TileImpl(p , GameConstants.PLAINS);
		return mapTile;
	}
	
}

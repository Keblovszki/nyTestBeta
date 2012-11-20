package hotciv.different;

import java.util.HashMap;

import hotciv.framework.*;
import hotciv.standard.*;

public class DeltaWorldLayoutStrategy implements WorldLayoutStrategy {
	HashMap<Position, UnitImpl> mapUnit = new HashMap<Position, UnitImpl>();
	HashMap<Position, CityImpl> mapCity = new HashMap<Position, CityImpl>();
	HashMap<Position, TileImpl> mapTile = new HashMap<Position, TileImpl>();

	public HashMap<Position, UnitImpl> makeUnitList() {
		mapUnit.put(new Position(2, 0), new UnitImpl(Player.RED, GameConstants.ARCHER));
		mapUnit.put(new Position(3, 2), new UnitImpl(Player.BLUE, GameConstants.LEGION));
		mapUnit.put(new Position(4, 3), new UnitImpl(Player.RED, GameConstants.SETTLER));
		return mapUnit;
	}

	public HashMap<Position, CityImpl> makeCityList() {
		mapCity.put(new Position(8, 12), new CityImpl(Player.RED));
		mapCity.put(new Position(4, 5), new CityImpl(Player.BLUE));
		return mapCity;
	}

	public HashMap<Position, TileImpl> makeTileList() {
		String[] layout;
		layout = new String[] { 
				"...ooMooooo.....", 
				"..ohhoooofffoo..", 
				".oooooMooo...oo.", 
				".ooMMMoooo..oooo",
				"...ofooohhoooo..", 
				".ofoofooooohhoo.", 
				"...ooo..........", 
				".ooooo.ooohooM..", 
				".ooooo.oohooof..",
				"offfoooo.offoooo",
				"oooooooo...ooooo",
				".ooMMMoooo......",
				"..ooooooffoooo..",
				"....ooooooooo...",
				"..ooohhoo.......",
				".....ooooooooo..", };
		String line;
		for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
			line = layout[r];
			for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
				char tileChar = line.charAt(c);
				String type = "error";
				if (tileChar == '.') {
					type = GameConstants.OCEANS;
				}
				if (tileChar == 'o') {
					type = GameConstants.PLAINS;
				}
				if (tileChar == 'M') {
					type = GameConstants.MOUNTAINS;
				}
				if (tileChar == 'f') {
					type = GameConstants.FOREST;
				}
				if (tileChar == 'h') {
					type = GameConstants.HILLS;
				}
				Position p = new Position(r, c);
				mapTile.put(p, new TileImpl(p, type));
			}
		}
		return mapTile;
	}
}

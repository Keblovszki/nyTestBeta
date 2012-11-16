package hotciv.standard;

import hotciv.framework.Position;
import hotciv.framework.Tile;

public class TileImpl implements Tile {
	private String type;
	private Position position;
	
	//Constructor
	public TileImpl(Position p, String t){
		position = p;
		type = t;
	}
	
	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public String getTypeString() {		
		return type;
	}

}

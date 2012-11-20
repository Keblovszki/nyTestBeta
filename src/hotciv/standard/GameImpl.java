package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.Unit;

import java.util.*;

/**
 * Skeleton implementation of HotCiv.
 * 
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 * 
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */

public class GameImpl implements Game {
	private Player playerInTurn = Player.RED;
	private int age = -4000;
	HashMap<Position, CityImpl> mapCity = new HashMap<Position, CityImpl>();
	HashMap<Position, UnitImpl> mapUnit = new HashMap<Position, UnitImpl>();
	HashMap<Position, TileImpl> mapTile = new HashMap<Position, TileImpl>();
	private WorldAgingStrategy worldAgingStrategy;
	private WinnerStrategy winnerStrategy;
	private UnitActionStrategy unitActionStrategy;
	private WorldLayoutStrategy worldLayoutStrategy;
	
	//Constructor
	public GameImpl(WorldAgingStrategy was, WinnerStrategy ws, UnitActionStrategy uas, WorldLayoutStrategy wls) {
		
		worldAgingStrategy = was;
		winnerStrategy = ws;
		unitActionStrategy = uas;
		worldLayoutStrategy = wls;
		
		mapCity.putAll(worldLayoutStrategy.makeCityList());
		
		mapUnit.putAll(worldLayoutStrategy.makeUnitList());
		
		mapTile.putAll(worldLayoutStrategy.makeTileList());
	}
	
	public Tile getTileAt(Position p) {
		return mapTile.get(p);
	}

	public Unit getUnitAt(Position p) {
		return mapUnit.get(p);
	}

	public City getCityAt(Position p) {
		return mapCity.get(p);
	}

	public Player getPlayerInTurn() {
		return playerInTurn;
	}

	public Player getWinner() {
		winnerStrategy.setGame(this);
		return winnerStrategy.winner();
	}

	public int getAge() {
		return age;
	}

	public boolean moveUnit(Position from, Position to) {
		if(mapUnit.get(to) == null ) {
			if(mapUnit.get(from).getDefensiveStrength() == 6) {
				return false;
			}
				else{mapUnit.put(to, mapUnit.get(from) );
				mapUnit.remove(from);
				if(mapCity.get(to) != null ) {
					if(mapCity.get(to).getOwner() == mapUnit.get(to).getOwner()) {
					return true;
					}
					else {
						mapCity.get(to).setOwner(mapUnit.get(to).getOwner());
						return true;
					}
				}
			}
		}
		else if(mapUnit.get(to) != null) {
			if(mapUnit.get(from).getDefensiveStrength() == 6) {
				return false;
			}
			else{
				mapUnit.remove(to);
				mapUnit.put(to, mapUnit.get(from) );
				mapUnit.remove(from);
				if(mapCity.get(to) != null ) {
					if(mapCity.get(to).getOwner() == mapUnit.get(to).getOwner()) {
						return true;
					}
					else {
						mapCity.get(to).setOwner(mapUnit.get(to).getOwner());
						return true;
					}
				}
			}
		}
			return false;
	}

	public void endOfTurn() {
		if(playerInTurn == Player.RED){
			playerInTurn = Player.BLUE;
		}
		else{
			age = worldAgingStrategy.worldAging(age);
			playerInTurn = Player.RED;
			for(CityImpl c : mapCity.values()){
				c.doProductionSum();
			}
		}
	}	

	public void changeWorkForceFocusInCityAt(Position p, String balance) {
		
	}

	public void changeProductionInCityAt(Position p, String unitType) {
		mapCity.get(p).setProduction(unitType);
	}

	public void performUnitActionAt(Position p) {
		unitActionStrategy.setGame(this);
		String u = mapUnit.get(p).getTypeString();
		unitActionStrategy.performUnitActionAt(u, p);
	}
	
	public void createProductionInCityAt(Position p) {
		final Position south = p.getSouth(p);
		final Position north = p.getNorth(p);
		final Position east = p.getEast(p);
		final Position west = p.getWest(p);
		final Position southWest = p.getSouthWest(p);
		final Position southEast = p.getSouthEast(p);
		final Position northWest = p.getNorthWest(p);
		final Position northEast = p.getNorthEast(p);
		final ArrayList<Position> aroundTheCity = new ArrayList<Position>();
		
		aroundTheCity.add(p);
		aroundTheCity.add(north);
		aroundTheCity.add(northEast);
		aroundTheCity.add(east);
		aroundTheCity.add(southEast);
		aroundTheCity.add(south);
		aroundTheCity.add(southWest);
		aroundTheCity.add(west);
		aroundTheCity.add(northWest);
		
		//Archer costs 10 production
		//Legions costs 15 production
		
		if(mapCity.get(p) != null) {
			CityImpl c = mapCity.get(p);
			if(c.getProduction() == GameConstants.SETTLER ) {
				if(c.getProductionSum() >= 30) {
					for(Position currentPosition : aroundTheCity) {
						if(mapUnit.get(currentPosition) == null) {
							mapUnit.put(currentPosition, new UnitImpl(c.getOwner(), GameConstants.SETTLER));
							break;
						}
					}
					c.setProductionSum(-30);
				}
			}
			if(c.getProduction() == GameConstants.LEGION) {
				if(c.getProductionSum() >= 15) {
					for(Position currentPosition : aroundTheCity) {
						if(mapUnit.get(currentPosition) == null) {
							mapUnit.put(currentPosition, new UnitImpl(c.getOwner(), GameConstants.LEGION));
							break;
						}
					}
					c.setProductionSum(-15);
				}
			}
			if(c.getProduction() == GameConstants.ARCHER ) {
				if(c.getProductionSum() >= 10) {
					for(Position currentPosition : aroundTheCity) {
						if(mapUnit.get(currentPosition) == null) {
							mapUnit.put(currentPosition, new UnitImpl(c.getOwner(), GameConstants.ARCHER));
							break;
						}
					}
					c.setProductionSum(-10);
				}
			}
		}
	}
	
	public void removeUnit(Position p) {
		mapUnit.remove(p);
	}
	
	public void addCity(Position p, Player owner) {
		mapCity.put(p, new CityImpl(owner));
	}
	
	public Collection<CityImpl> getAllCities() {
		return mapCity.values();
	}
}

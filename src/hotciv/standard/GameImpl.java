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
	private WorldAgingStrategy worldAgingStrategy;
	private WinnerStrategy winnerStrategy;
	private UnitActionStrategy unitActionStrategy;
	
	//Constructor
	public GameImpl(WorldAgingStrategy was, WinnerStrategy ws, UnitActionStrategy uas){
		mapCity.put(new Position(1, 1), new CityImpl(Player.RED));
		mapCity.put(new Position(4, 1), new CityImpl(Player.BLUE));	
		
		mapUnit.put(new Position(2, 0), new UnitImpl(Player.RED, GameConstants.ARCHER) );
		mapUnit.put(new Position(3, 2), new UnitImpl(Player.BLUE, GameConstants.LEGION) );
		mapUnit.put(new Position(4, 3), new UnitImpl(Player.RED, GameConstants.SETTLER) );
		
		worldAgingStrategy = was;
		winnerStrategy = ws;
		unitActionStrategy = uas;
	}
	
	public Tile getTileAt(Position p) {
		if(p.equals(new Position(1, 0))){
			return new TileImpl(new Position (1, 0), GameConstants.OCEANS);
		}
		if(p.equals(new Position(0, 1))){
			return new TileImpl(new Position (0, 1), GameConstants.HILLS);
		}
		if(p.equals(new Position(2, 2))){
			return new TileImpl(new Position (2, 2), GameConstants.MOUNTAINS);
		}
		else{
			return new TileImpl(p , GameConstants.PLAINS);
		}
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
		return winnerStrategy.winner();
	}

	public int getAge() {
		return age;
	}

	public boolean moveUnit(Position from, Position to) {
		if(mapUnit.get(to) == null ){
			mapUnit.put(to, mapUnit.get(from) );
			mapUnit.remove(from);
			return true;
		}
		if(mapUnit.get(to) != null){
			if(mapUnit.get(from).getDefensiveStrength() == 6)
			mapUnit.remove(to);
			mapUnit.put(to, mapUnit.get(from) );
			mapUnit.remove(from);
			return true;
		}
		else {
			return false;
		}
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
	public void removeUnit(Position p){
		mapUnit.remove(p);
	}
	public void addCity(Position p, Player owner){
		mapCity.put(p, new CityImpl(owner));
	}
}

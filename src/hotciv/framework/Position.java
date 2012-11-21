package hotciv.framework;

import java.util.ArrayList;

/**
 * Position on the world map.
 * 
 * Responsibilities: 1) Know a specific location (row,column).
 * 
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 * 
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class Position {

	/**
	 * create a position.
	 * 
	 * @param r
	 *            the row
	 * @param c
	 *            the column
	 */
	public Position(int r, int c) {
		this.r = r;
		this.c = c;
	}

	protected int r;
	protected int c;

	/**
	 * get the row represented by this position.
	 * 
	 * @return the row.
	 */
	public int getRow() {
		return r;
	}

	/**
	 * get the column represented by this position.
	 * 
	 * @return the column.
	 */
	public int getColumn() {
		return c;
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o.getClass() != Position.class) {
			return false;
		}
		Position other = (Position) o;
		return r == other.r && c == other.c;
	}

	public int hashCode() {
		// works ok for positions up to columns == 479
		return 479 * r + c;
	}

	public String toString() {
		return "[" + r + "," + c + "]";
	}

	public Position getWest(Position p) {
		return new Position(p.getRow(), p.getColumn() + 1);
	}
	
	public Position getEast(Position p) {
		return new Position(p.getRow(), p.getColumn() - 1);
	}
	
	public Position getNorth(Position p) {
		return new Position(p.getRow() - 1, p.getColumn());
	}
	
	public Position getSouth(Position p) {
		return new Position(p.getRow() + 1, p.getColumn());
	}
	
	public Position getNorthEast(Position p) {
		return new Position(p.getRow() - 1, p.getColumn() - 1);
	}
	
	public Position getNorthWest(Position p) {
		return new Position(p.getRow() - 1, p.getColumn() + 1);
	}
	
	public Position getSouthWest(Position p) {
		return new Position(p.getRow() + 1, p.getColumn() + 1);
	}
	
	public Position getSouthEast(Position p) {
		return new Position(p.getRow() + 1, p.getColumn() - 1);
	}
	
	public ArrayList<Position> getNeighbours() {
		final Position south = this.getSouth(this);
		final Position north = this.getNorth(this);
		final Position east = this.getEast(this);
		final Position west = this.getWest(this);
		final Position southWest = this.getSouthWest(this);
		final Position southEast = this.getSouthEast(this);
		final Position northWest = this.getNorthWest(this);
		final Position northEast = this.getNorthEast(this);
		final ArrayList<Position> aroundTheCity = new ArrayList<Position>();
		
		aroundTheCity.add(this);
		aroundTheCity.add(north);
		aroundTheCity.add(northEast);
		aroundTheCity.add(east);
		aroundTheCity.add(southEast);
		aroundTheCity.add(south);
		aroundTheCity.add(southWest);
		aroundTheCity.add(west);
		aroundTheCity.add(northWest);
		return aroundTheCity;
	}
}

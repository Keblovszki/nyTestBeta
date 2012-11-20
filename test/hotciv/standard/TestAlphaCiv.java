package hotciv.standard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.Unit;
import hotciv.different.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Skeleton class for AlphaCiv test cases
 * 
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 * 
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class TestAlphaCiv {
	private Game game;

	/** Fixture for alphaciv testing. */
	@Before
	public void setUp() {
		game = new GameImpl(
				new AlphaWorldAgingStrategy(),
				new AlphaWinnerStrategy(),
				new AlphaUnitActionStrategy(), 
				new AlphaWorldLayoutStrategy());
	}

	@Test
	public void shouldHaveRedCityAt1_1() {
		City c = game.getCityAt(new Position(1, 1));
		assertNotNull("There should be a city at (1, 1)", c);
		Player p = c.getOwner();
		assertEquals("City at (1, 1) should be owned by red", Player.RED, p);
	}

	@Test
	public void NoCityAt2_2() {
		City c = game.getCityAt(new Position(2, 2));
		assertNull("There shouldn't be a city at (2, 2)", c);
	}

	@Test
	public void NoCityAt3_2() {
		City c = game.getCityAt(new Position(3, 2));
		assertNull("There shouldn't be a city at (3, 2)", c);
	}

	@Test
	public void RedShouldWinInYear3000BC() {
		// runs the first ten rounds
		for (int i = 0; i < 20; i++) {
			game.endOfTurn();
		}
		int age = game.getAge();
		assertEquals("The year is 3000 BC", -3000, age);
		assertEquals("The winner is red", Player.RED, game.getWinner());
		// assertNull("No winner yet", game.getWinner());
	}

	@Test
	public void shouldHaveBlueCityAt4_1() {
		City c = game.getCityAt(new Position(4, 1));
		assertNotNull("There should be a city at (4, 1)", c);
		Player p = c.getOwner();
		assertEquals("City at (4, 1) should be owned by red", Player.BLUE, p);
	}

	@Test
	public void redPlayerStarts() {
		Player p = game.getPlayerInTurn();
		assertEquals("Red is the first player in turn", Player.RED, p);
	}

	@Test
	public void gameShouldStartIn4000BC() {
		int age = game.getAge();
		assertEquals("At game start, age should be 4000 BC", -4000, age);
	}

	@Test
	public void advanceYearWith100PrRound() {

		// runs the first round
		for (int i = 0; i < 2; i++) {
			game.endOfTurn();
		}

		int age = game.getAge();
		assertEquals("The year should be -3900 after the first round", -3900, age);

		// runs the second round
		for (int i = 0; i < 2; i++) {
			game.endOfTurn();
		}
		age = game.getAge();
		assertEquals("The year should be -3800 after the second round", -3800, age);

		// runs the next five rounds
		for (int i = 0; i < 10; i++) {
			game.endOfTurn();
		}
		age = game.getAge();
		assertEquals("There year should be -3300 after the seventh round", -3300, age);
	}

	@Test
	// Firstly it is red in turn, afterward it is blue in turn and round we go
	// ==> exactly two players red and blue
	public void exactlyTwoPlayersRedAndBlue() {
		// switch turn for the first ten rounds...
		for (int i = 0; i < 10; i++) {
			game.endOfTurn();
			assertEquals("The player in turn should be blue", Player.BLUE, game.getPlayerInTurn());
			game.endOfTurn();
			assertEquals("The player in turn should be red", Player.RED, game.getPlayerInTurn());
		}
	}

	@Test
	public void redCityPopulationIs1() {
		City c = game.getCityAt(new Position(1, 1));
		assertEquals("Cities has size 1", 1, c.getSize());
	}

	@Test
	public void tileAt1_0ShouldBeAnOcean() {
		Tile t = game.getTileAt(new Position(1, 0));
		assertNotNull("t should not be null", t);
		assertEquals("The tile at position (1, 0) should be an ocean", GameConstants.OCEANS, t.getTypeString());
		assertEquals("The position shuold be (1, 0)", new Position(1, 0), t.getPosition());
	}

	@Test
	public void tileAt0_1ShouldBeAHill() {
		Tile t = game.getTileAt(new Position(0, 1));
		assertNotNull("t should not be null", t);
		assertEquals("The tile at position (0, 1) should be a hill", GameConstants.HILLS, t.getTypeString());
		assertEquals("The position shuold be (0, 1)", new Position(0, 1), t.getPosition());
	}

	@Test
	public void tileAt2_2ShouldBeAMountain() {
		Tile t = game.getTileAt(new Position(2, 2));
		assertNotNull("t should not be null", t);
		assertEquals("The tile at position (2, 2) should be a mountain", GameConstants.MOUNTAINS, t.getTypeString());
		assertEquals("The position shuold be (2, 2)", new Position(2, 2), t.getPosition());
	}

	@Test
	public void restOfTheTilesArePlains() {
		Tile t = game.getTileAt(new Position(4, 4));
		assertNotNull("t should not be null", t);
		assertEquals("The tile at position (4, 4) should be plains", GameConstants.PLAINS, t.getTypeString());
		assertEquals("The position shuold be (4, 4)", new Position(4, 4), t.getPosition());
	}

	@Test
	public void restOfTheTilesArePlains1() {
		Tile t = game.getTileAt(new Position(12, 13));
		assertNotNull("t should not be null", t);
		assertEquals("The tile at position (12, 13) should be plains", GameConstants.PLAINS, t.getTypeString());
		assertEquals("The position shuold be (12, 13)", new Position(12, 13), t.getPosition());
	}

	@Test
	public void cityAt1_1Gain6productionEachRound() {
		City c = game.getCityAt(new Position(1, 1));
		assertEquals("The city at position (1, 1) should have production 0", 0, c.getProductionSum());

		// runs the first round
		for (int i = 0; i < 2; i++) {
			game.endOfTurn();
		}
		assertEquals("The city at position (1, 1) should have production 6", 6, c.getProductionSum());

		// runs three rounds more
		for (int i = 0; i < 6; i++) {
			game.endOfTurn();
		}
		assertEquals("The city at position (1, 1) should have production 24", 24, c.getProductionSum());
	}

	@Test
	public void cityAt4_1Gain6productionEachRound() {
		City c = game.getCityAt(new Position(4, 1));
		assertEquals("The city at position (4, 1) should have production 0", 0, c.getProductionSum());

		// runs the first round
		for (int i = 0; i < 2; i++) {
			game.endOfTurn();
		}
		assertEquals("The city at position (4, 1) should have production 6", 6, c.getProductionSum());

		// runs three rounds more
		for (int i = 0; i < 6; i++) {
			game.endOfTurn();
		}
		assertEquals("The city at position (4, 1) should have production 24", 24, c.getProductionSum());
	}

	@Test
	public void redArcherAt2_0() {
		Unit u = game.getUnitAt(new Position(2, 0));
		assertNotNull("u should not be null", u);
		assertEquals("The tile at position (2, 0) should be a archer", GameConstants.ARCHER, u.getTypeString());
		assertEquals("The player should be red", Player.RED, u.getOwner());
	}

	@Test
	public void blueLegionAt3_2() {
		Unit u = game.getUnitAt(new Position(3, 2));
		assertNotNull("u should not be null", u);
		assertEquals("The tile at position (3, 2) should be a archer", GameConstants.LEGION, u.getTypeString());
		assertEquals("The player should be blue", Player.BLUE, u.getOwner());
	}

	@Test
	public void redSettlerAt4_3() {
		Unit u = game.getUnitAt(new Position(4, 3));
		assertNotNull("u should not be null", u);
		assertEquals("The tile at position (4, 3) should be a archer", GameConstants.SETTLER, u.getTypeString());
		assertEquals("The player should be red", Player.RED, u.getOwner());
	}

	// We want that the cities production type should be null at the start
	@Test
	public void cityAt1_1ProducesNothing() {
		City c = game.getCityAt(new Position(1, 1));
		assertNull("The City at (1,1) produces nothing", c.getProduction());
	}

	@Test
	public void moveAUnit() {
		Unit u1 = game.getUnitAt(new Position(4, 3));
		assertNotNull("u1 should not be null", u1);

		game.moveUnit(new Position(4, 3), new Position(4, 4));

		Unit u2 = game.getUnitAt(new Position(4, 3));
		assertNull("u2 should be null", u2);

		Unit u3 = game.getUnitAt(new Position(4, 4));
		assertNotNull("u3 should not be null", u3);
	}

	@Test
	public void attackUnitsAttackerWins() {
		Unit u1 = game.getUnitAt(new Position(2, 0));
		assertNotNull("u should not be null", u1);

		Unit u2 = game.getUnitAt(new Position(3, 2));
		assertNotNull("u should not be null", u2);

		game.moveUnit(new Position(2, 0), new Position(3, 2));

		assertEquals("u1 has won", u1, game.getUnitAt(new Position(3, 2)));

		Unit u3 = game.getUnitAt(new Position(2, 0));
		assertNull("u1 should be moved", u3);

		assertNotSame("The unit u2 should have been removed", u2, game.getUnitAt(new Position(3, 2)));
	}

	@Test
	public void makeSettler() {
		City c = game.getCityAt(new Position(1, 1));
		game.changeProductionInCityAt(new Position (1, 1), GameConstants.SETTLER);
		assertEquals("The production in city1_1 is settlers", GameConstants.SETTLER, c.getProduction());
		
		// runs five rounds...
		for (int i = 0; i < 10; i++) {
			game.endOfTurn();
			game.createProductionInCityAt(new Position(1, 1));
		}
				
		Unit u1 = game.getUnitAt(new Position (1, 1));
		assertNotNull("There is a unit at position (1, 1)", u1);
		assertEquals("There should be a settler at (1, 1)", GameConstants.SETTLER, u1.getTypeString());
		assertEquals("The productionSum should be 0", 0, c.getProductionSum());
		assertNull("Is the position north for the city null", game.getUnitAt(new Position(1, 0)));
		
		// runs five rounds...
		for (int i = 0; i < 10; i++) {
			game.endOfTurn();
			game.createProductionInCityAt(new Position(1, 1));
		} 
		
		Unit u2 = game.getUnitAt(new Position (1, 0));
		assertNotNull("There is a unit at position (1, 0)", u2);
		assertEquals("There should be a settler at (1, 0)", GameConstants.SETTLER, u2.getTypeString());
		assertEquals("The productionSum should be 0", 0, c.getProductionSum());		
		
		// runs 25 rounds... and check for: Is it allowed to create a settler
		for (int i = 0; i < 50; i++) {
				game.endOfTurn();
				game.createProductionInCityAt(new Position(1, 1));
		}
		
		Unit u3 = game.getUnitAt(new Position (0, 2));
		assertNotNull("There is a unit at position (0, 2)", u3);
		assertEquals("There should be a settler at (0, 2)", GameConstants.SETTLER, u3.getTypeString());
		
		//because there is an archer before all this stuff at position (2, 0)
		assertNotNull("This spot shouldn't be null", game.getUnitAt(new Position (0, 1))); 
		
		//So we have to check that the next spot i null
		assertNull("This spot should be null", game.getUnitAt(new Position (0, 0)));
	}
	
	@Test
	public void makeLegion() {
		City c = game.getCityAt(new Position(4, 1));
		game.changeProductionInCityAt(new Position (4, 1), GameConstants.LEGION);
		assertEquals("The production in city4_1 is legions", GameConstants.LEGION, c.getProduction());
		
		// runs three rounds...
		for (int i = 0; i < 6; i++) {
			game.endOfTurn();
			game.createProductionInCityAt(new Position(4, 1));
		}
				
		Unit u1 = game.getUnitAt(new Position (4, 1));
		assertNotNull("There is a unit at position (4, 1)", u1);
		assertEquals("There should be a legion at (4, 1)", GameConstants.LEGION, u1.getTypeString());
		assertEquals("The productionSum should be 3", 3, c.getProductionSum());
		assertNull("Is the position north for the city null", game.getUnitAt(new Position(4, 0)));
		
		// runs nine rounds...
		for (int i = 0; i < 18; i++) {
			game.endOfTurn();
			game.createProductionInCityAt(new Position(4, 1));
		}
		
		Unit u2 = game.getUnitAt(new Position(5, 1));
		assertNotNull("There is a unit at position (5, 1)", u2);
		assertEquals("There should be a legion at (5, 1)", GameConstants.LEGION, u2.getTypeString());
		assertNull("This spot should be null", game.getUnitAt(new Position(5, 2)));
	}
	
	@Test
	public void makeArcher() {
		City c = game.getCityAt(new Position(1, 1));
		game.changeProductionInCityAt(new Position (1, 1), GameConstants.ARCHER);
		assertEquals("The production in city1_1 is archers", GameConstants.ARCHER, c.getProduction());
		
		// runs two rounds...
		for (int i = 0; i < 4; i++) {
			game.endOfTurn();
			game.createProductionInCityAt(new Position(1, 1));
		}
				
		Unit u1 = game.getUnitAt(new Position (1, 1));
		assertNotNull("There is a unit at position (1, 1)", u1);
		assertEquals("There should be a archer at (1, 1)", GameConstants.ARCHER, u1.getTypeString());
		assertEquals("The productionSum should be 2", 2, c.getProductionSum());
		assertNull("Is the position north for the city null", game.getUnitAt(new Position(1, 0)));
		
		// runs five rounds...
				for (int i = 0; i < 10; i++) {
					game.endOfTurn();
					game.createProductionInCityAt(new Position(1, 1));
				}
		
		Unit u2 = game.getUnitAt(new Position(2, 1));
		assertNotNull("There is a unit at position (2, 1)", u2);
		assertEquals("There should be a archer at (2, 1)", GameConstants.ARCHER, u2.getTypeString());
		
		//because there is an archer before all this stuff at position (2, 0)
		assertNotNull("This spot shouldn't be null", game.getUnitAt(new Position (2, 2))); 
		
		//So we have to check that the next spot i null
		assertNull("This spot should be null", game.getUnitAt(new Position(1, 2)));
	}

}
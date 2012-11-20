package hotciv.standard;

import static org.junit.Assert.*;
import hotciv.different.AlphaUnitActionStrategy;
import hotciv.different.AlphaWinnerStrategy;
import hotciv.different.AlphaWorldAgingStrategy;
import hotciv.different.DeltaWorldLayoutStrategy;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;

import org.junit.Before;
import org.junit.Test;

public class TestDeltaCiv {
	private Game game;
	
	@Before
	public void setUp(){
		//UnitActionStrategy is Alpha because they have the same UnitAction, namely nothing..
		game = new GameImpl(
				new AlphaWorldAgingStrategy(), 
				new AlphaWinnerStrategy(), 
				new AlphaUnitActionStrategy(), 
				new DeltaWorldLayoutStrategy());
	}
	
	@Test
	public void redCityAt8_12() {
		assertNotNull("There should be a city at (8, 12)", game.getCityAt(new Position(8, 12)));
		assertEquals("It is a red city", game.getCityAt(new Position(8, 12)).getOwner(), Player.RED);
	}
	
	@Test
	public void blueCityAt4_5() {
		assertNotNull("There should be a city at (4, 5)", game.getCityAt(new Position(4, 5)));
		assertEquals("It is a blue city", game.getCityAt(new Position(4, 5)).getOwner(), Player.BLUE);
	}
	
	@Test
	public void testRandomTiles(){
		assertEquals("plain at (2, 2)", game.getTileAt(new Position(2, 2)).getTypeString(), GameConstants.PLAINS );
		assertEquals("plain at (0, 4)", game.getTileAt(new Position(0, 4)).getTypeString(), GameConstants.PLAINS );
		assertEquals("plain at (0, 0)", game.getTileAt(new Position(0, 0)).getTypeString(), GameConstants.OCEANS );
		assertEquals("plain at (6, 7)", game.getTileAt(new Position(6, 7)).getTypeString(), GameConstants.OCEANS );
		assertEquals("plain at (5, 2)", game.getTileAt(new Position(5, 2)).getTypeString(), GameConstants.FOREST );
		assertEquals("plain at (8, 13)", game.getTileAt(new Position(8, 13)).getTypeString(), GameConstants.FOREST );
		assertEquals("plain at (14, 6)", game.getTileAt(new Position(14, 6)).getTypeString(), GameConstants.HILLS );
		assertEquals("plain at (11, 4)", game.getTileAt(new Position(11, 4)).getTypeString(), GameConstants.MOUNTAINS );
	}
}
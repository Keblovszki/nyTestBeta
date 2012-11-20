package hotciv.standard;

import static org.junit.Assert.*;
import hotciv.different.AlphaWinnerStrategy;
import hotciv.different.AlphaWorldAgingStrategy;
import hotciv.different.GammaUnitActionStrategy;
import hotciv.framework.*;

import org.junit.Before;
import org.junit.Test;

public class TestGammaCiv {
	private Game game;

	@Before
	public void setUp(){
		game = new GameImpl(new AlphaWorldAgingStrategy(), new AlphaWinnerStrategy(), new GammaUnitActionStrategy());
	}
	
	@Test
	public void theSettlerAt4_3ShouldCreateACity(){
		Position p = new Position(4, 3);
		assertEquals("There is a settler at (4, 3)", game.getUnitAt(p).getTypeString(), GameConstants.SETTLER);
		assertEquals("The settler is red", game.getUnitAt(p).getOwner(), Player.RED);
		
		game.performUnitActionAt(p);
		
		assertNull("The settler is removed", game.getUnitAt(p));
		assertNotNull("The spot has a city", game.getCityAt(new Position(4, 3)));
		assertEquals("The city is red", game.getCityAt(p).getOwner(), Player.RED);
	}
	
	@Test
	public void theArcherShouldFortifyCorrect(){
		Position p = new Position(2, 0);
		assertEquals("There is a settler at (2, 0)", game.getUnitAt(p).getTypeString(), GameConstants.ARCHER);
		assertEquals("The archer is red", game.getUnitAt(p).getOwner(), Player.RED);
		assertEquals("The defenseStrength should be 3", 3, game.getUnitAt(p).getDefensiveStrength());

		//do the fortify
		game.performUnitActionAt(p);
		assertEquals("The defenseStrength should be 6", 6, game.getUnitAt(p).getDefensiveStrength());
		
		//trying to move the archer but it shouldn't
		game.moveUnit(p, p.getNorth(p));
		assertNotNull("something", game.getUnitAt(p));
		assertEquals("The archer shouldn't move", game.getUnitAt(p).getTypeString(), GameConstants.ARCHER);
		
		//Invoke the fortify
		game.performUnitActionAt(p);
		
		assertEquals("The defenseStrength should be 3", 3, game.getUnitAt(p).getDefensiveStrength());
	}
}

package hotciv.standard;

import static org.junit.Assert.assertEquals;
import hotciv.different.AlphaUnitActionStrategy;
import hotciv.different.AlphaWorldLayoutStrategy;
import hotciv.different.BetaWinnerStrategy;
import hotciv.different.BetaWorldAgingStrategy;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;

import org.junit.Before;
import org.junit.Test;

public class TestBetaCiv {
	private Game game;
	
	@Before
	public void setUp(){
		//UnitActionStrategy is Alpha because they have the same UnitAction, namely nothing..
		game = new GameImpl(
				new BetaWorldAgingStrategy(),
				new BetaWinnerStrategy(), 
				new AlphaUnitActionStrategy(), 
				new AlphaWorldLayoutStrategy());
	}
	
	@Test
	public void betaAgingShouldBeCorrect() {
		assertEquals("Check that the year is 4000BC from start", -4000, game.getAge());
		
		//runs 20 rounds...
		for(int i = 0; i < 40; i++) {
			game.endOfTurn();
		}
		assertEquals("Check that the year is 4000BC from start", -2000, game.getAge());
		
		//runs 20 rounds...
		for(int i = 0; i < 40; i++) {
			game.endOfTurn();
		}
		assertEquals("Check that the year is 1BC", -1, game.getAge());
		
		//runs 1 round...
		for(int i = 0; i < 2; i++) {
			game.endOfTurn();
		}
		assertEquals("Check that the year is 1AD", 1, game.getAge());
		
		//runs 1 round...
		for(int i = 0; i < 2; i++) {
			game.endOfTurn();
		}
		assertEquals("Check that the year is 50AD", 50, game.getAge());
		
		//runs 29 rounds...
		for(int i = 0; i < 58; i++) {
			game.endOfTurn();
		}
		assertEquals("Check that the year is 1500AD", 1500, game.getAge());
		
		//runs 5 rounds...
		for(int i = 0; i < 10; i++) {
			game.endOfTurn();
		}
		assertEquals("Check that the year is 1750AD", 1750, game.getAge());
		
		//runs 6 rounds...
		for(int i = 0; i < 12; i++) {
			game.endOfTurn();
		}
		assertEquals("Check that the year is 1900AD", 1900, game.getAge());
		
		//runs 14 rounds...
		for(int i = 0; i < 28; i++) {
			game.endOfTurn();
		}
		assertEquals("Check that the year is 1970AD", 1970, game.getAge());
		
		//runs 20 rounds...
		for(int i = 0; i < 40; i++) {
			game.endOfTurn();
		}
		assertEquals("Check that the year is 1990AD", 1990, game.getAge());
	}
	
	@Test
	public void theWinnerShouldBeRedIfRedHasAllTheCities() {
		game.moveUnit(new Position(2, 0), new Position(4, 1));
		assertEquals("Red should be the winner", game.getWinner(), Player.RED);
	}
	
	@Test
	public void blueWinner() {
		game.moveUnit(new Position(3, 2), new Position(1, 1));
		assertEquals("Blue winner", game.getWinner(), Player.BLUE);
	}
}
	

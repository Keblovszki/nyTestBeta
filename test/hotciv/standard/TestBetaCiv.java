package hotciv.standard;

import hotciv.different.BetaWorldAgingStrategy;
import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;

public class TestBetaCiv {
	private Game game;
	
	@Before
	public void setUp(){
		game = new GameImpl(new BetaWorldAgingStrategy());
	}
	
	@Test
	public void betaAgingShouldBeCorrect(){
		assertEquals("Check that the year is 4000BC from start", -4000, game.getAge());
		
		//runs 20 rounds...
		for(int i = 0; i < 40; i++){
			game.endOfTurn();
		}
		assertEquals("Check that the year is 4000BC from start", -2000, game.getAge());
		
		//runs 20 rounds...
		for(int i = 0; i < 40; i++){
			game.endOfTurn();
		}
		assertEquals("Check that the year is 1BC", -1, game.getAge());
		
		//runs 1 round...
		for(int i = 0; i < 2; i++){
			game.endOfTurn();
		}
		assertEquals("Check that the year is 1AD", 1, game.getAge());
		
		//runs 1 round...
		for(int i = 0; i < 2; i++){
			game.endOfTurn();
		}
		assertEquals("Check that the year is 50AD", 50, game.getAge());
		
		//runs 29 rounds...
		for(int i = 0; i < 58; i++){
			game.endOfTurn();
		}
		assertEquals("Check that the year is 1500AD", 1500, game.getAge());
		
		//runs 5 rounds...
		for(int i = 0; i < 10; i++){
			game.endOfTurn();
		}
		assertEquals("Check that the year is 1750AD", 1750, game.getAge());
		
		//runs 6 rounds...
		for(int i = 0; i < 12; i++){
			game.endOfTurn();
		}
		assertEquals("Check that the year is 1900AD", 1900, game.getAge());
		
		//runs 14 rounds...
		for(int i = 0; i < 28; i++){
			game.endOfTurn();
		}
		assertEquals("Check that the year is 1970AD", 1970, game.getAge());
		
		//runs 20 rounds...
		for(int i = 0; i < 40; i++){
			game.endOfTurn();
		}
		assertEquals("Check that the year is 1990AD", 1990, game.getAge());
	}
}
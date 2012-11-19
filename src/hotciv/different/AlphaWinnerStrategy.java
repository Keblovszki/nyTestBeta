package hotciv.different;

import hotciv.framework.*;
import hotciv.standard.*;

public class AlphaWinnerStrategy implements WinnerStrategy {
	private Game game;
	
	@Override
	public Player winner(){
		int age = game.getAge();
		if(age >= -3000){
			return Player.RED;
		}
		return null;
	}

}

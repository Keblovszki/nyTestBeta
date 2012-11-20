package hotciv.different;

import hotciv.framework.*;
import hotciv.standard.*;

public class AlphaWinnerStrategy implements WinnerStrategy {
	private Game game;
	
	@Override
	public Player winner(){
		int age = this.game.getAge();
		if(age >= -3000){
			return Player.RED;
		}
		return null;
	}
	
	@Override
	public void setGame(GameImpl game) {
		this.game = game;
	}

}

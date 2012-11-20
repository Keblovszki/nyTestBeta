package hotciv.standard;

import hotciv.framework.*;

public interface WinnerStrategy {
	
	public Player winner();
	public void setGame(GameImpl game);

}

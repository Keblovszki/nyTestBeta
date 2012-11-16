package hotciv.different;

import hotciv.standard.WorldAgingStrategy;

public class AlphaWorldAgingStrategy implements WorldAgingStrategy{
	
	public int worldAging(int age){
		return age + 100;
	}
}

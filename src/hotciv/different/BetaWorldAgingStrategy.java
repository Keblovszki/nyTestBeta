package hotciv.different;

import hotciv.standard.WorldAgingStrategy;

public class BetaWorldAgingStrategy implements WorldAgingStrategy{

	@Override
	public int worldAging(int age) {
		//between 4000BC and 100BC
		if(age < -100){
			return age + 100;
		}
		
		//Around birth of Christ
		else if(age == -100){
			return age = -1;
		}
		else if(age == -1){
			return age = 1;
		}
		else if(age == 1){
			return age = 50;
		}
		
		//between 50 AD and 1750 AD
		else if(age < 1750){
			return age + 50;
		}
		
		//between 1750 AD and 1900 AD
		else if(age < 1900){
			return age + 25;
		}
		
		//between 1900 AD and 1970 AD
		else if(age < 1970){
			return age + 5;
		}
		
		//After 1970 AD
		return age + 1;
	}

}

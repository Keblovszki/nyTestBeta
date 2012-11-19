package hotciv.standard;
import hotciv.framework.*;

public class UnitImpl implements Unit{
	private String type;
	private Player owner;
	private int defenseStrength = 0;
	private int extraDefenseStrength = 0;
	private int attackStrength = 0;
	
	//Constructor
	public UnitImpl(Player p, String t){
		owner = p;
		type = t;
		
	}
	
	@Override
	public String getTypeString() {
		return type;
	}

	@Override
	public Player getOwner(){
		return owner;
	}	
	
	@Override
	public int getMoveCount(){
		return 0;
	}
	
	@Override
	public int getDefensiveStrength(){
		if(type.equals(GameConstants.ARCHER)) {
			defenseStrength = 3;
		}
		if(type.equals(GameConstants.SETTLER)) {
			defenseStrength = 3;
		}
		if(type.equals(GameConstants.LEGION)) {
			defenseStrength = 2;
		}
		return defenseStrength + extraDefenseStrength;
	}
	
	@Override
	public void setDefensiveStrength(int bonusStrength){
		extraDefenseStrength += bonusStrength;
	}
	
	@Override
	 public int getAttackingStrength(){
		if(type.equals(GameConstants.ARCHER)) {
			attackStrength = 2;
		}
		if(type.equals(GameConstants.LEGION)) {
			attackStrength = 4;
		}
		if(type.equals(GameConstants.SETTLER)) {
			attackStrength = 0;
		}
		return attackStrength;
	}

}

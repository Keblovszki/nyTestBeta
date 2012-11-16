package hotciv.standard;
import hotciv.framework.*;

public class UnitImpl implements Unit{
	private String type;
	private Player owner;
	
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
		return 0;
	}
	
	@Override
	 public int getAttackingStrength(){
		return 0;
	}

}

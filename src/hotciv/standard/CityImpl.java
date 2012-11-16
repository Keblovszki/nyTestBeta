package hotciv.standard;

import hotciv.framework.*;

public class CityImpl implements City {
	private Player owner;
	private int production;
	private String unitProduction = null;
	
	//Constructor
	public CityImpl(Player p){
		owner = p;
		production = 0;
	}
	
	@Override
	public Player getOwner() {
		return owner;
	}

	@Override
	public int getSize() {
		return 1;
	}

	@Override
	public String getProduction() {
		return unitProduction;
	}
	
	@Override
	public String getWorkforceFocus() {
		return null;
	}
	
	@Override
	public void doProductionSum(){
		production += 6;
	}
	
	@Override
	public int getProductionSum(){
		return production;
	}
	
	public void setProductionSum(int change){
		production += change; 
	}
	
	public void setProduction(String unitType){
		unitProduction = unitType;
	}
}

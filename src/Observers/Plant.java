package Observers;

import Actions.TempWaterRatio;
import Observable.Subject;
import ProjectGreenHouse.ProjectGreenHouse;

public class Plant implements Observer, TempWaterRatio{
	
	private int waterRequired;
	private int optimalTemp;
	private int tempDifference;
	private int waterDifference;
	private String type;
	private double health;
	
	Subject house;
	
	//Konstruktor
	public Plant(int water, int temp, String type, Subject greenHouse) {
		this.waterRequired=water;
		this.optimalTemp=temp;
		this.waterDifference=ProjectGreenHouse.gHCondition.getWaterLevel()-this.waterRequired;
		this.tempDifference=ProjectGreenHouse.gHCondition.getTemperature()-this.optimalTemp;
		this.type=type;
		this.health=1;
		
		this.house=greenHouse;
		this.house.registerObserver(this);
	}
	//*******************************************************************************	

	//Metody prze³oniête z interfejsu Observer
	@Override
	public void update(int waterLevel, int temperature,long time) {
		this.waterDifference=waterLevel-this.waterRequired;
		this.tempDifference=temperature-this.optimalTemp;
		this.health=this.ratio();
	}
	//*******************************************************************************	
	
	//Metody przes³oniête z interfejsu TempWaterRatio
	@Override
	public double ratio() {
		return 1+this.waterDifference/15.0+this.tempDifference/10;
	}
	//*******************************************************************************	
	
	//Get-ery
	public String getType() {
		return type;
	}
	public int getTempDifference() {
		return tempDifference;
	}
	public int getWaterDifference() {
		return waterDifference;
	}
	public double getHealth() {
		return health;
	}
	//*******************************************************************************	
	
}

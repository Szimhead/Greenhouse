package Observers;

import Actions.TempWaterRatio;
import Controler.Controler1;
import Observable.Subject;

public class PlantsValue implements TempWaterRatio,Observer {
	private int waterLevel;
	private int temperature;
	private double value;
	private int money;
	private int income;
	private int daysTotal;
	private long days;

	Controler1 controler;
	Subject house;

	//Konstruktor
	public PlantsValue(Subject house) {
		this.money=1000;
		this.house=house;
		this.income=20;
		this.daysTotal=0;
		this.days=0;
		
		house.registerObserver(this);
	}
	//*******************************************************************************	

	//Ustawienie kontrolera
	public void setControler(Controler1 controler) {
		this.controler=controler;
	}
	//*******************************************************************************	

	//Zmiana wartoœci szklarni w wyniku obliczenia wskaznika op³acalnoœci
	public void changeValue() {
		this.value=ratio();
	}
	//*******************************************************************************	

	//Op³aty za wodê, ciep³o i ch³odzenie szklarni
	public void payForWater() {
		this.money-=70;
	}

	public void payForHeat() {
		this.money-=94;
	}

	public void payForCooling() {
		this.money-=48;
	}
	//*******************************************************************************	

	//Obni¿enie zarobków w wyniku œmierci roœlin
	public void lowerIncome() {
		this.income--;
	}
	//*******************************************************************************	

	//Obliczenie potencjalnych zysków przy danym wskazniku op³acalnoœci i liczbie ¿ywych roœlin
	public double currentProfit() {
		return this.income*40-Math.abs((1-this.value)*700);
	}
	//*******************************************************************************	

	//Realizacja sprzeda¿y
	public void sell() {
		this.money+=this.currentProfit();
	}
	//*******************************************************************************	

	//Get-ery
	public int getMoney() {
		return this.money;
	}

	public int getDays() {
		return this.daysTotal;
	}
	//*******************************************************************************	
	
	//Reset stanu - wynik posadzenia nowych roœlin
	public void resetIncome() {
		this.income=20;
	}
	//*******************************************************************************	
	
	//Metody przes³oniête z interfejsu Observer
	@Override
	public void update(int waterLevel, int temperature,long time) {
		this.waterLevel=waterLevel;
		this.temperature=temperature;
		if(time!=this.days) {
			this.daysTotal++;
			this.days=time;
		}
		
		this.changeValue();
	}
	//*******************************************************************************	
	
	//Metody przes³oniête z interfejsu TempWaterRatio
	@Override
	public double ratio() {
		return this.temperature/26.6*this.waterLevel/34.6;
	}
	//*******************************************************************************	
}

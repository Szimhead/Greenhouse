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

	//Zmiana warto�ci szklarni w wyniku obliczenia wskaznika op�acalno�ci
	public void changeValue() {
		this.value=ratio();
	}
	//*******************************************************************************	

	//Op�aty za wod�, ciep�o i ch�odzenie szklarni
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

	//Obni�enie zarobk�w w wyniku �mierci ro�lin
	public void lowerIncome() {
		this.income--;
	}
	//*******************************************************************************	

	//Obliczenie potencjalnych zysk�w przy danym wskazniku op�acalno�ci i liczbie �ywych ro�lin
	public double currentProfit() {
		return this.income*40-Math.abs((1-this.value)*700);
	}
	//*******************************************************************************	

	//Realizacja sprzeda�y
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
	
	//Reset stanu - wynik posadzenia nowych ro�lin
	public void resetIncome() {
		this.income=20;
	}
	//*******************************************************************************	
	
	//Metody przes�oni�te z interfejsu Observer
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
	
	//Metody przes�oni�te z interfejsu TempWaterRatio
	@Override
	public double ratio() {
		return this.temperature/26.6*this.waterLevel/34.6;
	}
	//*******************************************************************************	
}

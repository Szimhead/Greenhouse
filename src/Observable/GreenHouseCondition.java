package Observable;

import java.util.ArrayList;

import Controler.Controler1;
import Level.Difficulty;
import Observers.*;


public class GreenHouseCondition implements Subject{
	ArrayList<Observer> ObserverList;

	Controler1 controler;
	private Difficulty difficultyLevel;
	
	private int waterLevel;
	private int temperature;
	private long time;
	
	//Konstruktor
	public GreenHouseCondition() {
		 ObserverList = new ArrayList<Observer>();
		 this.waterLevel=40;
		 this.temperature=25;
		 this.time=1;
	}
	//*******************************************************************************	
	
	//Przypisanie kontrolera
	public void setControler(Controler1 controler) {
		this.controler=controler;
	}
	//*******************************************************************************	
	
	//Zmiana stanu szklarni w wyniku dzia³ania gracza
	public void putWater() {
		this.waterLevel+=5;
		this.notifyObservers();
	}
	
	public void warmUp() {
		this.temperature+=3;
		this.waterLevel-=1;
		this.notifyObservers();
	}
	
	public void coolDown() {
		this.temperature-=3;
		this.notifyObservers();
	}
	
	public void setLevel(Difficulty level) {
		this.difficultyLevel=level;
	}
	//*******************************************************************************	
	
	//Pozyskanie stanu szklarni
	public int getWaterLevel() {
		return waterLevel;
	}
	
	public int getTemperature() {
		return temperature;
	}
	
	public long getTime() {
		return time;
	}
	//*******************************************************************************	
	
	//Reset warunków - posadzenie nowych roœlin
	public void resetConditions() {
		this.waterLevel=40;
		this.temperature=25;
		this.time=1;
		this.notifyObservers();
	}
	//*******************************************************************************	
	
	//Metody przes³oniête z interfejsu Subject
	@Override
	public void registerObserver(Observer observer) 
	{	
		ObserverList.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) 
	{
		ObserverList.remove(observer);
	}
	
	@Override
	public void notifyObservers() 
	{
		for(int i = 0; i<ObserverList.size(); i++) 
			ObserverList.get(i).update(this.waterLevel,this.temperature,this.time);
		controler.upload();
		controler.checkPlants();
	}
	//*******************************************************************************	
	
	//Zmiana stanu szklarni w wyniku symulacji
	 public void drainWater() {
		this.waterLevel-= this.difficultyLevel.waterLoss();
		this.notifyObservers();
	}
	 
	 public void increaseTemp() {
		 this.temperature+=this.difficultyLevel.tempChange();
		 this.waterLevel-=this.difficultyLevel.waterEvaporation();
		 this.notifyObservers();
	 }
	 
	 public void decreaseTemp() {
		 this.temperature-=this.difficultyLevel.waterEvaporation();
		 this.notifyObservers();
	 }
	 
	 public void timePassed() {
		 this.time++;
		 this.notifyObservers();
		 }
	//*******************************************************************************	
}

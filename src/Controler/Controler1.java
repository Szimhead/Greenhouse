package Controler;

import GUI.Window;
import Observable.GreenHouseCondition;
import Observers.GreenHouse;
import Observers.PlantsValue;
import ProjectGreenHouse.ProjectGreenHouse;
import Level.*;


public class Controler1 {
	private Window window;
	private GreenHouseCondition ghc;
	private GreenHouse gHouse;
	private PlantsValue businessman;
	
	//Konstruktor
	public Controler1(Window window, GreenHouseCondition ghc, GreenHouse gHouse, PlantsValue businessman) {
		this.window=window;
		this.ghc=ghc;
		this.gHouse=gHouse;
		this.businessman=businessman;
	}
	//*******************************************************************************
	
	//Akcje spowodowane uruchomieniem przycisk�w
	public void waterPressed() {
		this.ghc.putWater();
		this.businessman.payForWater();
		this.upload();
	}
	
	public void warmPressed() {
		this.ghc.warmUp();
		this.businessman.payForHeat();
		this.upload();
	}
	
	public void coldPressed() {
		this.ghc.coolDown();
		this.businessman.payForCooling();
		this.upload();
	}
	
	public void sellPressed() {
		this.businessman.sell();
		this.businessman.resetIncome();
		this.ghc.resetConditions();
		this.window.resetPlants();
	}
	
	public void hardLevel() {
		this.ghc.setLevel(new Hard());
		this.window.play();
		this.upload();
	}
	
	public void easyLevel() {
		this.ghc.setLevel(new Easy());
		this.window.play();
		this.upload();
	}
	//*******************************************************************************	
	
	//Obni�enie zysk�w biznesmena z powodu �mierci ro�liny
	public void dead() {
		this.businessman.lowerIncome();
	}
	//*******************************************************************************	
	
	//Pozyskiwanie stany ro�liny
	public int waterDif(int i) {
		return gHouse.plants.get(i).getWaterDifference();
	}
	
	public int tempDif(int i) {
		return gHouse.plants.get(i).getTempDifference();
	}
	
	public String plantType(int i) {
		return gHouse.plants.get(i).getType();
	}
	
	public double getRatio(int i) {
		return gHouse.plants.get(i).getHealth();
	}
	//*******************************************************************************	
	
	//Pozyskiwanie stanu biznesmena
	public double getValue() {
		return businessman.ratio();
	}
	
	public int getBalance() {
		return this.businessman.getMoney();
	}
	
	public double getCurrentProfit() {
		return this.businessman.currentProfit();
	}
	//*******************************************************************************	
	
	//Czas (od posadzenia nowych ro�lin)
	public long getDays() {
		return ghc.getTime();
	}
	//*******************************************************************************	
	
	//Polecenie od�wie�enia widoku
	public  void upload() {
		this.window.view();
	}
	//*******************************************************************************	
	
	//Kontrola �ycia ro�lin
	public void checkPlants() {
		this.window.anyLeft();
	}
	//*******************************************************************************
	
	//Bankrut biznesmena i zatrzymanie czasu - skutkuje zako�czeniem gry
	public void allDead() {
		this.stop();
		this.window.gameOver();
	}
	//*******************************************************************************
	
	public void stop() {
		ProjectGreenHouse.endOfTime();
	}
	//*******************************************************************************	
	
	//Pozyskanie informacji o liczbie dni dzia�alno�ci szklarni
	public int howManyDays() {
		return this.businessman.getDays();
	}
	//*******************************************************************************	
}

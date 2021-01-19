//MAIN
package ProjectGreenHouse;

import java.util.Random;

import Controler.Controler1;
import GUI.*;
import Observable.*;
import Observers.*;

public class ProjectGreenHouse {
		public static GreenHouseCondition gHCondition;
		public static GreenHouse gHouse;
		public static long startTime;
		public static Window window;
		public static PlantsValue businessman;
		
	public static void main(String[] args) {
		game();
	}

	//Tworzenie elementów gry
	public static void game() {
		gHCondition=new GreenHouseCondition();
		gHouse=new GreenHouse(gHCondition);
		businessman=new PlantsValue(gHCondition);
		window=new Window();
		Controler1 controler=new Controler1(window, gHCondition,gHouse,businessman);
		gHCondition.setControler(controler);
		gHouse.setControler(controler);
		window.setControler(controler);
		businessman.setControler(controler);
		window.chooseLevel();
		simulation();
	}
	//*******************************************************************************
	
	//Symulacja - losowe zmiany temperatury, spadek poziomu wody w szklarni
	public static void simulation() {
		startTime=System.currentTimeMillis();
		int dif=6000;
		Random random=new Random();
		boolean b;
		while(startTime>0) {
			if((System.currentTimeMillis()-startTime)>dif) {
				gHCondition.drainWater();
				dif+=4500;
				b=random.nextBoolean();
				if(b) {
					gHCondition.increaseTemp();
				}
				else
					gHCondition.decreaseTemp();
				gHCondition.timePassed();
			}
		}
	}
	//*******************************************************************************	
	
	//Zatrzymanie czasu
	public static void endOfTime() {
		startTime=0;
	}
	//*******************************************************************************	
}

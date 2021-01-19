package Observers;

import java.util.ArrayList;

import Controler.Controler1;
import Observable.Subject;
import Plants.*;

public class GreenHouse {
	 public ArrayList<Plant> plants;
	 Controler1 controler;
	
	 //Konstruktor
	public GreenHouse(Subject greenHouse) {
		plants=new ArrayList<Plant>();
		this.fill(greenHouse);
	}
	//*******************************************************************************	
	
	//Ustawienie kontrolera
	public void setControler(Controler1 controler) {
		this.controler=controler;
	}
	//*******************************************************************************	
	
	//Wype³nienie listy ró¿nymi gatunkami roœlin
	public void fill(Subject greenHouse) {
		Cactus c1=new Cactus(greenHouse);
		Cactus c2=new Cactus(greenHouse);
		Cactus c3=new Cactus(greenHouse);
		Cactus c4=new Cactus(greenHouse);
		Cactus c5=new Cactus(greenHouse);
		Haworthia h1=new Haworthia(greenHouse);
		Haworthia h2=new Haworthia(greenHouse);
		Haworthia h3=new Haworthia(greenHouse);
		Haworthia h4=new Haworthia(greenHouse);
		Haworthia h5=new Haworthia(greenHouse);
		Haworthia h6=new Haworthia(greenHouse);
		Haworthia h7=new Haworthia(greenHouse);
		Haworthia h8=new Haworthia(greenHouse);
		Alocasia a1=new Alocasia(greenHouse);
		Alocasia a2=new Alocasia(greenHouse);
		Alocasia a3=new Alocasia(greenHouse);
		Alocasia a4=new Alocasia(greenHouse);
		Alocasia a5=new Alocasia(greenHouse);
		Alocasia a6=new Alocasia(greenHouse);
		Alocasia a7=new Alocasia(greenHouse);
		
		plants.add(c1);
		plants.add(c2);
		plants.add(c3);
		plants.add(c4);
		plants.add(c5);

		plants.add(h1);
		plants.add(h2);
		plants.add(h3);
		plants.add(h4);
		plants.add(h5);
		plants.add(h6);
		plants.add(h7);
		plants.add(h8);
		
		plants.add(a1);
		plants.add(a2);
		plants.add(a3);
		plants.add(a4);
		plants.add(a5);
		plants.add(a6);
		plants.add(a7);
	}
	//*******************************************************************************	
}

package Plants;

import Observable.Subject;
import Observers.Plant;

public class Cactus extends Plant{

	public Cactus(Subject greenHouse) {
		super(32,30,"kaktus",greenHouse);
	}
}

package Observable;
import Observers.*;

public interface Subject {

		  public void registerObserver(Observer observer);
	      public void removeObserver(Observer observer);
	      public void notifyObservers();   
	
}

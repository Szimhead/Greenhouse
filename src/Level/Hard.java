package Level;

public class Hard implements Difficulty{
	
	@Override
	public int waterLoss() {
		return 4;
	}
	
	@Override
	public int tempChange() {
		return 3;
	}
	
	@Override
	public int waterEvaporation() {
		return 2;
	}

}

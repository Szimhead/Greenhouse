package Level;

public class Easy implements Difficulty{
	
	@Override
	public int waterLoss() {
		return 3;
	}
	
	@Override
	public int tempChange() {
		return 1;
	}
	
	@Override
	public int waterEvaporation() {
		return 1;
	}
}

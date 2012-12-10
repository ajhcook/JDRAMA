package behaviors;
import Intelligence;
import Robot;

public class TurnBehavior extends Behavior {
	public TurnBehavior(Robot r, Intelligence i)
	{
		robot = r;
		intelligence = i;
	}
	
	public boolean takeControl()
	{
		return i.shouldTurn();
	}
	
	public void action()
	{
		
	}
	
	public void suppress()
	{
		suppressed = true;
	}
	
	private Robot robot;
	private Intelligence intelligence;
	
	private boolean suppressed = false;
}
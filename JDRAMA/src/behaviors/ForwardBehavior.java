package behaviors;
import Robot;


public class ForwardBehavior extends Behavior
{
	public ForwardBehavior(Robot robot)
	{
		this.robot = robot;
	}
	
	public boolean takeControl()
	{
		return true;
	}
	
	public void action()
	{
		robot.leftMotor.forward();
		robot.rightMotor.forward();
	}
	
	public void suppress()
	{
		suppressed = true;
	}
	
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}
	
	Robot robot;
	private int speed;
	private boolean suppressed;
}

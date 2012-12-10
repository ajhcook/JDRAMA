import lejos.nxt.Button;
import lejos.nxt.ButtonListener;



public class Overlord
{
	
	/**
	 * Overarching main program that runs the robot.
	 */
	public Overlord()
	{

	}
	
	
	public static void main(String[] args) throws Exception 
	{
		Button.ENTER.addButtonListener
		(new ButtonListener() 
			{
		      	public void buttonPressed(Button b) 
		      	{
		      		System.exit(0);
		      	}

		      	public void buttonReleased(Button b) 
		      	{
		    	  
		      	} 
			}
		);

	}
}

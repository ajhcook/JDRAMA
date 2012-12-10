import java.util.ArrayList;
import java.util.List;
public class Intelligence 
{
	public Intelligence(int theAngleChange)
	{
		angleChange = theAngleChange;
		dataPoints = (160/theAngleChange) + 1;
		theMemory = new Memory();
	}
	
	public float getBestHeading(List<Opening> gapsList)
	{
		//a heading of 0 is straight forward, positive is right, negative is left
		float middle = (float)((dataPoints-1)/2.0);
		float bestHeadingDistance = dataPoints;
		int angleOfBestHeading = 0;
		for(Opening opening : gapsList)				//all numbers refer to dataPoint locations, not degrees
		{
			float distanceOffCenter = (float)((opening.getStartIndex()+opening.getEndIndex())/2.0)-middle;		//distance from the midpoint of the gap to the midpoint of the bot's vision
			if (Math.abs(distanceOffCenter) < Math.abs(bestHeadingDistance))
			{
				bestHeadingDistance = distanceOffCenter;
				angleOfBestHeading = (opening.getEndIndex()-opening.getStartIndex())*angleChange;
			}
		}
		bestHeading = bestHeadingDistance*angleChange;
		theMemory.addEntry(angleOfBestHeading);
		return bestHeading;	
	}
	public List<Opening> findOpenings(int[] theVisionArray)
	{
		ArrayList<Opening> openingsList = new ArrayList<Opening>();
		Opening opening = null;
		boolean openingStarted = false;
		for(int index = 0; index <= theVisionArray.length-1 ; index++)
		{
			if (theVisionArray[index] >= OPENING_THRESHOLD && openingStarted == false)		//the loop reaches a new opening
			{
				opening = new Opening();
				opening.setStartIndex(index);
				openingStarted = true;
			}
			else if (openingStarted == true && theVisionArray[index] < OPENING_THRESHOLD)	//the loop reaches the end of an opening
			{
				opening.setEndIndex(index - 1);
				openingsList.add(opening);
				openingStarted = false;
			}
			if (index == theVisionArray.length-1 && theVisionArray[index] >= OPENING_THRESHOLD)	//the loop reaches the end of the vision array but hasn't closed an opening yet
			{
				opening.setEndIndex(index);
				openingsList.add(opening);
			}
		}

		
		return openingsList;
	}
	
	public List<Opening> findGaps(List<Opening> theOpenings, int[] theVision)
	{
		ArrayList<Opening> gaps = new ArrayList<Opening>();
		
		for ( Opening opening : theOpenings )
		{
			try
			{
				// Distance immediately preceding the start of the range
				int precedingDistance = theVision[opening.getStartIndex() - 1];
				
				// Distance immediately succeeding the end of the range
				int succeedingDistance = theVision[opening.getEndIndex() + 1];
				
				// Law of cosines determines the width of the opening in centimeters
				double openingWidth = Math.sqrt(
						Math.pow(precedingDistance, 2) + Math.pow(succeedingDistance, 2)
						- 2 * precedingDistance * succeedingDistance
						    * Math.cos(CONE_WIDTH + ((opening.getEndIndex() - opening.getStartIndex())
						    		    * angleChange) * (Math.PI/180)));
				
				int o = (int) openingWidth;
				
				if ( openingWidth > BOT_WIDTH )
				{
					gaps.add(opening);
				}
			}
			catch (Exception e)
			{
				gaps.add(opening);
			}
		}

		return gaps;	
	}
	
	public int getTimeMoving()		//lame method that just passes along power info
	{
		return theMemory.getMilliseconds();
	}
	
	private int angleChange;
	private static final int BOT_WIDTH = 40;	
	private static final int CONE_WIDTH = 15;
	private static final int OPENING_THRESHOLD = 60;
	private int dataPoints;
	private float bestHeading;
	private Memory theMemory;
	
	/**
	 * Should the robot turn given the current data? Updated by self whenever
	 * new data comes in.
	 */
	public boolean shouldTurn;
}

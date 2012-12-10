
public class Opening 
{
	public int getStartIndex() 
	{
		return startIndex;
	}
	
	public void setStartIndex(int startIndex)
	{
		this.startIndex = startIndex;
	}
	
	public int getEndIndex() 
	{
		return endIndex;
	}
	
	public void setEndIndex(int endIndex)
	{
		this.endIndex = endIndex;
	}
	
	public String toString() 
	{
		return "(" + startIndex + ", " + endIndex + ")";
	}

	public int startIndex;
	public int endIndex;
}
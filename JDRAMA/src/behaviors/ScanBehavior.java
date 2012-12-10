package behaviors;

enum ScanRange {
	SCAN_RANGE_NARROW,
	SCAN_RANGE_WIDE
}

public class ScanBehavior {
	public ScanBehavior(Intelligence i) {
		intelligence = i;
	}
	
	public boolean takeControl() {
		return true;
	}
	
	public void action()
	{
		List<Gap> gaps;
		
		if ( range == ScanRange.SCAN_RANGE_NARROW ) {
			
		} else if ( range == ScanRange.SCAN_RANGE_WIDE ) {
			
		}
		
		i.sendNewGapData(gaps);
	}
	
	public void suppress()
	{
		suppressed = true;
	}
	
	public void setScanRange(ScanRange _range) {
		range = _range;
	}
	
	private Intelligence intelligence;
	
	private boolean suppressed;
	private ScanRange range;
}

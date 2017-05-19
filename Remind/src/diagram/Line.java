package diagram;

public class Line {

	private Topic startTopic;
	private Topic endTopic;
	private int key;
	private boolean folded;

	public Line() {
		this.startTopic = null;
		this.endTopic	= null;
	}

	public Line(Topic firstTopic, Topic secondTopic) {
		this.startTopic  = firstTopic;
		this.endTopic 	 = secondTopic;
		this.key 		 = endTopic.getKey();		
	}

	public Topic getStartTopic() {
		return startTopic;
	}

	public void setStartTopic(Topic startTopic) {
		this.startTopic = startTopic;
	}

	public Topic getEndTopic() {
		return endTopic;
	}

	public void setEndTopic(Topic endTopic) {
		this.endTopic = endTopic;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	
	public boolean equals(Object otherLine) {
		if(otherLine == null) return false;
		if(!otherLine.getClass().equals(this.getClass())) return false;
		
		Line j = (Line)otherLine;
		if(!((j.startTopic == startTopic && j.endTopic == endTopic) ||
			 (j.startTopic == endTopic && j.endTopic == startTopic))) { return false; }
		return true;
	}

	public boolean isFolded() {
		return folded;
	}

	public void setFolded(boolean folded) {
		this.folded = folded;
	}
}

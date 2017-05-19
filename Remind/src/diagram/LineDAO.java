package diagram;

import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LineDAO {

//	private HashMap<Integer, Line> lineDAO;
	private List<Line> lineDAO;

	public LineDAO() {

		lineDAO = new LinkedList<Line>();
	}

	public LineDAO(List<Line> lineDAO) {
		this.lineDAO = lineDAO;
	}

	public void insert(Line line) {

		lineDAO.add(line);
	}

	public void remove(Line line) {
		lineDAO.remove(line);
	}

	public List<Line> searchForTopic(Topic topic) {
		List<Line> tempLine = new LinkedList<Line>();
		Iterator it = lineDAO.iterator();
		while(it.hasNext()){
			Line temp = (Line)it.next();
			if(temp.getStartTopic().equals(topic)|| temp.getEndTopic().equals(topic))
				tempLine.add(temp);
		}
		return tempLine;

	}
	
	public Line searchForTopic(Topic startTopic, Topic endTopic){
		Iterator it = lineDAO.iterator();
		while(it.hasNext()){
			Line temp = (Line)it.next();
			if(temp.getStartTopic().equals(startTopic)&& temp.getEndTopic().equals(endTopic))
				return temp;
		}
		return null;
	}
	

	public Line searchForKey(int key) {

		return lineDAO.get(key);
	}

	public Line searchForLine(Line line) {

		return lineDAO.get(line.getKey());
	}


	public boolean modifyFirstTopic(Line line, Topic firstTopic) {
		Line dst = lineDAO.get(line.getKey());
		dst.setStartTopic(firstTopic);
		if (dst == null) {
			return false;
		}
		return true;
	}

	public boolean modifySecondTopic(Line line, Topic secondTopic) {

		Line dst = lineDAO.get(line.getKey());
		dst.setEndTopic(secondTopic);
		if (dst == null) {
			return false;
		}
		return true;
	}

	public List<Line> getLineDAO() {
		return lineDAO;
	}

	public void setLineDAO(List<Line> lineDAO) {
		this.lineDAO = lineDAO;
	}
	public void lineFoldedSetting(Line line, boolean folded){
		line.setFolded(folded);
	}

	@Override
	public String toString() {
		return "LineDAO [lineDAO=" + lineDAO + "]";
	}

//	public boolean modifyColor(Line line, Color color) {
//
//		Line dst = lineDAO.get(line.getKey());
//		dst.setColor(color);
//
//		if (dst == null) {
//
//			return false;
//		}
//		return true;
//	}
//
//	public boolean modifyLineShape(Line line, String lineShape) {
//
//		Line dst = lineDAO.get(line.getKey());
//		dst.setLineShape(lineShape);
//
//		if (dst == null) {
//
//			return false;
//		}
//		return true;
//	}

	

}

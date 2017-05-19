package main;

import java.util.Iterator;
import java.util.List;

import mindmap.view.center.RemindMindMapView;
import diagram.Topic;

public class TextModeController {
	public final static int NOTE = 0;
	public final static int EXAM = 1;

	private int selectMode = NOTE;
	private MindMapController c;
	// private List<Topic> list;
	private String[][] str;

	public int getSelectMode() {
		return selectMode;
	}

	public void setSelectMode(int selectMode) {
		this.selectMode = selectMode;
	}

	public TextModeController(RemindMindMapView mindMapView) {
		this(new MindMapController(), mindMapView);
	}

	public String[][] getStr() {
		return str;
	}

	public void setStr(String[][] str) {
		this.str = str;
	}

	public TextModeController(MindMapController c, RemindMindMapView mindMapView) {
		this.c = c;
		str = listToArray(c.getTopicList(mindMapView.getPanelNum(mindMapView.getTapPane().getSelectedIndex())));
	}

	public String[][] listToArray(List<Topic> list) {
		if (list.size() == 0)
			return null;
		String[][] arr = new String[list.size()][4];
		int i = 0;
		Iterator<Topic> it = list.iterator();
		while (it.hasNext()) {
			Topic topic = it.next();
			arr[i][0] = topic.getTitle();
			System.out.println(" ย๎นไ"+arr[i][0]);
			arr[i][1] = topic.getNote().getWhat();
			System.out.println(" ย๎นไ"+arr[i][1]);
			arr[i][2] = topic.getNote().getWhy();
			arr[i++][3] = topic.getNote().getHow();
		}
		return arr;
	}

	public void changeMode() {
		if (selectMode == NOTE)
			selectMode = EXAM;
		else
			selectMode = NOTE;
	}

	public int check(String[][] test) {
		
		int correct = 0;
		
		for(int i=0; i < str.length; i++){
			System.out.println("ธÞที");
			System.out.println(str[i][1]);
			System.out.println(test[i][0]);
			if(str[i][1].trim().equals(test[i][0].trim()) 
					&& str[i][2].trim().equals(test[i][1].trim()) 
					&& str[i][3].trim().equals(test[i][2].trim())){
			
//			System.out.println(i);
//			System.out.println(str[i][0]);
//			System.out.println(str[i][1]);
//			System.out.println(str[i][2]);
			
				correct++;
			}
		}
		
		return correct;
	}
}
package main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import diagram.Line;
import diagram.LineDAO;
import diagram.Memo;
import diagram.Note;
import diagram.Topic;
import diagram.TopicDAO;

public class MindMapController {
	HashMap<Integer, TopicDAO> topicDaoList;
	HashMap<Integer, LineDAO> lineDaoList;
//	List<ViewTopic> viewTopic; // view에 보낼때 모델..?
	
	public MindMapController(){
		this(new TopicDAO());
	}
	
	public void setTopicDaoList(HashMap<Integer, TopicDAO> topicDaoList) {
		this.topicDaoList = topicDaoList;
	}

	public MindMapController(TopicDAO dao){
		topicDaoList = new HashMap<Integer, TopicDAO>();
		lineDaoList  = new HashMap<Integer, LineDAO>();
		topicDaoList.put(1, dao);
		lineDaoList.put(1,new LineDAO());
	}
	
	public void addSheet(int paneNum){
		topicDaoList.put(paneNum, new TopicDAO());
		lineDaoList.put(paneNum, new LineDAO());
	}
	
	public void closeSheet(int paneNum){
		topicDaoList.remove(paneNum);
		lineDaoList.remove(paneNum);
	}
	
	public void closeAllSheet(){
		topicDaoList.clear();
		lineDaoList.clear();
	}
	
	public HashMap<Integer, TopicDAO> getTopicDaoList() {
		return topicDaoList;
	}

	public HashMap<Integer, LineDAO> getLineDaoList() {
		return lineDaoList;
	}

	public void add(int paneNum, int x, int y, Topic parent){
		topicDaoList.get(paneNum).insert(x, y,parent);
	}
	
	//Topicdao 메소드 호출
	public void add(int paneNum, Topic newChild, Topic parent){
		topicDaoList.get(paneNum).insert(newChild, parent);
	}
	
	public Topic getParent(int paneNum, Topic topic){
		return topicDaoList.get(paneNum).getParent(topic);
	}
	
	public void modify(int paneNum, Topic topic,Object obj){
		if(obj instanceof Point)
			topicDaoList.get(paneNum).modifyPoint(topic,(Point)obj);
		else if(obj instanceof Note)
			topicDaoList.get(paneNum).modifyNote(topic, (Note)obj);
		else if(obj instanceof Memo)
			topicDaoList.get(paneNum).modifyMemo(topic, (Memo)obj);
		else if(obj instanceof Topic)
			topicDaoList.get(paneNum).modifyParent(topic, (Topic)obj);
	}
	
	public void modifyColor(int paneNum, Topic topic, String color){
		topicDaoList.get(paneNum).modifyColor(topic, color);
	}
	
	public void modify(int paneNum, Topic topic, String what,String why, String how){
		topicDaoList.get(paneNum).modifyNote(topic, what, why, how);
	}
	public void modifyTopicText(int paneNum, Topic topic,String topicText){
		topicDaoList.get(paneNum).modifyTopicText(topic, topicText);
	}
	
	public void modifyShape(int paneNum, Topic topic,String shape){
		topicDaoList.get(paneNum).modifyShape(topic, shape);
	}
	public void modifyWidth(int paneNum, Topic topic,int width){
		topicDaoList.get(paneNum).modifyWidth(topic, width);
	}
	public void modifyHeight(int paneNum, Topic topic,int height){
		topicDaoList.get(paneNum).modifyHeight(topic, height);
	}
	public void modifyMemo(int paneNum, Topic topic, String memo){
		topicDaoList.get(paneNum).modifyMemo(topic, memo);
	}
//	public void remove(int paneNum, Topic node) {
//	      List<Topic> children = this.getChildList(paneNum, node);
//	      List<Line> lines = new ArrayList<Line>();
//	      Iterator<Line> lists = this.lineDaoList.get(paneNum).getLineDAO().iterator();
//	      
//	      System.out.println(children.size());
//	      
//	      for(int i=0; i< children.size(); i++){
//	         while(lists.hasNext()){
//	            Line line = lists.next();
//	            if(line.getKey() == children.get(i).getKey()){
//	               lines.add(line);
//	               break;
//	            }
//	         }
//	      }
//	      
//	      for(int i=0; i<lines.size(); i++){
//	         lineDaoList.get(paneNum).remove(lines.get(i));
//	      }
//	      
//	      topicDaoList.get(paneNum).remove(node);
//	   }
	
	public void remove(int paneNum, Topic topic) {

		List<Topic> children = this.getChildList(paneNum, topic);
		List<Line> lines = new ArrayList<Line>();
		
		lineHasSameKeysInTopic(paneNum, children, lines);
		remove(paneNum, lines);
		topicDaoList.get(paneNum).remove(topic);
	}
	
	public void foldAndUnfold(int paneNum, Topic topic, boolean folded){
		List<Topic> children = this.getChildList(paneNum, topic);
		if(children.contains(topic)){
			children.remove(topic);
		}
		List<Line> lines = new ArrayList<Line>();
		
		lineHasSameKeysInTopic(paneNum, children, lines);
		
		lineFoldedSetting(paneNum, lines, folded);
		topicFoldedSetting(paneNum, children, folded);
	}
	
	public void topicFoldedSetting(int paneNum, List<Topic> children, boolean folded){
		
		Iterator<Topic> it = children.iterator();

		while(it.hasNext()){
			Topic child = it.next();
			topicDaoList.get(paneNum).topicFoldedSetting(child, folded);
		}
	}
	
	public void lineHasSameKeysInTopic(int paneNum, List<Topic> topics, List<Line> lines){
		
		Iterator<Line> lists = this.lineDaoList.get(paneNum).getLineDAO().iterator();

		for (int i = 0; i < topics.size(); i++) {
			while (lists.hasNext()) {
				Line line = lists.next();
				if (line.getKey() == topics.get(i).getKey()) {
					lines.add(line);
					break;
				}
			}
		}
	}
	
	public DefaultTreeModel getTopicDAO(int paneNum){
		return topicDaoList.get(paneNum).getTopicDAO();
	}
	public void setTopicDAO(int paneNum, DefaultTreeModel topicDAO){
		topicDaoList.get(paneNum).setTopicDAO(topicDAO);
	}
	
	//-----------------------------------------------------------
	public List<Topic> getTopicList(int paneNum){
	      /*
	       * list로 노드 정보를 화면용으로 리턴해주는 메소드
	       * */
//	      DefaultMutableTreeNode theNode = null;
	      List<Topic> nodeList = new LinkedList<Topic> ();
	     
	      for (Enumeration e = ((DefaultMutableTreeNode)topicDaoList.get(paneNum).getTopicDAO().getRoot()).preorderEnumeration(); e.hasMoreElements();) {
	          nodeList.add((Topic)((DefaultMutableTreeNode)e.nextElement()).getUserObject());
	         }
	      return nodeList;
	   }
	   
	   public List<Topic> getChildList(int paneNum, Topic node){
	      List<Topic> nodeList = new LinkedList<Topic>();
	      for (Enumeration e = ((DefaultMutableTreeNode)topicDaoList.get(paneNum).search(node)).preorderEnumeration(); e.hasMoreElements();) {
	          nodeList.add((Topic)((DefaultMutableTreeNode)e.nextElement()).getUserObject());
	         }
	      return nodeList;
	   }
	
	public List<Line> getLineList(int paneNum) {
		return lineDaoList.get(paneNum).getLineDAO();
	}

	public void setLineDao(int paneNum, LineDAO lineDao) {
		this.lineDaoList.replace(paneNum, lineDao);
	}

	//xml		
	public void Topicload(){
		/*
		 * xml에서 불러오기
		 * */
	}
	public void Topicsave(){
		/*
		 * xml에서 저장하기
		 * */
	}
	
//LineDAO
	
	
	public void insert(int paneNum, Line line){
		lineDaoList.get(paneNum).insert(line);
	}
	
	public void lineFoldedSetting(int paneNum, List<Line> lines, boolean folded){
		for (int i = 0; i < lines.size(); i++)
			lineDaoList.get(paneNum).lineFoldedSetting(lines.get(i), folded);
	}
	
	public void remove(int paneNum, List<Line> lines) {

		for (int i = 0; i < lines.size(); i++)
			lineDaoList.get(paneNum).remove(lines.get(i));
	}
	
	public void remove(int paneNum, Line line){
		lineDaoList.get(paneNum).remove(line);
	}
	
	public List<Line> searchLineForTopic(int paneNum, Topic topic){
		return lineDaoList.get(paneNum).searchForTopic(topic);
	}
	
	public Line searchLineForTopic(int paneNum, Topic startTopic, Topic endTopic){
		return lineDaoList.get(paneNum).searchForTopic(startTopic, endTopic);
	}
	
	public Line searchForLine(int paneNum, Line line){
		return lineDaoList.get(paneNum).searchForLine(line);
	}
	
	public boolean modifyFirstTopic(Line line,Topic startTopic){
		return modifyFirstTopic(line,startTopic);
	}
	
	public boolean modifySecondTopic(int paneNum, Line line, Topic endTopic){
		return lineDaoList.get(paneNum).modifySecondTopic(line, endTopic);
	}
}

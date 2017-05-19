package diagram;

import java.awt.Font;
import java.awt.Point;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import diagram.Topic.TopicColor;


public class TopicDAO {

	private DefaultTreeModel topicDAO;
	private List<DefaultMutableTreeNode> nodeList = new LinkedList<DefaultMutableTreeNode>();
	public TopicDAO() {
		Topic topic = new Topic();
		topic.setColor(TopicColor.CENTER.getColor());
		topic.setFont(new Font("맑은 고딕 Bold", Font.BOLD, 15));
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(topic);
		nodeList.add(node);
		System.out.println(node);
		topicDAO = new DefaultTreeModel(node);
		System.out.println(topicDAO.getRoot());
	}

	public TopicDAO(DefaultTreeModel topicDAO) {
		this.topicDAO = topicDAO;
	}
//DefaultMutableTreeNode parentNode
	public void insert(Topic newChild, Topic parent) {
		DefaultMutableTreeNode node = search(parent);
		if(node!=null){
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newChild);
			topicDAO.insertNodeInto(newNode, node, node.getChildCount());
			nodeList.add(newNode);
		}
		System.out.println(node);
	}
	
	public void insert(int x, int y,Topic parent){
	      insert(new Topic("하위 토픽", new Point(x,y)),parent);
	   }
	
public Topic getParent(Topic topic) {		
	      DefaultMutableTreeNode node = search(topic);
	     	      
	      return (Topic)((DefaultMutableTreeNode)(node.getParent())).getUserObject();
	   }

	public DefaultMutableTreeNode search(Topic topic){
		Iterator<DefaultMutableTreeNode> it = nodeList.iterator();
		while(it.hasNext()){
			DefaultMutableTreeNode temp = (DefaultMutableTreeNode)it.next();
			if((temp.getUserObject()).equals(topic)){
				return temp;
			}
		}
		return null;
	}
	public void topicFoldedSetting(Topic topic, boolean folded){
		topic.setFolded(folded);
	}

	public void remove(Topic topic) {
	      
	      DefaultMutableTreeNode node = search(topic);
	     
	      topicDAO.removeNodeFromParent(node);
	      nodeList.remove(node);
	      if(node.getChildCount() == 0)
		         return;
	      childRemove(node);
	   }

	   public void childRemove(DefaultMutableTreeNode node) {
	      if(node.getChildCount() == 0){
	         topicDAO.removeNodeFromParent(node);
	         nodeList.remove(node);
	         return;
	      }
	      
	      for(int i=0 ; i < node.getChildCount(); i++)
	         childRemove((DefaultMutableTreeNode)node.getChildAt(i));
	      
	   }
	
	public Topic searchForTopicText(String search) {
		Enumeration nodeEnumeration = (((DefaultMutableTreeNode)topicDAO.getRoot())).breadthFirstEnumeration();
		while (nodeEnumeration.hasMoreElements()) {
			Topic topic = (Topic)((DefaultMutableTreeNode)nodeEnumeration.nextElement()).getUserObject();
			String found = topic.getTitle();
			System.out.println(found);
			if (search.equals(found)) {
				return topic;
			}
		}
		return null;
	}
	
	public Topic seachForPoint(Point point) {
		Enumeration nodeEnumeration = (((DefaultMutableTreeNode)topicDAO.getRoot())).breadthFirstEnumeration();
		while (nodeEnumeration.hasMoreElements()) {
			Topic topic = (Topic)((DefaultMutableTreeNode)nodeEnumeration.nextElement()).getUserObject();
			if (topic.isContained(point)) {
				return topic;
			}
		}
		return null;
	}

	public Topic searchForKey(int key) {
		Enumeration nodeEnumeration = (((DefaultMutableTreeNode)topicDAO.getRoot())).breadthFirstEnumeration();
		while (nodeEnumeration.hasMoreElements()) {
			Topic topic = (Topic)((DefaultMutableTreeNode)nodeEnumeration.nextElement()).getUserObject();
			int found = topic.getKey();
			if (key == found) {
				return topic;
			}
		}
		return null;
	}
	public Topic serachForTopic(Topic foundTopic) { // 선택된 토픽으로 토픽을 찾는다

		Enumeration nodeEnumeration = (((DefaultMutableTreeNode)topicDAO.getRoot())).breadthFirstEnumeration();
		while (nodeEnumeration.hasMoreElements()) {
			Topic topic = (Topic) nodeEnumeration.nextElement();
			if (foundTopic.equals(topic)) {
				return topic;
			}
		}
		return null;
	}
	public boolean modifyTopicText(Topic topic, String topicText) {
		Topic searchTopic = serachForTopic(topic);
		if(searchTopic == null)
			return false;
		
		searchTopic.setTitle(topicText);
		return true;
	}

	 public boolean modifyPoint(Topic topic, Point point) {
	      Topic searchTopic = serachForTopic(topic);
	      if (searchTopic == null)
	         return false;

	      searchTopic.setPoint(point);
	      return true;
	   }

	   public boolean modifyColor(Topic topic, String color) {
	      Topic searchTopic = serachForTopic(topic);
	      if (searchTopic == null)
	         return false;

	      searchTopic.setColor(color);
	      return true;
	   }

	   public boolean modifyShape(Topic topic, String shape) {
	      Topic searchTopic = serachForTopic(topic);
	      if (searchTopic == null)
	         return false;

	      searchTopic.setShape(shape);
	      return true;
	   }

	   public boolean modifyWidth(Topic topic, int width) {
	      Topic searchTopic = serachForTopic(topic);
	      if (searchTopic == null)
	         return false;

	      searchTopic.setWidth(width);
	      return true;
	   }

	   public boolean modifyHeight(Topic topic, int height) {

	      Topic searchTopic = serachForTopic(topic);
	      if (searchTopic == null)
	         return false;

	      searchTopic.setHeight(height);
	      return true;
	   }

	   public boolean modifyNote(Topic topic, Note note) {

	      Topic searchTopic = serachForTopic(topic);
	      if (searchTopic == null)
	         return false;

	      searchTopic.setNote(note);
	      return true;
	   }
	 
	   public List<DefaultMutableTreeNode> getNodeList() {
		return nodeList;
	}

	public void setNodeList(List<DefaultMutableTreeNode> nodeList) {
		this.nodeList = nodeList;
	}

	public boolean modifyNote(Topic topic, String what, String why, String how) {

	      Topic searchTopic = serachForTopic(topic);
	      if (searchTopic == null)
	         return false;

	      searchTopic.getNote().setWhat(what);
	      searchTopic.getNote().setWhy(why);
	      searchTopic.getNote().setHow(how);

	      return true;
	   }

	   public boolean modifyMemo(Topic topic, Memo memo) {

	      Topic searchTopic = serachForTopic(topic);
	      if (searchTopic == null)
	         return false;

	      searchTopic.setMemo(memo);
	      return true;
	   }

	   public boolean modifyMemo(Topic topic, String memo) {

	      Topic searchTopic = serachForTopic(topic);
	      if (searchTopic == null)
	         return false;

	      searchTopic.getMemo().setContent(memo);
	      return true;
	   }

	   public boolean modifyParent(Topic topic, Topic parent) {

	      // Topic searchTopic = serachForTopic(topic);
	      DefaultMutableTreeNode node = search(topic);
	      DefaultMutableTreeNode pNode = search(parent);
	      if (node == null || pNode == null)
	         return false;

	      node.setParent(pNode);
	      return true;
	   }

	public DefaultTreeModel getTopicDAO() {
		return topicDAO;
	}

	public void setTopicDAO(DefaultTreeModel topicDAO) {
		this.topicDAO = topicDAO;
	}
	
	@Override
	public String toString() {
		return "TopicDAO [topicDAO=" + topicDAO + "]";
	}
}


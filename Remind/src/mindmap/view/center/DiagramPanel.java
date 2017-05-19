package mindmap.view.center;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;

import main.MindMapController;
import mindmap.view.bottom.RemindNoteView;
import mindmap.view.menu.dialog.RemindTopicOptionDialog;
import diagram.Line;
import diagram.Topic;
import diagram.Topic.TopicColor;
class SharedArea{
	private MindMapController controller;
	private int               paneNum;
	public SharedArea(int paneNum, MindMapController controller){
		this.paneNum    = paneNum;
		this.controller = controller;
	}
	synchronized public Topic calculateNearNode(int x, int y) {
		double bestDist = -1;
		Topic bestNode 	= null;
		double dist     = -1;
		for(Topic node : controller.getTopicList(paneNum)) {
			int radius = node.getRadius();
			if(radius>20)
				dist= Math.sqrt(Math.pow(Math.abs(node.getPoint().x - x), 1.7) + 
						Math.pow(Math.abs(node.getPoint().y - y), 2));
			else
			    dist = Math.sqrt(Math.pow(Math.abs(node.getPoint().x - x), 2) + 
									Math.pow(Math.abs(node.getPoint().y - y), 2));
			if(dist < node.getRadius() && (bestNode == null || dist < bestDist)) {
				bestNode = node;
				bestDist = dist;
			}
		}
		return bestNode;
	}
}

public class DiagramPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	
	private static final long serialVersionUID = -772532722413032001L;
	private JFrame 	frame;
	private Topic 	nodeDragged;
	private Topic 	nodeJoinStart;
	private Topic   selectedTopic;
	private Topic   mouseMovingTopic;
	private int 	mouseX;
	private int 	mouseY;
	private RemindNoteView notePanel;
	private Toolkit toolkit = this.getToolkit();
	
	private JTextField textField;
	private Topic   nearNode;
	private Topic   canAddNode;
	private int	    lineType;
	private Color   lineColor;
	private Font    topicFont;
	private int     lineStyle;
	private boolean mouseClicked;
	private int     paneNum;
	private boolean exit;
	private int     topicType;
	private Color   topicColor;
	private Color   topicStyle;
	
	private MindMapController controller;
	
	private SharedArea sharedArea;
	
	public DiagramPanel(MindMapController controller, JFrame frame,
			RemindNoteView notePanel, int paneNum) {
		this.controller         = controller;
		
		this.paneNum            = paneNum;
		this.frame 				= frame;
		this.notePanel			= notePanel;
		this.nodeDragged 		= null;
		this.nodeJoinStart 		= null;
		this.notePanel				= notePanel;
		this.selectedTopic      = null;
		this.mouseMovingTopic   = null;
		this.lineType           = 1;
		this.lineStyle          = 1;
		this.mouseClicked       = false;
		this.exit               = false;
		this.lineColor          = Color.black;
		this.topicType          = 2;
		this.topicStyle         = RemindTopicOptionDialog.AUTUMN_COLOR;
		this.topicColor         = new Color(255, 250, 200);
		this.setLayout(null);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);

		this.setPreferredSize(new Dimension(RemindMindMapView.SIZE_WIDTH,
				RemindMindMapView.SIZE_HEIGHT));
		
		sharedArea = new SharedArea(paneNum, controller);
		
		new Thread(){
			public void run(){
				while(!exit){
					if(!mouseClicked){
						nearNode = sharedArea.calculateNearNode(mouseX, mouseY);
					}
					try{
						Thread.sleep(1);
						//sharedArea.notify();
						sharedArea.wait();
					}
					catch(Exception e){}
				}
			}
		}.start();
		
		new Thread(){
			public void run(){
				while(!exit){
					if(!mouseClicked)
						canAddNode = sharedArea.calculateNearNode(mouseX-50, mouseY+48);
					try{
						Thread.sleep(1);
						sharedArea.wait();
						//sharedArea.notify();
					}
					catch(Exception e){}
				}
			}
		}.start();		
	}
	
	public void commandExit(){
		this.exit = true;
	}
	
	
	
	
	public void paint(Graphics g) {		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2.setColor(lineColor);
		g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		for(Line join: controller.getLineList(paneNum)){
			if(join.isFolded())
	            continue;
			if(lineStyle == 2){
				float dash1[] = {10.0f};
				g2.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 5000, dash1, 1.0f));
			}
			if(lineType == 1){
			g2.drawLine(join.getStartTopic().getPoint().x,
					  join.getStartTopic().getPoint().y,
					   join.getEndTopic().getPoint().x,
					   join.getEndTopic().getPoint().y);
			}
			else{
				RoundLine roundLine = new RoundLine(getWidth());
				g2.setPaint(lineColor);
				g2.draw(roundLine.getPath(join.getStartTopic(), join.getEndTopic()));
			}
		}
		
		g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
		
		for(Topic node : controller.getTopicList(paneNum)) {
			if(node.isFolded())
	            continue;
			RectangularShape rRect1 = null;
			if(this.topicType == 1)
				rRect1 = new Rectangle2D.Double(node.getPoint().x -node.getRadius()*3, node.getPoint().y - 40, 
						   node.getRadius() * 6, 40 * 1.5);
			else if(this.topicType == 2)
				rRect1 = new RoundRectangle2D.Double(node.getPoint().x -node.getRadius()*3, node.getPoint().y - 40, 
						node.getRadius() * 6, 40 * 1.5 , 30, 30);
			else if(this.topicType == 3)
				rRect1 = new Ellipse2D.Float((float)node.getPoint().x -node.getRadius()*3f, (float)node.getPoint().y - 40f, 
						(float)node.getRadius() * 6f, (float)40 * 1.5f );
			else{
				rRect1 = new Ellipse2D.Float((float)node.getPoint().x -node.getRadius()*3f, (float)node.getPoint().y -node.getRadius()*3.6f, 
						(float)node.getRadius() * 6f, (float)node.getRadius() * 6f );
			}
				
			if(node.getColor() == TopicColor.CENTER.getColor()){
				g2.setColor(new Color(255, 100, 205));
				g2.draw(rRect1);
				g2.setColor(new Color(255, 100, 100));
			}
			else{
				g2.setColor(topicStyle);
				g2.draw(rRect1);
				g2.setColor(topicColor);
			}
			g2.fill(rRect1);
			if(node.isSelected()){
				g2.setColor(new Color(255, 0, 100));
				g2.draw(rRect1);
			}
			if(node.isMouseOn()){
				g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(new Color(0, 100, 0));
				g2.drawOval(node.getPoint().x + node.getRadius()*3 , node.getPoint().y - 48, 18, 18);
				g2.drawImage(toolkit.getImage("src\\mindmap\\view\\center\\icon\\edit_add.png"),
						node.getPoint().x+ node.getRadius()*3 + 2, node.getPoint().y - 46, this);
			}
			
			g2.setColor(new Color(0, 0, 0));
			g2.setFont(node.getFont());
			g2.drawString(node.getTitle(), node.getPoint().x - (int)(node.getRadius()*2.25), node.getPoint().y - 5);
		}

		if(nodeJoinStart != null) {
			if(lineType==2){
				RoundLine roundLine = new RoundLine(getWidth());
				g2.setPaint(Color.LIGHT_GRAY);
				g2.draw(roundLine.getPath(nodeJoinStart, mouseX, mouseY));
				g2.setPaint(Color.black.darker());
			}
			RectangularShape rRect1 = null;
			if(this.topicType == 1)
				rRect1 = new Rectangle2D.Double(mouseX-42, mouseY-40, 
						   42*2, 40 * 1.5);
			else if(this.topicType == 2)
				rRect1 = new RoundRectangle2D.Double(mouseX-42, mouseY-40,
						42*2, 40 * 1.5 , 30, 30);
			else if(this.topicType == 3)
				rRect1 = new Ellipse2D.Float((float)(mouseX-42),  (float)mouseY-40, 
						42*2f, 40 * 1.5f);
			else{
				rRect1 = new Ellipse2D.Float((float)(mouseX-42),  (float)mouseY-40, 
						42*2f, 42*2f);
			}
				
			g2.draw(rRect1);
		}	
	}
	
	public void mousePressed(MouseEvent e) {
		mouseClicked = true;
		this.requestFocus();
		if(e.getButton() == MouseEvent.BUTTON3) {
			if(nodeDragged == null && nodeJoinStart == null) {
				Topic nearbyNode = nearNode;
				if (nearbyNode == null){
					if(selectedTopic!=null)
						selectedTopic.setSelected(false);
					selectedTopic = null;
					return;
				}
				else if(selectedTopic!=null){
					selectedTopic.setSelected(false);
					notePanel.setText(selectedTopic.getNote(), selectedTopic.getMemo());
				}
			    selectedTopic = nearbyNode;
			    notePanel.getText(selectedTopic.getNote(), selectedTopic.getMemo());
				selectedTopic.setSelected(true);
				if(nearbyNode == controller.getTopicList(paneNum).get(0)){
					repaint();
					return;
				}
				nodeDragged = nearbyNode;
			}
		}
		else if(e.getButton() == MouseEvent.BUTTON1) {
				 nodeJoinStart = canAddNode;
		}
	}
	
	public void mouseReleased(MouseEvent arg0) {
		mouseClicked = false;
		if(arg0.getButton() == MouseEvent.BUTTON3) {
			if(this.nodeDragged != null) {
				this.nodeDragged = null;
			}
		}
		else if(arg0.getButton() == MouseEvent.BUTTON1) {
			if(this.nodeJoinStart != null) {				
				Topic newNode = new Topic("하위 토픽", new Point(arg0.getX(), arg0.getY()));
				controller.add(paneNum, newNode, nodeJoinStart);
				if(selectedTopic!=null){
					notePanel.setText(selectedTopic.getNote(), selectedTopic.getMemo());
					this.selectedTopic.setSelected(false);
				}
				Line newJoin 		= new Line(this.nodeJoinStart, newNode);			
				controller.insert(paneNum, newJoin);
					
				this.selectedTopic = newNode;
				notePanel.getText(selectedTopic.getNote(), selectedTopic.getMemo());
				newNode.setSelected(true);					
				this.repaint();
				nodeJoinStart = null;
			}
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		if(mouseClicked)
			return;
		mouseX = e.getX(); 
		mouseY = e.getY();
		Topic nearbyNode = nearNode;
		
		if(nearbyNode == null){
		    if(mouseMovingTopic !=null){
		    	mouseMovingTopic.setMouseOn(false);
		    }
			mouseMovingTopic = null;
			return;
		}
		else if(mouseMovingTopic == null){
			mouseMovingTopic = nearbyNode;
			return;
		}
		mouseMovingTopic.setMouseOn(false);
		mouseMovingTopic = nearbyNode;
		mouseMovingTopic.setMouseOn(true);
		repaint();
	}
	
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		if(nodeDragged != null) {
			nodeDragged.setPoint(new Point(mouseX, mouseY));
			this.repaint();
		}
		if(nodeJoinStart != null) this.repaint();
	}
	
	public void mouseClicked(MouseEvent e) {
		if(selectedTopic==null)
			return;
		if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON3){
			mouseClicked = true;
		textField = new JTextField(selectedTopic.getTitle());
		textField.setBounds(selectedTopic.getPoint().x - selectedTopic.getRadius()*3+1,
				selectedTopic.getPoint().y-30, selectedTopic.getRadius()*6 - 2, 40);
		this.add(textField);
		if(((DefaultMutableTreeNode)controller
				.getTopicDAO(paneNum).getRoot()).getUserObject() == selectedTopic){
			textField.setBorder(BorderFactory.createLineBorder(Color.RED));
			textField.setBackground(new Color(255,200,200));
		}
		else{
			textField.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
			textField.setBackground(new Color(255,255,250));
		}
		
		textField.requestFocus();
		textField.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) 	{
				if(textField.getText().length()>=8){
					textField.setEditable(false);
					if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
						textField.setEditable(true);
				}
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					selectedTopic.setTitle(textField.getText());
					textField.removeKeyListener(this);
					if((int)(textField.getText().length()*2.8)<=14)
						selectedTopic.setRadius(14);
					else
						selectedTopic.setRadius((int)(textField.getText().length()*2.8));
					textField.setEnabled(false);
					remove(textField);
					repaint();
					mouseClicked = false;
					return;
					}
				}
			
				public void keyReleased(KeyEvent e) {}
				public void keyTyped(KeyEvent e)	{}
			});
		}
	}
	public void mouseEntered(MouseEvent arg0) 	{}
	public void mouseExited(MouseEvent arg0) 	{}
	
	public void keyPressed(KeyEvent arg0) 		{
		if(arg0.getKeyCode() == KeyEvent.VK_DELETE) {
			if(selectedTopic != null) {
				if(selectedTopic == controller.getTopicList(paneNum).get(0)){
					JOptionPane.showMessageDialog
					(frame, "중심 토픽은 삭제할 수 없습니다.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(JOptionPane.showConfirmDialog
					(frame, "토픽 \"" + selectedTopic.getTitle() + "\" 삭제 하시겠습니까?", "Delete Node", 
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					Topic temp = controller.getParent(paneNum, selectedTopic);
					controller.remove(paneNum, selectedTopic);
					selectedTopic = temp;
					selectedTopic.setSelected(true);
					this.repaint();
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) 		{}
	
	public void setLineType(int lineType){
		this.lineType = lineType;
	}
	
	public int getLineType(){
		return lineType;
	}
	
	public Topic getSelectedTopic() {
		return selectedTopic;
	}

	public void setSelectedTopic(Topic selectedTopic) {
		this.selectedTopic = selectedTopic;
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public int getLineStyle() {
		return lineStyle;
	}

	public void setLineStyle(int lineStyle) {
		this.lineStyle = lineStyle;
	}

	public Font getTopicFont() {
		return topicFont;
	}

	public void setTopicFont(Font topicFont) {
		this.topicFont = topicFont;
	}

	public int getTopicType() {
		return topicType;
	}

	public void setTopicType(int topicType) {
		this.topicType = topicType;
	}

	public Color getTopicColor() {
		return topicColor;
	}

	public void setTopicColor(Color topicColor) {
		this.topicColor = topicColor;
	}

	public Color getTopicStyle() {
		return topicStyle;
	}

	public void setTopicStyle(Color topicStyle) {
		this.topicStyle = topicStyle;
	}	
	
}
package mindmap.view.menu;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import main.MindMapController;
import mindmap.view.center.RemindMindMapView;
import mindmap.view.center.ZoomPanel;
import diagram.Line;
import diagram.Topic;

public class RemindInsertMenu {
	
	/**-----------------------------------------------------------------------------------------
	------------------------------------------------------------------------------Member Field*/
	
	private JMenu 		insertMenu;
	private JMenuItem 	insertTopic;
	private JMenuItem 	insertIcon;
	private JMenuItem 	insertImage;
	private JMenuItem 	insertAttechment;
	private JMenuItem 	insertDate;	
	
	private RemindMindMapView  mindMapView;
	private MindMapController  controller;
	
	/**-----------------------------------------------------------------------------------------
	-------------------------------------------------------------------------------Constructor*/
	
	public RemindInsertMenu(JMenuBar menuBar, RemindMindMapView mindMapView) {	
		RemindMenuTemplate createMenu = new RemindMenuTemplate();
		
		this.mindMapView        = mindMapView;
		this.controller         = mindMapView.getController();
				
		this.insertMenu 		= createMenu.addMenu("삽입");
		menuBar.add(this.insertMenu);
		this.insertTopic 		= createMenu.addMenuItem("토픽 삽입");
		this.insertIcon 		= createMenu.addMenuItem("아이콘 삽입");
		this.insertImage 		= createMenu.addMenuItem("이미지 삽입");
		this.insertAttechment 	= createMenu.addMenuItem("첨부파일 삽입");
		this.insertDate 		= createMenu.addMenuItem("날짜 및 시간 삽입");
		
		addMenu(createMenu);
		addListener();
	}
	
	/**-----------------------------------------------------------------------------------------
	------------------------------------------------------------------------------메뉴에 아이템을 추가 */
	
	private void addMenu(RemindMenuTemplate createMenu) {
		this.insertMenu.add(this.insertTopic);
		this.insertMenu.add(createMenu.addSeparator());
		this.insertMenu.add(this.insertIcon);
		this.insertMenu.add(this.insertImage);
		this.insertMenu.add(this.insertAttechment);
		this.insertMenu.add(this.insertDate);
	}

	private void addListener(){
		insertTopic.addActionListener(new InsertionTopicListener());
	}
	/**-----------------------------------------------------------------------------------------
	------------------------------------------------------------------------------------Getter*/
	
	public JMenu getInsertMenu() 			{ return this.insertMenu; }
	public JMenuItem getInsertTopic() 		{ return this.insertTopic; }
	public JMenuItem getInsertIcon() 		{ return this.insertIcon; }
	public JMenuItem getInsertImage() 		{ return this.insertImage; }
	public JMenuItem getInsertAttechment() 	{ return this.insertAttechment; }
	public JMenuItem getInsertDate() 		{ return this.insertDate; }
	
	private class InsertionTopicListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			ZoomPanel zoomPanel = ((ZoomPanel)(mindMapView.getScrollPane().
					get(mindMapView.getTapPane().getSelectedIndex()).
					getViewport().getComponent(0)));
			Topic selectedTopic = zoomPanel.getSelectedTopic();
			if(selectedTopic==null)
				return;
			
			Topic newNode = new Topic("하위 토픽", 
					new Point(selectedTopic.getPoint().x+100, selectedTopic.getPoint().y));
			int paneNum        = mindMapView.getPanelNum(mindMapView.getTapPane().getSelectedIndex());
			
			controller.add(paneNum, newNode, selectedTopic);
			
			mindMapView.getNoteView().setText(selectedTopic.getNote(), selectedTopic.getMemo());
			selectedTopic.setSelected(false);
			
			Line newJoin 		= new Line(selectedTopic, newNode);			
			controller.insert(paneNum, newJoin);
				
			zoomPanel.setSelectedTopic(newNode);
			mindMapView.getNoteView().getText(selectedTopic.getNote(), selectedTopic.getMemo());
			newNode.setSelected(true);				
			zoomPanel.repaint();
		}
	}
}
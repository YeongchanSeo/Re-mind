package mindmap.view.menu;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;

import mindmap.view.RemindMainFrame;
import mindmap.view.center.RemindMindMapView;
import mindmap.view.center.ZoomPanel;
import diagram.Topic;

public class RemindViewMenu {
		
	/**-----------------------------------------------------------------------------------------
	------------------------------------------------------------------------------Member Field*/
	
	private JMenu 		viewMenu;
	private JMenuItem 	viewAllList;
	private JMenuItem 	warpCenterTopic;
	private JMenuItem 	viewAllMap;
	private JMenuItem 	zoomIn;
	private JMenuItem 	zoomOut;
	private JMenuItem	zoomDefault;
	private JMenuItem 	foldTopic;
	private JMenuItem 	unfoldTopic;
	private JMenuItem 	foldAllTopic;
	private JMenuItem 	unfoldAllTopic;
	
	private RemindMindMapView   mindMapView;
	
	/**-----------------------------------------------------------------------------------------
	-------------------------------------------------------------------------------Constructor*/
	
	public RemindViewMenu(JMenuBar menuBar, RemindMindMapView mindMapView) {		
		RemindMenuTemplate createMenu = new RemindMenuTemplate();
		
		this.mindMapView = mindMapView;
		
		this.viewMenu 			= createMenu.addMenu("보기");
		menuBar.add(this.viewMenu);
		this.viewAllList 		= createMenu.addMenuItem("전체 목록 보기");
		this.warpCenterTopic 	= createMenu.addMenuItem("중심 토픽으로 이동");
		this.viewAllMap 		= createMenu.addMenuItem("맵 전체보기");
		this.zoomIn 			= createMenu.addMenuItem("화면 확대");
		this.zoomOut 			= createMenu.addMenuItem("화면 축소");
		this.zoomDefault		= createMenu.addMenuItem("기본값");
		this.foldTopic 			= createMenu.addMenuItem("토픽 접기");
		this.unfoldTopic 		= createMenu.addMenuItem("토픽 펴기");
		this.foldAllTopic 		= createMenu.addMenuItem("전체 토픽 접기");
		this.unfoldAllTopic 	= createMenu.addMenuItem("전체 토픽 펴기");
		
		addMenu(createMenu);
		addListener();
	}
	
	/**-----------------------------------------------------------------------------------------
	------------------------------------------------------------------------------메뉴에 아이템을 추가 */
	
	private void addMenu(RemindMenuTemplate createMenu) {
		this.viewMenu.add(this.viewAllList);
		this.viewMenu.add(this.warpCenterTopic);
		this.viewMenu.add(createMenu.addSeparator());
		this.viewMenu.add(this.viewAllMap);
		this.viewMenu.add(this.zoomIn);
		this.viewMenu.add(this.zoomOut);
		this.viewMenu.add(this.zoomDefault);
		this.viewMenu.add(createMenu.addSeparator());
		this.viewMenu.add(this.foldTopic);
		this.viewMenu.add(this.unfoldTopic);
		this.viewMenu.add(this.foldAllTopic);
		this.viewMenu.add(this.unfoldAllTopic);		
	}
	
	private void addListener(){
		this.warpCenterTopic.addActionListener(new CenterTopicListener());
		this.zoomIn.addActionListener(new ZoomInListener());
		this.zoomOut.addActionListener(new ZoomOutListener());
		this.zoomDefault.addActionListener(new ZoomOriginalListener());
		this.foldTopic.addActionListener(new foldTopicListener());
		this.unfoldTopic.addActionListener(new unfoldTopicListener());
		this.foldAllTopic.addActionListener(new foldAllTopicListener());
		this.unfoldAllTopic.addActionListener(new unfoldAllTopicListener());
	}
	
	/**-----------------------------------------------------------------------------------------
	------------------------------------------------------------------------------------Getter*/
	
	public JMenu getViewMenu() 				{ return this.viewMenu; }
	public JMenuItem getViewAllList() 		{ return this.viewAllList; }
	public JMenuItem getWarpCenterTopic() 	{ return this.warpCenterTopic; }
	public JMenuItem getViewAllMap() 		{ return this.viewAllMap; }
	public JMenuItem getZoomIn() 			{ return this.zoomIn; }
	public JMenuItem getZoomOut() 			{ return this.zoomOut; }
	public JMenuItem getZoomDefault() 		{ return this.zoomDefault; }
	public JMenuItem getFoldTopic() 		{ return this.foldTopic;	}
	public JMenuItem getUnfoldTopic() 		{ return this.unfoldTopic; }
	public JMenuItem getFoldAllTopic() 		{ return this.foldAllTopic; }
	public JMenuItem getUnfoldAllTopic() 	{ return this.unfoldAllTopic; }
	
	private class CenterTopicListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			ZoomPanel zoomPanel = ((ZoomPanel)(mindMapView.getScrollPane().
					get(mindMapView.getTapPane().getSelectedIndex()).
					getViewport().getComponent(0)));
			mindMapView.getScrollPane().get(mindMapView.getTapPane().getSelectedIndex()).getViewport().setViewPosition(
			new Point((int)(zoomPanel.getWidth()/2*1) - RemindMainFrame.VIEWSIZE_WIDTH/2 ,
				(int)(zoomPanel.getHeight()/2*1) - RemindMainFrame.VIEWSIZE_HEIGHT/2+200));
		}
	}
	
	private class ZoomInListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			ZoomPanel zoomPanel = ((ZoomPanel)(mindMapView.getScrollPane().
					get(mindMapView.getTapPane().getSelectedIndex()).
					getViewport().getComponent(0)));
			float zoom = zoomPanel.getZoom();
			if(zoom > 1.6f)
				return;
			mindMapView.getScrollPane().get(mindMapView.getTapPane().getSelectedIndex()).getViewport().setViewPosition(
			new Point((int)(zoomPanel.getWidth()/2*zoom - (RemindMainFrame.VIEWSIZE_WIDTH-300)/2),
				(int)(zoomPanel.getHeight()/2*zoom) - RemindMainFrame.VIEWSIZE_HEIGHT/2 + 350));
			zoomPanel.setZoom(zoom += 0.1f);
		}
	}
	
	private class ZoomOutListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			ZoomPanel zoomPanel = ((ZoomPanel)(mindMapView.getScrollPane().
					get(mindMapView.getTapPane().getSelectedIndex()).
					getViewport().getComponent(0)));
			float zoom = zoomPanel.getZoom();
			if(zoom < 0.5f)
				return;
			mindMapView.getScrollPane().get(mindMapView.getTapPane().getSelectedIndex()).getViewport().setViewPosition(
			new Point((int)(zoomPanel.getWidth()/2*zoom) - RemindMainFrame.VIEWSIZE_WIDTH/2 -150,
				(int)(zoomPanel.getHeight()/2*zoom) - RemindMainFrame.VIEWSIZE_HEIGHT/2+50));
			zoomPanel.setZoom(zoom -= 0.1f);
		}
	}
	
	private class ZoomOriginalListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			ZoomPanel zoomPanel = ((ZoomPanel)(mindMapView.getScrollPane().
					get(mindMapView.getTapPane().getSelectedIndex()).
					getViewport().getComponent(0)));
			mindMapView.getScrollPane().get(mindMapView.getTapPane().getSelectedIndex()).getViewport().setViewPosition(
					new Point((int)(zoomPanel.getWidth()/2 - (RemindMainFrame.VIEWSIZE_WIDTH)/2),
						(int)(zoomPanel.getHeight()/2) - RemindMainFrame.VIEWSIZE_HEIGHT/2 + 200));
			zoomPanel.setZoom(1.0f);
		}
	}
	
	private class foldTopicListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			ZoomPanel zoomPanel = ((ZoomPanel) (mindMapView.getScrollPane()
					.get(mindMapView.getTapPane().getSelectedIndex())
					.getViewport().getComponent(0)));
			
			mindMapView.getController().foldAndUnfold(mindMapView.getPanelNum(mindMapView.
					getTapPane().getSelectedIndex()),
					zoomPanel.getSelectedTopic(), true);
			zoomPanel.repaint();
		}
	}
	private class foldAllTopicListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			ZoomPanel zoomPanel = ((ZoomPanel) (mindMapView.getScrollPane()
					.get(mindMapView.getTapPane().getSelectedIndex())
					.getViewport().getComponent(0)));

			int paneNum = mindMapView.getPanelNum(mindMapView.getTapPane().getSelectedIndex());
			
			mindMapView.getController().foldAndUnfold(paneNum,
					(Topic)((DefaultMutableTreeNode)mindMapView.getController()
							.getTopicDAO(paneNum).getRoot()).getUserObject(), true);
			zoomPanel.repaint();
		}
	}
	private class unfoldAllTopicListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			ZoomPanel zoomPanel = ((ZoomPanel)(mindMapView.getScrollPane().
					get(mindMapView.getTapPane().getSelectedIndex()).
					getViewport().getComponent(0)));

			int paneNum = mindMapView.getPanelNum(mindMapView.getTapPane().getSelectedIndex());
			
			mindMapView.getController().foldAndUnfold(paneNum,
					(Topic)((DefaultMutableTreeNode)mindMapView.getController()
							.getTopicDAO(paneNum).getRoot()).getUserObject(), false);
			zoomPanel.repaint();
		}
	}
	
	private class unfoldTopicListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			ZoomPanel zoomPanel = ((ZoomPanel)(mindMapView.getScrollPane().
					get(mindMapView.getTapPane().getSelectedIndex()).
					getViewport().getComponent(0)));

			mindMapView.getController().foldAndUnfold(mindMapView.getPanelNum
					(mindMapView.getTapPane().getSelectedIndex()),
					zoomPanel.getSelectedTopic(), false);
			zoomPanel.repaint();
		}
	}
}
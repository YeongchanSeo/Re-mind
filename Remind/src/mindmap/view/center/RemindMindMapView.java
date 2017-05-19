package mindmap.view.center;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;

import main.MindMapController;
import main.ModeModule;
import mindmap.view.RemindMainFrame;
import mindmap.view.bottom.RemindNoteView;

public class RemindMindMapView extends JPanel{
	
	private static final long serialVersionUID = 8347715760914436833L;

	public static final int SIZE_WIDTH = 3000;
	public static final int SIZE_HEIGHT = 3000;
	private static int   panelNum;
	private static int   realPanelNum;
	
	private JFrame      frame;
	private RemindNoteView noteView;
	private Container	contentPane;
	private JTabbedPane tapPane;
	private ZoomPanel	panelOne;
	private List<JScrollPane> scrollPane;
	
	private HashMap<Integer, Integer> mapManageNum;
	
	private MindMapController controller;
	
	public RemindMindMapView(ModeModule mode, JFrame frame, RemindNoteView noteView) {
		this.frame			= frame;
		this.noteView       = noteView;
		this.scrollPane     = new ArrayList<JScrollPane>();
		this.controller     = mode.getController();
		this.mapManageNum   = new HashMap<Integer, Integer>();
		this.contentPane 	= frame.getContentPane();
		this.tapPane 		= new JTabbedPane();
		this.panelNum       = 1;
		this.tapPane.setBorder(new CompoundBorder(new MatteBorder(2,2,2,2, new Color(255,255,255)), 
				   new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY)));
		this.panelOne 		= new ZoomPanel(controller, frame, noteView, panelNum);
		this.panelOne.setSize(new Dimension(SIZE_WIDTH, SIZE_HEIGHT));
		this.scrollPane.add(new JScrollPane(this.panelOne));
		this.scrollPane.get(0).setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.scrollPane.get(0).setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.get(realPanelNum).getViewport().setViewPosition(
				new Point(panelOne.getWidth()/2 - RemindMainFrame.VIEWSIZE_WIDTH/2,
						panelOne.getHeight()/2 - RemindMainFrame.VIEWSIZE_HEIGHT/2 + 200));
	
		
		this.mapManageNum.put(realPanelNum, panelNum);
		noteView.setTreeModel(controller.getTopicDAO(panelNum));
		this.tapPane.addTab(panelNum+++"번패널", this.scrollPane.get(realPanelNum++));
		this.tapPane.addMouseListener(new TabPaneListener());
		contentPane.add(tapPane, BorderLayout.CENTER);
	}
	
	public void newFile(){
		controller.addSheet(panelNum);
		this.mapManageNum.put(realPanelNum, panelNum);
		noteView.setTreeModel(controller.getTopicDAO(panelNum));
		
		this.panelOne 		= new ZoomPanel(controller, frame, noteView, panelNum);
		this.panelOne.setSize(new Dimension(SIZE_WIDTH, SIZE_HEIGHT));
		
		this.scrollPane.add(new JScrollPane(this.panelOne));
		this.scrollPane.get(realPanelNum).setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.scrollPane.get(realPanelNum).setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		scrollPane.get(realPanelNum).getViewport().setViewPosition(
				new Point(panelOne.getWidth()/2 - RemindMainFrame.VIEWSIZE_WIDTH/2,
						panelOne.getHeight()/2 - RemindMainFrame.VIEWSIZE_HEIGHT/2 + 200));
		
		this.panelOne.setBackground(Color.BLACK);
		this.tapPane.addTab(panelNum+++"번패널", this.scrollPane.get(realPanelNum));
		this.tapPane.setSelectedIndex(realPanelNum++);
	}
	
	public void closeFile(){
		if(realPanelNum == 0)
			return;

		controller.closeSheet(this.mapManageNum.get(this.tapPane.getSelectedIndex()));
		
		ZoomPanel zoomPanel = ((ZoomPanel)scrollPane.
				get(tapPane.getSelectedIndex()).
				getViewport().getComponent(0));
		zoomPanel.commandExit();
		
		this.scrollPane.remove(this.tapPane.getSelectedIndex());
		this.tapPane.remove(this.tapPane.getSelectedIndex());
		
		realPanelNum--;		
	}
	
	public void allCloseFile(){
		
		for(int index = scrollPane.size() - 1; index>=0; index--){
			 ((ZoomPanel)scrollPane.get(index).
						getViewport().getComponent(0)).commandExit();
			this.scrollPane.remove(index);
		}
		
		controller.closeAllSheet();
		this.tapPane.removeAll();
		
		realPanelNum = 0;
	}
	
	public JTabbedPane getTapPane() {
		return tapPane;
	}

	public void setTapPane(JTabbedPane tapPane) {
		this.tapPane = tapPane;
	}
	
	public Container getContentPane() 					{ return contentPane; }
	public List<JScrollPane> getScrollPane() 					{ return scrollPane; }
	
	public void setContentPane(Container contentPane) 	{ this.contentPane = contentPane; }
	public void setScrollPane(List<JScrollPane> scrollPane) 	{ this.scrollPane = scrollPane;}

	public void paint(Graphics g) {}	

	public ZoomPanel getPanelOne() {
		return panelOne;
	}

	public void setPanelOne(ZoomPanel panelOne) {
		this.panelOne = panelOne;
	}

	public MindMapController getController() {
		return controller;
	}

	public void setController(MindMapController controller) {
		this.controller = controller;
	}

	public RemindNoteView getNoteView() {
		return noteView;
	}

	public void setNoteView(RemindNoteView noteView) {
		this.noteView = noteView;
	}
	
	public int getPanelNum(int index){
		return this.mapManageNum.get(index);
	}
	
	private class TabPaneListener implements MouseListener{
		public void mouseClicked(MouseEvent e){
			noteView.setTreeModel(controller.getTopicDAO(
					mapManageNum.get(tapPane.getSelectedIndex())));
		}
		public void mousePressed(MouseEvent e){}
		public void mouseEntered(MouseEvent e){}
		public void mouseExited(MouseEvent e){}
		public void mouseReleased(MouseEvent e){}
	}
	
}
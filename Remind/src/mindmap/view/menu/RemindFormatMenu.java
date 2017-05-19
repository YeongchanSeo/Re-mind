package mindmap.view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import mindmap.view.center.RemindMindMapView;
import mindmap.view.menu.dialog.RemindFontOptionDialog;
import mindmap.view.menu.dialog.RemindLineOptionDialog;
import mindmap.view.menu.dialog.RemindTopicOptionDialog;

public class RemindFormatMenu {
	
	/**-----------------------------------------------------------------------------------------
	------------------------------------------------------------------------------Member Field*/
	
	private JMenu 		formatMenu;
	private JMenuItem 	fontOption;
	private JMenuItem 	lineOption;
	private JMenuItem 	topicOption;
	private  RemindMindMapView mindMapView;
	private int			paneNum;
	
	/**-----------------------------------------------------------------------------------------
	-------------------------------------------------------------------------------Constructor*/
	
	public RemindFormatMenu(JMenuBar menuBar, RemindMindMapView mindMapView) {		
		RemindMenuTemplate createMenu 	= new RemindMenuTemplate();
		
		this.mindMapView    = mindMapView;
		this.paneNum        = mindMapView.getPanelNum(mindMapView.getTapPane().getSelectedIndex());
		this.formatMenu 	= createMenu.addMenu("서식");
		menuBar.add(this.formatMenu);		
		this.fontOption 	= createMenu.addMenuItem("글꼴 설정");
		this.lineOption 	= createMenu.addMenuItem("선 설정");
		this.topicOption 	= createMenu.addMenuItem("토픽 설정");
		
		addMenu(createMenu);
		addListener();
	}
	
	/**-----------------------------------------------------------------------------------------
	------------------------------------------------------------------------------메뉴에 아이템을 추가 */
	
	private void addMenu(RemindMenuTemplate createMenu) {
		this.formatMenu.add(this.fontOption);
		this.formatMenu.add(this.lineOption);
		this.formatMenu.add(this.topicOption);
	}
	
	private void addListener(){
		this.fontOption.addActionListener(new FontOptionListener());
		this.lineOption.addActionListener(new LineOptionListener());
		this.topicOption.addActionListener(new TopicOptionListener());
	}
	
	/**-----------------------------------------------------------------------------------------
	------------------------------------------------------------------------------------Getter*/
	
	public JMenu getFormatMenu() 		{ return this.formatMenu;}
	public JMenuItem getFontOption() 	{ return this.fontOption; }
	public JMenuItem getLineOption() 	{ return this.lineOption; }
	public JMenuItem getTopicOption() 	{ return this.topicOption; }
	
	private class FontOptionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			new RemindFontOptionDialog(mindMapView, paneNum);
		}
	}
	
	private class LineOptionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			new RemindLineOptionDialog(mindMapView, paneNum);
		}
	}
	
	private class TopicOptionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			new RemindTopicOptionDialog(mindMapView, paneNum);
		}
	}
}
package mindmap.view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import main.MindMapController;
import mindmap.view.center.RemindMindMapView;
import mindmap.view.center.ZoomPanel;
import diagram.Topic;

public class RemindEditMenu {
	
	/**-----------------------------------------------------------------------------------------
	------------------------------------------------------------------------------Member Field*/
	
	private JMenu 		editMenu;
	private JMenuItem 	undo;
	private JMenuItem 	redo;
	private JMenuItem 	cut;
	private JMenuItem 	copy;
	private JMenuItem 	paste;
	private JMenuItem 	delete;
	private JMenuItem 	findAndChange;
	
	private RemindMindMapView mindMapView;
	private MindMapController controller;
		
	/**-----------------------------------------------------------------------------------------
	-------------------------------------------------------------------------------Constructor*/
	
	public RemindEditMenu(JMenuBar menuBar, RemindMindMapView mindMapView) {		
		RemindMenuTemplate createMenu = new RemindMenuTemplate();
		
		this.mindMapView    = mindMapView;
		this.controller     = mindMapView.getController();
		
		this.editMenu 		= createMenu.addMenu("편집");
		menuBar.add(this.editMenu);
		this.undo 			= createMenu.addMenuItem("실행 취소");
		this.redo 			= createMenu.addMenuItem("다시 실행");
		this.cut 			= createMenu.addMenuItem("잘라내기");
		this.copy 			= createMenu.addMenuItem("복사");
		this.paste 			= createMenu.addMenuItem("붙여넣기");
		this.delete 		= createMenu.addMenuItem("삭제");
		this.findAndChange 	= createMenu.addMenuItem("찾기/바꾸기");
		
		addMenu(createMenu);
		addListener();
	}
	
	/**-----------------------------------------------------------------------------------------
	------------------------------------------------------------------------------메뉴에 아이템을 추가 */
	
	private void addMenu(RemindMenuTemplate createMenu) {
		this.editMenu.add(this.undo);
		this.editMenu.add(this.redo);
		this.editMenu.add(createMenu.addSeparator());
		this.editMenu.add(this.cut);
		this.editMenu.add(this.copy);
		this.editMenu.add(this.paste);
		this.editMenu.add(createMenu.addSeparator());
		this.editMenu.add(this.delete);
		this.editMenu.add(createMenu.addSeparator());
		this.editMenu.add(this.findAndChange);
	}
	
	private void addListener(){
		this.delete.addActionListener(new DeletionTopicListener());
	}

	/**-----------------------------------------------------------------------------------------
	------------------------------------------------------------------------------------Getter*/
	
	public JMenu getEditMenu() 			{ return this.editMenu; }
	public JMenuItem getUndo() 			{ return this.undo; }
	public JMenuItem getRedo() 			{ return this.redo; }
	public JMenuItem getCut() 			{ return this.cut; }
	public JMenuItem getCopy()			{ return this.copy; }
	public JMenuItem getPaste() 		{ return this.paste; }
	public JMenuItem getDelete() 		{ return this.delete; }
	public JMenuItem getFindAndChange() { return this.findAndChange; }
	
	private class DeletionTopicListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			ZoomPanel zoomPanel = ((ZoomPanel)(mindMapView.getScrollPane().
					get(mindMapView.getTapPane().getSelectedIndex()).
					getViewport().getComponent(0)));
			Topic selectedTopic = zoomPanel.getSelectedTopic();
			int paneNum        = mindMapView.getPanelNum(mindMapView.getTapPane().getSelectedIndex());
			if(selectedTopic == null){
				JOptionPane.showMessageDialog
				(zoomPanel.getFrame(), "토픽을 선택하여 주십시오.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else if(selectedTopic == controller.getTopicList(paneNum).get(0)){
				JOptionPane.showMessageDialog
				(zoomPanel.getFrame(), "중심 토픽은 삭제할 수 없습니다.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Topic temp = controller.getParent(paneNum, selectedTopic);
			controller.remove(paneNum, selectedTopic);
			selectedTopic = temp;
			selectedTopic.setSelected(true);
			zoomPanel.setSelectedTopic(selectedTopic);
			
			zoomPanel.repaint();
		}
	}
}


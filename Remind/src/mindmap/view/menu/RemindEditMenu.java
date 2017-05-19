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
		
		this.editMenu 		= createMenu.addMenu("����");
		menuBar.add(this.editMenu);
		this.undo 			= createMenu.addMenuItem("���� ���");
		this.redo 			= createMenu.addMenuItem("�ٽ� ����");
		this.cut 			= createMenu.addMenuItem("�߶󳻱�");
		this.copy 			= createMenu.addMenuItem("����");
		this.paste 			= createMenu.addMenuItem("�ٿ��ֱ�");
		this.delete 		= createMenu.addMenuItem("����");
		this.findAndChange 	= createMenu.addMenuItem("ã��/�ٲٱ�");
		
		addMenu(createMenu);
		addListener();
	}
	
	/**-----------------------------------------------------------------------------------------
	------------------------------------------------------------------------------�޴��� �������� �߰� */
	
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
				(zoomPanel.getFrame(), "������ �����Ͽ� �ֽʽÿ�.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else if(selectedTopic == controller.getTopicList(paneNum).get(0)){
				JOptionPane.showMessageDialog
				(zoomPanel.getFrame(), "�߽� ������ ������ �� �����ϴ�.", "Error", JOptionPane.ERROR_MESSAGE);
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


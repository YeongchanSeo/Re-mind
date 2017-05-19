package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import mindmap.view.RemindMainFrame;
import textpackage.RemindTextMainFrame;


public class ModeModule {
	
	public static final int MAP_MODE = 0;
	public static final int TEXT_MODE = 1;
	
	private RemindMainFrame map;
//	private MainFrame map;
	private RemindTextMainFrame text;
	private MindMapController controller;
	private int selectMode = MAP_MODE;
	//JTree용 model
	private DefaultTreeModel model ;
	private ActionListener changeAction;
	
	
	public ModeModule(){
		controller = new MindMapController();
		DefaultMutableTreeNode node = new DefaultMutableTreeNode("첫째");
		model = new DefaultTreeModel(node);
		DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("둘째");
		model.insertNodeInto(node2, node, 0);
		model.insertNodeInto(new DefaultMutableTreeNode("셋째"),node2,0);
	}
	public void run(){
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
		catch(Exception e){}
		map = new RemindMainFrame(this);
		changeAction = new ChangeModeListener(this);
		map.runFrame();
//		map = new MainFrame();
	}
	public void changeMode(){
		if(selectMode == TEXT_MODE){
			if(text!=null ){
				text.dispose();
				map.setVisible(true);
				text = null;
			}
			selectMode = MAP_MODE;
		}else{
			selectMode = TEXT_MODE;
//			text = new RemindTextMainFrame(this);
			try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
			catch(Exception e){}
			text = new RemindTextMainFrame(this);
			text.setFrame();
			map.setVisible(false);
			//map.dispose();
		}
	}
	public RemindMainFrame getMap() {
		return map;
	}
	public void setMap(RemindMainFrame map) {
		this.map = map;
	}
	public RemindTextMainFrame getText() {
		return text;
	}
	public void setText(RemindTextMainFrame text) {
		this.text = text;
	}
	public MindMapController getController() {
		return controller;
	}
	public void setController(MindMapController controller) {
		this.controller = controller;
	}
	public int getSelectMode() {
		return selectMode;
	}
	public void setSelectMode(int selectMode) {
		this.selectMode = selectMode;
	}
	public DefaultTreeModel getModel() {
		return model;
	}
	public void setModel(DefaultTreeModel model) {
		this.model = model;
	}
	public ActionListener getChangeAction() {
		return changeAction;
	}

	
}

 class ChangeModeListener implements ActionListener {
	private ModeModule mode;

	public ChangeModeListener(ModeModule mode){
		this.mode = mode;
	}
	public void actionPerformed(ActionEvent e){
		System.out.println("모드 전환");
		System.out.println(mode.getSelectMode());
		mode.changeMode();
	}
}


package mindmap.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import main.ModeModule;
import mindmap.view.bottom.RemindNoteView;
import mindmap.view.center.RemindMindMapView;
import mindmap.view.menu.RemindEditMenu;
import mindmap.view.menu.RemindFileMenu;
import mindmap.view.menu.RemindFormatMenu;
import mindmap.view.menu.RemindHelpMenu;
import mindmap.view.menu.RemindInsertMenu;
import mindmap.view.menu.RemindViewMenu;
import mindmap.view.toolbar.RemindToolBar;

public class RemindMainFrame extends JFrame {

	private static final long serialVersionUID = -1823822083638550008L;
	
	public  static final int VIEWSIZE_WIDTH;
	public  static final int VIEWSIZE_HEIGHT;
	public  static final int START_XCOORD;
	public  static final int START_YCOORD;
	
	static { VIEWSIZE_WIDTH = 1280;
			 VIEWSIZE_HEIGHT = 720;
			 START_XCOORD = 0;
			 START_YCOORD = 0;}
	
	private JMenuBar 			menuBar;
	private RemindFileMenu 		fileMenuList;
	private RemindEditMenu 		editMenuList;
	private RemindInsertMenu 	insertMenuList;
	private RemindViewMenu 		viewMenuList;
	private RemindFormatMenu 	formatMenuList;
	private RemindHelpMenu 		helpMenuList;
	
	private RemindToolBar		toolBarList;	
	private RemindNoteView		noteView;
	
	private RemindMindMapView	mindMapView;
	

	
	private ModeModule mode;
	public RemindMainFrame(ModeModule mode){
		super("Re-Mind");
		Container contentPane 	= super.getContentPane();
		
		this.menuBar 			= new JMenuBar();	
		super.setJMenuBar(this.menuBar);
		
		this.mode 				= mode;
		this.noteView 			= new RemindNoteView(contentPane, mode.getController());
		
		this.mindMapView 		= new RemindMindMapView(mode, this, noteView);
		this.fileMenuList 		= new RemindFileMenu(this.menuBar, mindMapView);
		this.editMenuList 		= new RemindEditMenu(this.menuBar, mindMapView);
		this.insertMenuList		= new RemindInsertMenu(this.menuBar, mindMapView);
		this.viewMenuList		= new RemindViewMenu(this.menuBar, mindMapView);
		this.formatMenuList		= new RemindFormatMenu(this.menuBar, mindMapView);
		this.helpMenuList		= new RemindHelpMenu(this.menuBar);
		this.toolBarList 		= new RemindToolBar(contentPane, mindMapView);
	}

	public void runAction(){
		toolBarList.getExportTextBtn().addActionListener(mode.getChangeAction());
		fileMenuList.getExportText().addActionListener(mode.getChangeAction());
	}

	public void runFrame() {
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLocationByPlatform(true);
		super.setPreferredSize(new Dimension(RemindMainFrame.VIEWSIZE_WIDTH, RemindMainFrame.VIEWSIZE_HEIGHT));
		super.setLocation(0, 0);
		super.pack();
		this.runAction();
		super.setVisible(true);
	}
	
	public RemindFileMenu getFileMenuList() 						{ return this.fileMenuList; }
	public RemindEditMenu getEditMenuList() 						{ return this.editMenuList; }
	public RemindInsertMenu getInsertMenuList() 					{ return this.insertMenuList; }
	public RemindViewMenu getViewMenuList() 						{ return this.viewMenuList; }
	public RemindFormatMenu getFormatMenuList() 					{ return this.formatMenuList; }
	public RemindHelpMenu getHelpMenuList() 						{ return this.helpMenuList; }
	public RemindMindMapView getMindMapView() 						{ return this.mindMapView; }
	public RemindToolBar getToolBarList()							{ return this.toolBarList; }
	public RemindNoteView getNoteView() 							{ return this.noteView; }
	
	public void setFileMenuList(RemindFileMenu fileMenuList) 		{ this.fileMenuList = fileMenuList; }
	public void setEditMenuList(RemindEditMenu editMenuList) 		{ this.editMenuList = editMenuList; }
	public void setInsertMenuList(RemindInsertMenu insertMenuList) 	{ this.insertMenuList = insertMenuList; }
	public void setViewMenuList(RemindViewMenu viewMenuList) 		{ this.viewMenuList = viewMenuList; }
	public void setFormatMenuList(RemindFormatMenu formatMenuList) 	{ this.formatMenuList = formatMenuList; }
	public void setHelpMenuList(RemindHelpMenu helpMenuList) 		{ this.helpMenuList = helpMenuList; }
	public void setMindMapView(RemindMindMapView mindMapView) 		{ this.mindMapView = mindMapView; }
	public void setToolBarList(RemindToolBar toolBarList) 			{ this.toolBarList = toolBarList; }
	public void setNoteView(RemindNoteView noteView) 				{ this.noteView = noteView; }
}
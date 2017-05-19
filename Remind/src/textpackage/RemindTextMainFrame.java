package textpackage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import main.ModeModule;
import main.TextModeController;

public class RemindTextMainFrame extends JFrame {

	private static final long serialVersionUID = -1823822083638550008L;
	
	private JMenuBar 			    menuBar;
	private RemindTextFileMenu 		fileMenuList;
	private RemindTextEditMenu 		editMenuList;
	private RemindTextViewMenu 		viewMenuList;
	private RemindTextFormatMenu 	formatMenuList;
	private RemindTextHelpMenu 		helpMenuList;
	private RemindTextSidePanel		sidePanel;
	private JTabbedPane				sheet;
	private JEditorPane 			pane;
	private JScrollPane 			scroll;
	private TestPane				testPane;
	private TextModeController 		controller;
	
	//모드있을 경우
	private ModeModule mode;
	public RemindTextMainFrame(ModeModule mode){
		super("Re-mind");
		this.mode = mode;
		this.controller 	= new TextModeController(mode.getController(), mode.getMap().getMindMapView());
		this.menuBar 		= new JMenuBar();
		this.fileMenuList 	= new RemindTextFileMenu(menuBar);
		this.editMenuList 	= new RemindTextEditMenu(menuBar);
		this.viewMenuList	= new RemindTextViewMenu(menuBar);
		this.formatMenuList	= new RemindTextFormatMenu(menuBar);
		this.helpMenuList	= new RemindTextHelpMenu(menuBar);
		this.sheet 			= new JTabbedPane();
		sheet.setOpaque(false);
		this.testPane 		= new TestPane(controller.getStr());
		this.sidePanel 		= new RemindTextSidePanel(mode, testPane, controller, mode.getMap().getMindMapView());
		this.pane 			= new JEditorPane();
		
		
		sheet.add(testPane);
		super.setJMenuBar(menuBar);
		JPanel panel = new JPanel(new BorderLayout());
		JTextField field = new JTextField("what");
		panel.add(field,BorderLayout.SOUTH);
		panel.add(sheet);
		pane.setEditable(false);
		Container contentPane = super.getContentPane();
		contentPane.add(new JScrollPane(panel));
		contentPane.add(sidePanel, BorderLayout.EAST);	
	}
	//모드 없을 경우
	public RemindTextMainFrame() { 
		super("Re-mind");
		this.menuBar 		= new JMenuBar();
		this.fileMenuList 	= new RemindTextFileMenu(menuBar);
		this.editMenuList 	= new RemindTextEditMenu(menuBar);
		this.viewMenuList	= new RemindTextViewMenu(menuBar);
		this.formatMenuList	= new RemindTextFormatMenu(menuBar);
		this.helpMenuList	= new RemindTextHelpMenu(menuBar);
//		this.sidePanel 		= new RemindTextSidePanel(mode);
		this.pane = new JEditorPane();
		this.sheet = new JTabbedPane();
		this.scroll = new JScrollPane(sheet);
		sheet.add(testPane);
		super.setJMenuBar(menuBar);
		JPanel panel = new JPanel(new BorderLayout());
		JTextField field = new JTextField("what");
		panel.add(field,BorderLayout.SOUTH);
		panel.add(scroll);
		pane.setEditable(true);
		Container contentPane = super.getContentPane();
		contentPane.add(new JScrollPane(panel));
		contentPane.add(sidePanel, BorderLayout.EAST);
	}
	
	public void setFrame() {
		super.setLocationByPlatform(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setPreferredSize(new Dimension(1280, 720));
		super.setLocation(0, 0);
		super.pack();
		super.setVisible(true);
	}
	
	public RemindTextFileMenu getFileMenuList() 						{ return fileMenuList; }
	public RemindTextEditMenu getEditMenuList() 						{ return editMenuList; }
	
	public RemindTextViewMenu getViewMenuList() 						{ return viewMenuList; }
	public RemindTextFormatMenu getFormatMenuList() 					{ return formatMenuList; }
	public RemindTextHelpMenu getHelpMenuList() 						{ return helpMenuList; }

	public void setFileMenuList(RemindTextFileMenu fileMenuList) 		{ this.fileMenuList = fileMenuList; }
	public void setEditMenuList(RemindTextEditMenu editMenuList) 		{ this.editMenuList = editMenuList; }
	
	public void setViewMenuList(RemindTextViewMenu viewMenuList) 		{ this.viewMenuList = viewMenuList; }
	public void setFormatMenuList(RemindTextFormatMenu formatMenuList) 	{ this.formatMenuList = formatMenuList; }
	public void setHelpMenuList(RemindTextHelpMenu helpMenuList) 		{ this.helpMenuList = helpMenuList; }
}
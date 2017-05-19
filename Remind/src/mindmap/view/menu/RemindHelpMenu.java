package mindmap.view.menu;

import javax.swing.*;

public class RemindHelpMenu {
	
	/**-----------------------------------------------------------------------------------------
	------------------------------------------------------------------------------Member Field*/
	
	private JMenu 		helpMenu;
	private JMenuItem 	help;
	
	/**-----------------------------------------------------------------------------------------
	-------------------------------------------------------------------------------Constructor*/
	
	public RemindHelpMenu(JMenuBar menuBar) {
		RemindMenuTemplate createMenu = new RemindMenuTemplate();
		
		this.helpMenu 	= createMenu.addMenu("����");
		menuBar.add(this.helpMenu);		
		this.help 		= createMenu.addMenuItem("����");
		this.helpMenu.add(this.help);
	}

	/**-----------------------------------------------------------------------------------------
	------------------------------------------------------------------------------------Getter*/
	
	public JMenu getHelpMenu() { return this.helpMenu; }
	public JMenuItem getHelp() { return this.help; }
}
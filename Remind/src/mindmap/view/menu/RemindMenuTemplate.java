package mindmap.view.menu;

import javax.swing.*;

public class RemindMenuTemplate {
	
	/**-----------------------------------------------------------------------------------------
	-----------------------------------------------------------------------------------�޴� ���� Ʋ*/
	
	public JMenu addMenu(String title) 			{ return new JMenu(title); }
	
	/**-----------------------------------------------------------------------------------------
	-------------------------------------------------------------------------------�޴� ������ ���� Ʋ*/
	
	public JMenuItem addMenuItem(String title) 	{ return new JMenuItem(title); }
	
	/**-----------------------------------------------------------------------------------------
	---------------------------------------------------------------------------------���� �� ���� Ʋ */
	
	public JSeparator addSeparator() 			{ return new JSeparator(); }
}
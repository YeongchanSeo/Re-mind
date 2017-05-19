package textpackage;
import javax.swing.*;

public class RemindTextCreateMenuComponent {	
	public JMenu addMenu(String title) 			{ return new JMenu(title); }
	public JMenuItem addMenuItem(String title) 	{ return new JMenuItem(title); }
	public JSeparator addSeparator() 			{ return new JSeparator(); }
}
package textpackage;
import javax.swing.*;

public class RemindTextHelpMenu {
	
	RemindTextCreateMenuComponent createMenu;
	
	public RemindTextHelpMenu(JMenuBar menuBar) {
		
		this.createMenu = new RemindTextCreateMenuComponent();
		
		JMenu formatMenu = this.createMenu.addMenu("도움말");
		menuBar.add(formatMenu);
		formatMenu.add(this.createMenu.addMenuItem("도움말"));
		
	}
}
package textpackage;
import javax.swing.*;

public class RemindTextEditMenu {
	
	RemindTextCreateMenuComponent createMenu;
	
	public RemindTextEditMenu(JMenuBar menuBar) {
		
		this.createMenu = new RemindTextCreateMenuComponent();
		
		JMenu editMenu = this.createMenu.addMenu("����");
		menuBar.add(editMenu);
		editMenu.add(this.createMenu.addMenuItem("���� ���"));
		editMenu.add(this.createMenu.addMenuItem("�ٽ� ����"));
		editMenu.add(this.createMenu.addSeparator());
		editMenu.add(this.createMenu.addMenuItem("�߶󳻱�"));
		editMenu.add(this.createMenu.addMenuItem("����"));
		editMenu.add(this.createMenu.addMenuItem("�ٿ��ֱ�"));
		editMenu.add(this.createMenu.addSeparator());
		editMenu.add(this.createMenu.addMenuItem("����"));
		editMenu.add(this.createMenu.addSeparator());
		editMenu.add(this.createMenu.addMenuItem("ã��/�ٲٱ�"));
	}
}
package textpackage;
import javax.swing.*;

public class RemindTextFormatMenu {
	
	RemindTextCreateMenuComponent createMenu;
	
	public RemindTextFormatMenu(JMenuBar menuBar) {
		
		this.createMenu = new RemindTextCreateMenuComponent();
		
		JMenu formatMenu = this.createMenu.addMenu("����");
		menuBar.add(formatMenu);
		formatMenu.add(this.createMenu.addMenuItem("�۲� ����"));
		formatMenu.add(this.createMenu.addMenuItem("�׸� �� ����"));
//		formatMenu.add(this.createMenu.addSeparator());
//		formatMenu.add(this.createMenu.addMenuItem("���� ��� ����"));
//		formatMenu.add(this.createMenu.addMenuItem("���� ��Ÿ�� ����"));
//		formatMenu.add(this.createMenu.addSeparator());
//		formatMenu.add(this.createMenu.addMenuItem("������ �� ����"));
//		formatMenu.add(this.createMenu.addMenuItem("������ ��� ����"));
//		formatMenu.add(this.createMenu.addMenuItem("������ ��Ÿ�� ����"));
//		formatMenu.add(this.createMenu.addSeparator());
//		formatMenu.add(this.createMenu.addMenuItem("���� �ڵ� ���� ����"));
//		formatMenu.add(this.createMenu.addMenuItem("���� �ڵ� �� ���� ����"));
	}
}
package textpackage;
import javax.swing.*;

public class RemindTextViewMenu {
	
	private RemindTextCreateMenuComponent createMenu;
	
	public RemindTextViewMenu(JMenuBar menuBar) {
		
		this.createMenu = new RemindTextCreateMenuComponent();
		
		JMenu viewMenu = this.createMenu.addMenu("����");
		menuBar.add(viewMenu);
		viewMenu.add(this.createMenu.addMenuItem("��ü ��� ����"));
		viewMenu.add(this.createMenu.addMenuItem("�߽� �������� �̵�"));
		viewMenu.add(this.createMenu.addSeparator());
		viewMenu.add(this.createMenu.addMenuItem("�� ��ü����"));
		viewMenu.add(this.createMenu.addMenuItem("ȭ�� Ȯ��"));
		viewMenu.add(this.createMenu.addMenuItem("ȭ�� ���"));
		viewMenu.add(this.createMenu.addSeparator());
		viewMenu.add(this.createMenu.addMenuItem("���� ����"));
		viewMenu.add(this.createMenu.addMenuItem("���� ���"));
		viewMenu.add(this.createMenu.addMenuItem("��ü ���� ����"));
		viewMenu.add(this.createMenu.addMenuItem("��ü ���� ���"));
	}
}
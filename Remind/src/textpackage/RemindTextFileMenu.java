package textpackage;
import javax.swing.*;

public class RemindTextFileMenu {
	
	RemindTextCreateMenuComponent createMenu;
	
	public RemindTextFileMenu(JMenuBar menuBar) {
		
		this.createMenu = new RemindTextCreateMenuComponent();
		
		JMenu fileMenu = this.createMenu.addMenu("����");
		menuBar.add(fileMenu);
		fileMenu.add(this.createMenu.addMenuItem("������"));
		JMenu fileOpen = this.createMenu.addMenu("���� ����");
		fileMenu.add(fileOpen);
			fileOpen.add(this.createMenu.addMenuItem("����"));
			fileOpen.add(this.createMenu.addSeparator());
			fileOpen.add(this.createMenu.addMenuItem("�ֱ� ����� �׸�"));
			fileOpen.add(this.createMenu.addSeparator());
		fileMenu.add(this.createMenu.addMenuItem("���� �ݱ�"));
		fileMenu.add(this.createMenu.addMenuItem("��� �ݱ�"));
		fileMenu.add(this.createMenu.addSeparator());
		fileMenu.add(this.createMenu.addMenuItem("�����ϱ�"));
		fileMenu.add(this.createMenu.addMenuItem("�ٸ� �̸����� �����ϱ�"));
		fileMenu.add(this.createMenu.addSeparator());
		fileMenu.add(this.createMenu.addMenuItem("�μ�"));
		fileMenu.add(this.createMenu.addMenuItem("ȯ�漳��"));
		fileMenu.add(this.createMenu.addSeparator());
		fileMenu.add(this.createMenu.addMenuItem("�ؽ�Ʈ�� ���ε������ ��ȯ"));
		fileMenu.add(this.createMenu.addSeparator());
		fileMenu.add(this.createMenu.addMenuItem("����"));
	}
}
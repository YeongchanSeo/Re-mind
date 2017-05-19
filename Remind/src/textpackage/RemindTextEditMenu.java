package textpackage;
import javax.swing.*;

public class RemindTextEditMenu {
	
	RemindTextCreateMenuComponent createMenu;
	
	public RemindTextEditMenu(JMenuBar menuBar) {
		
		this.createMenu = new RemindTextCreateMenuComponent();
		
		JMenu editMenu = this.createMenu.addMenu("편집");
		menuBar.add(editMenu);
		editMenu.add(this.createMenu.addMenuItem("실행 취소"));
		editMenu.add(this.createMenu.addMenuItem("다시 실행"));
		editMenu.add(this.createMenu.addSeparator());
		editMenu.add(this.createMenu.addMenuItem("잘라내기"));
		editMenu.add(this.createMenu.addMenuItem("복사"));
		editMenu.add(this.createMenu.addMenuItem("붙여넣기"));
		editMenu.add(this.createMenu.addSeparator());
		editMenu.add(this.createMenu.addMenuItem("삭제"));
		editMenu.add(this.createMenu.addSeparator());
		editMenu.add(this.createMenu.addMenuItem("찾기/바꾸기"));
	}
}
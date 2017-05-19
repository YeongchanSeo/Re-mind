package textpackage;
import javax.swing.*;

public class RemindTextFileMenu {
	
	RemindTextCreateMenuComponent createMenu;
	
	public RemindTextFileMenu(JMenuBar menuBar) {
		
		this.createMenu = new RemindTextCreateMenuComponent();
		
		JMenu fileMenu = this.createMenu.addMenu("파일");
		menuBar.add(fileMenu);
		fileMenu.add(this.createMenu.addMenuItem("새파일"));
		JMenu fileOpen = this.createMenu.addMenu("파일 열기");
		fileMenu.add(fileOpen);
			fileOpen.add(this.createMenu.addMenuItem("열기"));
			fileOpen.add(this.createMenu.addSeparator());
			fileOpen.add(this.createMenu.addMenuItem("최근 사용한 항목"));
			fileOpen.add(this.createMenu.addSeparator());
		fileMenu.add(this.createMenu.addMenuItem("파일 닫기"));
		fileMenu.add(this.createMenu.addMenuItem("모두 닫기"));
		fileMenu.add(this.createMenu.addSeparator());
		fileMenu.add(this.createMenu.addMenuItem("저장하기"));
		fileMenu.add(this.createMenu.addMenuItem("다른 이름으로 저장하기"));
		fileMenu.add(this.createMenu.addSeparator());
		fileMenu.add(this.createMenu.addMenuItem("인쇄"));
		fileMenu.add(this.createMenu.addMenuItem("환경설정"));
		fileMenu.add(this.createMenu.addSeparator());
		fileMenu.add(this.createMenu.addMenuItem("텍스트를 마인드맵으로 변환"));
		fileMenu.add(this.createMenu.addSeparator());
		fileMenu.add(this.createMenu.addMenuItem("종료"));
	}
}
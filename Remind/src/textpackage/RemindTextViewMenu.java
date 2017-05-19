package textpackage;
import javax.swing.*;

public class RemindTextViewMenu {
	
	private RemindTextCreateMenuComponent createMenu;
	
	public RemindTextViewMenu(JMenuBar menuBar) {
		
		this.createMenu = new RemindTextCreateMenuComponent();
		
		JMenu viewMenu = this.createMenu.addMenu("보기");
		menuBar.add(viewMenu);
		viewMenu.add(this.createMenu.addMenuItem("전체 목록 보기"));
		viewMenu.add(this.createMenu.addMenuItem("중심 토픽으로 이동"));
		viewMenu.add(this.createMenu.addSeparator());
		viewMenu.add(this.createMenu.addMenuItem("맵 전체보기"));
		viewMenu.add(this.createMenu.addMenuItem("화면 확대"));
		viewMenu.add(this.createMenu.addMenuItem("화면 축소"));
		viewMenu.add(this.createMenu.addSeparator());
		viewMenu.add(this.createMenu.addMenuItem("토픽 접기"));
		viewMenu.add(this.createMenu.addMenuItem("토픽 펴기"));
		viewMenu.add(this.createMenu.addMenuItem("전체 토픽 접기"));
		viewMenu.add(this.createMenu.addMenuItem("전체 토픽 펴기"));
	}
}
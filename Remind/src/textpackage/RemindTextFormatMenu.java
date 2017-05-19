package textpackage;
import javax.swing.*;

public class RemindTextFormatMenu {
	
	RemindTextCreateMenuComponent createMenu;
	
	public RemindTextFormatMenu(JMenuBar menuBar) {
		
		this.createMenu = new RemindTextCreateMenuComponent();
		
		JMenu formatMenu = this.createMenu.addMenu("서식");
		menuBar.add(formatMenu);
		formatMenu.add(this.createMenu.addMenuItem("글꼴 설정"));
		formatMenu.add(this.createMenu.addMenuItem("테마 색 설정"));
//		formatMenu.add(this.createMenu.addSeparator());
//		formatMenu.add(this.createMenu.addMenuItem("토픽 모양 설정"));
//		formatMenu.add(this.createMenu.addMenuItem("토픽 스타일 설정"));
//		formatMenu.add(this.createMenu.addSeparator());
//		formatMenu.add(this.createMenu.addMenuItem("가지선 색 설정"));
//		formatMenu.add(this.createMenu.addMenuItem("가지선 모양 설정"));
//		formatMenu.add(this.createMenu.addMenuItem("가지선 스타일 설정"));
//		formatMenu.add(this.createMenu.addSeparator());
//		formatMenu.add(this.createMenu.addMenuItem("토픽 자동 맞춤 설정"));
//		formatMenu.add(this.createMenu.addMenuItem("토픽 자동 줄 맞춤 설정"));
	}
}
package mindmap.view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import mindmap.view.center.RemindMindMapView;

public class RemindFileMenu {
	
	/**-----------------------------------------------------------------------------------------
	------------------------------------------------------------------------------Member Field*/
	
	private JMenu 		fileMenu;
	private JMenuItem 	newFile;
	private JMenuItem 	fileOpen;
	private JMenuItem 	fileClose;
	private JMenuItem 	allClose;
	private JMenuItem	save;
	private JMenuItem 	saveAs;
	private JMenuItem 	printer;
	private JMenuItem 	option;
	private JMenuItem 	exportText;
	private JMenuItem 	quit;
	private RemindMindMapView  	mindMapView;
	
	/**-----------------------------------------------------------------------------------------
	-------------------------------------------------------------------------------Constructor*/
	
	public RemindFileMenu(JMenuBar menuBar, RemindMindMapView mindMapView) {
		
		this.mindMapView = mindMapView;	
		RemindMenuTemplate createMenu = new RemindMenuTemplate();
		
		this.fileMenu 	= createMenu.addMenu("파일");
		menuBar.add(this.fileMenu);
		this.newFile 	= createMenu.addMenuItem("새 파일 만들기");
		this.fileOpen 	= createMenu.addMenuItem("파일 열기");
		this.fileClose 	= createMenu.addMenuItem("파일 닫기");
		this.allClose 	= createMenu.addMenuItem("모두 닫기");
		this.save 		= createMenu.addMenuItem("저장하기");
		this.saveAs 	= createMenu.addMenuItem("다른 이름으로 저장하기");
		this.printer 	= createMenu.addMenuItem("인쇄");
		this.option 	= createMenu.addMenuItem("환경설정");
		this.exportText = createMenu.addMenuItem("마인드맵을 텍스트로 변환");		
		this.quit 		= createMenu.addMenuItem("종료");
		
		addMenu(createMenu);
		addListener();
	}
	
	/**-----------------------------------------------------------------------------------------
	------------------------------------------------------------------------------메뉴에 아이템을 추가 */
	
	private void addMenu(RemindMenuTemplate createMenu) {
		this.fileMenu.add(this.newFile);
		this.fileMenu.add(createMenu.addSeparator());
		this.fileMenu.add(this.fileOpen);
		this.fileMenu.add(this.fileClose);
		this.fileMenu.add(this.allClose);
		this.fileMenu.add(createMenu.addSeparator());
		this.fileMenu.add(this.save);
		this.fileMenu.add(this.saveAs);
		this.fileMenu.add(createMenu.addSeparator());
		this.fileMenu.add(this.printer);
		this.fileMenu.add(this.option);
		this.fileMenu.add(createMenu.addSeparator());
		this.fileMenu.add(this.exportText);
		this.fileMenu.add(createMenu.addSeparator());
		this.fileMenu.add(this.quit);
	}
	
	private void addListener(){
		this.newFile.addActionListener(new NewFileListener());
		this.fileClose.addActionListener(new FileCloseListener());
		this.allClose.addActionListener(new AllFileCloseListener());
		this.fileOpen.addActionListener(new FileOpenListener());
		this.save.addActionListener(new FileSaveListener());
	}

	/**-----------------------------------------------------------------------------------------
	------------------------------------------------------------------------------------Getter*/
	
	public JMenu getFileMenu() 			{ return this.fileMenu; }
	public JMenuItem getNewFile() 		{ return this.newFile; }
	public JMenuItem getFileOpen() 		{ return this.fileOpen; }
	public JMenuItem getFileClose() 	{ return this.fileClose; }
	public JMenuItem getAllClose() 		{ return this.allClose; }
	public JMenuItem getSave() 			{ return this.save; }
	public JMenuItem getSaveAs() 		{ return this.saveAs; }
	public JMenuItem getPrinter() 		{ return this.printer; }
	public JMenuItem getOption() 		{ return this.option; }
	public JMenuItem getExportText() 	{ return this.exportText; }
	public JMenuItem getQuit() 			{ return this.quit; }
	
	private class NewFileListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			mindMapView.newFile();			
		}
	}
	
	private class FileCloseListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			mindMapView.closeFile();			
		}
	}
	
	private class AllFileCloseListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			mindMapView.allCloseFile();			
		}
	}
	
	private class FileOpenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JFileChooser c = new JFileChooser();
			// Demonstrate "Open" dialog:
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"XML file", "xml");
			File workingDirectory = new File(System.getProperty("user.dir"));
			c.setCurrentDirectory(workingDirectory);

			c.setFileFilter(filter);
			int rVal = c.showOpenDialog(c);
			if (rVal == JFileChooser.APPROVE_OPTION) {

			}
		}
	}

	private class FileSaveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JFileChooser c = new JFileChooser();
			// Demonstrate "Save" dialog:
			int rVal = c.showSaveDialog(c);
			File workingDirectory = new File(System.getProperty("user.dir"));
			c.setCurrentDirectory(workingDirectory);

			if (rVal == JFileChooser.APPROVE_OPTION) {
			}
		}

	}
}
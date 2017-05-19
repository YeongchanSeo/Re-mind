package textpackage;
import javax.swing.UIManager;


public class RemindTextStarter {
	public static void main(String[] args) {
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
		catch(Exception e){}
		new RemindTextMainFrame().setFrame();
	}
}
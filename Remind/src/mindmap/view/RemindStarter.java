package mindmap.view;

import javax.swing.UIManager;

import main.ModeModule;

public class RemindStarter {
	
	public static int splashCount = 1;
	public static int splashSleep = 500;
	
	/**-----------------------------------------------------------------------------------------
	------------------------------------------------------------------------------Main Starter*/
	
	public static void main(String[] args) {
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
		catch(Exception e){}		
		new RemindSplashScreen(RemindStarter.splashSleep);
		ModeModule module = new ModeModule();
		module.run();
	}
}
package mindmap.view;

import java.awt.*;
import javax.swing.*;

public class RemindSplashScreen extends JWindow {
	
	private static final long serialVersionUID = 3606612241448457039L;		

	public RemindSplashScreen(int sleepSec) {
		JPanel content 	= (JPanel)getContentPane();
		Color line 		= new Color(200, 200, 200);
		
		content.setBackground(Color.white);
		content.setBorder(BorderFactory.createLineBorder(line, 7));

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((screen.width - 510) / 2, (screen.height - 290) / 2, 510, 290);
		
		JLabel copyRT 	 	= new JLabel("Lectopia 12±â ³ÍÁö½Ã Remind_Ver.1   ", JLabel.RIGHT);
		JLabel logoImage 	= new JLabel(new ImageIcon("src\\mindmaplogoimage\\RemindLogo" + 
														RemindStarter.splashCount + ".jpg"));	

		copyRT.setFont(new Font("Sans-Serif", Font.BOLD, 13));
		content.add(logoImage, BorderLayout.CENTER);
		content.add(copyRT, BorderLayout.SOUTH);
		
		setVisible(true);
		
		if(RemindStarter.splashCount < 5) { try { Thread.sleep(sleepSec); } catch (Exception e) {} }
		if(RemindStarter.splashCount > 4) {	try { Thread.sleep(30); } catch (Exception e) {} }
		if(RemindStarter.splashCount++ == 32) {
			try { Thread.sleep(2000); } catch(Exception e) {}
			setVisible(false); return;
		}
		new RemindSplashScreen(RemindStarter.splashSleep);
		
		setVisible(false);
	}
}
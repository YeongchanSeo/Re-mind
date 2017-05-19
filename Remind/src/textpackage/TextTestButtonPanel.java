package textpackage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

import textpackage.listener.TextExamListener;

public class TextTestButtonPanel extends JPanel{
	private JButton button;
	private RemindTextSidePanel panel;
	
	public TextTestButtonPanel(TestPane pane, RemindTextSidePanel panel){
		
		super(new BorderLayout());
		this.panel = panel;
		button = new JButton("시험 제출");
		button.setPreferredSize(new Dimension(100, 170));
		button.setFont(new Font(button.getFont().getName(), Font.BOLD, 50));
		add(button);
		button.addActionListener(new TextExamListener(pane.getTextTable(), panel));
	}
}

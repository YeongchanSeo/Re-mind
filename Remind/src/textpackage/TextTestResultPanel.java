package textpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class TextTestResultPanel extends JPanel{
	
	private JTextField title;
	private JLabel correctCount;
	private JLabel totalCount;
	
	public TextTestResultPanel(String title, int correct, int total){
		super(new BorderLayout());
		
		this.title = new JTextField(title);
		this.correctCount = new JLabel(correct+"");
		this.totalCount = new JLabel(total+"");
		
		JLabel slash = new JLabel("/");
		
		init(slash);
	}
	private void init(JLabel slash){
		
		titleInit(false, new Color(250, 220, 130), BorderFactory.createBevelBorder(BevelBorder.RAISED));
		fontSetting(slash, Font.BOLD, 150, Font.PLAIN, 70);
		
		JPanel content = new JPanel();
		contentSetColor(content, new Color(255, 255, 255));
		
		content.add(correctCount);
		content.add(slash);
		content.add(totalCount);
		contentSetSize(content, new Dimension(100, 150));
		add(title, BorderLayout.NORTH);
		add(content);
	}
	
	private void titleInit(boolean editable, Color titleBackground, Border style){
		
		title.setEditable(editable);
		title.setBackground(titleBackground);
		title.setBorder(style);
	}
	private void fontSetting(JLabel slash, int correctFont, int correctFontSize, int otherFont, int otherFontSize){
		
		correctCount.setFont(new Font(correctCount.getFont().getName(), correctFont, correctFontSize));
		slash.setFont(new Font(slash.getFont().getName(), otherFont, otherFontSize));
		totalCount.setFont(new Font(totalCount.getFont().getName(), otherFont, otherFontSize));
	}
	private void contentSetColor(Container content, Color contentBackgroundColor){
		content.setBackground(contentBackgroundColor);
	}
	private void contentSetSize(Container content, Dimension contentSize){
		content.setPreferredSize(contentSize);
	}
	
	public void settingTotalCount(int totalCount){
		this.totalCount.setText(totalCount+"");
	}
	public void settingCorrectCount(int correctCount){
		this.correctCount.setText(correctCount+"");
	}
}

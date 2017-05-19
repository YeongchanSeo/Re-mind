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
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class TextTestNotePanel extends JPanel{
	
	private JTextField title;
	private JLabel correctCount;
	private JLabel totalCount;
	
	public TextTestNotePanel(String title){
		super(new BorderLayout());
		
		this.title = new JTextField(title);
		this.correctCount = new JLabel();
		this.totalCount = new JLabel();
		
		JLabel slash = new JLabel();
		
		init(slash);
	}
	private void init(JLabel slash){
		
		titleInit(false, new Color(250, 220, 130), BorderFactory.createBevelBorder(BevelBorder.RAISED));
		fontSetting(slash, Font.BOLD, 150, Font.PLAIN, 70);
		JPanel content = new JPanel();
		contentSetColor(content, new Color(255, 255, 255));
		contentSetSize(content, new Dimension(100, 150));
		add(title, BorderLayout.NORTH);
		JLabel label = new JLabel("",SwingConstants.CENTER);

		label.setFont(new Font("¸¼Àº °íµñ BOLD", 0, 24));
		contentSetSize(label, new Dimension(100,150));
		content.add(label);
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

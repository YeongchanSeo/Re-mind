package textpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class TextTopicListPanel extends JPanel{
	
	private JTextField title;
	
	public TextTopicListPanel(JTree list, String title){
		super(new BorderLayout());
		
		this.title = new JTextField(title);
		init(list);
	}
	private void init(JTree list){
		titleInit(false, new Color(250, 220, 130), BorderFactory.createBevelBorder(BevelBorder.RAISED));
		JScrollPane content = new JScrollPane(list);
		contentSetSize(content, new Dimension(300, 50));
		add(title, BorderLayout.NORTH);
		add(content);
	}
	private void titleInit(boolean editable, Color titleBackground, Border style){
		title.setEditable(editable);
		title.setBackground(titleBackground);
		title.setBorder(style);
	}
	private void contentSetColor(Container content, Color contentBackgroundColor){
		content.setBackground(contentBackgroundColor);
	}
	private void contentSetSize(Container content, Dimension contentSize){
		content.setPreferredSize(contentSize);
	}
}

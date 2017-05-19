package mindmap.view.bottom;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;

public class RemindBottomTemplate {
	public void makeNote(ImageIcon icon, Box box, JTextArea textArea, Color backColor) {		
		JLabel writeField = new JLabel(icon);
		textArea.setBackground(backColor);
		
		JScrollPane writeScroll = new JScrollPane(textArea);
		writeScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		box.add(writeField);
		box.add(writeScroll);
	}
	
	public void makeMemo(String title, Box box, JTextArea textArea, Color fontColor, Color backColor) {		
		JTextField writeField = new JTextField(title);
		writeField.setFont(new Font("¸¼Àº °íµñ", Font.CENTER_BASELINE, 16));
		writeField.setForeground(fontColor);
		writeField.setEditable(false);
		writeField.setBackground(backColor);
		textArea.setBackground(new Color(200, 240, 200));
		
		JScrollPane writeScroll = new JScrollPane(textArea);
		writeScroll.setPreferredSize(new Dimension(100, 210));
		writeScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		box.add(writeField);
		box.add(writeScroll);
	}
	
	public void makeTree(String title, Box box, JTree tree, Color fontColor, Color backColor) {		
		JTextField writeField = new JTextField(title);
		writeField.setFont(new Font("¸¼Àº °íµñ", Font.CENTER_BASELINE, 16));
		writeField.setForeground(fontColor);
		writeField.setEditable(false);
		writeField.setBackground(backColor);
		
		JScrollPane treeScroll 	= new JScrollPane(tree);
		treeScroll.setPreferredSize(new Dimension(100, 210));
		tree.setBackground(new Color(245, 250, 250));
		
		box.add(writeField);
		box.add(treeScroll);
	}
}
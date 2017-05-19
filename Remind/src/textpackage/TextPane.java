package textpackage;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import mindmap.view.bottom.RemindBottomTemplate;

public class TextPane extends JComponent {
	// private JPanel notePanel;
	private ImageIcon whatIcon;
	private ImageIcon whyIcon;
	private ImageIcon howIcon;

	private JLabel title;
	private Box whatBox;
	private Box whyBox;
	private Box howBox;

	private JTextArea whatTextArea;
	private JTextArea whyTextArea;
	private JTextArea howTextArea;

	public TextPane() {

		this.title = new JLabel("what");
		this.whatIcon = new ImageIcon(
				"src\\mindmap\\view\\bottomicon\\what.png");
		this.whyIcon = new ImageIcon("src\\mindmap\\view\\bottomicon\\why.png");
		this.howIcon = new ImageIcon("src\\mindmap\\view\\bottomicon\\how.png");
		// this.notePanel = new JPanel(new GridLayout(3,1));
		this.setLayout(new GridLayout(4, 1));

		this.whatBox = Box.createHorizontalBox();
		this.whyBox = Box.createHorizontalBox();
		this.howBox = Box.createHorizontalBox();

		this.whatTextArea = new JTextArea();
		this.whyTextArea = new JTextArea();
		this.howTextArea = new JTextArea();

		RemindBottomTemplate makeBottom = new RemindBottomTemplate();

//		makeBottom.makeNote(this.whatIcon, this.whatBox, this.whatTextArea,
//				new Color(200, 240, 245));
//		makeBottom.makeNote(this.whyIcon, this.whyBox, this.whyTextArea,
//				new Color(255, 200, 220));
//		makeBottom.makeNote(this.howIcon, this.howBox, this.howTextArea,
//				new Color(255, 230, 220));
		makeBottom.makeNote(this.whatIcon, this.whatBox, this.whatTextArea,
				new Color(254, 254, 254));
		makeBottom.makeNote(this.whyIcon, this.whyBox, this.whyTextArea,
				new Color(254, 254, 254));
		makeBottom.makeNote(this.howIcon, this.howBox, this.howTextArea,
				new Color(254, 254, 254));

		this.add(title);
		this.add(this.whatBox);
		this.add(this.whyBox);
		this.add(this.howBox);

		// Container contentPane = getContentPane();
		// contentPane.add(notePanel);

	}

	public TextPane(String[] str) {
		this();
		addString(str);
	}

	
	public void addString(String[] str) {
		title.setFont(new Font("¸¼Àº °íµñ", 0, 20));
		title.setText(str[0]);
		whatTextArea.setText(str[1]);
		whatTextArea.setEditable(false);
		System.out.println("¿Ï·á");
		whyTextArea.setText(str[2]);
		whyTextArea.setEditable(false);
		howTextArea.setText(str[3]);
		howTextArea.setEditable(false);
	}

	public void initTextArea() {
		whatTextArea.setText("");
		whatTextArea.setEditable(true);
		whyTextArea.setText("");
		whyTextArea.setEditable(true);
		howTextArea.setText("");
		howTextArea.setEditable(true);
	}

	public JLabel getTitle() {
		return title;
	}

	public String[] getText() {
		String[] str = { whatTextArea.getText(), whyTextArea.getText(),
				howTextArea.getText() };

		return str;
	}

	public void setTitle(JLabel title) {
		this.title = title;
	}

	public Box getWhatBox() {
		return whatBox;
	}

	public void setWhatBox(Box whatBox) {
		this.whatBox = whatBox;
	}

	public Box getWhyBox() {
		return whyBox;
	}

	public void setWhyBox(Box whyBox) {
		this.whyBox = whyBox;
	}

	public Box getHowBox() {
		return howBox;
	}

	public void setHowBox(Box howBox) {
		this.howBox = howBox;
	}

	public JTextArea getWhatTextArea() {
		return whatTextArea;
	}

	public void setWhatTextArea(JTextArea whatTextArea) {
		this.whatTextArea = whatTextArea;
	}

	public JTextArea getWhyTextArea() {
		return whyTextArea;
	}

	public void setWhyTextArea(JTextArea whyTextArea) {
		this.whyTextArea = whyTextArea;
	}

	public JTextArea getHowTextArea() {
		return howTextArea;
	}

	public void setHowTextArea(JTextArea howTextArea) {
		this.howTextArea = howTextArea;
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		String[][] str = { { "a", "a", "a", "a" }, { "b", "b", "b", "b" },
				{ "c", "c", "c", "c" } };

		JComponent text = new TextPane(str[0]);
		Container contentPane = frame.getContentPane();
		frame.setPreferredSize(new Dimension(1000, 1000));

		JPanel panel = new JPanel();
		Box box = Box.createVerticalBox();
		box.add(text);

		JComponent text2 = new TextPane();
		box.add(text2);

		JComponent text3 = new TextPane();
		box.add(text3);

		JComponent text4 = new TextPane();
		box.add(text4);

		JComponent text5 = new TextPane();
		box.add(text5);

		JScrollPane pane = new JScrollPane(box);
		// pane.add(panel);

		contentPane.add(pane);

		frame.pack();
		frame.setVisible(true);

	}
}
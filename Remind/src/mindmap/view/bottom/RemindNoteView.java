package mindmap.view.bottom;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.TreeModel;

import main.MindMapController;
import diagram.Memo;
import diagram.Note;

public class RemindNoteView {
	
	private JPanel 		notePanel;
	
	private ImageIcon 	whatIcon;
	private ImageIcon 	whyIcon;
	private ImageIcon 	howIcon;
	
	private Box 		memoBox;
	private Box 		listBox;
	private Box 		whatBox;
	private Box 		whyBox;
	private Box 		howBox;
	
	private JTextArea 	whatTextArea;
	private JTextArea 	whyTextArea;
	private JTextArea 	howTextArea;
	private JTextArea 	memoTextArea;
	
	private JTree 		allTopicList;
	
	public RemindNoteView(Container contentPane, MindMapController controller) {				
		this.whatIcon 	= new ImageIcon("src\\mindmap\\view\\bottomicon\\what.png");
		this.whyIcon 	= new ImageIcon("src\\mindmap\\view\\bottomicon\\why.png");
		this.howIcon 	= new ImageIcon("src\\mindmap\\view\\bottomicon\\how.png");
		this.notePanel 	= new JPanel(new GridLayout(3, 1));
		
		this.whatBox = Box.createHorizontalBox();
		this.whyBox  = Box.createHorizontalBox();
		this.howBox  = Box.createHorizontalBox();
		this.memoBox = Box.createVerticalBox();
		this.listBox = Box.createVerticalBox();
		
		this.whatTextArea 	= new JTextArea();
		this.whyTextArea 	= new JTextArea();
		this.howTextArea	= new JTextArea();
		this.memoTextArea 	= new JTextArea();		
		this.allTopicList 	= new JTree();

		allTopicList.addTreeExpansionListener(new TreeExpansionListener(){
            @Override
            public void treeCollapsed(TreeExpansionEvent arg0) {
               // TODO Auto-generated method stub
               allTopicList.repaint();
            }
            @Override
            public void treeExpanded(TreeExpansionEvent arg0) {
               // TODO Auto-generated method stub
               allTopicList.repaint();
            }   
         });
		
		JSplitPane writePane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		JSplitPane totalPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		addNote(writePane, totalPane);
		setNote(contentPane, writePane, totalPane);
	}
	
	private void addNote(JSplitPane writePane, JSplitPane totalPane) {
		RemindBottomTemplate makeBottom = new RemindBottomTemplate();

//		makeBottom.makeNote(this.whatIcon, this.whatBox, this.whatTextArea, new Color(200, 240, 245));
//		makeBottom.makeNote(this.whyIcon, this.whyBox, this.whyTextArea, new Color(255, 200, 220));
//		makeBottom.makeNote(this.howIcon, this.howBox, this.howTextArea, new Color(255, 230, 220));
		
		makeBottom.makeNote(this.whatIcon, this.whatBox, this.whatTextArea, new Color(253, 253, 253));
		makeBottom.makeNote(this.whyIcon, this.whyBox, this.whyTextArea, new Color(253, 253, 253));
		makeBottom.makeNote(this.howIcon, this.howBox, this.howTextArea, new Color(253, 253, 253));
		
		this.notePanel.add(this.whatBox);
		this.notePanel.add(this.whyBox);
		this.notePanel.add(this.howBox);
		
		makeBottom.makeMemo("메모", this.memoBox, this.memoTextArea, new Color(50, 50, 110), new Color(255, 218, 124));
		makeBottom.makeTree("토픽 전체 목록", this.listBox, this.allTopicList, new Color(50, 50, 110), new Color(255, 218, 124));
		
		writePane.add(this.notePanel);
		writePane.add(this.memoBox);
		
		totalPane.add(writePane);
		totalPane.add(this.listBox);
	}
	
	private void setNote(Container contentPane, JSplitPane writePane, JSplitPane totalPane) {
		writePane.setResizeWeight(0.7d);
		totalPane.setResizeWeight(0.7d);
		contentPane.add(totalPane, BorderLayout.SOUTH);
	}

	public void getText(Note noteContent, Memo memoContent){
		whatTextArea.setText(noteContent.getWhat());
		whyTextArea.setText(noteContent.getWhy());
		howTextArea.setText(noteContent.getHow());
		memoTextArea.setText(memoContent.getContent());
	}
	
	public void setText(Note noteContent, Memo memoContent){
		noteContent.setWhat(whatTextArea.getText());
		noteContent.setWhy(whyTextArea.getText());
		noteContent.setHow(howTextArea.getText());
		memoContent.setContent(memoTextArea.getText());
	}

	public JTree getAllTopicList() {
		return allTopicList;
	}

	public void setTreeModel(TreeModel model) {
		allTopicList.setModel(model);
	}
}
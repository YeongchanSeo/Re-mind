package textpackage;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTree;

import main.ModeModule;
import main.TextModeController;
import mindmap.view.center.RemindMindMapView;
import textpackage.listener.TestModeListener;

public class RemindTextSidePanel extends JSplitPane{

	private TextModeController controller;
	private JPanel topicListPanel;
	private JTree topicList;
	private JButton searchButton;
	private JButton searchAllButton;
	private JButton changeButton;
	private JButton changeAllButton;
	private JButton mindMapModeChangeButton;
	private JButton textModeTestButton;
	private TextTestResultPanel resultPanel;
	private TextTestNotePanel   notePanel;
	private TextTestButtonPanel examPanel;
	private JSplitPane userPane;
	
	public RemindTextSidePanel(ModeModule mode, TestPane pane, TextModeController controller, RemindMindMapView mindMapView){
		
		super(JSplitPane.VERTICAL_SPLIT);
		super.setResizeWeight(0.8d);
		
		this.controller = controller;
		this.topicList = new JTree(mode.getController().getTopicDAO(mindMapView.getPanelNum(mindMapView.getTapPane().getSelectedIndex())));
		this.mindMapModeChangeButton = new JButton("마인드맵 모드로");
		this.mindMapModeChangeButton.addActionListener(mode.getChangeAction());
		this.textModeTestButton = new JButton("시험 보기");
		this.textModeTestButton.addActionListener(new TestModeListener(pane, this));
		this.resultPanel = new TextTestResultPanel("시험 결과", pane.getTextTable().size(), pane.getTextTable().size());
		this.notePanel = new TextTestNotePanel("메모장");
		this.examPanel = new TextTestButtonPanel(pane, this);
		this.userPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		
		init();
	}
	
	private void init(){
		
		TextTopicListPanel topicListPanel = new TextTopicListPanel(topicList, "토픽 전체 목록");
		add(topicListPanel);
		//토픽 전체 목록 패널
		userPane.setResizeWeight(0.5d);
		changUserPanel(TestModeListener.NOTE);
		add(userPane);
		//유저패널(결과창, 버튼들)
	}
	
	public void setTextModeTestButtonUsable(boolean usable){
		textModeTestButton.setEnabled(usable);
	}
	
	public void setTextModeTestButtonTitle(String title){
		textModeTestButton.setText(title);
	}
	
	public void changUserPanel(int mode){
		
		userPane.removeAll();
		
		if(mode == TestModeListener.NOTE){
			userPane.add(notePanel);
			setTextModeTestButtonUsable(true);
			setTextModeTestButtonTitle("시험 보기");
		}
		else
		{
			userPane.add(examPanel);
			setTextModeTestButtonUsable(false);
			setTextModeTestButtonTitle("노트 보기");
		}
		
		JPanel modeButtonPanel = new JPanel(new GridLayout(1, 2));
		modeButtonPanel.add(mindMapModeChangeButton);
		modeButtonPanel.add(textModeTestButton);
		
		userPane.add(modeButtonPanel);
	}
	
	public void changeUserPanel(int correct){
		userPane.removeAll();
		
		JPanel modeButtonPanel = new JPanel(new GridLayout(1, 2));
		modeButtonPanel.add(mindMapModeChangeButton);
		modeButtonPanel.add(textModeTestButton);
		resultPanel.settingCorrectCount(correct);
		userPane.add(resultPanel);
		userPane.add(modeButtonPanel);
	}
	
	public JButton getTextModeTestButton(){
		return textModeTestButton;
	}
	
	public TextModeController getController(){
		return controller;
	}

	public JSplitPane getUserPane() {
		return userPane;
	}

	public void setUserPane(JSplitPane userPane) {
		this.userPane = userPane;
	}
	
}

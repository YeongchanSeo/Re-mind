package mindmap.view.toolbar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

import main.MindMapController;
import mindmap.view.RemindMainFrame;
import mindmap.view.center.RemindMindMapView;
import mindmap.view.center.ZoomPanel;
import diagram.Line;
import diagram.Topic;

public class RemindToolBar {
	
	/**-----------------------------------------------------------------------------------------
	------------------------------------------------------------------------------Member Field*/
	
	private JToolBar 	toolBar;
	private JButton 	newFileBtn;
	private JButton 	printerBtn;
	private JButton 	exportTextBtn;
	private JButton 	undoBtn;
	private JButton 	redoBtn;
	private JButton 	copyBtn;
	private JButton 	cutBtn;
	private JButton 	pasteBtn;
	private JButton 	insertTopicBtn;
	private JButton 	deleteTopicBtn;
	private JButton 	setTopicBtn;
	private JButton 	findAndChangeBtn;
	private JButton 	zoomInBtn;
	private JButton 	zoomOutBtn;
	private JButton		zoomDefaultBtn;
	private JButton		helpBtn;
		
	private RemindMindMapView mindMapView;
	private MindMapController controller;
	
	/**-----------------------------------------------------------------------------------------
	-------------------------------------------------------------------------------Constructor*/
	
	public RemindToolBar(Container contentPane, RemindMindMapView mindMapView) {
		this.mindMapView = mindMapView;
		this.controller  = mindMapView.getController();
		
		this.toolBar 			= new JToolBar();
		this.newFileBtn 		= new JButton(new ImageIcon("src\\mindmap\\view\\toolbaricon\\NewFile.png"));
		this.printerBtn			= new JButton(new ImageIcon("src\\mindmap\\view\\toolbaricon\\Printer.png"));
		this.exportTextBtn 		= new JButton(new ImageIcon("src\\mindmap\\view\\toolbaricon\\ExportTextFile.png"));
		this.undoBtn 			= new JButton(new ImageIcon("src\\mindmap\\view\\toolbaricon\\Undo.png"));
		this.redoBtn 			= new JButton(new ImageIcon("src\\mindmap\\view\\toolbaricon\\Redo.png"));
		this.copyBtn 			= new JButton(new ImageIcon("src\\mindmap\\view\\toolbaricon\\Copy.png"));
		this.cutBtn 			= new JButton(new ImageIcon("src\\mindmap\\view\\toolbaricon\\Cut.png"));
		this.pasteBtn 			= new JButton(new ImageIcon("src\\mindmap\\view\\toolbaricon\\Paste.png"));
		this.findAndChangeBtn 	= new JButton(new ImageIcon("src\\mindmap\\view\\toolbaricon\\FindAndChange.png"));
		this.insertTopicBtn 	= new JButton(new ImageIcon("src\\mindmap\\view\\toolbaricon\\InsertTopic.png"));
		this.deleteTopicBtn		= new JButton(new ImageIcon("src\\mindmap\\view\\toolbaricon\\DeleteTopic.png"));
		this.setTopicBtn		= new JButton(new ImageIcon("src\\mindmap\\view\\toolbaricon\\SetTopic.png"));
		this.zoomInBtn			= new JButton(new ImageIcon("src\\mindmap\\view\\toolbaricon\\ZoomIn.png"));
		this.zoomOutBtn			= new JButton(new ImageIcon("src\\mindmap\\view\\toolbaricon\\ZoomOut.png"));
		this.zoomDefaultBtn		= new JButton(new ImageIcon("src\\mindmap\\view\\toolbaricon\\ZoomDefault.png"));
		this.helpBtn			= new JButton(new ImageIcon("src\\mindmap\\view\\toolbaricon\\Help.png"));
		contentPane.add(this.toolBar, BorderLayout.NORTH);

		this.addToolBar();
		this.addToolTip();
		this.setToolBar();
		this.addListener();
	}
	
	/**-----------------------------------------------------------------------------------------
	-------------------------------------------------------------------------------���ٿ� ��ư�� ���� */
	
	private void addToolBar() {
		this.toolBar.add(this.newFileBtn);
		this.toolBar.add(this.printerBtn); 
		this.toolBar.add(this.exportTextBtn);
		this.toolBar.addSeparator();
		this.toolBar.add(this.undoBtn);
		this.toolBar.add(this.redoBtn);
		this.toolBar.add(this.copyBtn);
		this.toolBar.add(this.cutBtn);
		this.toolBar.add(this.pasteBtn);
		this.toolBar.addSeparator();
		this.toolBar.add(this.insertTopicBtn);
		this.toolBar.add(this.deleteTopicBtn);
		this.toolBar.add(this.setTopicBtn);
		this.toolBar.add(this.findAndChangeBtn);
		this.toolBar.addSeparator();
		this.toolBar.add(this.zoomInBtn);
		this.toolBar.add(this.zoomOutBtn);
		this.toolBar.add(this.zoomDefaultBtn);
		this.toolBar.addSeparator();
		this.toolBar.add(this.helpBtn);
	}
	
	/**-----------------------------------------------------------------------------------------
	-----------------------------------------------------------------------------���ٹ�ư�� ������ ���� */
	
	private void addToolTip() {
		this.newFileBtn.setToolTipText		("�� ���� �����");
		this.printerBtn.setToolTipText		("�μ�");
		this.exportTextBtn.setToolTipText	("���ε���� �ؽ�Ʈ�� ��ȯ");
		this.undoBtn.setToolTipText			("���");
		this.redoBtn.setToolTipText			("����");
		this.copyBtn.setToolTipText			("����");
		this.cutBtn.setToolTipText			("�߶󳻱�");
		this.pasteBtn.setToolTipText		("�ٿ��ֱ�");
		this.findAndChangeBtn.setToolTipText("ã��/�ٲٱ�");
		this.insertTopicBtn.setToolTipText	("���� ����");
		this.deleteTopicBtn.setToolTipText	("���� ����");
		this.setTopicBtn.setToolTipText		("���� ����");
		this.zoomInBtn.setToolTipText		("Ȯ��");
		this.zoomOutBtn.setToolTipText		("���");
		this.zoomDefaultBtn.setToolTipText	("�⺻��");
		this.helpBtn.setToolTipText			("����");
	}
	
	/**-----------------------------------------------------------------------------------------
	-------------------------------------------------------------------------------------���� ����*/
	
	private void setToolBar() {
		this.newFileBtn.setBackground(Color.WHITE);
		this.printerBtn.setBackground(Color.WHITE);
		this.exportTextBtn.setBackground(Color.WHITE);
		this.undoBtn.setBackground(Color.WHITE);
		this.redoBtn.setBackground(Color.WHITE);
		this.copyBtn.setBackground(Color.WHITE);
		this.cutBtn.setBackground(Color.WHITE);
		this.pasteBtn.setBackground(Color.WHITE);
		this.findAndChangeBtn.setBackground(Color.WHITE);
		this.insertTopicBtn.setBackground(Color.WHITE);
		this.deleteTopicBtn.setBackground(Color.WHITE);
		this.setTopicBtn.setBackground(Color.WHITE);
		this.zoomInBtn.setBackground(Color.WHITE);
		this.zoomOutBtn.setBackground(Color.WHITE);
		this.zoomDefaultBtn.setBackground(Color.WHITE);
		this.helpBtn.setBackground(Color.WHITE);
		this.toolBar.setBackground(Color.WHITE);
		this.toolBar.setFloatable(false);
		this.toolBar.setFocusable(true);
		this.toolBar.setEnabled(false);
	}
	
	private void addListener(){
		this.newFileBtn.addActionListener(new NewFileListener());
		this.insertTopicBtn.addActionListener(new InsertionTopicListener());
		this.deleteTopicBtn.addActionListener(new DeletionTopicListener());
		this.zoomInBtn.addActionListener(new ZoomInListener());
		this.zoomOutBtn.addActionListener(new ZoomOutListener());
		this.zoomDefaultBtn.addActionListener(new ZoomOriginalListener());
	}

	/**-----------------------------------------------------------------------------------------
	--------------------------------------------------
	----------------------------------Getter*/
	
	public JToolBar getToolBar() 			{ return this.toolBar; }
	public JButton getNewFileBtn() 			{ return this.newFileBtn; }
	public JButton getPrinterBtn() 			{ return this.printerBtn; }
	public JButton getExportTextBtn() 		{ return this.exportTextBtn; }
	public JButton getUndoBtn() 			{ return this.undoBtn; }
	public JButton getRedoBtn() 			{ return this.redoBtn; }
	public JButton getCopyBtn() 			{ return this.copyBtn; }
	public JButton getCutBtn() 				{ return this.cutBtn; }
	public JButton getPasteBtn()			{ return this.pasteBtn; }
	public JButton getFindAndChangeBtn()	{ return this.findAndChangeBtn; }
	public JButton getInsertTopicBtn() 		{ return this.insertTopicBtn; }
	public JButton getDeleteTopicBtn() 		{ return this.deleteTopicBtn; }
	public JButton getSetTopicBtn() 		{ return this.setTopicBtn; }
	public JButton getZoomInBtn() 			{ return this.zoomInBtn; }
	public JButton getZoomOutBtn() 			{ return this.zoomOutBtn; }
	public JButton getZoomDefaultBtn() 		{ return this.zoomDefaultBtn; }
	public JButton getHelpBtn() 			{ return this.helpBtn; }
	
	private class NewFileListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			mindMapView.newFile();			
		}
	}
	
	private class ZoomInListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			ZoomPanel zoomPanel = ((ZoomPanel)(mindMapView.getScrollPane().
					get(mindMapView.getTapPane().getSelectedIndex()).
					getViewport().getComponent(0)));
			float zoom = zoomPanel.getZoom();
			if(zoom > 1.6f)
				return;
			mindMapView.getScrollPane().get(mindMapView.getTapPane().getSelectedIndex()).getViewport().setViewPosition(
			new Point((int)(zoomPanel.getWidth()/2*zoom - (RemindMainFrame.VIEWSIZE_WIDTH-300)/2),
				(int)(zoomPanel.getHeight()/2*zoom) - RemindMainFrame.VIEWSIZE_HEIGHT/2 + 350));
			zoomPanel.setZoom(zoom += 0.1f);
		}
	}
	
	private class ZoomOutListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			ZoomPanel zoomPanel = ((ZoomPanel)(mindMapView.getScrollPane().
					get(mindMapView.getTapPane().getSelectedIndex()).
					getViewport().getComponent(0)));
			float zoom = zoomPanel.getZoom();
			if(zoom < 0.5f)
				return;
			mindMapView.getScrollPane().get(mindMapView.getTapPane().getSelectedIndex()).getViewport().setViewPosition(
			new Point((int)(zoomPanel.getWidth()/2*zoom) - RemindMainFrame.VIEWSIZE_WIDTH/2 -150,
				(int)(zoomPanel.getHeight()/2*zoom) - RemindMainFrame.VIEWSIZE_HEIGHT/2+50));
			zoomPanel.setZoom(zoom -= 0.1f);
		}
	}
	
	private class ZoomOriginalListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			ZoomPanel zoomPanel = ((ZoomPanel)(mindMapView.getScrollPane().
					get(mindMapView.getTapPane().getSelectedIndex()).
					getViewport().getComponent(0)));
			mindMapView.getScrollPane().get(mindMapView.getTapPane().getSelectedIndex()).getViewport().setViewPosition(
					new Point((int)(zoomPanel.getWidth()/2 - (RemindMainFrame.VIEWSIZE_WIDTH)/2),
						(int)(zoomPanel.getHeight()/2) - RemindMainFrame.VIEWSIZE_HEIGHT/2 + 200));
			zoomPanel.setZoom(1.0f);
		}
	}
	
	private class InsertionTopicListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			ZoomPanel zoomPanel = ((ZoomPanel)(mindMapView.getScrollPane().
					get(mindMapView.getTapPane().getSelectedIndex()).
					getViewport().getComponent(0)));
			Topic selectedTopic = zoomPanel.getSelectedTopic();
			if(selectedTopic==null)
				return;
			
			int paneNum = mindMapView.getPanelNum(mindMapView.getTapPane().getSelectedIndex());
			
			Topic newNode = new Topic("���� ����", 
					new Point(selectedTopic.getPoint().x+100, selectedTopic.getPoint().y));
			controller.add(paneNum, newNode, selectedTopic);
			
			mindMapView.getNoteView().setText(selectedTopic.getNote(), selectedTopic.getMemo());
			selectedTopic.setSelected(false);
			
			Line newJoin 		= new Line(selectedTopic, newNode);			
			controller.insert(paneNum, newJoin);
				
			zoomPanel.setSelectedTopic(newNode);
			mindMapView.getNoteView().getText(selectedTopic.getNote(), selectedTopic.getMemo());
			newNode.setSelected(true);				
			zoomPanel.repaint();
		}
	}
	
	private class DeletionTopicListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			ZoomPanel zoomPanel = ((ZoomPanel)(mindMapView.getScrollPane().
					get(mindMapView.getTapPane().getSelectedIndex()).
					getViewport().getComponent(0)));
			Topic selectedTopic = zoomPanel.getSelectedTopic();
			
			int paneNum = mindMapView.getPanelNum(mindMapView.getTapPane().getSelectedIndex());
			
			if(selectedTopic == null){
				JOptionPane.showMessageDialog
				(zoomPanel.getFrame(), "������ �����Ͽ� �ֽʽÿ�.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else if(selectedTopic == controller.getTopicList(paneNum).get(0)){
				JOptionPane.showMessageDialog
				(zoomPanel.getFrame(), "�߽� ������ ������ �� �����ϴ�.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Topic temp = controller.getParent(paneNum, selectedTopic);
			controller.remove(paneNum, selectedTopic);
			selectedTopic = temp;
			selectedTopic.setSelected(true);
			zoomPanel.setSelectedTopic(selectedTopic);
			
			zoomPanel.repaint();
		}
	}
}
package mindmap.view.menu.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import mindmap.view.RemindMainFrame;
import mindmap.view.center.RemindMindMapView;
import mindmap.view.center.ZoomPanel;
import mindmap.view.menu.dialog.helper.RemindColorPalette;

public class RemindLineOptionDialog extends JDialog implements ActionListener {

   /**-----------------------------------------------------------------------------------------
   ------------------------------------------------------------------------------Static Field*/
   
   private static final long serialVersionUID = -7694398288087904012L;

   /**-----------------------------------------------------------------------------------------
   ------------------------------------------------------------------------------Member Field*/
   
   private JLabel         topLabel;
   private JLabel         botLabel;
   private JRadioButton   topRadioBtn;
   private JRadioButton   botRadioBtn;
   private JTextField      setColor;
   private JButton       colorChooserBtn;
   private JRadioButton    fullLineRadioBtn;
   private JRadioButton    dottedLineRadioBtn;
   private JButton       okBtn;
   private JButton       cancelBtn;
   
	private RemindMindMapView mindMapView;
	private ZoomPanel       zoomPanel;
	private int             tempLineType;
	private Color           tempLineColor;
	private int             tempLineStyle;
   
   /**-----------------------------------------------------------------------------------------
   -------------------------------------------------------------------------------Constructor*/
 	
   public RemindLineOptionDialog(RemindMindMapView mindMapView, int paneNum) {      
      this.setTitle("선 설정");
      this.setLayout(new BorderLayout());
      
      this.mindMapView = mindMapView;
		this.zoomPanel   = ((ZoomPanel)(mindMapView.getScrollPane().
				get(mindMapView.getTapPane().getSelectedIndex()).
				getViewport().getComponent(0)));
	  this.tempLineType = zoomPanel.getLineType();
      this.tempLineColor = zoomPanel.getLineColor();
      this.tempLineStyle = zoomPanel.getLineStyle();
      
      JPanel westPanel = new JPanel(new GridLayout(1, 1));
      JPanel shapePanel = new JPanel(new GridLayout(2, 1));
      JPanel topPanel = new JPanel(new BorderLayout());
      JPanel botPanel = new JPanel(new BorderLayout());
      
      this.topLabel = new JLabel(new ImageIcon("src\\mindmap\\view\\menu\\dialog\\dialogicon\\StraightLine.png"));
	  this.botLabel = new JLabel(new ImageIcon("src\\mindmap\\view\\menu\\dialog\\dialogicon\\CurveLine.png"));
            
      ButtonGroup shapeBtnGroup = new ButtonGroup();
      this.topRadioBtn = new JRadioButton("직선");
      this.botRadioBtn = new JRadioButton("곡선");
      
		if(tempLineType == 1)
			this.topRadioBtn.setSelected(true);
		else
			this.botRadioBtn.setSelected(true);
      
      shapePanel.setBorder(BorderFactory.createTitledBorder("선 모양"));
      this.add(westPanel, BorderLayout.CENTER);
      westPanel.add(shapePanel);
      shapePanel.add(topPanel);
      shapePanel.add(botPanel);
      topPanel.add(this.topLabel, BorderLayout.CENTER);
      botPanel.add(this.botLabel, BorderLayout.CENTER);
      shapeBtnGroup.add(this.topRadioBtn);
      shapeBtnGroup.add(this.botRadioBtn);
      topPanel.add(this.topRadioBtn, BorderLayout.SOUTH);
      botPanel.add(this.botRadioBtn, BorderLayout.SOUTH);
      
      this.topLabel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
      this.botLabel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
      this.topLabel.setOpaque(true);
      this.botLabel.setOpaque(true);
      this.topLabel.setBackground(new Color(240, 250, 250));
      this.botLabel.setBackground(new Color(250, 250, 240));
      
      JPanel eastPanel       = new JPanel(new BorderLayout());
      JPanel colorPanel       = new JPanel();
      JPanel stylePanel       = new JPanel(new BorderLayout());
      JPanel linePanel       = new JPanel(new GridLayout(2, 1));
      JPanel fullLinePanel    = new JPanel(new GridLayout(2, 1));
      JPanel dottedLinePanel    = new JPanel(new GridLayout(2, 1));
      
      this.setColor          = new JTextField(2);
      this.setColor.addPropertyChangeListener(new PropertyChangeListener(){
    	  public void propertyChange(PropertyChangeEvent e){
    		  zoomPanel.setLineColor(setColor.getBackground());
				zoomPanel.repaint();
    	  }
      });
      
      this.colorChooserBtn    = new JButton("색상 선택");
      
      JLabel fullLineLabel 	= new JLabel(new ImageIcon("src\\mindmap\\view\\menu\\dialog\\dialogicon\\FullLine.png"));
		JLabel dottedLineLabel 	= new JLabel(new ImageIcon("src\\mindmap\\view\\menu\\dialog\\dialogicon\\DottedLine.png"));
      
      ButtonGroup styleBtnGroup    = new ButtonGroup();
      this.fullLineRadioBtn       = new JRadioButton("실선");
      this.dottedLineRadioBtn    = new JRadioButton("점선");
      
      this.add(eastPanel, BorderLayout.EAST);
      colorPanel.setBorder(BorderFactory.createTitledBorder("선 색상"));
      eastPanel.add(colorPanel, BorderLayout.NORTH);
      colorPanel.add(this.setColor);
      colorPanel.add(this.colorChooserBtn);

      stylePanel.setBorder(BorderFactory.createTitledBorder("선 스타일"));
      eastPanel.add(stylePanel, BorderLayout.CENTER);
      stylePanel.add(linePanel);
      styleBtnGroup.add(this.fullLineRadioBtn);
      styleBtnGroup.add(this.dottedLineRadioBtn);
      fullLinePanel.add(fullLineLabel);
      fullLinePanel.add(this.fullLineRadioBtn);
      linePanel.add(fullLinePanel);
      dottedLinePanel.add(dottedLineLabel);
      dottedLinePanel.add(this.dottedLineRadioBtn);
      linePanel.add(dottedLinePanel);

      this.setColor.setBackground(zoomPanel.getLineColor());
      this.setColor.setEditable(false);
      this.colorChooserBtn.setFocusable(false);
      this.fullLineRadioBtn.setFocusable(false);
      setFocusable(false);
      
      if(tempLineStyle == 1)
    	  this.fullLineRadioBtn.setSelected(true);
	  else
		  this.dottedLineRadioBtn.setSelected(true);
      
      fullLineLabel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
      dottedLineLabel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
      fullLineLabel.setPreferredSize(new Dimension(80, 30));
      dottedLineLabel.setPreferredSize(new Dimension(80, 30));
      fullLineLabel.setOpaque(true);
      dottedLineLabel.setOpaque(true);
      fullLineLabel.setBackground(new Color(250, 240, 250));
      dottedLineLabel.setBackground(new Color(240, 250, 240));

      JPanel buttonPanel    = new JPanel();
      this.okBtn          = new JButton("설정");
      this.cancelBtn       = new JButton("취소");
      
      this.add(buttonPanel, BorderLayout.SOUTH);
      buttonPanel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
      buttonPanel.add(this.okBtn);
      buttonPanel.add(this.cancelBtn);
      
      this.okBtn.setFocusable(false);
      this.cancelBtn.setFocusable(false);
      
      this.colorChooserBtn.addActionListener(this);    
      addListener();
      setDialog();
   }
   
   /**-----------------------------------------------------------------------------------------
   ---------------------------------------------------------------------------------다이얼로그 설정*/
   
   public void setDialog() {
      this.setSize(new Dimension(350, 300));
      this.setLocation(RemindMainFrame.START_XCOORD + RemindMainFrame.VIEWSIZE_WIDTH / 2 - 175, 
                   RemindMainFrame.START_YCOORD + RemindMainFrame.VIEWSIZE_HEIGHT / 2 - 150);
      this.setModal(true);
      this.setResizable(false);
      this.setVisible(true);
   }

   /**-----------------------------------------------------------------------------------------
   -----------------------------------------------------------actionPerformed Method Override*/
   
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == this.colorChooserBtn)    { new RemindColorPalette(this.setColor, true, zoomPanel); }
   }
   
   /**-----------------------------------------------------------------------------------------
   ------------------------------------------------------------------------------------Getter*/

   public JLabel getTopLabel()             { return this.topLabel; }
   public JLabel getBotLabel()             { return this.botLabel; }
   public JRadioButton getTopRadioBtn()       { return this.topRadioBtn; }
   public JRadioButton getBotRadioBtn()       { return this.botRadioBtn; }
   public JTextField getSetColor()          { return this.setColor; }
   public JButton getColorChooserBtn()       { return this.colorChooserBtn; }
   public JRadioButton getFullLineRadioBtn()    { return this.fullLineRadioBtn; }
   public JRadioButton getDottedLineRadioBtn() { return this.dottedLineRadioBtn; }
   public JButton getOkBtn()                { return this.okBtn; }
   public JButton getCancelBtn()             { return this.cancelBtn; }
   
   private void addListener(){
	   this.topRadioBtn.addActionListener(new LineShapeListener(1));
	   this.botRadioBtn.addActionListener(new LineShapeListener(2));
	   this.fullLineRadioBtn.addActionListener(new LineStyleListener(1));
	   this.dottedLineRadioBtn.addActionListener(new LineStyleListener(2));
	   this.okBtn.addActionListener(new OkButtonListener());
	   this.cancelBtn.addActionListener(new CancelButtonListener());
   }
   
	private class LineShapeListener implements ActionListener{
	private int lineType;
	public LineShapeListener(int lineType){
		this.lineType = lineType;
	}
	
	public void actionPerformed(ActionEvent e){
		ZoomPanel zoomPanel = ((ZoomPanel)(mindMapView.getScrollPane().
				get(mindMapView.getTapPane().getSelectedIndex()).
				getViewport().getComponent(0)));
			
		zoomPanel.setLineType(lineType);
	    zoomPanel.repaint();
		}
	}
	
	private class LineStyleListener implements ActionListener{
		private int lineStyle;
		public LineStyleListener(int lineStyle){
			this.lineStyle = lineStyle;
		}
		
		public void actionPerformed(ActionEvent e){
			ZoomPanel zoomPanel = ((ZoomPanel)(mindMapView.getScrollPane().
					get(mindMapView.getTapPane().getSelectedIndex()).
					getViewport().getComponent(0)));
				
			zoomPanel.setLineStyle(lineStyle);
		    zoomPanel.repaint();
			}
	}

private class OkButtonListener implements ActionListener{
	public void actionPerformed(ActionEvent e){
		dispose();
	}
}

private class CancelButtonListener implements ActionListener{
	public void actionPerformed(ActionEvent e){
		zoomPanel.setLineType(tempLineType);
		zoomPanel.setLineColor(tempLineColor);
		zoomPanel.setLineStyle(tempLineStyle);
		zoomPanel.repaint();
		dispose();
		}
	}
}

//package mindmap.view.menu.dialog;
//
//import java.awt.BasicStroke;
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//
//import javax.swing.BorderFactory;
//import javax.swing.ButtonGroup;
//import javax.swing.JButton;
//import javax.swing.JDialog;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JRadioButton;
//import javax.swing.JTextField;
//
//import mindmap.view.RemindMainFrame;
//import mindmap.view.center.RemindMindMapView;
//import mindmap.view.center.ZoomPanel;
//import mindmap.view.menu.dialog.helper.RemindColorPalette;
//
//public class RemindLineOptionDialog extends JDialog implements ActionListener {
//
//	/**-----------------------------------------------------------------------------------------
//	------------------------------------------------------------------------------Static Field*/
//	
//	private static final long serialVersionUID = -7694398288087904012L;
//
//	/**-----------------------------------------------------------------------------------------
//	------------------------------------------------------------------------------Member Field*/
//	
//	private JLabel			shapeChange;
//	private JButton 		okBtn;
//	private JButton 		cancelBtn;
//	private JButton 		colorChooserBtn;
//	private JRadioButton 	straightLine;
//	private JRadioButton 	curveLine;
//	private JRadioButton 	fullLine;
//	private JRadioButton 	dottedLine;
//	private JTextField		setColor;
//	
//	private RemindMindMapView mindMapView;
//	private ZoomPanel       zoomPanel;
//	private int             tempLineType;
//	
//	/**-----------------------------------------------------------------------------------------
//	-------------------------------------------------------------------------------Constructor*/
//	
//	public RemindLineOptionDialog(RemindMindMapView mindMapView, int paneNum) {		
//		this.setTitle("선 설정");
//		this.setLayout(null);
//		
//		this.mindMapView = mindMapView;
//		this.zoomPanel   = ((ZoomPanel)(mindMapView.getScrollPane().
//				get(mindMapView.getTapPane().getSelectedIndex()).
//				getViewport().getComponent(0)));
//		this.tempLineType = zoomPanel.getLineType();
//		
//		JPanel shapePanel  = new JPanel(new BorderLayout());
//		shapePanel.setBorder(BorderFactory.createTitledBorder("선 모양"));
//		shapePanel.setSize(new Dimension(150, 185));
//		shapePanel.setLocation(5, 0);
//		this.add(shapePanel);
//		this.shapeChange = new JLabel();
//		this.shapeChange.setOpaque(true);
//		this.shapeChange.setBackground(new Color(240, 240, 250));
//		shapePanel.add(this.shapeChange, BorderLayout.CENTER);
//		
//		JPanel shapeBtnPanel	= new JPanel();
//		shapeBtnPanel.setBackground(new Color(240, 240, 250));
//		ButtonGroup shapeGroup	= new ButtonGroup();		
//		this.straightLine = new JRadioButton("직선");
//		this.straightLine.setBackground(new Color(240, 240, 250));
//		this.straightLine.setFocusable(false);
//		this.straightLine.addActionListener(new LineShapeListener(1));
//		shapeGroup.add(this.straightLine);
//		shapeBtnPanel.add(this.straightLine);
//		this.curveLine = new JRadioButton("곡선");
//		this.curveLine.addActionListener(new LineShapeListener(2));
//		this.curveLine.setBackground(new Color(240, 240, 250));
//		this.curveLine.setFocusable(false);
//		shapeGroup.add(this.curveLine);
//		shapeBtnPanel.add(this.curveLine);		
//		shapePanel.add(shapeBtnPanel, BorderLayout.SOUTH);
//		if(tempLineType == 1)
//			this.straightLine.setSelected(true);
//		else
//			this.curveLine.setSelected(true);
//		
//		JPanel colorPanel = new JPanel();
//		colorPanel.setBorder(BorderFactory.createTitledBorder("선 색"));
//		colorPanel.setSize(new Dimension(135, 90));
//		colorPanel.setLocation(155, 0);
//		this.add(colorPanel);
//		this.setColor = new JTextField(2);
//		this.setColor.setBackground(Color.BLACK);
//		this.setColor.setEnabled(false);
//		colorPanel.add(this.setColor);
//		
//		this.colorChooserBtn = new JButton("색상 선택");
//		this.colorChooserBtn.setFocusable(false);
//		colorPanel.add(this.colorChooserBtn);		
//		
//		JPanel stylePanel = new JPanel();
//		stylePanel.setBorder(BorderFactory.createTitledBorder("선 스타일"));
//		stylePanel.setBackground(new Color(255, 230, 230));
//		stylePanel.setSize(new Dimension(135, 95));
//		stylePanel.setLocation(155, 90);
//		this.add(stylePanel);
//		
//		ButtonGroup	styleGroup	= new ButtonGroup();		
//		this.fullLine = new JRadioButton("실선");
//		this.fullLine.setBackground(new Color(255, 230, 230));
//		this.fullLine.setFocusable(false);
//		this.fullLine.setSelected(true);
//		
//		styleGroup.add(this.fullLine);
//		stylePanel.add(this.fullLine);
//
//		this.dottedLine = new JRadioButton("점선");
//		this.dottedLine.setBackground(new Color(255, 230, 230));
//		this.dottedLine.setFocusable(false);
//		styleGroup.add(this.dottedLine);
//		stylePanel.add(this.dottedLine);
//
//		JPanel buttonPanel = new JPanel();
//		buttonPanel.setSize(new Dimension(200, 80));
//		buttonPanel.setLocation(45, 185);
//
//		this.okBtn = new JButton("설정");
//		this.okBtn.setFocusable(false);
//		okBtn.addActionListener(new OkButtonListener());
//		buttonPanel.add(this.okBtn);
//
//		this.cancelBtn = new JButton("취소");
//		this.cancelBtn.setFocusable(false);
//		this.cancelBtn.addActionListener(new CancelButtonListener());
//		buttonPanel.add(this.cancelBtn);
//		this.add(buttonPanel);
//		
//		this.colorChooserBtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) { new RemindColorPalette(setColor, true); }
//		});
//		
//		addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) { dispose(); }
//		});
//	
//		setDialog();
//	}
//	
//	/**-----------------------------------------------------------------------------------------
//	---------------------------------------------------------------------------------다이얼로그 설정*/
//	
//	public void setDialog() {
//		this.setSize(new Dimension(300, 265));
//		this.setLocation(RemindMainFrame.START_XCOORD + RemindMainFrame.VIEWSIZE_WIDTH / 2 - 150, 
//						 RemindMainFrame.START_YCOORD + RemindMainFrame.VIEWSIZE_HEIGHT / 2 - 100);
//		this.setModal(true);
//		this.setResizable(false);
//		this.setVisible(true);
//	}
//
//	
//	/**-----------------------------------------------------------------------------------------
//	-----------------------------------------------------------actionPerformed Method Override*/
//	
//	public void actionPerformed(ActionEvent e) {}
//	
//	/**-----------------------------------------------------------------------------------------
//	------------------------------------------------------------------------------------Getter*/
//	
//	public JLabel getShapeChange() 			{ return this.shapeChange; }
//	public JButton getOkBtn() 				{ return this.okBtn; }
//	public JButton getCancelBtn() 			{ return this.cancelBtn; }
//	public JButton getColorChooserBtn() 	{ return this.colorChooserBtn; }
//	public JRadioButton getStraightLine() 	{ return this.straightLine; }
//	public JRadioButton getCurveLine() 		{ return this.curveLine; }
//	public JRadioButton getFullLine() 		{ return this.fullLine; }
//	public JRadioButton getDottedLine() 	{ return this.dottedLine; }	
//	public JTextField getSetColor() 		{ return this.setColor; }
//	
//	private class LineShapeListener implements ActionListener{
//		private int lineType;
//		public LineShapeListener(int lineType){
//			this.lineType = lineType;
//		}
//		
//		public void actionPerformed(ActionEvent e){
//			ZoomPanel zoomPanel = ((ZoomPanel)(mindMapView.getScrollPane().
//					get(mindMapView.getTapPane().getSelectedIndex()).
//					getViewport().getComponent(0)));
//			
//			if(lineType == 1){
//				
//			}
//			
//			zoomPanel.setLineType(lineType);
//		    zoomPanel.repaint();
//		}
//	}
//	
//	private class OkButtonListener implements ActionListener{
//		public void actionPerformed(ActionEvent e){
//			if(straightLine.isSelected())
//				zoomPanel.setLineType(1);
//			else
//				zoomPanel.setLineType(2);
//			dispose();
//		}
//	}
//	
//	private class CancelButtonListener implements ActionListener{
//		public void actionPerformed(ActionEvent e){
//			zoomPanel.setLineType(tempLineType);
//			zoomPanel.repaint();
//			dispose();
//		}
//	}
//}
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

public class RemindTopicOptionDialog extends JDialog implements ActionListener {

   /**-----------------------------------------------------------------------------------------
   ------------------------------------------------------------------------------Static Field*/
   
   private static final long serialVersionUID = 6658091223899422204L;
   
   private static final Color SPRING_COLOR;
   private static final Color SUMMER_COLOR;
   public static final Color AUTUMN_COLOR;
   private static final Color WINTER_COLOR;
   
   static { SPRING_COLOR = new Color(140, 255, 140); SUMMER_COLOR = new Color(255, 100, 140);
          AUTUMN_COLOR = new Color(255, 240, 150); WINTER_COLOR = new Color(100, 200, 255); }

   /**-----------------------------------------------------------------------------------------
   ------------------------------------------------------------------------------Member Field*/
   
   private JLabel          topLLabel;
   private JLabel          topRLabel;
   private JLabel          botLLabel;
   private JLabel          botRLabel;
   private JRadioButton    topLRadioBtn;
   private JRadioButton    topRRadioBtn;
   private JRadioButton   botLRadioBtn;
   private JRadioButton    botRRadioBtn;
   private JTextField       setColor;
   private JButton         colorChooserBtn;
   private JRadioButton    springRadioBtn;
   private JRadioButton    summerRadioBtn;
   private JRadioButton    autumnRadioBtn;
   private JRadioButton    winterRadioBtn;
   private JButton       okBtn;
   private JButton       cancelBtn;
   private ZoomPanel     zoomPanel;
   private int           paneNum;
   
   /**-----------------------------------------------------------------------------------------
   -------------------------------------------------------------------------------Constructor*/
   
   public RemindTopicOptionDialog(RemindMindMapView mindMapView, int paneNum) {
      this.setTitle("토픽 설정");
      this.setLayout(new BorderLayout());

      this.zoomPanel = ((ZoomPanel)(mindMapView.getScrollPane().
				get(mindMapView.getTapPane().getSelectedIndex()).
				getViewport().getComponent(0)));
      this.paneNum = paneNum;
      
      JPanel westPanel    = new JPanel(new GridLayout(1, 1));
      JPanel shapePanel    = new JPanel(new GridLayout(2, 2));
      JPanel topLPanel    = new JPanel(new BorderLayout()); 
      JPanel topRPanel    = new JPanel(new BorderLayout());
      JPanel botLPanel    = new JPanel(new BorderLayout());
      JPanel botRPanel    = new JPanel(new BorderLayout());
      
      this.topLLabel = new JLabel(new ImageIcon("src\\mindmap\\view\\menu\\dialog\\dialogicon\\Rectangle.png"));
		this.topRLabel = new JLabel(new ImageIcon("src\\mindmap\\view\\menu\\dialog\\dialogicon\\RoundRectangle.png"));
		this.botLLabel = new JLabel(new ImageIcon("src\\mindmap\\view\\menu\\dialog\\dialogicon\\Ellipse.png"));
		this.botRLabel = new JLabel(new ImageIcon("src\\mindmap\\view\\menu\\dialog\\dialogicon\\Oval.png"));
      
      ButtonGroup shapeBtnGroup = new ButtonGroup();
      this.topLRadioBtn = new JRadioButton("사각형");
      this.topRRadioBtn = new JRadioButton("둥근 사각형");
      this.botLRadioBtn = new JRadioButton("타원형");
      this.botRRadioBtn = new JRadioButton("원형");
      switch(zoomPanel.getTopicType()){
      case 1:
    	  topLRadioBtn.setSelected(true);
    	  break;
      case 2:
    	  topRRadioBtn.setSelected(true);
    	  break;
      case 3:
    	  botLRadioBtn.setSelected(true);
    	  break;
      case 4:
    	  botRRadioBtn.setSelected(true);
    	  break;
      }
      
      shapePanel.setBorder(BorderFactory.createTitledBorder("토픽 모양"));
      this.add(westPanel, BorderLayout.CENTER);
      westPanel.add(shapePanel);
      shapePanel.add(topLPanel);
      shapePanel.add(topRPanel);
      shapePanel.add(botLPanel);
      shapePanel.add(botRPanel);
      topLPanel.add(this.topLLabel, BorderLayout.CENTER);
      topRPanel.add(this.topRLabel, BorderLayout.CENTER);
      botLPanel.add(this.botLLabel, BorderLayout.CENTER);
      botRPanel.add(this.botRLabel, BorderLayout.CENTER);
      shapeBtnGroup.add(this.topLRadioBtn);
      shapeBtnGroup.add(this.topRRadioBtn);
      shapeBtnGroup.add(this.botLRadioBtn);
      shapeBtnGroup.add(this.botRRadioBtn);
      topLPanel.add(this.topLRadioBtn, BorderLayout.SOUTH);
      topRPanel.add(this.topRRadioBtn, BorderLayout.SOUTH);
      botLPanel.add(this.botLRadioBtn, BorderLayout.SOUTH);
      botRPanel.add(this.botRRadioBtn, BorderLayout.SOUTH);
      
      this.topLLabel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
      this.topRLabel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
      this.botLLabel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
      this.botRLabel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
      this.topLLabel.setOpaque(true);
      this.topRLabel.setOpaque(true);
      this.botLLabel.setOpaque(true);
      this.botRLabel.setOpaque(true);      
      this.topLLabel.setBackground(new Color(240, 250, 250));
      this.topRLabel.setBackground(new Color(250, 240, 250));
      this.botLLabel.setBackground(new Color(250, 250, 240));
      this.botRLabel.setBackground(new Color(240, 250, 240));
      
      JPanel eastPanel     = new JPanel(new BorderLayout());
      JPanel colorPanel    = new JPanel();
      JPanel stylePanel    = new JPanel(new BorderLayout());
      JPanel seasonPanel    = new JPanel(new GridLayout(4, 1));
      JPanel springPanel    = new JPanel();
      JPanel summerPanel    = new JPanel();
      JPanel autumnPanel   = new JPanel();
      JPanel winterPanel    = new JPanel();
      
      this.setColor          = new JTextField(2);
      this.colorChooserBtn   = new JButton("색상 선택");
      
      this.setColor.addPropertyChangeListener(new PropertyChangeListener(){
    	  public void propertyChange(PropertyChangeEvent e){
    		  zoomPanel.setTopicColor(setColor.getBackground());
				zoomPanel.repaint();
    	  }
      });
      
      JLabel springLabel = new JLabel();
      JLabel summerLabel = new JLabel();
      JLabel autumnLabel = new JLabel();
      JLabel winterLabel = new JLabel();
      
      ButtonGroup styleBtnGroup   = new ButtonGroup();
      this.springRadioBtn         = new JRadioButton("봄   ");
      this.summerRadioBtn         = new JRadioButton("여름");
      this.autumnRadioBtn         = new JRadioButton("가을");
      this.winterRadioBtn         = new JRadioButton("겨울");
      
      this.add(eastPanel, BorderLayout.EAST);
      colorPanel.setBorder(BorderFactory.createTitledBorder("토픽 색상"));
      eastPanel.add(colorPanel, BorderLayout.NORTH);
      colorPanel.add(this.setColor);
      colorPanel.add(this.colorChooserBtn);

      stylePanel.setBorder(BorderFactory.createTitledBorder("토픽 스타일"));
      eastPanel.add(stylePanel, BorderLayout.CENTER);
      stylePanel.add(seasonPanel);
      styleBtnGroup.add(this.springRadioBtn);
      styleBtnGroup.add(this.summerRadioBtn);
      styleBtnGroup.add(this.autumnRadioBtn);
      styleBtnGroup.add(this.winterRadioBtn);
      springPanel.add(springLabel);
      springPanel.add(this.springRadioBtn);
      seasonPanel.add(springPanel);
      summerPanel.add(summerLabel);
      summerPanel.add(this.summerRadioBtn);
      seasonPanel.add(summerPanel);
      autumnPanel.add(autumnLabel);
      autumnPanel.add(this.autumnRadioBtn);
      seasonPanel.add(autumnPanel);
      winterPanel.add(winterLabel);
      winterPanel.add(this.winterRadioBtn);
      seasonPanel.add(winterPanel);
      
      this.setColor.setBackground(zoomPanel.getTopicColor());
      this.setColor.setEditable(false);
      this.colorChooserBtn.setFocusable(false);
      this.springRadioBtn.setFocusable(false);
      this.summerRadioBtn.setFocusable(false);
      this.autumnRadioBtn.setFocusable(false);
      this.winterRadioBtn.setFocusable(false);
      
      springLabel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
      summerLabel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
      autumnLabel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
      winterLabel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
      springLabel.setPreferredSize(new Dimension(80, 30));
      summerLabel.setPreferredSize(new Dimension(80, 30));
      autumnLabel.setPreferredSize(new Dimension(80, 30));
      winterLabel.setPreferredSize(new Dimension(80, 30));
      springLabel.setOpaque(true);
      summerLabel.setOpaque(true);
      autumnLabel.setOpaque(true);
      winterLabel.setOpaque(true);
      springLabel.setBackground(RemindTopicOptionDialog.SPRING_COLOR);
      summerLabel.setBackground(RemindTopicOptionDialog.SUMMER_COLOR);
      autumnLabel.setBackground(RemindTopicOptionDialog.AUTUMN_COLOR);
      winterLabel.setBackground(RemindTopicOptionDialog.WINTER_COLOR);
      Color style = zoomPanel.getTopicStyle();
      if(style == RemindTopicOptionDialog.SPRING_COLOR)
    	  springRadioBtn.setSelected(true);
      else if(style == RemindTopicOptionDialog.SUMMER_COLOR)
    	  summerRadioBtn.setSelected(true);
      else if(style == RemindTopicOptionDialog.AUTUMN_COLOR)
    	  autumnRadioBtn.setSelected(true);
      else
    	  winterRadioBtn.setSelected(true);
      
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
      this.okBtn.addActionListener(this);
      this.cancelBtn.addActionListener(this);
      
      setDialog();      
   }
   
   /**-----------------------------------------------------------------------------------------
   ---------------------------------------------------------------------------------다이얼로그 설정*/
   
   public void setDialog() {
      this.setSize(new Dimension(500, 300));
      this.setLocation(RemindMainFrame.START_XCOORD + RemindMainFrame.VIEWSIZE_WIDTH / 2 - 250, 
                   RemindMainFrame.START_YCOORD + RemindMainFrame.VIEWSIZE_HEIGHT / 2 - 150);
      this.setModal(true);
      this.setResizable(false);
      this.setVisible(true);   
   }
   
   /**-----------------------------------------------------------------------------------------
   -----------------------------------------------------------actionPerformed Method Override*/
   
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == this.colorChooserBtn)    { new RemindColorPalette(this.setColor, false, zoomPanel); }
      else if(e.getSource() == this.okBtn)       { 
    	  if(this.topLRadioBtn.isSelected())
    		  zoomPanel.setTopicType(1);
    	  else if(this.topRRadioBtn.isSelected())
    		  zoomPanel.setTopicType(2);
    	  else if(this.botLRadioBtn.isSelected())
    		  zoomPanel.setTopicType(3);
    	  else
    		  zoomPanel.setTopicType(4);
    	  zoomPanel.setTopicColor(this.setColor.getBackground());
    	  if(this.springRadioBtn.isSelected())
    		  zoomPanel.setTopicStyle(RemindTopicOptionDialog.SPRING_COLOR);
    	  else if(this.summerRadioBtn.isSelected())
    		  zoomPanel.setTopicStyle(RemindTopicOptionDialog.SUMMER_COLOR);
    	  else if(this.autumnRadioBtn.isSelected())
    		  zoomPanel.setTopicStyle(RemindTopicOptionDialog.AUTUMN_COLOR);
    	  else{
    		  zoomPanel.setTopicStyle(RemindTopicOptionDialog.WINTER_COLOR);
    	  }
          zoomPanel.repaint();
    	  this.setVisible(false); }
      else if(e.getSource() == this.cancelBtn)    { this.setVisible(false); }
   }
   
   /**-----------------------------------------------------------------------------------------
   ------------------------------------------------------------------------------------Getter*/
   
   public JLabel getTopLLabel()             { return this.topLLabel; }
   public JLabel getTopRLabel()             { return this.topRLabel; }
   public JLabel getBotLLabel()             { return this.botLLabel; }
   public JLabel getBotRLabel()             { return this.botRLabel; }   
   public JRadioButton getTopLRadioBtn()       { return topLRadioBtn; }
   public JRadioButton getTopRRadioBtn()      { return topRRadioBtn; }
   public JRadioButton getBotLRadioBtn()       { return botLRadioBtn; }
   public JRadioButton getBotRRadioBtn()       { return botRRadioBtn; }
   public JTextField getSetColor()          { return this.setColor; }
   public JButton getColorChooserBtn()       { return this.colorChooserBtn; }
   public JRadioButton getSpringRadioBtn()    { return this.springRadioBtn; }
   public JRadioButton getSummerRadioBtn()    { return this.summerRadioBtn; }
   public JRadioButton getWinterRadioBtn()    { return this.winterRadioBtn; }
   public JButton getOkBtn()                { return this.okBtn; }
   public JButton getCancelBtn()             { return this.cancelBtn; }
}
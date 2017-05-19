package mindmap.view.menu.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import main.MindMapController;
import mindmap.view.RemindMainFrame;
import mindmap.view.center.RemindMindMapView;
import mindmap.view.center.ZoomPanel;

public class RemindFontOptionDialog extends JDialog implements ActionListener {

   /**-----------------------------------------------------------------------------------------
   ------------------------------------------------------------------------------Static Field*/
   
   private static final long serialVersionUID = 6802471642051635572L;

   /**-----------------------------------------------------------------------------------------
   ------------------------------------------------------------------------------Member Field*/

   private SimpleAttributeSet    attributes;
   private JComboBox<String>    fontComboBox;
   private JTextField          sizeTextField;   
   private JLabel             previewLabel;   
   private JCheckBox          boldCheckBox;
   private JCheckBox          italicCheckBox;
   private JButton          okBtn;
   private JButton          cancelBtn;   
   private Font             newFont;
   private ZoomPanel        zoomPanel;
   private MindMapController controller;
   private RemindMindMapView mindMapView;
   
   /**-----------------------------------------------------------------------------------------
   -------------------------------------------------------------------------------Constructor*/
   
   public RemindFontOptionDialog(RemindMindMapView mindMapView, int paneNum) {
      this.setTitle("글꼴 설정");
      this.setLayout(new BorderLayout());
      
      this.zoomPanel = ((ZoomPanel)(mindMapView.getScrollPane().
				get(mindMapView.getTapPane().getSelectedIndex()).
				getViewport().getComponent(0)));
      this.controller = mindMapView.getController();
      this.mindMapView = mindMapView;
      
      JPanel centerPanel = new JPanel(new BorderLayout());
      centerPanel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
      this.add(centerPanel, BorderLayout.CENTER);

      JPanel northPanel = new JPanel();
      
      this.attributes    = new SimpleAttributeSet();
      this.fontComboBox    = new JComboBox<String>(getFontList());
      JLabel sizeLabel    = new JLabel(" 글꼴 크기 ▶ ");
      this.sizeTextField    = new JTextField("10", 4);
      
      northPanel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
      centerPanel.add(northPanel, BorderLayout.NORTH);
      northPanel.add(fontComboBox);
      northPanel.add(sizeLabel);
      northPanel.add(this.sizeTextField);      
      
      this.fontComboBox.setSelectedIndex(0);
      this.fontComboBox.setFocusable(false);
      sizeLabel.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 110)));
      sizeLabel.setPreferredSize(new Dimension(100, 21));
      sizeLabel.setForeground(new Color(50, 50, 150));

      JPanel previewPanel = new JPanel(new BorderLayout());      
      this.previewLabel    = new JLabel("글꼴 미리보기", JLabel.CENTER);
      
      centerPanel.add(previewPanel, BorderLayout.CENTER);
      previewPanel.add(this.previewLabel, BorderLayout.CENTER);

      previewPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
      this.previewLabel.setOpaque(true);
      this.previewLabel.setBackground(new Color(250, 240, 240));      
      
      JPanel eastPanel    = new JPanel(new BorderLayout());
      JPanel stylePanel    = new JPanel();
      JPanel buttonPanel    = new JPanel();
      
      this.boldCheckBox    = new JCheckBox("Bold");
      this.italicCheckBox = new JCheckBox("Italic");
      
      this.okBtn          = new JButton("설정");
      this.cancelBtn       = new JButton("취소");
      
      centerPanel.add(eastPanel, BorderLayout.EAST);
      eastPanel.add(stylePanel, BorderLayout.CENTER);
      stylePanel.setBorder(BorderFactory.createTitledBorder("글꼴 스타일"));
      stylePanel.add(boldCheckBox);
      stylePanel.add(italicCheckBox);
      
      eastPanel.add(buttonPanel, BorderLayout.SOUTH);
      stylePanel.setOpaque(true);
      buttonPanel.add(this.okBtn);
      buttonPanel.add(this.cancelBtn);
      
      this.fontComboBox.addActionListener(this);
      this.sizeTextField.addActionListener(this);
      this.boldCheckBox.addActionListener(this);
      this.italicCheckBox.addActionListener(this);      
      this.okBtn.addActionListener(this);
      this.cancelBtn.addActionListener(this);

      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) { newFont = null; dispose(); }
      });
      momentSetting();
      setDialog();
   }
   
   /**-----------------------------------------------------------------------------------------
   ---------------------------------------------------------------------------------다이얼로그 설정*/
   
   public void setDialog() {
      this.setSize(new Dimension(450, 180));
      this.setLocation(RemindMainFrame.START_XCOORD + RemindMainFrame.VIEWSIZE_WIDTH / 2 - 200, 
                   RemindMainFrame.START_YCOORD + RemindMainFrame.VIEWSIZE_HEIGHT / 2 - 75);
      this.setModal(true);
      this.setResizable(false);
      this.setVisible(true);
   }
   
   /**-----------------------------------------------------------------------------------------
   ---------------------------------------------------------------------------윈도우 폰트 목록 가져오기 */
   
   public String[] getFontList() {
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      Font[] fontList = ge.getAllFonts();
      String[] arrFontName = new String[fontList.length];
      
      for(int i = 0; i < fontList.length; i++)
         arrFontName[i] = fontList[i].getFontName();
      
      return arrFontName;
   }
   
   /**-----------------------------------------------------------------------------------------
   -----------------------------------------------------------------------------미리보기 화면 업데이트 */
   
   private void updatePreviewFont() {
      String name    = StyleConstants.getFontFamily(this.attributes);
      boolean bold    = StyleConstants.isBold(this.attributes);
      boolean ital    = StyleConstants.isItalic(this.attributes);
      int size       = StyleConstants.getFontSize(this.attributes);      
      Font font       = new Font(name, (bold ? Font.BOLD : 0) + (ital ? Font.ITALIC : 0), size);
      
      this.previewLabel.setFont(font);
   }
   
   /**-----------------------------------------------------------------------------------------
   ----------------------------------------------------------------------------현재 폰트 설정 업데이트 */
   
   public void momentSetting() {
      if(!StyleConstants.getFontFamily(this.attributes).equals(this.fontComboBox.getSelectedItem())) 
         StyleConstants.setFontFamily(this.attributes, (String)this.fontComboBox.getSelectedItem());
      if(!this.sizeTextField.getText().equals("")) {
         if(StyleConstants.getFontSize(this.attributes) != Integer.parseInt(this.sizeTextField.getText()))
            StyleConstants.setFontSize(this.attributes, Integer.parseInt(this.sizeTextField.getText()));
      }
      else { StyleConstants.setFontSize(this.attributes, Integer.parseInt("0")); }
      if(StyleConstants.isBold(this.attributes) != this.boldCheckBox.isSelected())
         StyleConstants.setBold(this.attributes, this.boldCheckBox.isSelected());
      if(StyleConstants.isItalic(this.attributes) != this.italicCheckBox.isSelected())
         StyleConstants.setItalic(this.attributes, this.italicCheckBox.isSelected());
      updatePreviewFont();
   }
   
   /**-----------------------------------------------------------------------------------------
   -----------------------------------------------------------actionPerformed Method Override*/
   
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == this.fontComboBox || e.getSource() == this.sizeTextField ||
         e.getSource() == this.boldCheckBox || e.getSource() == this.italicCheckBox) { momentSetting(); }
      else if(e.getSource() == this.okBtn)       { this.newFont = previewLabel.getFont();
      zoomPanel.getSelectedTopic().setFont(newFont);
      zoomPanel.repaint();
      setVisible(false); }
      else if(e.getSource() == this.cancelBtn)    { this.newFont = null; setVisible(false); }      
   }

   /**-----------------------------------------------------------------------------------------
   ---------------------------------------------------------------------------Getter & Setter*/
   
   public SimpleAttributeSet getAttributes()    { return this.attributes; }
   public JComboBox<String> getFontComboBox()    { return this.fontComboBox; }
   public JTextField getSizeTextField()       { return this.sizeTextField; }
   public JLabel getPreviewLabel()          { return this.previewLabel; }
   public JCheckBox getBoldCheckBox()          { return this.boldCheckBox; }
   public JCheckBox getItalicCheckBox()       { return this.italicCheckBox; }
   public JButton getOkBtn()                { return this.okBtn; }
   public JButton getCancelBtn()             { return this.cancelBtn;}
   public Font getNewFont()                { return this.newFont; }
}
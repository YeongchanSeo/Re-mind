package mindmap.view.menu.dialog.helper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Hashtable;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import mindmap.view.RemindMainFrame;
import mindmap.view.center.ZoomPanel;

public class RemindColorPalette extends JDialog {

	/**-----------------------------------------------------------------------------------------
	------------------------------------------------------------------------------Static Field*/
	
	private static final long serialVersionUID = 8009785301726170579L;
	
	private static int paletteWidth;
	private static int paletteHeight;	
	
	public static int getPaletteWidth() 					{ return RemindColorPalette.paletteWidth; }
	public static int getPaletteHeight() 					{ return RemindColorPalette.paletteHeight; }
	public static void setPaletteWidth(int paletteWidth) 	{ RemindColorPalette.paletteWidth = paletteWidth; }
	public static void setPaletteHeight(int paletteHeight) 	{ RemindColorPalette.paletteHeight = paletteHeight; }

	/**-----------------------------------------------------------------------------------------
	------------------------------------------------------------------------------Member Field*/
	
	private Hashtable<Color, ColorPane> paneTable;

	private Border 		unselectedBorder;
	private Border 		selectedBorder;
	private Border 		activeBorder;
	private ColorPane 	colorPane;
	private JTextField	setColor;
	private ZoomPanel   zoomPanel;

	/**-----------------------------------------------------------------------------------------
	-------------------------------------------------------------------------------Constructor*/
	
	public RemindColorPalette(JTextField setColor, boolean lineCall, ZoomPanel zoomPanel) {
		this.setColor = setColor;
		this.zoomPanel = zoomPanel;
		this.unselectedBorder 	= new CompoundBorder(new MatteBorder(1, 1, 1, 1, new Color(240, 250, 250)),
								  new BevelBorder(BevelBorder.LOWERED, Color.white, Color.gray));
		this.selectedBorder 	= new CompoundBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK), 
								  new MatteBorder(1, 1, 1, 1, getBackground()));
		this.activeBorder 		= new CompoundBorder(new MatteBorder(1, 1, 1, 1, Color.blue), 
				  				  new MatteBorder(1, 1, 1, 1, new Color(240, 250, 250)));
		this.paneTable 			= new Hashtable<Color, ColorPane>();
		JPanel colorPanel 		= null;
		
		if(lineCall == true) {
			setPaletteWidth(200);
			setPaletteHeight(200);
			colorPanel = new JPanel(new GridLayout(4, 4));
			colorPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
			
			int[][] colorValues = new int[][] { { 30,  30,  30}, {255, 255, 255}, {255, 100, 100}, {100, 100, 255},
												{255, 240, 240}, {255, 255, 150}, {255, 240, 255}, {255, 255, 240},
												{200, 200, 255}, {200, 255, 200}, {255, 200, 200}, {200, 255, 255},
												{255, 200, 255}, {255, 255, 200}, {240, 240, 255}, {240, 255, 240} };
			for(int i = 0; i < colorValues.length; i++) {
				Color color = new Color(colorValues[i][0], colorValues[i][1], colorValues[i][2]);
				ColorPane colorPane = new ColorPane(color);
				colorPanel.add(colorPane);
				this.paneTable.put(color, colorPane);
			}
		}
		else {
			setPaletteWidth(200);
			setPaletteHeight(70);
			colorPanel = new JPanel(new GridLayout(1, 4));
			colorPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
			
			int[][] colorValues = new int[][] { {255, 250, 200}, {200, 240, 245}, {255, 200, 220}, {255, 230, 220} };
			
			for(int i = 0; i < colorValues.length; i++) {
				Color color = new Color(colorValues[i][0], colorValues[i][1], colorValues[i][2]);
				ColorPane colorPane = new ColorPane(color, lineCall);
				colorPanel.add(colorPane);
				this.paneTable.put(color, colorPane);
			}
		}
			
		add(colorPanel);
		setDialog();
	}
	
	/**-----------------------------------------------------------------------------------------
	---------------------------------------------------------------------------------다이얼 로그 설정*/
	
	public void setDialog() {
	    this.setSize(new Dimension(RemindColorPalette.paletteWidth, RemindColorPalette.paletteHeight));
		this.setLocation(RemindMainFrame.START_XCOORD + RemindMainFrame.VIEWSIZE_WIDTH / 2 - 150 + 310,
						 RemindMainFrame.START_YCOORD + RemindMainFrame.VIEWSIZE_HEIGHT / 2 - 100);
		this.setModal(true);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	/**-----------------------------------------------------------------------------------------
	------------------------------------------------------------------------------현재 선택된 색을 반환*/

	public Color selectColor(Color color) {
		Object obj = this.paneTable.get(color);
		
		if (obj == null) return null;
		if (this.colorPane != null) this.colorPane.setSelected(false);
		
		this.colorPane = (ColorPane) obj;
		this.colorPane.setSelected(true);
		
		return color;
	}
	public void setSetColor(Color color) { this.setColor.setBackground(color); }
	
	/**-----------------------------------------------------------------------------------------
	------------------------------------------------------------------------------Nested Class*/
	
	class ColorPane extends JPanel implements MouseListener {
		
		private static final long serialVersionUID = -2021881862151681750L;
		
		private Color 	color;
		private boolean isSelected;
		
		/**-----------------------------------------------------------------------------------------
		-------------------------------------------------------------------------------Constructor*/
		
		public ColorPane(Color color) {
			this.color = color;
			setBackground(color);
			setBorder(unselectedBorder);
			String msg = "RGB(" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ")";
			setToolTipText(msg);
			addMouseListener(this);
		}
		
		public ColorPane(Color color, boolean topicCall) {
			this.color = color;
			setBackground(color);
			setBorder(unselectedBorder);
			String msg = null;
			
			if(color.getRed() == 255 && color.getGreen() == 250 && color.getBlue() == 200) { msg = "기본 토픽"; }
			if(color.getRed() == 200 && color.getGreen() == 240 && color.getBlue() == 245) { msg = "WHAT 토픽"; }
			if(color.getRed() == 255 && color.getGreen() == 200 && color.getBlue() == 220) { msg = "WHY 토픽"; }
			if(color.getRed() == 255 && color.getGreen() == 230 && color.getBlue() == 220) { msg = "HOW 토픽"; }
			
			setToolTipText(msg);
			addMouseListener(this);
		}
		
		/**-----------------------------------------------------------------------------------------
		---------------------------------------------------------------------------Getter & Setter*/
		
		public Color getColor() 	{ return this.color; }
		public boolean isSelected() { return this.isSelected;}
		
		public void setSelected(boolean selected) {
			this.isSelected = selected;
			
			if(this.isSelected) setBorder(selectedBorder);
			else 				setBorder(unselectedBorder);
		}
		
		/**-----------------------------------------------------------------------------------------
		------------------------------------------------------------------Listener Method Override*/
		
		public void mousePressed(MouseEvent e) 	{}
		public void mouseClicked(MouseEvent e) 	{ 
			setSetColor(selectColor(color)); 
			
			dispose();}
		public void mouseEntered(MouseEvent e) 	{ setBorder(activeBorder); }
		public void mouseExited(MouseEvent e)  	{ setBorder(isSelected ? selectedBorder : unselectedBorder); }
		public void mouseReleased(MouseEvent e) {}
	}
}
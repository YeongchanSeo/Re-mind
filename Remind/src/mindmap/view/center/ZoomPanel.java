package mindmap.view.center;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import main.MindMapController;
import mindmap.view.bottom.RemindNoteView;

public class ZoomPanel extends DiagramPanel{
	
	private static final float ZOOM_CORRECTION_FACTOR = 1.0F;
	private float zoom = 1.0F;
	
	public ZoomPanel(MindMapController controller, JFrame frame,
			RemindNoteView notePanel, int paneNum) {
		super(controller, frame, notePanel, paneNum);
	}
	public void paint(Graphics g) {
		float zoom = getZoom();
		if (zoom != 1F) {
			final Graphics2D g2 = (Graphics2D) g;
			zoom *= ZOOM_CORRECTION_FACTOR;
			//final AffineTransform transform = g2.getTransform();
			g2.scale(zoom, zoom);
			super.paint(g);
		} else {
			super.paint(g);
		}
	}
	
	public void setZoom(float zoom) {
		this.zoom = zoom;
		repaint();
	}
	
	public float getZoom(){
		return zoom;
	}
}

package mindmap.view.center;

import java.awt.Point;
import java.awt.geom.Path2D;

import diagram.Topic;

public class RoundLine {
	private Path2D.Double path = new Path2D.Double();
	private Point[] points;
	private int width;
	
	public RoundLine(int width){
		this.width = width;
	}
	
	public Path2D.Double getPath(Topic startTopic, Topic endTopic){
		points = new Point[3];
		int[][] cds = {{startTopic.getPoint().x,
				  startTopic.getPoint().y},{(endTopic.getPoint().x),
					  (startTopic.getPoint().y)},
				   {endTopic.getPoint().x,
				   endTopic.getPoint().y}};
		points = new Point[cds.length];
		for (int j = 0; j < cds.length; j++) {
			points[j] = new Point(cds[j][0], cds[j][1]);
		}
		setPath();
		
		return path;
	}
	
	public Path2D.Double getPath(Topic startTopic, int xCoord, int yCoord){
		points = new Point[3];
		int[][] cds = {{startTopic.getPoint().x,
				  startTopic.getPoint().y},{xCoord,
					  startTopic.getPoint().y},
				   {xCoord,
				   yCoord}};
		points = new Point[cds.length];
		for (int j = 0; j < cds.length; j++) {
			points[j] = new Point(cds[j][0], cds[j][1]);
		}
		setPath();
		
		return path;
	}
	
	private void setPath() {
		path.reset();
		int n = points.length;
		int w = width;
		for (int j = 0; j <= w; j++) {
			double t = (double) j / w;
			double x = 0;
			double y = 0;
			for (int k = 0; k < n; k++) {
				x += B(n - 1, k, t) * points[k].x;
				y += B(n - 1, k, t) * points[k].y;
			}
			if (j > 0)
				path.lineTo(x, y);
			else
				path.moveTo(x, y);
		}
	}

	private double B(int n, int m, double t) {
		return C(n, m) * Math.pow(t, m) * Math.pow(1.0 - t, n - m);
	}

	private double C(int n, int m) {
		return factorial(n) / (factorial(m) * factorial(n - m));
	}

	private int factorial(int n) {
		return (n > 1) ? n * factorial(n - 1) : 1;
	}
}

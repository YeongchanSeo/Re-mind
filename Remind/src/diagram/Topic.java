package diagram;

import java.awt.Font;
import java.awt.Point;

public class Topic {
	
	public enum TopicColor{
		WHAT("what"),WHY("why"),HOW("how"),BASIC("basic"),CENTER("center");
		private String color;
		private TopicColor(String color){
			this.color = color;
		}
		public String getColor(){
			return color;
		}
	}
	
	public enum TopicShape{
		RECTANGLE("rectangle"),OVERALL("overall");
		private String shape;
		private TopicShape(String shape){
			this.shape = shape;
		}
		public String getShape(){
			return shape;
		}
	}
	
	private int 	radius;
	private String 	title;
	private Point 	point;
	private String 	color;
	private String 	shape;
	private int 	width;
	private int 	height;
	private Note 	note;
	private Memo 	memo;
	private int 	key;
	private boolean selected;
	private boolean mouseOn;
	private boolean folded;
	private Font    font;
	
	public Topic() {
		this("¡ﬂΩ… ≈‰«»", new Point(1500, 1500));
	}

	public Topic(String title, Point point) {
		
		super();
		this.title 		= title;
		this.point	= point;
		color		= TopicColor.BASIC.getColor();
		shape 		= TopicShape.RECTANGLE.getShape();
		width 		= 26;
		height 		= 40;
		note 		= new Note();
		memo 		= new Memo();
		this.radius = 14;
		key = Key.getKey();
		font        = new Font("∏º¿∫ ∞ÌµÒ", 0, 14);
	}
	

	@Override
	public String toString() {
		return title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + (folded ? 1231 : 1237);
		result = prime * result + height;
		result = prime * result + key;
		result = prime * result + ((memo == null) ? 0 : memo.hashCode());
		result = prime * result + (mouseOn ? 1231 : 1237);
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((point == null) ? 0 : point.hashCode());
		result = prime * result + radius;
		result = prime * result + (selected ? 1231 : 1237);
		result = prime * result + ((shape == null) ? 0 : shape.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + width;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topic other = (Topic) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (folded != other.folded)
			return false;
		if (height != other.height)
			return false;
		if (key != other.key)
			return false;
		if (memo == null) {
			if (other.memo != null)
				return false;
		} else if (!memo.equals(other.memo))
			return false;
		if (mouseOn != other.mouseOn)
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (point == null) {
			if (other.point != null)
				return false;
		} else if (!point.equals(other.point))
			return false;
		if (radius != other.radius)
			return false;
		if (selected != other.selected)
			return false;
		if (shape == null) {
			if (other.shape != null)
				return false;
		} else if (!shape.equals(other.shape))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (width != other.width)
			return false;
		return true;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	public Memo getMemo() {
		return memo;
	}

	public void setMemo(Memo memo) {
		this.memo = memo;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isMouseOn() {
		return mouseOn;
	}

	public void setMouseOn(boolean mouseOn) {
		this.mouseOn = mouseOn;
	}

	public boolean isFolded() {
		return folded;
	}

	public void setFolded(boolean folded) {
		this.folded = folded;
	}

	public boolean isContained(Point pt){
		
		return (pt.x <= point.x + width/2 && pt.x >= point.x - width/2 && 
				pt.y <= point.y + height/2 && pt.y >= point.y - height/2);
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}	
}

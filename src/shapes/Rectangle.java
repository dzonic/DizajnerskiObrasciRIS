package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape {
	private Point upperLeftPoint;
	private int width;
	private int height;
	public Rectangle(Point upperLeftPoint, int height, int width) {
		this.upperLeftPoint = upperLeftPoint;
		setHeight(height);
		setWidth(width);
	}
	public Rectangle(Point upperLeftPoint, int height, int width, Color edgeColor, Color innerColor) {
		this.upperLeftPoint = upperLeftPoint;
		setHeight(height);
		setWidth(width);
		setEdgeColor(edgeColor);
		setInnerColor(innerColor);
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(getEdgeColor());
		g.drawRect(this.getUpperLeftPoint().getxCoordinate(), this.getUpperLeftPoint().getyCoordinate(), this.width, this.height);
		g.setColor(getInnerColor());
		g.fillRect(this.getUpperLeftPoint().getxCoordinate() + 1, this.getUpperLeftPoint().getyCoordinate() + 1, this.width - 1, this.height - 1);

		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.getUpperLeftPoint().getxCoordinate() - 3, this.getUpperLeftPoint().getyCoordinate() - 3, 6, 6);
			g.drawRect(this.getUpperLeftPoint().getxCoordinate() - 3 + getWidth(), this.getUpperLeftPoint().getyCoordinate() - 3, 6, 6);
			g.drawRect(this.getUpperLeftPoint().getxCoordinate() - 3, this.getUpperLeftPoint().getyCoordinate() - 3 + getHeight(), 6, 6);
			g.drawRect(this.getUpperLeftPoint().getxCoordinate() + getWidth() - 3, this.getUpperLeftPoint().getyCoordinate() + getHeight() - 3, 6, 6);
		}
	}
	@Override
	public void moveBy(int byX, int byY) {
		upperLeftPoint.moveBy(byX, byY);
	}
	@Override
	public int compareTo(Object o) {
		if (o instanceof Rectangle) {
			return (int)(this.area() - ((Rectangle) o).area());
		}
		return 0;
	}
	public boolean contains(int x, int y) {
		if (this.getUpperLeftPoint().getxCoordinate() <= x &&
				x <= this.getUpperLeftPoint().getxCoordinate() + width &&
				this.getUpperLeftPoint().getyCoordinate() <= y &&
				y <= this.getUpperLeftPoint().getyCoordinate() + height) {
			return true;
		} else {
			return false;
		}
	}
	public boolean contains(Point p) {
		if (this.getUpperLeftPoint().getxCoordinate() <= p.getxCoordinate() &&
				p.getxCoordinate() <= this.getUpperLeftPoint().getxCoordinate() + width &&
				this.getUpperLeftPoint().getyCoordinate() <= p.getyCoordinate() &&
				p.getyCoordinate() <= this.getUpperLeftPoint().getyCoordinate() + height) {
			return true;
		} else {
			return false;
		}
	}
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle r = (Rectangle) obj;
			if (this.upperLeftPoint.equals(r.getUpperLeftPoint()) && this.height == r.getHeight() &&
					this.width == r.getWidth()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	public int area() {
		return width * height;
	}
	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
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
	public String toString() {
		return "Rectangle UpperLeftPoint(" + upperLeftPoint.getxCoordinate() + "|" + upperLeftPoint.getyCoordinate() + ")|Width(" + width + ")|Height(" + height + ")|EdgeColor(" + getEdgeColor().getRGB() + ")|InnerColor(" + getInnerColor().getRGB() + ")";
	}

	public static Rectangle parse(String shape) {
		shape = shape.replace("Rectangle UpperLeftPoint(", "").replace(")", "");
		String[] params = shape.split("\\|");

		int x = Integer.parseInt(params[0]);
		int y = Integer.parseInt(params[1]);
		int width = Integer.parseInt(params[2].replace("Width(", ""));
		int height = Integer.parseInt(params[3].replace("Height(", ""));
		Color edgeColor = Color.decode(params[4].replace("EdgeColor(", ""));
		Color innerColor = Color.decode(params[5].replace("InnerColor(", ""));

		return new Rectangle(new Point(x, y), height, width, edgeColor, innerColor);
	}
	@Override
	public Shape clone() {
		Rectangle rectangle = new Rectangle(new Point(getUpperLeftPoint().getxCoordinate(), getUpperLeftPoint().getyCoordinate()), getHeight(), getWidth());
		rectangle.setEdgeColor(getEdgeColor());
		rectangle.setInnerColor(getInnerColor());
		rectangle.setSelected(isSelected());

		return rectangle;
	}

}
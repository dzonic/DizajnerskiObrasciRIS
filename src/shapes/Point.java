package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {
	private int xCoordinate;
	private int yCoordinate;
	public Point() {
	}
	public Point(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		setyCoordinate(yCoordinate);
	}
	public Point(int xCoordinate, int yCoordinate, Color edgeColor) {
		this.xCoordinate = xCoordinate;
		setyCoordinate(yCoordinate);
		setEdgeColor(edgeColor);
	}
	public Point(Point p, Color edgeColor) {
		this.xCoordinate = p.getxCoordinate();
		this.yCoordinate = p.getyCoordinate();
		setEdgeColor(edgeColor);
	}
	public Point(int xCoordinate, int yCoordinate, boolean selected) {
		this(xCoordinate, yCoordinate);
		setSelected(selected);
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(getEdgeColor());
		g.drawLine(this.xCoordinate -2, yCoordinate, this.xCoordinate +2, yCoordinate);
		g.drawLine(xCoordinate, this.yCoordinate -2, xCoordinate, this.yCoordinate +2);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.xCoordinate -3, this.getyCoordinate()-3, 6, 6);
		}
	}
	@Override
	public void moveBy(int byX, int byY) {
		this.xCoordinate = this.xCoordinate + byX;
		this.yCoordinate += byY;
	}
	@Override
	public int compareTo(Object o) {
		if (o instanceof Point) {
			Point start = new Point(0, 0);
			return (int) (this.distance(start.getxCoordinate(), start.getyCoordinate()) - ((Point) o).distance(start.getxCoordinate(), start.getyCoordinate()));
		}
		return 0;
	}
	public boolean contains(int xCoordinate, int yCoordinate) {
		return this.distance(xCoordinate, yCoordinate) <= 3;
	}
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point p = (Point) obj;
			if (this.xCoordinate == p.getxCoordinate() &&
					this.yCoordinate == p.getyCoordinate()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	public double distance(int x2, int y2) {
		double dx = this.xCoordinate - x2;
		double dy = this.yCoordinate - y2;
		double d = Math.sqrt(dx*dx + dy*dy);
		return d;
	}
	public int getxCoordinate() {
		return this.xCoordinate;
	}
	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}
	public int getyCoordinate() {
		return this.yCoordinate;
	}
	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	public String toString() {
		return "Point(" + xCoordinate + "|" + yCoordinate +")|EdgeColor(" + getEdgeColor().getRGB() + ")";
	}
	public static Point parse(String shape) {
		shape = shape.replace("Point(", "").replace(")", "");
		
		String[] params = shape.split("\\|");
		
		int xCoordinate = Integer.parseInt(params[0]);
		int yCoordinate = Integer.parseInt(params[1]);
		Color edgeColor = Color.decode(params[2].replace("EdgeColor(", "").replace(")",""));
		
		return new Point(xCoordinate,yCoordinate,edgeColor);
	}
	@Override
	public Shape clone() {
		Point point = new Point(getxCoordinate(), getyCoordinate(), getEdgeColor());
		point.setSelected(isSelected());
		return point;
	}
}

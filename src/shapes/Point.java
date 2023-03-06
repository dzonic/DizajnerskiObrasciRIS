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
	public Point(Point point, Color edgeColor) {
		this.xCoordinate = point.getxCoordinate();
		this.yCoordinate = point.getyCoordinate();
		setEdgeColor(edgeColor);
	}
	public Point(int xCoordinate, int yCoordinate, boolean selected) {
		this(xCoordinate, yCoordinate);
		setSelected(selected);
	}
	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(getEdgeColor());
		graphics.drawLine(this.xCoordinate -2, yCoordinate, this.xCoordinate +2, yCoordinate);
		graphics.drawLine(xCoordinate, this.yCoordinate -2, xCoordinate, this.yCoordinate +2);
		
		if (isSelected()) {
			graphics.setColor(Color.BLUE);
			graphics.drawRect(this.xCoordinate -3, this.getyCoordinate()-3, 6, 6);
		}
	}
	@Override
	public void moveBy(int byX, int byY) {
		this.xCoordinate = this.xCoordinate + byX;
		this.yCoordinate += byY;
	}
	@Override
	public int compareTo(Object object) {
		if (object instanceof Point) {
			Point start = new Point(0, 0);
			return (int) (this.distance(start.getxCoordinate(), start.getyCoordinate()) - ((Point) object).distance(start.getxCoordinate(), start.getyCoordinate()));
		}
		return 0;
	}
	public boolean contains(int xCoordinate, int yCoordinate) {
		return this.distance(xCoordinate, yCoordinate) <= 3;
	}
	public boolean equals(Object object) {
		if (object instanceof Point) {
			Point p = (Point) object;
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
	public double distance(int xCoordinate, int yCoordinate) {
		double dstanceXSquare = this.xCoordinate - xCoordinate;
		double distanceYSquare = this.yCoordinate - yCoordinate;
		double distanceSquare = Math.sqrt(dstanceXSquare*dstanceXSquare + distanceYSquare*distanceYSquare);
		return distanceSquare;
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

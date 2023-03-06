package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {
	private Point startPoint;
	private Point endPoint;
	public Line() {
	}
	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		setEndPoint(endPoint);
	}
	public Line(Point startPoint, Point endPoint, Color edgeColor) {
		this.startPoint = startPoint;
		setEndPoint(endPoint);
		setEdgeColor(edgeColor);
	}
	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(getEdgeColor());
		graphics.drawLine(this.getStartPoint().getxCoordinate(), this.getStartPoint().getyCoordinate(), this.endPoint.getxCoordinate(), this.getEndPoint().getyCoordinate());
		
		if (isSelected()) {
			graphics.setColor(Color.BLUE);
			graphics.drawRect(this.getStartPoint().getxCoordinate() - 3, this.getStartPoint().getyCoordinate() - 3, 6, 6);
			graphics.drawRect(this.getEndPoint().getxCoordinate() - 3, this.getEndPoint().getyCoordinate() - 3, 6, 6);
			graphics.drawRect(this.middleOfLine().getxCoordinate() - 3, this.middleOfLine().getyCoordinate() - 3, 6, 6);
		}
	}
	@Override
	public int compareTo(Object object) {
		if (object instanceof Line) {
			return (int) (this.length() - ((Line) object).length());
		}
		return 0;
	}
	@Override
	public void moveBy(int xCoordinate, int yCoordinate) {
		startPoint.moveBy(xCoordinate, yCoordinate);
		endPoint.moveBy(xCoordinate, yCoordinate);
	}
	public Point middleOfLine() {
		int middleByX = (this.getStartPoint().getxCoordinate() + this.getEndPoint().getxCoordinate()) / 2;
		int middleByY = (this.getStartPoint().getyCoordinate() + this.getEndPoint().getyCoordinate()) / 2;
		Point p = new Point(middleByX, middleByY);
		return p;
	}
	public boolean contains(int xCoordinate, int yCoordinate) {
		if ((startPoint.distance(xCoordinate, yCoordinate) + endPoint.distance(xCoordinate, yCoordinate)) - length() <= 0.05) {
			return true;
		} else {
			return false;
		}
	}
	public boolean equals(Object object) {
		if (object instanceof Line) {
			Line l = (Line) object;
			if (this.startPoint.equals(l.getStartPoint()) && 
					this.endPoint.equals(l.getEndPoint())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	public double length() {
		return startPoint.distance(endPoint.getxCoordinate(), endPoint.getyCoordinate());
	}
	public Point getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public Point getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	public String toString() {
		return "Line(X1 " + startPoint.getxCoordinate() + "|Y1 " + startPoint.getyCoordinate() + "|X2 " + endPoint.getxCoordinate() + "|Y2 " + endPoint.getyCoordinate() + ")|EdgeColor(" + getEdgeColor().getRGB()+")";
	}
	public static Line parse(String shape) {
		shape = shape.replace("Line(X1 ", "").replace(")", "");
		
		String[] params = shape.split("\\|");
		int x1 = Integer.parseInt(params[0]);
		int y1 = Integer.parseInt(params[1].replace("Y1 ", ""));
		int x2 = Integer.parseInt(params[2].replace("X2 ",""));
		int y2 = Integer.parseInt(params[3].replace("Y2 ", ""));
		Color edgeColor = Color.decode(params[4].replace("EdgeColor(", ""));
		
		return new Line(new Point(x1,y1),new Point(x2,y2), edgeColor);
	}
	@Override
	public Shape clone() {
		Line line = new Line();
		line.setStartPoint(new Point(getStartPoint().getxCoordinate(), getStartPoint().getyCoordinate()));
		line.setEndPoint(new Point(getEndPoint().getxCoordinate(), getEndPoint().getyCoordinate()));
		line.setEdgeColor(getEdgeColor());
		line.setInnerColor(getInnerColor());
		line.setSelected(isSelected());
		
		return line;
	}
}

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
	public void draw(Graphics g) {
		g.setColor(getEdgeColor());
		g.drawLine(this.getStartPoint().getxCoordinate(), this.getStartPoint().getyCoordinate(), this.endPoint.getxCoordinate(), this.getEndPoint().getyCoordinate());
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.getStartPoint().getxCoordinate() - 3, this.getStartPoint().getyCoordinate() - 3, 6, 6);
			g.drawRect(this.getEndPoint().getxCoordinate() - 3, this.getEndPoint().getyCoordinate() - 3, 6, 6);
			g.drawRect(this.middleOfLine().getxCoordinate() - 3, this.middleOfLine().getyCoordinate() - 3, 6, 6);
		}
	}
	@Override
	public int compareTo(Object o) {
		if (o instanceof Line) {
			return (int) (this.length() - ((Line) o).length());
		}
		return 0;
	}
	@Override
	public void moveBy(int byX, int byY) {
		startPoint.moveBy(byX, byY);
		endPoint.moveBy(byX, byY);
	}
	public Point middleOfLine() {
		int middleByX = (this.getStartPoint().getxCoordinate() + this.getEndPoint().getxCoordinate()) / 2;
		int middleByY = (this.getStartPoint().getyCoordinate() + this.getEndPoint().getyCoordinate()) / 2;
		Point p = new Point(middleByX, middleByY);
		return p;
	}
	public boolean contains(int x, int y) {
		if ((startPoint.distance(x, y) + endPoint.distance(x, y)) - length() <= 0.05) {
			return true;
		} else {
			return false;
		}
	}
	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line l = (Line) obj;
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

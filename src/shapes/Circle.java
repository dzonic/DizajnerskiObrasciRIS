package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape {
	private Point center;
	private int radius;
	public Circle() {
	}
	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}
	public Circle(Point center, int radius, Color edgeColor, Color innerColor) {
		this.center = center;
		this.radius = radius;
		setEdgeColor(edgeColor);
		setInnerColor(innerColor);
	}
	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		setSelected(selected);
	}
	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(getEdgeColor());
		graphics.drawOval(this.getCenter().getxCoordinate() - getRadius(), this.getCenter().getyCoordinate() - this.getRadius(), getRadius()*2, this.getRadius()*2);
		graphics.setColor(getInnerColor());
		graphics.fillOval(this.getCenter().getxCoordinate()-this.getRadius(), this.getCenter().getyCoordinate()-this.getRadius(), this.getRadius()*2, this.getRadius()*2);
	
		if (isSelected()) {
			graphics.setColor(Color.BLUE);
			graphics.drawRect(getCenter().getxCoordinate() - 3, getCenter().getyCoordinate() - 3, 6, 6);
			graphics.drawRect(getCenter().getxCoordinate() - 3 - getRadius(), getCenter().getyCoordinate() - 3, 6, 6);
			graphics.drawRect(getCenter().getxCoordinate() - 3 + getRadius(), getCenter().getyCoordinate() - 3, 6, 6);
			graphics.drawRect(getCenter().getxCoordinate() - 3, getCenter().getyCoordinate() - 3 + getRadius() , 6, 6);
			graphics.drawRect(getCenter().getxCoordinate() - 3, getCenter().getyCoordinate() - 3 - getRadius(), 6, 6);
		}
	}
	@Override
	public void moveBy(int byXCoordinate, int byYCoordinate) {
		center.moveBy(byXCoordinate, byYCoordinate);
	}
	@Override
	public int compareTo(Object object) {
		if (object instanceof Circle) {
			return (this.radius - ((Circle) object).radius);
		}
		return 0;
	}
	public boolean contains(int xCoordinate, int yCoordinate) {
		return this.getCenter().distance(xCoordinate, yCoordinate) <= radius;
	}
	public boolean contains(Point point) {
		return point.distance(getCenter().getxCoordinate(), getCenter().getyCoordinate()) <= radius;
	}
	public boolean equals(Object object) {
		if (object instanceof Circle) {
			Circle circle = (Circle) object;
			if (this.center.equals(circle.getCenter()) && this.radius == circle.getRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	public double area() {
		return radius * radius * Math.PI;
	}
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
			this.radius = radius;
	}
	public String toString() {
		return "Circle Center(" + center.getxCoordinate()+"|"+center.getyCoordinate() + ")|Radius(" + radius + ")|EdgeColor("+getEdgeColor().getRGB()+")|InnerColor("+getInnerColor().getRGB() + ")";
	}
	public static Circle parse(String shape) {
		shape = shape.replace("Circle Center(", "").replace(")", "");
		
		String [] params = shape.split("\\|");
		
		int x = Integer.parseInt(params[0]);
		int y = Integer.parseInt(params[1]);
		int r = Integer.parseInt(params[2].replace("Radius(", ""));
		
		Color edgeColor = Color.decode(params[3].replace("EdgeColor(", ""));
		Color innerColor = Color.decode(params[4].replace("InnerColor(", ""));
		
		return new Circle(new Point(x, y), r, edgeColor, innerColor);
	}
	@Override
	public Shape clone() {
		Circle circle = new Circle();
		circle.setCenter(new Point(center.getxCoordinate(), center.getyCoordinate()));
		circle.setRadius(getRadius());
		circle.setEdgeColor(getEdgeColor());
		circle.setInnerColor(getInnerColor());
		circle.setSelected(isSelected());
		
		return circle;
	}
}

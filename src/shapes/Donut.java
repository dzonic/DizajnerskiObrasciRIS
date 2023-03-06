package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
public class Donut extends Circle {
	private int innerRadius;
	public Donut() {
	}
	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}
	public Donut(Point center, int radius, int innerRadius, Color edgeColor, Color innerColor) {
		super(center, radius, edgeColor, innerColor);
		this.innerRadius = innerRadius;
		setEdgeColor(edgeColor);
		setInnerColor(innerColor);
	}
	public void draw(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		Path2D path = new Path2D.Double(Path2D.WIND_EVEN_ODD);
		
		path.append(new Ellipse2D.Double(getCenter().getxCoordinate() - getRadius(), this.getCenter().getyCoordinate() - getRadius(), getRadius()*2, getRadius()*2), false);
		path.append(new Ellipse2D.Double(getCenter().getxCoordinate() - getInnerRadius(), this.getCenter().getyCoordinate() - getInnerRadius(), getInnerRadius()*2, getInnerRadius()*2), false);

		graphics2D.setColor(getInnerColor());
		graphics2D.fill(path);

		graphics2D.setColor(getEdgeColor());
		graphics2D.drawOval(getCenter().getxCoordinate() - getRadius(), this.getCenter().getyCoordinate() - getRadius(), getRadius()*2, getRadius()*2);
		graphics2D.drawOval(getCenter().getxCoordinate() - getInnerRadius(), this.getCenter().getyCoordinate() - getInnerRadius(), getInnerRadius()*2, getInnerRadius()*2);
		
		if (isSelected()) {
			graphics2D.setColor(Color.BLUE);
			graphics2D.drawRect(getCenter().getxCoordinate() - 3, getCenter().getyCoordinate() - 3, 6, 6);
			graphics2D.drawRect(getCenter().getxCoordinate() - 3 - getRadius(), getCenter().getyCoordinate() - 3, 6, 6);
			graphics2D.drawRect(getCenter().getxCoordinate() - 3 + getRadius(), getCenter().getyCoordinate() - 3, 6, 6);
			graphics2D.drawRect(getCenter().getxCoordinate() - 3, getCenter().getyCoordinate() - 3 + getRadius() , 6, 6);
			graphics2D.drawRect(getCenter().getxCoordinate() - 3, getCenter().getyCoordinate() - 3 - getRadius(), 6, 6);
		}
	}
	public int compareTo(Object object) {
		if (object instanceof Donut) {
			return (int) (this.area() - ((Donut) object).area());
		}
		return 0;
	}
	public boolean contains(int xCoordinate, int yCoordinate) {
		double dFromCenter = this.getCenter().distance(xCoordinate, yCoordinate);
		return super.contains(xCoordinate, yCoordinate) && dFromCenter > innerRadius;
	}
	public boolean contains(Point point) {
		double dFromCenter = this.getCenter().distance(point.getxCoordinate(), point.getyCoordinate());
		return super.contains(point.getxCoordinate(), point.getyCoordinate()) && dFromCenter > innerRadius;
	}
	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}
	public boolean equals(Object object) {
		if (object instanceof Donut) {
			Donut donut = (Donut) object;
			if (this.getCenter().equals(donut.getCenter()) &&
					this.getRadius() == donut.getRadius() &&
					innerRadius == donut.getInnerRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	public int getInnerRadius() {
		return innerRadius;
	}
	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	public String toString() {
		return "Donut Center(" + super.getCenter().getxCoordinate() + "|" + super.getCenter().getyCoordinate() + ")|Radius(" + super.getRadius() + ")|InnerRadius("+ innerRadius +")|EdgeColor("+getEdgeColor().getRGB()+")|InnerColor("+getInnerColor().getRGB() +")";
	}
	public static Donut parse(String shape) {
		shape = shape.replace("Donut Center(", "").replace(")", "");
		
		String [] params = shape.split("\\|");
		
		int x = Integer.parseInt(params[0]);
		int y = Integer.parseInt(params[1]);
		int r = Integer.parseInt(params[2].replace("Radius(", ""));
		int ir = Integer.parseInt(params[3].replace("InnerRadius(", ""));
		Color edgeColor = Color.decode(params[4].replace("EdgeColor(", ""));
		Color innerColor = Color.decode(params[5].replace("InnerColor(", ""));
		
		return new Donut(new Point(x,y),r,ir,edgeColor,innerColor);
	}
	public Shape clone() {
		Donut donut = new Donut(new Point(getCenter().getxCoordinate(), getCenter().getyCoordinate()), getRadius(), getInnerRadius(), getEdgeColor(), getInnerColor() );
		donut.setSelected(isSelected());
		return donut;
	}
}

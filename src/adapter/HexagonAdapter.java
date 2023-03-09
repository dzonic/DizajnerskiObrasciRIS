package adapter;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;
import shapes.Point;
import shapes.Shape;

public class HexagonAdapter extends Shape {
	private Hexagon hexagon;
	public HexagonAdapter(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	public HexagonAdapter (Point centar, int radius) {
		this.hexagon = new Hexagon(centar.getxCoordinate(), centar.getyCoordinate(), radius);
	}
	public HexagonAdapter(Hexagon hexagon, Color inner, Color edge) {
		this.hexagon = hexagon;
		this.hexagon.setAreaColor(inner);
		this.hexagon.setBorderColor(edge);
	}
	public HexagonAdapter(Point center, int radius, Color edgeColor, Color innerColor) {
		this(center, radius);
		this.hexagon.setBorderColor(edgeColor);
		this.hexagon.setAreaColor(innerColor);
	}
	@Override
	public void moveBy(int byXCoordinate, int byYCoordinate) {
		hexagon.setX(byXCoordinate);
		hexagon.setY(byYCoordinate);
	}
	@Override
	public int compareTo(Object object) {
		if(object instanceof HexagonAdapter)
			return hexagon.getR() - ((HexagonAdapter)object).hexagon.getR();
		return 0;
	}
	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}
	@Override
	public void draw(Graphics graphics) {
		this.hexagon.paint(graphics);
	}
	public boolean isSelected() {
		return this.hexagon.isSelected();
	}
	public void setSelected(boolean selected) {
		this.hexagon.setSelected(selected);
	}
	public Color getInnerColor() {
		return this.hexagon.getAreaColor();
	}
	public void setInnerColor(Color inner) {
		this.hexagon.setAreaColor(inner);
	}
	public Color getEdgeColor() {
		return this.hexagon.getBorderColor();
	}
	public void setEdgeColor(Color edge) {
		this.hexagon.setBorderColor(edge);
	}
	public Hexagon getHexagon() {
		return hexagon;
	}
	public String toString() {
		return "Hexagon Center(" + hexagon.getX() + "|"+hexagon.getY() + ")|Radius(" + hexagon.getR() + ")|EdgeColor("+getEdgeColor().getRGB()+")|InnerColor("+getInnerColor().getRGB() + ")";	
		}
	public static HexagonAdapter parse(String shape) {
		shape = shape.replace("Hexagon Center(", "").replace(")", "");
		String[] params = shape.split("\\|");
		
		int x = Integer.parseInt(params[0]);
		int y = Integer.parseInt(params[1]);
		int r = Integer.parseInt(params[2].replace("Radius(", ""));
		Color edgeColor = Color.decode(params[3].replace("EdgeColor(", ""));
		Color innerColor = Color.decode(params[4].replace("InnerColor(", ""));
		
		return new HexagonAdapter(new Point(x,y), r, edgeColor, innerColor);
	}
	@Override
	public Shape clone() {
		HexagonAdapter hexagon = new HexagonAdapter(new Point(getHexagon().getX(), getHexagon().getY()), getHexagon().getR());
		hexagon.setEdgeColor(getEdgeColor());
		hexagon.setInnerColor(getInnerColor());
		hexagon.setSelected(isSelected());
		
		return hexagon;
	}
	public boolean equals(Object object) {
		if (object instanceof HexagonAdapter) {
			HexagonAdapter hexagonAdapter = (HexagonAdapter) object;
			
			if (hexagon.getX() == hexagonAdapter.hexagon.getX() && hexagon.getY() == hexagonAdapter.hexagon.getY() && hexagon.getR() == hexagonAdapter.hexagon.getR()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}

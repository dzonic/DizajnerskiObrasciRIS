package view;

import javax.swing.JPanel;

import model.DrawingModel;
import shapes.Shape;


import java.awt.Color;
import java.awt.Graphics;

public class DrawingView extends JPanel {
	DrawingModel model;
	public void setModel(DrawingModel model) {
		this.model = model;
	}
	public DrawingView() {
		setBackground(Color.WHITE);
	}
	public void paint(Graphics graphics) {
		super.paint(graphics);
		if(model != null) {
			model.getShapes().forEach(shape -> shape.draw(graphics));
		}
	}
}

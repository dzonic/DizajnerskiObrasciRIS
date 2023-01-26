package app;

import javax.swing.JFrame;

import controller.DrawingController;
import view.FrmDrawing;
import model.DrawingModel;

public class Application {
	public static void main(String[] args) {
		DrawingModel model = new DrawingModel();
		FrmDrawing frame = new FrmDrawing();
		frame.getView().setModel(model);
		DrawingController controller = new DrawingController(model,frame);
		controller.addObserver(frame);
		frame.setController(controller);
		frame.getBtnColorEdge().setBackground(controller.getEdgeColor());
		frame.getBtnColorInner().setBackground(controller.getInnerColor());
		frame.setVisible(true);
	}
}

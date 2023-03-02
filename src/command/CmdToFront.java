package command;

import model.DrawingModel;
import shapes.Shape;

public class CmdToFront implements Command {
	private Shape shape;
	private DrawingModel model;
	private String log;
	private int index;
	public CmdToFront(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}
	@Override
	public void execute() {
		index = model.getIndex(shape);
		if (index == model.getShapes().size() - 1) {
			return;
		}
		model.remove(shape);
		model.addShapeAtIndex(shape, index + 1);
		log = "CMD_TO_FRONT_EXECUTE:" +shape;
	}
	@Override
	public void unexecute() {
		if (index == 0) { return; }
		model.remove(shape);
		model.addShapeAtIndex(shape, index);
		log = "CMD_TO_FRONT_UNEXECUTE:" +shape;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
}

package command;

import model.DrawingModel;
import shapes.Shape;

public class CmdBringToFront implements Command {
	private Shape shape;
	private DrawingModel model;
	int index;
	private String log;
	public CmdBringToFront(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}
	@Override
	public void execute() {
		index = model.getIndex(shape);
		
		model.remove(shape);
		model.addShapeAtIndex(shape, model.getShapes().size());
		
		log = "CMD_BRING_TO_FRONT_EXECUTE:" +shape;
	}
	@Override
	public void unexecute() {
		if(index > model.getShapes().size()-1) return;
		
		model.remove(shape);
		model.addShapeAtIndex(shape, index);
		
		log = "CMD_BRING_TO_FRONT_UNEXECUTE:" +shape;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
}

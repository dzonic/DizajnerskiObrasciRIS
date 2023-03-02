package command;

import model.DrawingModel;
import shapes.Shape;

public class CmdBringToBack implements Command{
	private Shape shape;
	private DrawingModel model;
	private String log;
	private int index;
	public CmdBringToBack(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}
	@Override
	public void execute() {
		index = model.getIndex(shape);
		
		model.remove(shape);
		model.addShapeAtIndex(shape, 0);
		log = "CMD_BRING_TO_BACK_EXECUTE:" +shape;
	}
	@Override
	public void unexecute() {
		if(index > model.getShapes().size()-1) return;
		
		model.remove(shape);
		model.addShapeAtIndex(shape, index);
		log = "CMD_BRING_TO_BACK_UNEXECUTE:" +shape;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
}

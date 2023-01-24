package command;

import model.DrawingModel;
import shapes.Shape;

public class CmdToBack implements Command {

	private Shape shape;
	private DrawingModel model;
	private String log;
	private int index;
	
	public CmdToBack(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}
	
	@Override
	public void execute() {
		index = model.getIndex(shape);
		
		model.remove(shape);
		model.addShapeAtIndex(shape, index - 1);
		
		log = "CMD_TO_BACK_EXECUTE:" +shape;
	}

	@Override
	public void unexecute() {
		
		model.remove(shape);
		model.addShapeAtIndex(shape, index);
		
		log = "CMD_TO_BACK_UNEXECUTE:" +shape;
		
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}
	
	

}

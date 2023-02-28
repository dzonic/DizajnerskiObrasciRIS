package command;

import model.DrawingModel;
import shapes.Shape;

public class CmdAddShape implements Command  {
	private Shape shape;
	private DrawingModel model;
	private String log;

	public CmdAddShape (Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}
	@Override
	public void execute() {
		this.model.addShape(shape);
		log = "CMD_ADD_EXECUTE:" + shape;
	}
	@Override
	public void unexecute() {
		this.model.remove(shape);
		log = "CMD_ADD_UNEXECUTE:" + shape;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
}

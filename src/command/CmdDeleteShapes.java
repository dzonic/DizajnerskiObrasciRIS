package command;

import java.util.List;

import model.DrawingModel;
import shapes.Shape;

public class CmdDeleteShapes implements Command{

	private DrawingModel model;
	private List <Shape> shapesForDelete;
	private String log;
	
	public CmdDeleteShapes(List <Shape> shapes, DrawingModel model) {
		this.model = model;
		shapesForDelete = shapes;
	}
	
	@Override
	public void execute() {
		shapesForDelete.forEach(shape ->{
			model.remove(shape);
		});
		log = "CMD_DELETE_EXECUTE:" + shapesForDelete;
	}

	@Override
	public void unexecute() {
		shapesForDelete.forEach(shape ->{
			model.addShape(shape);
			shape.setSelected(true);
		});
		
		log = "CMD_DELETE_UNEXECUTE:" + shapesForDelete;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	
}

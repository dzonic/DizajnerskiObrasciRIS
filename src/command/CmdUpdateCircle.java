package command;

import shapes.Circle;

public class CmdUpdateCircle implements Command {
	private Circle oldState;
	private Circle newState;
	private Circle original;
	private String log;
	public CmdUpdateCircle(Circle oldState, Circle newState) {
		this.oldState = oldState;
		this.newState = newState;
		this.original = (Circle)oldState.clone();
	}
	@Override
	public void execute() {
		oldState.getCenter().setxCoordinate(newState.getCenter().getxCoordinate());
		oldState.getCenter().setyCoordinate(newState.getCenter().getyCoordinate());
		oldState.setRadius(newState.getRadius());
		oldState.setEdgeColor(newState.getEdgeColor());
		oldState.setInnerColor(newState.getInnerColor());
		oldState.setSelected(newState.isSelected());
		
		log = "CMD_UPDATE_EXECUTE:" + original + "->" + newState;
		
	}
	@Override
	public void unexecute() {
		oldState.getCenter().setxCoordinate(original.getCenter().getxCoordinate());
		oldState.getCenter().setyCoordinate(original.getCenter().getyCoordinate());
		oldState.setRadius(original.getRadius());
		oldState.setEdgeColor(original.getEdgeColor());
		oldState.setInnerColor(original.getInnerColor());
		oldState.setSelected(original.isSelected());
		
		log = "CMD_UPDATE_UNEXECUTE:" + newState + "->" + original;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
}

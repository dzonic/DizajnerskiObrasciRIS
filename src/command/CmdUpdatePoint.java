package command;

import shapes.Point;

public class CmdUpdatePoint implements Command {
	private Point oldState;
	private Point newState;
	private Point original;
	private String log;
	public CmdUpdatePoint(Point oldState, Point newState) {
		this.oldState = oldState;
		this.newState = newState;
		original = (Point)oldState.clone();
	}
	@Override
	public void execute() {
		oldState.setxCoordinate(newState.getxCoordinate());
		oldState.setyCoordinate(newState.getyCoordinate());
		oldState.setEdgeColor(newState.getEdgeColor());
		oldState.setSelected(newState.isSelected());
		
		log = "CMD_UPDATE_EXECUTE:" + original + "->" + newState;
	}
	@Override
	public void unexecute() {
		oldState.setxCoordinate(original.getxCoordinate());
		oldState.setyCoordinate(original.getyCoordinate());
		oldState.setEdgeColor(original.getEdgeColor());
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

package command;

import shapes.Line;

public class CmdUpdateLine implements Command {
	private Line oldState;
	private Line newState;
	private Line original;
	private String log;
	public CmdUpdateLine(Line oldState, Line newState) {
		this.oldState = oldState;
		this.newState = newState;
		original = (Line)oldState.clone();
	}
	@Override
	public void execute() {
		oldState.getStartPoint().setxCoordinate(newState.getStartPoint().getxCoordinate());
		oldState.getStartPoint().setyCoordinate(newState.getStartPoint().getyCoordinate());
		oldState.getEndPoint().setxCoordinate(newState.getEndPoint().getxCoordinate());
		oldState.getEndPoint().setyCoordinate(newState.getEndPoint().getyCoordinate());
		oldState.setEdgeColor(newState.getEdgeColor());
		oldState.setSelected(newState.isSelected());
		
		log = "CMD_UPDATE_EXECUTE:" + original + "->" + newState;
	}
	@Override
	public void unexecute() {
		oldState.getStartPoint().setxCoordinate(original.getStartPoint().getxCoordinate());
		oldState.getStartPoint().setyCoordinate(original.getStartPoint().getyCoordinate());
		oldState.getEndPoint().setxCoordinate(original.getEndPoint().getxCoordinate());
		oldState.getEndPoint().setyCoordinate(original.getEndPoint().getyCoordinate());
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

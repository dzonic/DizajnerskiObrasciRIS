package command;

import shapes.Donut;

public class CmdUpdateDonut implements Command{
	private Donut oldState;
	private Donut newState;
	private Donut original;
	private String log;
	public CmdUpdateDonut(Donut oldState, Donut newState) {
		this.oldState = oldState;
		this.newState = newState;
		this.original =(Donut)oldState.clone();
	}
	@Override
	public void execute() {
		oldState.getCenter().setxCoordinate(newState.getCenter().getxCoordinate());
		oldState.getCenter().setyCoordinate(newState.getCenter().getyCoordinate());
		oldState.setRadius(newState.getRadius());
		oldState.setInnerRadius(newState.getInnerRadius());
		oldState.setEdgeColor(newState.getEdgeColor());
		oldState.setInnerColor(newState.getInnerColor());
		oldState.setSelected(newState.isSelected());
		
		log = "CMD_UPDATE_EXECUTE:" + original + "->" + newState;
	}
	@Override
	public void unexecute() {
		oldState.setCenter(original.getCenter());
		oldState.setRadius(original.getRadius());
		oldState.setInnerRadius(original.getInnerRadius());
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

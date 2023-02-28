package command;

import shapes.Rectangle;

public class CmdUpdateRectangle implements Command{
	private Rectangle oldState;
	private Rectangle newState;
	private Rectangle original;
	private String log;
	public CmdUpdateRectangle(Rectangle oldState, Rectangle newState) {
		this.oldState = oldState;
		this.newState = newState;
		original = (Rectangle)oldState.clone();
	}
	@Override
	public void execute() {
		oldState.getUpperLeftPoint().setxCoordinate(newState.getUpperLeftPoint().getxCoordinate());
		oldState.getUpperLeftPoint().setyCoordinate(newState.getUpperLeftPoint().getyCoordinate());
		oldState.setHeight(newState.getHeight());
		oldState.setWidth(newState.getWidth());
		oldState.setInnerColor(newState.getInnerColor());
		oldState.setEdgeColor(newState.getEdgeColor());
		oldState.setSelected(newState.isSelected());
		
		log = "CMD_UPDATE_EXECUTE:" + original + "->" + newState;
	}
	@Override
	public void unexecute() {
		oldState.getUpperLeftPoint().setxCoordinate(original.getUpperLeftPoint().getxCoordinate());
		oldState.getUpperLeftPoint().setyCoordinate(original.getUpperLeftPoint().getyCoordinate());
		oldState.setHeight(original.getHeight());
		oldState.setWidth(original.getWidth());
		oldState.setInnerColor(original.getInnerColor());
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

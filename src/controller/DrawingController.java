package controller;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import adapter.HexagonAdapter;
import command.CmdAddShape;
import command.CmdBringToBack;
import command.CmdBringToFront;
import command.CmdDeleteShapes;
import command.CmdToBack;
import command.CmdToFront;
import command.CmdUpdateCircle;
import command.CmdUpdateDonut;
import command.CmdUpdateHexagon;
import command.CmdUpdateLine;
import command.CmdUpdatePoint;
import command.CmdUpdateRectangle;
import command.Command;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import view.FrmDrawing;
import shapes.*;
import strategy.Context;
import strategy.FileSerialization;
import strategy.LogFile;
import model.DrawingModel;
import observer.Observer;
import observer.Subject;
import dialogs.*;

public class DrawingController implements Subject {
	private DrawingModel model;
	private FrmDrawing frame;
	private Stack<Command> executedCmd;
	private Stack<Command> unexecutedCmd;
	private boolean lineWaitingForSecondPoint = false;
	private Point lineFirstPoint;
	private Color edgeColor, innerColor ;
	private DefaultListModel<String> log;
	private Context context;
	private FileSerialization fileSerialization;
	private LogFile logFile;
	private ArrayList <Observer> observers;
	private int currentState;
	private String typeOfFile = null;
	private boolean isRedo;
	private DialogCircle dialogCircle;
	private DialogRectangle dialogRectangle;
	private DialogLine dialogLine;
	private DialogPoint dialogPoint;
	private DialogDonut dialogDonut;
	private DialogHexagon dialogHexagon;
	public void setCurrentState(int state) {
		this.currentState = state;
		notifyObservers();
	}
	public DrawingController (DrawingModel model, FrmDrawing frame) {
		this.model = model;
		this.frame = frame;
		this.edgeColor = Color.BLACK;
		this.innerColor = Color.WHITE;
		this.executedCmd = new Stack<>();
		this.unexecutedCmd = new Stack<>();
		this.log = frame.getDefaultListLogModel();
		this.fileSerialization = new FileSerialization(model);
		this.logFile = new LogFile(this, model, frame);
		this.observers = new ArrayList<>();
		isRedo = false;
		dialogCircle = new DialogCircle();
		dialogPoint = new DialogPoint();
		dialogLine = new DialogLine();
		dialogRectangle = new DialogRectangle();
		dialogHexagon = new DialogHexagon();
		dialogDonut = new DialogDonut();
	}
	public void OperationDrawing(MouseEvent click) {
			Point mouseClick = new Point(click.getX(), click.getY());
			CmdAddShape cmdAddShape;
			if (currentState == frame.getOPERATION_EDIT_DELETE()) {
				model.getShapes().forEach(shape ->{
					if(shape.contains(click.getX(), click.getY())) {
						if(shape.isSelected()) {
							shape.setSelected(false);
							log.addElement("CMD_DESELECT:" + shape + "|MouseClick:(" + click.getX() +"|"+ click.getY()+")");
						}
						else {
							shape.setSelected(true);
							log.addElement("CMD_SELECT:" + shape+ "|MouseClick:(" + click.getX() +"|"+ click.getY()+")");
						}
						notifyObservers();
					}
				});
				frame.getView().repaint();
				return;	
			}
			else
				model.deselect();
			frame.getView().repaint();
				
			if (!frame.getBtnShapeLine().isSelected()) lineWaitingForSecondPoint = false;
			
			if (frame.getBtnShapePoint().isSelected()) {
					Point point = new Point(mouseClick.getxCoordinate(), mouseClick.getyCoordinate(), edgeColor);
					point.setEdgeColor(frame.getBtnColorEdge().getBackground());
					cmdAddShape = new CmdAddShape(point, model);
					execute(cmdAddShape);
			} else if (frame.getBtnShapeLine().isSelected()) {
				
				if (lineWaitingForSecondPoint) {
					Line line = new Line(lineFirstPoint, mouseClick, edgeColor);
					line.setEdgeColor(frame.getBtnColorEdge().getBackground());
					cmdAddShape = new CmdAddShape(line,model);
					execute(cmdAddShape);

					lineWaitingForSecondPoint = false;
					return;
				}
				lineFirstPoint = mouseClick;
				lineWaitingForSecondPoint = true;

			} else if (frame.getBtnShapeRectangle().isSelected()) {
				dialogRectangle.setPoint(mouseClick);
				dialogRectangle.setColors(frame.getBtnColorEdge().getBackground(),frame.getBtnColorInner().getBackground());
				dialogRectangle.setVisible(true);
				
				if(dialogRectangle.getRectangle() != null) {
					frame.getBtnColorEdge().setBackground(dialogRectangle.getEdgeColor());
					frame.getBtnColorInner().setBackground(dialogRectangle.getInnerColor());

					cmdAddShape = new CmdAddShape(dialogRectangle.getRectangle(), model);
					execute(cmdAddShape);

				}
			} else if (frame.getBtnShapeCircle().isSelected()) {
				dialogCircle.setPoint(mouseClick);
				dialogCircle.setColors(frame.getBtnColorEdge().getBackground(),frame.getBtnColorInner().getBackground());
				dialogCircle.setVisible(true);
				
				if(dialogCircle.getCircle() != null) {
					frame.getBtnColorEdge().setBackground(dialogCircle.getEdgeColor());
					frame.getBtnColorInner().setBackground(dialogCircle.getInnerColor());
					
					cmdAddShape = new CmdAddShape(dialogCircle.getCircle(), model);
					execute(cmdAddShape);
				}
			} else if (frame.getBtnShapeDonut().isSelected()) {
				dialogDonut.setPoint(mouseClick);
				dialogDonut.setColors(frame.getBtnColorEdge().getBackground(),frame.getBtnColorInner().getBackground());
				dialogDonut.setVisible(true);
				
				if(dialogDonut.getDonut() != null) {
					frame.getBtnColorEdge().setBackground(dialogDonut.getEdgeColor());
					frame.getBtnColorInner().setBackground(dialogDonut.getInnerColor());
					
					cmdAddShape = new CmdAddShape(dialogDonut.getDonut(), model);
					execute(cmdAddShape);
				}
			}
			else if(frame.getBtnShapeHexagon().isSelected()) {
				dialogHexagon.setPoint(mouseClick);
				dialogHexagon.setColors(frame.getBtnColorEdge().getBackground(),frame.getBtnColorInner().getBackground());
				dialogHexagon.setVisible(true);
				
				if(dialogHexagon.getHexagon() != null) {
					frame.getBtnColorEdge().setBackground(dialogHexagon.getEdgeColor());
					frame.getBtnColorInner().setBackground(dialogHexagon.getInnerColor());
					
					cmdAddShape = new CmdAddShape(dialogHexagon.getHexagon(), model);
					execute(cmdAddShape);
				}
			}
		}
	public void selectDeselectShapeFromLog(int x, int y) {
		frame.setActiveOperation(frame.getOPERATION_EDIT_DELETE());
		setCurrentState(frame.getOPERATION_EDIT_DELETE());
		MouseEvent event = new MouseEvent(frame.getView(), MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, x,y, 1, false);
		OperationDrawing(event);
	}
	public void operationEdit() {
		int index = model.getSelected();
		if (index == -1) return;

		Shape shape = model.getShape(index);
		
		if (shape instanceof Point) {
			dialogPoint.setPoint((Point)shape);
			dialogPoint.setVisible(true);
			
			if(dialogPoint.getPoint() != null) {
				frame.getBtnColorEdge().setBackground(dialogPoint.getEdgeColor());
				CmdUpdatePoint cmdUpdatePoint = new CmdUpdatePoint((Point)shape, dialogPoint.getPoint());
				execute(cmdUpdatePoint);
			}
		} else if (shape instanceof Line) {
			dialogLine.setLine((Line)shape);
			dialogLine.setVisible(true);
			
			if(dialogLine.getLine() != null) {
				frame.getBtnColorEdge().setBackground(dialogLine.getEdgeColor());
				
				CmdUpdateLine cmdUpdateLine = new CmdUpdateLine((Line)shape, dialogLine.getLine());
				execute(cmdUpdateLine);
			}
		} else if (shape instanceof Rectangle) {
			dialogRectangle.setRectangle((Rectangle)shape);
			dialogRectangle.setVisible(true);
			
			if(dialogRectangle.getRectangle() != null) {
				frame.getBtnColorEdge().setBackground(dialogRectangle.getEdgeColor());
				frame.getBtnColorInner().setBackground(dialogRectangle.getInnerColor());
				
				CmdUpdateRectangle cmdUpdateRectangle = new CmdUpdateRectangle((Rectangle)shape, dialogRectangle.getRectangle());
				execute(cmdUpdateRectangle);
			}
		} else if (shape instanceof Donut) {
				dialogDonut.setDonut((Donut)shape);
				dialogDonut.setVisible(true);

				if(dialogDonut.getDonut() != null) {
					frame.getBtnColorEdge().setBackground(dialogDonut.getEdgeColor());
					frame.getBtnColorInner().setBackground(dialogDonut.getInnerColor());
					CmdUpdateDonut cmdUpdateDonut = new CmdUpdateDonut((Donut)shape, dialogDonut.getDonut());
					execute(cmdUpdateDonut);
				}
		} else if (shape instanceof Circle) {
			dialogCircle.setCircle((Circle)shape);
			dialogCircle.setVisible(true);
			
			if(dialogCircle.getCircle() != null) {
				frame.getBtnColorEdge().setBackground(dialogCircle.getEdgeColor());
				frame.getBtnColorInner().setBackground(dialogCircle.getInnerColor());

				CmdUpdateCircle cmdUpdateCircle = new CmdUpdateCircle((Circle)shape, dialogCircle.getCircle());
				execute(cmdUpdateCircle);
			}
		}
		else if(shape instanceof HexagonAdapter) {
			dialogHexagon.setHexagon((HexagonAdapter)shape);
			dialogHexagon.setVisible(true);
			
			if(dialogHexagon.getHexagon()!= null) {
				frame.getBtnColorEdge().setBackground(dialogHexagon.getEdgeColor());
				frame.getBtnColorInner().setBackground(dialogHexagon.getInnerColor());

				CmdUpdateHexagon cmdUpdateHexagon =  new CmdUpdateHexagon((HexagonAdapter)shape, dialogHexagon.getHexagon());
				execute(cmdUpdateHexagon);
			}
		}
	}
	public void operationDelete() {
		if (model.isEmpty()) return;

		if (JOptionPane.showConfirmDialog(null, "Da li zaista zelite da obrisete selektovane oblike?", "Potvrda", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
			CmdDeleteShapes cmdDeleteShapes = new CmdDeleteShapes(model.getSelectedShapes(), model);
			execute(cmdDeleteShapes);
		} 
	}
	public void saveFile() {
		if(!model.getShapes().isEmpty()) frame.getSaveFileChooser().setFileFilter(frame.getDrawFilter());
		if(!log.isEmpty()) frame.getSaveFileChooser().setFileFilter(frame.getLogFilter());

		if (frame.getSaveFileChooser().showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			if(frame.getSaveFileChooser().getFileFilter().getDescription() == "Crtez")
				context = new Context(fileSerialization);
			else if(frame.getSaveFileChooser().getFileFilter().getDescription() == "Log")
				context = new Context(logFile);

			context.saveFile(frame.getSaveFileChooser().getSelectedFile());
		}
		frame.getSaveFileChooser().setVisible(false);
	}
	public void readCommand() {
		logFile.readCommand();
	}
	public void openFile() {
		if(frame.getOpenFileChooser().showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			if(frame.getOpenFileChooser().getFileFilter().getDescription() == "Crtez") context = new Context(fileSerialization); typeOfFile = "Crtez";

			if(frame.getOpenFileChooser().getFileFilter().getDescription() == "Log") context = new Context(logFile); typeOfFile = "Log";

			context.openFile(frame.getOpenFileChooser().getSelectedFile());
			frame.getView().repaint();
		}
		frame.getOpenFileChooser().setVisible(false);
		notifyObservers();
	}
	public void execute(Command command) {
		if(!isRedo) unexecutedCmd.clear();
		command.execute();
		if(command instanceof CmdAddShape) log.addElement(command.getLog());
		else if(command instanceof CmdDeleteShapes) log.addElement(command.getLog());
		else if(command instanceof CmdUpdateCircle) log.addElement(command.getLog());
		else if(command instanceof CmdUpdateDonut) log.addElement(command.getLog());
		else if(command instanceof CmdUpdateHexagon) log.addElement(command.getLog());
		else if(command instanceof CmdUpdateLine) log.addElement(command.getLog());
		else if(command instanceof CmdUpdatePoint) log.addElement(command.getLog());
		else if(command instanceof CmdUpdateRectangle) log.addElement(command.getLog());
		else if (command instanceof CmdBringToBack) log.addElement(command.getLog());
		else if(command instanceof CmdBringToFront) log.addElement(command.getLog());
		else if(command instanceof CmdToBack) log.addElement(command.getLog());
		else if(command instanceof CmdToFront) log.addElement(command.getLog());

		executedCmd.push(command);
		frame.getView().repaint();
		notifyObservers();
	}
	public void unexecute(Command command) {
		command.unexecute();
		if(command instanceof CmdAddShape) log.addElement(command.getLog());
		else if(command instanceof CmdDeleteShapes) log.addElement(command.getLog());
		else if(command instanceof CmdUpdateCircle) log.addElement(command.getLog());
		else if(command instanceof CmdUpdateDonut) log.addElement(command.getLog());
		else if(command instanceof CmdUpdateHexagon) log.addElement(command.getLog());
		else if(command instanceof CmdUpdateLine) log.addElement(command.getLog());
		else if(command instanceof CmdUpdatePoint) log.addElement(command.getLog());
		else if(command instanceof CmdUpdateRectangle) log.addElement(command.getLog());
		else if (command instanceof CmdBringToBack) log.addElement(command.getLog());
		else if(command instanceof CmdBringToFront) log.addElement(command.getLog());
		else if(command instanceof CmdToBack) log.addElement(command.getLog());
		else if(command instanceof CmdToFront) log.addElement(command.getLog());

		unexecutedCmd.push(command);
		frame.getView().repaint();
		notifyObservers();
	}

	public void undoCommand() {
		if(!executedCmd.isEmpty()) {
			Command undoCommand= executedCmd.pop();
			unexecute(undoCommand);
		}
	}
	public void redoCommand() {
		if(!unexecutedCmd.isEmpty()) {
			Command redoCommand= unexecutedCmd.pop();
			isRedo = true;
			execute(redoCommand);
		}
		isRedo = false;
	}
	public int numberOfSelectedShapes() {
		int numberOfSelected = model.getSelectedShapes().size();
		return numberOfSelected;
	}
	
	public void toFront() {
		int index = model.getSelected();
		Shape selectedShape = model.getShape(index);

		if(index == model.getShapes().size()-1) return;

		CmdToFront cmdToFront = new CmdToFront(selectedShape, model);
		execute(cmdToFront);
	}
	
	public void bringToFront() {
		int index = model.getSelected();
		Shape selectedShape = model.getShape(index);

		if(index == model.getShapes().size() -1) return;

		CmdBringToFront cmdBringToFront = new CmdBringToFront(selectedShape, model);
		execute(cmdBringToFront);
	}
	
	public void toBack() {
		int index = model.getSelected();
		Shape selectedShape = model.getShape(index);

		if(index == 0) return;

		CmdToBack cmdToBack = new CmdToBack(selectedShape, model);
		execute(cmdToBack);
	}
	public void bringToBack() {
		int index = model.getSelected();
		Shape selectedShape = model.getShape(index);
		
		if(index == 0) return;
		
		CmdBringToBack cmdBringToBack = new CmdBringToBack(selectedShape, model);
		execute(cmdBringToBack);
	}
	public Color getEdgeColor() {
		return edgeColor;
	}
	public Color getInnerColor() {
		return innerColor;
	}
	public void setEdgeColor(Color edgeColor) {
		this.edgeColor = edgeColor;
	}
	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}
	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}
	@Override
	public void notifyObservers() {
		for(Observer observer: observers) {
			observer.update(currentState, numberOfSelectedShapes(), model.getShapes().size(), unexecutedCmd.size(),
					executedCmd.size(),log.size(), typeOfFile);
		}
	}
}

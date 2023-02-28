package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
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
	private int currentState; // currentState
	private String typeOfFile = null;
	private boolean isRedo;
	
	public void setCurrentState(int state) {
		this.currentState = state;
		notifyObservers();
	}
	public DrawingController (DrawingModel model, FrmDrawing frame) {
		
		this.model = model;
		this.frame = frame;
		
		this.edgeColor = Color.BLACK;
		this.innerColor = Color.WHITE;
		
		this.executedCmd = new Stack<Command>();
		this.unexecutedCmd = new Stack<Command>();
		
		this.log = frame.getDefaultListLogModel();
		
		this.fileSerialization = new FileSerialization(model);
		this.logFile = new LogFile(this, model, frame);
		
		this.observers = new ArrayList<Observer>();

		isRedo = false;
	}
	public void OperationDrawing(MouseEvent e) {
		
			Point mouseClick = new Point(e.getX(), e.getY());
			CmdAddShape cmdAddShape;
			
			if (currentState == frame.getOPERATION_EDIT_DELETE()) {
				model.getShapes().forEach(shape ->{
					if(shape.contains(e.getX(), e.getY())) {
						if(shape.isSelected()) {
							shape.setSelected(false);
							log.addElement("CMD_DESELECT:" + shape + "|MouseClick:(" + e.getX() +"|"+ e.getY()+")");
						}
						else
						{
							shape.setSelected(true);
							log.addElement("CMD_SELECT:" + shape+ "|MouseClick:(" + e.getX() +"|"+ e.getY()+")");
							
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
				return;
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
				return;
			} else if (frame.getBtnShapeRectangle().isSelected()) {
				DialogRectangle dialogRectangle = new DialogRectangle();
				dialogRectangle.setPoint(mouseClick);
				dialogRectangle.setColors(frame.getBtnColorEdge().getBackground(),frame.getBtnColorInner().getBackground());
				dialogRectangle.setVisible(true);
				
				if(dialogRectangle.getRectangle() != null) {
					frame.getBtnColorEdge().setBackground(dialogRectangle.getEdgeColor());
					frame.getBtnColorInner().setBackground(dialogRectangle.getInnerColor());
					
					cmdAddShape = new CmdAddShape(dialogRectangle.getRectangle(), model);
					execute(cmdAddShape);
				
				}
				return;
			} else if (frame.getBtnShapeCircle().isSelected()) {
				DialogCircle dialogCircle = new DialogCircle();
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
				DialogDonut dialogDonut = new DialogDonut();
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
				DialogHexagon dialogHexagon = new DialogHexagon();
				dialogHexagon.setPoint(mouseClick);
				dialogHexagon.setColors(frame.getBtnColorEdge().getBackground(),frame.getBtnColorInner().getBackground());
				dialogHexagon.setVisible(true);
				
				if(dialogHexagon.getHexagon() != null)
				{
					frame.getBtnColorEdge().setBackground(dialogHexagon.getEdgeColor());
					frame.getBtnColorInner().setBackground(dialogHexagon.getInnerColor());
					
					cmdAddShape = new CmdAddShape(dialogHexagon.getHexagon(), model);
					execute(cmdAddShape);
					
				}
			}
		}
	public void selectDeselectShapeFromLog(int x, int y)
	{
		frame.setActiveOperation(frame.getOPERATION_EDIT_DELETE());
		setCurrentState(frame.getOPERATION_EDIT_DELETE());
		MouseEvent e = new MouseEvent(frame.getView(), MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, x,y, 1, false);
		OperationDrawing(e);
	}
	public void operationEdit(ActionEvent e) {

		int index = model.getSelected();
		if (index == -1) return;

		Shape shape = model.getShape(index);
		
		if (shape instanceof Point) {
			DialogPoint dialogPoint = new DialogPoint();
			dialogPoint.setPoint((Point)shape);
			dialogPoint.setVisible(true);
			
			if(dialogPoint.getPoint() != null) {
				
				frame.getBtnColorEdge().setBackground(dialogPoint.getEdgeColor());
				
				CmdUpdatePoint cmdUpdatePoint = new CmdUpdatePoint((Point)shape, dialogPoint.getPoint());
				execute(cmdUpdatePoint);
			}
		} else if (shape instanceof Line) {
			DialogLine dialogLine = new DialogLine();
			dialogLine.setLine((Line)shape);
			dialogLine.setVisible(true);
			
			if(dialogLine.getLine() != null) {
				
				frame.getBtnColorEdge().setBackground(dialogLine.getEdgeColor());
				
				CmdUpdateLine cmdUpdateLine = new CmdUpdateLine((Line)shape, dialogLine.getLine());
				execute(cmdUpdateLine);
			}
		} else if (shape instanceof Rectangle) {
			DialogRectangle dialogRectangle = new DialogRectangle();
			dialogRectangle.setRectangle((Rectangle)shape);
			dialogRectangle.setVisible(true);
			
			if(dialogRectangle.getRectangle() != null) {
				
				frame.getBtnColorEdge().setBackground(dialogRectangle.getEdgeColor());
				frame.getBtnColorInner().setBackground(dialogRectangle.getInnerColor());
				
				CmdUpdateRectangle cmdUpdateRectangle = new CmdUpdateRectangle((Rectangle)shape, dialogRectangle.getRectangle());
				execute(cmdUpdateRectangle);
			}
		}else if (shape instanceof Donut) {
				DialogDonut dialogDonut = new DialogDonut();
				dialogDonut.setDonut((Donut)shape);
				dialogDonut.setVisible(true);
				if(dialogDonut.getDonut() != null) {
					frame.getBtnColorEdge().setBackground(dialogDonut.getEdgeColor());
					frame.getBtnColorInner().setBackground(dialogDonut.getInnerColor());
					CmdUpdateDonut cmdUpdateDonut = new CmdUpdateDonut((Donut)shape, dialogDonut.getDonut());
					execute(cmdUpdateDonut);
				}
		} else if (shape instanceof Circle) {
			DialogCircle dialogCircle = new DialogCircle();
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
			DialogHexagon dialogHexagon = new DialogHexagon();
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
	public void operationDelete(ActionEvent e) {
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
	public void readCommand()
	{
		logFile.readCommand();
	}
	public void openFile() {

		if(frame.getOpenFileChooser().showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			if(frame.getOpenFileChooser().getFileFilter().getDescription() == "Crtez") context = new Context(fileSerialization); typeOfFile = "Crtez";

			if(frame.getOpenFileChooser().getFileFilter().getDescription() == "Log") context = new Context(logFile); typeOfFile = "Log";

			context.openFile(frame.getOpenFileChooser().getSelectedFile());
			frame.getView().repaint();
		}

		frame.getOpenFileChooser().setVisible(false);
		notifyObservers();

	}
	public void execute(Command cmd) {

		if(!isRedo) unexecutedCmd.clear();
		cmd.execute();
		if(cmd instanceof CmdAddShape) log.addElement(cmd.getLog());
		else if(cmd instanceof CmdDeleteShapes) log.addElement(cmd.getLog());
		else if(cmd instanceof CmdUpdateCircle) log.addElement(cmd.getLog());
		else if(cmd instanceof CmdUpdateDonut) log.addElement(cmd.getLog());
		else if(cmd instanceof CmdUpdateHexagon) log.addElement(cmd.getLog());
		else if(cmd instanceof CmdUpdateLine) log.addElement(cmd.getLog());
		else if(cmd instanceof CmdUpdatePoint) log.addElement(cmd.getLog());
		else if(cmd instanceof CmdUpdateRectangle) log.addElement(cmd.getLog());
		else if (cmd instanceof CmdBringToBack) log.addElement(cmd.getLog());
		else if(cmd instanceof CmdBringToFront) log.addElement(cmd.getLog());
		else if(cmd instanceof CmdToBack) log.addElement(cmd.getLog());
		else if(cmd instanceof CmdToFront) log.addElement(cmd.getLog());

		executedCmd.push(cmd);
		frame.getView().repaint();
		notifyObservers();

	}
	
	public void unexecute(Command cmd) {

		cmd.unexecute();
		if(cmd instanceof CmdAddShape) log.addElement(cmd.getLog());
		else if(cmd instanceof CmdDeleteShapes) log.addElement(cmd.getLog());
		else if(cmd instanceof CmdUpdateCircle) log.addElement(cmd.getLog());
		else if(cmd instanceof CmdUpdateDonut) log.addElement(cmd.getLog());
		else if(cmd instanceof CmdUpdateHexagon) log.addElement(cmd.getLog());
		else if(cmd instanceof CmdUpdateLine) log.addElement(cmd.getLog());
		else if(cmd instanceof CmdUpdatePoint) log.addElement(cmd.getLog());
		else if(cmd instanceof CmdUpdateRectangle) log.addElement(cmd.getLog());
		else if (cmd instanceof CmdBringToBack) log.addElement(cmd.getLog());
		else if(cmd instanceof CmdBringToFront) log.addElement(cmd.getLog());
		else if(cmd instanceof CmdToBack) log.addElement(cmd.getLog());
		else if(cmd instanceof CmdToFront) log.addElement(cmd.getLog());
		
		unexecutedCmd.push(cmd);
		frame.getView().repaint();
		notifyObservers();
	}

	public void undoCommand()
	{
		if(!executedCmd.isEmpty()) {
			Command undoCmd= executedCmd.pop();
			unexecute(undoCmd);
		}
	}

	public void redoCommand() {
		if(!unexecutedCmd.isEmpty())
		{
			Command redoCmd= unexecutedCmd.pop();
			isRedo = true;
			execute(redoCmd);
		}
		isRedo = false;
	}
	public int numOfSelectedShapes() {
		int numOfSelected = model.getSelectedShapes().size();
		return numOfSelected;
	}
	
	public void toFront(){
		int ind = model.getSelected();
		Shape selectedShape = model.getShape(ind);

		if(ind == model.getShapes().size()-1) return;

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
		int ind = model.getSelected();
		Shape selectedShape = model.getShape(ind);
		
		if(ind == 0) return;
		
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
			observer.update(currentState, numOfSelectedShapes(), model.getShapes().size(), unexecutedCmd.size(),
					executedCmd.size(),log.size(), typeOfFile);
		}
	}
}

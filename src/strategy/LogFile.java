package strategy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.swing.DefaultListModel;

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
import controller.DrawingController;
import model.DrawingModel;
import shapes.Circle;
import shapes.Donut;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import view.FrmDrawing;

public class LogFile implements Strategy {

	private DrawingController controller;
	private DrawingModel model;
	private FrmDrawing frame;
	private BufferedReader reader;
	private BufferedWriter writer;

	public LogFile(DrawingController controller, DrawingModel model, FrmDrawing frame) {
		this.controller = controller;
		this.model = model;
		this.frame = frame;
	}
	@Override
	public void saveFile(File file) {
		try {
			writer = new BufferedWriter(new FileWriter(file + ".log"));
			DefaultListModel < String > defaultListModel = frame.getDefaultListLogModel();
			for (int i = 0; i < defaultListModel.size(); i++) {
				writer.write(defaultListModel.getElementAt(i));
				writer.newLine();
			}
			writer.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	@Override
	public void openFile(File file) {
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void readCommand() {
		try {
			Command command;
			int index;
			String operation = reader.readLine();

			if (operation == null) {
				frame.getBtnReadCommand().setEnabled(false);
				return;
			}
			String[] cmdOperation = operation.split(":");
			switch (cmdOperation[0]) {
				case "CMD_ADD_EXECUTE":
					command = new CmdAddShape(parseShape(cmdOperation[1]), model);
					controller.execute(command);
					break;
				case "CMD_ADD_UNEXECUTE":
					controller.undoCommand();
					break;
				case "CMD_BRING_TO_BACK_EXECUTE":
					index = model.getIndex(parseShape(cmdOperation[1]));
					command = new CmdBringToBack(model.getShape(index), model);
					controller.execute(command);
					break;
				case "CMD_BRING_TO_BACK_UNEXECUTE":
					controller.undoCommand();
					break;
				case "CMD_BRING_TO_FRONT_EXECUTE":
					index = model.getIndex(parseShape(cmdOperation[1]));
					command = new CmdBringToFront(model.getShape(index), model);
					controller.execute(command);
					break;
				case "CMD_BRING_TO_FRONT_UNEXECUTE":
					controller.undoCommand();
					break;
				case "CMD_TO_BACK_EXECUTE":
					index = model.getIndex(parseShape(cmdOperation[1]));
					command = new CmdToBack(model.getShape(index), model);
					controller.execute(command);
					break;
				case "CMD_TO_BACK_UNEXECUTE":
					controller.undoCommand();
					break;
				case "CMD_TO_FRONT_EXECUTE":
					index = model.getIndex(parseShape(cmdOperation[1]));
					command = new CmdToFront(model.getShape(index), model);
					controller.execute(command);
					break;
				case "CMD_TO_FRONT_UNEXECUTE":
					controller.undoCommand();
					break;
				case "CMD_DELETE_EXECUTE":
					String shapes = cmdOperation[1].replace("[", "").replace("]", "");
					ArrayList < Shape > shapesForDelete = new ArrayList < Shape > ();
					String[] s = shapes.split(",");

					for (int i = 0; i < s.length; i++) {
						shapesForDelete.add(parseShape(s[i].trim()));
					}

					command = new CmdDeleteShapes(shapesForDelete, model);
					controller.execute(command);
					break;
				case "CMD_DELETE_UNEXECUTE":
					controller.undoCommand();
					break;
				case "CMD_UPDATE_EXECUTE":

					String[] arrayOfShapes;

					if (cmdOperation[1].startsWith("Point")) {
						arrayOfShapes = cmdOperation[1].split("->");
						Point oldState = Point.parse(arrayOfShapes[0]);
						Point newState = Point.parse(arrayOfShapes[1]);

						index = model.getIndex(oldState);

						command = new CmdUpdatePoint((Point) model.getShape(index), newState);
						controller.execute(command);
					} else if (cmdOperation[1].startsWith("Line")) {
						arrayOfShapes = cmdOperation[1].split("->");
						Line oldState = Line.parse(arrayOfShapes[0]);
						Line newState = Line.parse(arrayOfShapes[1]);

						index = model.getIndex(oldState);

						command = new CmdUpdateLine((Line) model.getShape(index), newState);
						controller.execute(command);
					} else if (cmdOperation[1].startsWith("Circle")) {
						arrayOfShapes = cmdOperation[1].split("->");
						Circle oldState = Circle.parse(arrayOfShapes[0]);
						Circle newState = Circle.parse(arrayOfShapes[1]);

						index = model.getIndex(oldState);

						command = new CmdUpdateCircle((Circle) model.getShape(index), newState);
						controller.execute(command);
					} else if (cmdOperation[1].startsWith("Donut")) {
						arrayOfShapes = cmdOperation[1].split("->");
						Donut oldState = Donut.parse(arrayOfShapes[0]);
						Donut newState = Donut.parse(arrayOfShapes[1]);

						index = model.getIndex(oldState);

						command = new CmdUpdateDonut((Donut) model.getShape(index), newState);
						controller.execute(command);
					} else if (cmdOperation[1].startsWith("Rectangle")) {
						arrayOfShapes = cmdOperation[1].split("->");
						Rectangle oldState = Rectangle.parse(arrayOfShapes[0]);
						Rectangle newState = Rectangle.parse(arrayOfShapes[1]);

						index = model.getIndex(oldState);

						command = new CmdUpdateRectangle((Rectangle) model.getShape(index), newState);
						controller.execute(command);
					} else if (cmdOperation[1].startsWith("Hexagon")) {
						arrayOfShapes = cmdOperation[1].split("->");
						HexagonAdapter oldState = HexagonAdapter.parse(arrayOfShapes[0]);
						HexagonAdapter newState = HexagonAdapter.parse(arrayOfShapes[1]);

						index = model.getIndex(oldState);

						command = new CmdUpdateHexagon((HexagonAdapter) model.getShape(index), newState);
						controller.execute(command);
					}
					break;
				case "CMD_UPDATE_UNEXECUTE":
					controller.undoCommand();
					break;
				case "CMD_SELECT":
					String e = cmdOperation[2];
					String[] pointForSelect = e.split("\\|");
					int x = Integer.parseInt(pointForSelect[0].replace("(", ""));
					int y = Integer.parseInt(pointForSelect[1].replace(")", ""));
					controller.selectDeselectShapeFromLog(x, y);
					break;
				case "CMD_DESELECT":
					e = cmdOperation[2];
					e.replace("(", "").replace(")", "");
					String[] pointForDeselect = e.split("\\|");
					x = Integer.parseInt(pointForDeselect[0].replace("(", ""));
					y = Integer.parseInt(pointForDeselect[1].replace(")", ""));
					controller.selectDeselectShapeFromLog(x, y);
					break;
				default:
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Shape parseShape(String cmd) {
		Map<String, Function<String, Shape>> shapeParsers = new HashMap<>();
		shapeParsers.put("Point", Point::parse);
		shapeParsers.put("Line", Line::parse);
		shapeParsers.put("Circle", Circle::parse);
		shapeParsers.put("Donut", Donut::parse);
		shapeParsers.put("Rectangle", Rectangle::parse);
		shapeParsers.put("Hexagon", HexagonAdapter::parse);

		for (Map.Entry<String, Function<String, Shape>> parser : shapeParsers.entrySet()) {
			if (cmd.startsWith(parser.getKey())) {
				return parser.getValue().apply(cmd);
			}
		}
		return null;
	}

}
package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import observer.Observer;
import controller.DrawingController;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JList;

public class FrmDrawing extends JFrame implements Observer {
	private DrawingView view = new DrawingView();
	private DrawingController controller;
	private final int OPERATION_DRAWING = 1;
	private final int OPERATION_EDIT_DELETE = 0;
	private int activeOperation = OPERATION_DRAWING;
	private ButtonGroup btnsOperation = new ButtonGroup();
	private ButtonGroup btnsShapes = new ButtonGroup();
	private JToggleButton btnOperationDrawing = new JToggleButton("Crtanje");
	private JToggleButton btnOperationEditOrDelete = new JToggleButton("Selektuj");
	private JButton btnActionEdit = new JButton("Izmeni");
	private JButton btnActionDelete = new JButton("Obrisi");
	private JToggleButton btnShapePoint = new JToggleButton("Tacka");
	private JToggleButton btnShapeLine = new JToggleButton("Linija");
	private JToggleButton btnShapeRectangle = new JToggleButton("Pravougaonik");
	private JToggleButton btnShapeCircle = new JToggleButton("Krug");
	private JToggleButton btnShapeDonut = new JToggleButton("Krofna");
	private JToggleButton btnShapeHexagon = new JToggleButton("Sestougao");
	private JButton btnUndo = new JButton("Ponisti");
	private JButton btnRedo = new JButton("Ponovi");
	private JButton btnColorEdge;
	private JButton btnColorInner;
	private JButton btnToFront = new JButton("Ispred");
	private JButton btnBringToFront = new JButton("Ispred svih");
	private JButton btnToBack = new JButton("Iza");
	private JButton btnBringToBack = new JButton("iza svih");
	private JButton btnSaveFile = new JButton("Sacuvaj");
	private JButton btnOpenFile = new JButton("Otvori");
	private JButton btnReadCommand = new JButton("Ucitaj komandu");
	private JPanel contentPane;
	private final JPanel pnlLog = new JPanel();
	private JList listLog = new JList();
	private DefaultListModel<String> defaultListModel = new DefaultListModel<String>();
	private FileNameExtensionFilter drawFilter = new FileNameExtensionFilter("Crtez", "draw");
	private FileNameExtensionFilter logFilter = new FileNameExtensionFilter("Log", "log");
	public JButton getBtnReadCommand() {
		return btnReadCommand;
	}
	public FileNameExtensionFilter getDrawFilter() {
		return drawFilter;
	}
	public FileNameExtensionFilter getLogFilter() {
		return logFilter;
	}
	private JFileChooser saveFileChooser;
	private JFileChooser openFileChooser;
	public JFileChooser getSaveFileChooser() {
		return saveFileChooser;
	}
	public JFileChooser getOpenFileChooser() {
		return openFileChooser;
	}
	public DefaultListModel<String> getDefaultListLogModel() {
		return this.defaultListModel;
	}
	public DrawingView getView() {
		return view;
	}
	public void setController(DrawingController controller) {
		this.controller = controller;
		controller.setCurrentState(OPERATION_DRAWING);
	}
	public int getOPERATION_EDIT_DELETE() {
		return OPERATION_EDIT_DELETE;
	}
	public void setActiveOperation(int operation) {
		this.activeOperation = operation;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				FrmDrawing frame = new FrmDrawing();
				frame.setVisible(true);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		});
	}
	public FrmDrawing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 900);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(1100, 700));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controller.OperationDrawing(arg0);
			}
		});
		contentPane.add(view, BorderLayout.CENTER);
		
		JPanel panelBasic = new JPanel();
		contentPane.add(panelBasic, BorderLayout.WEST);
		panelBasic.setLayout(new GridLayout(4, 0, 0, 0));
		
		JPanel panelOperations = new JPanel();
		panelOperations.setBorder(new TitledBorder(null, "Operacije", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelBasic.add(panelOperations);
		
		btnOperationDrawing.setSelected(true);
		
		btnOperationDrawing.addActionListener(e -> controller.setCurrentState(OPERATION_DRAWING));
		btnOperationDrawing.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsOperation.add(btnOperationDrawing);
		
		btnOperationEditOrDelete.addActionListener(e -> controller.setCurrentState(OPERATION_EDIT_DELETE));
		btnOperationEditOrDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsOperation.add(btnOperationEditOrDelete);

		JPanel panelAllActions = new JPanel();
		panelAllActions.setBorder(new TitledBorder(null, "Akcije", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelBasic.add(panelAllActions);

		btnActionEdit.addActionListener(e -> controller.operationEdit());
		btnActionEdit.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		btnActionDelete.addActionListener(e -> controller.operationDelete());
		btnActionDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		btnUndo.addActionListener(e -> controller.undoCommand());
		btnRedo.addActionListener(e -> controller.redoCommand());
		btnToFront.addActionListener(e -> controller.toFront());
		btnBringToFront.addActionListener(e -> controller.bringToFront());
		btnToBack.addActionListener(e -> controller.toBack());
		btnBringToBack.addActionListener(e -> controller.bringToBack());
		
		GroupLayout gl_panelAllActions = new GroupLayout(panelAllActions);
		gl_panelAllActions.setHorizontalGroup(
				gl_panelAllActions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAllActions.createSequentialGroup()
					.addGap(48)
					.addGroup(gl_panelAllActions.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelAllActions.createSequentialGroup()
							.addComponent(btnActionEdit)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnActionDelete))
						.addGroup(gl_panelAllActions.createSequentialGroup()
							.addGroup(gl_panelAllActions.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelAllActions.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnToFront)
									.addComponent(btnUndo))
								.addComponent(btnToBack))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelAllActions.createParallelGroup(Alignment.LEADING)
								.addComponent(btnBringToFront)
								.addComponent(btnBringToBack)
								.addComponent(btnRedo))))
					.addGap(32))
		);
		gl_panelAllActions.setVerticalGroup(
				gl_panelAllActions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAllActions.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelAllActions.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnActionEdit)
						.addComponent(btnActionDelete))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelAllActions.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUndo)
						.addComponent(btnRedo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelAllActions.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnToFront)
						.addComponent(btnBringToFront))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelAllActions.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnToBack)
						.addComponent(btnBringToBack))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		panelAllActions.setLayout(gl_panelAllActions);

		JPanel panelAllShapes = new JPanel();
		panelAllShapes.setBorder(new TitledBorder(null, "Oblici", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelBasic.add(panelAllShapes);
		panelAllShapes.setLayout(new BoxLayout(panelAllShapes, BoxLayout.Y_AXIS));
		
		btnShapePoint.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(btnShapePoint);
		panelAllShapes.add(btnShapePoint);
		
		btnShapeLine.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(btnShapeLine);
		panelAllShapes.add(btnShapeLine);
		
		btnShapeRectangle.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(btnShapeRectangle);
		panelAllShapes.add(btnShapeRectangle);
		
		btnShapeCircle.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(btnShapeCircle);
		panelAllShapes.add(btnShapeCircle);
		
		btnShapeDonut.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(btnShapeDonut);
		panelAllShapes.add(btnShapeDonut);
		
		btnShapeHexagon.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(btnShapeHexagon);
		panelAllShapes.add(btnShapeHexagon);

		btnOpenFile.addActionListener(arg0 -> {
			openFileChooser = new JFileChooser();
			openFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			openFileChooser.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
			openFileChooser.setAcceptAllFileFilterUsed(false);
			openFileChooser.setFileHidingEnabled(false);
			openFileChooser.setMultiSelectionEnabled(false);
			openFileChooser.enableInputMethods(true);
			openFileChooser.setEnabled(true);
			openFileChooser.setFileFilter(drawFilter);
			openFileChooser.setFileFilter(logFilter);

			controller.openFile();
		});

		btnSaveFile.addActionListener(actionEvent -> {
			saveFileChooser = new JFileChooser();
			saveFileChooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
			saveFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			saveFileChooser.enableInputMethods(false);
			saveFileChooser.setMultiSelectionEnabled(false);
			saveFileChooser.setFileHidingEnabled(false);
			saveFileChooser.setEnabled(true);
			saveFileChooser.setDialogTitle("Sacuvaj");
			saveFileChooser.setAcceptAllFileFilterUsed(false);

			controller.saveFile();
		});
		btnReadCommand.addActionListener(actionEvent -> controller.readCommand());
		
		GroupLayout gl_panelOperations = new GroupLayout(panelOperations);
		gl_panelOperations.setHorizontalGroup(
				gl_panelOperations.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOperations.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_panelOperations.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelOperations.createSequentialGroup()
							.addGap(10)
							.addComponent(btnReadCommand))
						.addGroup(gl_panelOperations.createSequentialGroup()
							.addComponent(btnOpenFile)
							.addGap(18)
							.addComponent(btnSaveFile))
						.addGroup(gl_panelOperations.createSequentialGroup()
							.addComponent(btnOperationDrawing)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnOperationEditOrDelete)))
					.addContainerGap(48, Short.MAX_VALUE))
		);
		gl_panelOperations.setVerticalGroup(
				gl_panelOperations.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOperations.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelOperations.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOperationDrawing)
						.addComponent(btnOperationEditOrDelete))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelOperations.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOpenFile)
						.addComponent(btnSaveFile))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnReadCommand)
					.addGap(37))
		);
		panelOperations.setLayout(gl_panelOperations);
		btnShapePoint.setSelected(true);
		
		JPanel panelColorSettings = new JPanel();
		panelColorSettings.setBorder(new TitledBorder(null, "Boje", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelBasic.add(panelColorSettings);
	
		btnColorEdge = new JButton("");
		btnColorEdge.addActionListener(edgeColorChooser());
		btnColorEdge.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		btnColorInner = new JButton("");
		btnColorInner.addActionListener(innerColorChooser());
		btnColorInner.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel lblEdgeColor = new JLabel("Boja ivice");
		
		JLabel lblInnerColor = new JLabel("Boja unutrasnosti");
		GroupLayout gl_panelColorSettings = new GroupLayout(panelColorSettings);
		gl_panelColorSettings.setHorizontalGroup(
				gl_panelColorSettings.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelColorSettings.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panelColorSettings.createParallelGroup(Alignment.LEADING)
						.addComponent(btnColorEdge, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEdgeColor))
					.addGroup(gl_panelColorSettings.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelColorSettings.createSequentialGroup()
							.addGap(18)
							.addComponent(lblInnerColor))
						.addGroup(gl_panelColorSettings.createSequentialGroup()
							.addGap(35)
							.addComponent(btnColorInner, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(56, Short.MAX_VALUE))
		);
		gl_panelColorSettings.setVerticalGroup(
				gl_panelColorSettings.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelColorSettings.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_panelColorSettings.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEdgeColor)
						.addComponent(lblInnerColor))
					.addGap(18)
					.addGroup(gl_panelColorSettings.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnColorInner, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnColorEdge, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
					.addContainerGap(131, Short.MAX_VALUE))
		);
		panelColorSettings.setLayout(gl_panelColorSettings);
		
		contentPane.add(pnlLog, BorderLayout.SOUTH);
		
		listLog.setModel(defaultListModel);
		listLog.setVisibleRowCount(10);
		listLog.setFixedCellWidth(1080);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(listLog);
		scrollPane.getVerticalScrollBar().addAdjustmentListener(e -> e.getAdjustable().setValue(e.getAdjustable().getMaximum()));
		pnlLog.add(scrollPane);
	}
	private ActionListener edgeColorChooser() {
		return actionEvent -> {
			Color edgeColor = JColorChooser.showDialog(null, "Izaberite boju ivice", controller.getEdgeColor());
			if (edgeColor != null) {
				controller.setEdgeColor(edgeColor);
				btnColorEdge.setBackground(edgeColor);
			}
		};
	}
	private ActionListener innerColorChooser() {
	return actionEvent -> {
		Color innerColor = JColorChooser.showDialog(null, "Izaberite boju unutrasnosti", controller.getInnerColor());
		if (innerColor != null) {
			controller.setInnerColor(innerColor);
			btnColorInner.setBackground(innerColor);
		}
	};
}
	@Override
	public void update(int currState,int numOfSelectedShapes, int numOfShapes,int numOfUnexecutedCmd,int numOfExecutedCmd,int numOfLogs,String typeOfFile) {

		if(numOfShapes != 0 && numOfLogs == 0)
			btnSaveFile.setEnabled(true);
		else if(numOfShapes == 0 && numOfLogs != 0) 
			btnSaveFile.setEnabled(true);
		else if(numOfShapes != 0 && numOfLogs != 0 )
			btnSaveFile.setEnabled(true);
		else
			btnSaveFile.setEnabled(false);
		
		if(typeOfFile == "Log") btnReadCommand.setEnabled(true);
		else btnReadCommand.setEnabled(false);

		if(numOfUnexecutedCmd == 0) btnRedo.setEnabled(false);
		else btnRedo.setEnabled(true);
		if(numOfExecutedCmd == 0)btnUndo.setEnabled(false);
		else btnUndo.setEnabled(true);

		if(OPERATION_DRAWING == currState) {
			btnActionEdit.setEnabled(false);
			btnActionDelete.setEnabled(false);
			btnBringToBack.setEnabled(false);
			btnBringToFront.setEnabled(false);
			btnToBack.setEnabled(false);
			btnToFront.setEnabled(false);
			
			btnShapePoint.setEnabled(true);
			btnShapeLine.setEnabled(true);
			btnShapeRectangle.setEnabled(true);
			btnShapeCircle.setEnabled(true);
			btnShapeDonut.setEnabled(true);
			btnShapeHexagon.setEnabled(true);
			
			btnColorEdge.setEnabled(true);
			btnColorInner.setEnabled(true);
		}
		if(OPERATION_EDIT_DELETE == currState) {
			if(numOfSelectedShapes>1) {
				btnActionEdit.setEnabled(false);
				btnBringToBack.setEnabled(false);
				btnBringToFront.setEnabled(false);
				btnToBack.setEnabled(false);
				btnToFront.setEnabled(false);
			} else if(numOfSelectedShapes == 1) {
				btnActionEdit.setEnabled(true);
				btnBringToBack.setEnabled(true);
				btnBringToFront.setEnabled(true);
				btnToBack.setEnabled(true);
				btnToFront.setEnabled(true);
			} else {
				btnActionEdit.setEnabled(true);
				btnBringToBack.setEnabled(false);
				btnBringToFront.setEnabled(false);
				btnToBack.setEnabled(false);
				btnToFront.setEnabled(false);
			}
			if(numOfSelectedShapes >=1)
				btnActionDelete.setEnabled(true);
			else
				btnActionDelete.setEnabled(false);
				btnShapePoint.setEnabled(false);
				btnShapeLine.setEnabled(false);
				btnShapeRectangle.setEnabled(false);
				btnShapeCircle.setEnabled(false);
				btnShapeDonut.setEnabled(false);
				btnShapeHexagon.setEnabled(false);
			
				btnColorEdge.setEnabled(false);
				btnColorInner.setEnabled(false);
		}
	}
	public JToggleButton getBtnShapePoint() {
		return btnShapePoint;
	}
	public JToggleButton getBtnShapeLine() {
		return btnShapeLine;
	}
	public JToggleButton getBtnShapeRectangle() {
		return btnShapeRectangle;
	}
	public JToggleButton getBtnShapeCircle() {
		return btnShapeCircle;
	}
	public JToggleButton getBtnShapeDonut() {
		return btnShapeDonut;
	}
	public JButton getBtnColorEdge() {
		return btnColorEdge;
	}
	public JButton getBtnColorInner() {
		return btnColorInner;
	}
	public JToggleButton getBtnShapeHexagon() {
		return btnShapeHexagon;
	}
}

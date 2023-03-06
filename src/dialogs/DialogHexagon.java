package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;

import shapes.Point;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import adapter.HexagonAdapter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogHexagon extends JDialog {
	JTextField txtX;
	JTextField txtY;
	JTextField txtRadius;
	private HexagonAdapter hexagon= null;
	Color edgeColor = null;
	Color innerColor = null;
	boolean isSelected = false;
	JButton btnEdgeColor = new JButton(" ");
	JButton btnInnerColor = new JButton(" ");
	public DialogHexagon() {
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 264, 179);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(5, 2, 0, 0));
			{
				JLabel lblXkoordinata = new JLabel("X koordinata", SwingConstants.CENTER);
				panel.add(lblXkoordinata);
			}
			{
				txtX = new JTextField();
				panel.add(txtX);
				txtX.setColumns(10);
			}
			{
				JLabel lblYkoordinata = new JLabel("Y koordinata");
				lblYkoordinata.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblYkoordinata);
			}
			{
				txtY = new JTextField();
				panel.add(txtY);
				txtY.setColumns(10);
			}
			{
				JLabel lblRadijus = new JLabel("Radijus");
				lblRadijus.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblRadijus);
			}
			{
				txtRadius = new JTextField();
				panel.add(txtRadius);
				txtRadius.setColumns(10);
			}
			{
				JLabel lblEdgeColor = new JLabel("Boja ivice");
				lblEdgeColor.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblEdgeColor);
			}
			{
				btnEdgeColor.setHorizontalAlignment(SwingConstants.CENTER);
				btnEdgeColor.addActionListener(event -> {
					edgeColor = JColorChooser.showDialog(null, "Izaberite boju ivice", edgeColor);
					if (edgeColor == null) edgeColor = Color.BLACK;
					btnEdgeColor.setBackground(edgeColor);
				});
				panel.add(btnEdgeColor);
			}
			{
				JLabel lblInnerColor = new JLabel("Boja unutrasnjosti");
				lblInnerColor.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblInnerColor);
			}
			{
				btnInnerColor.setHorizontalAlignment(SwingConstants.CENTER);
				btnInnerColor.addActionListener(event -> {
					innerColor = JColorChooser.showDialog(null, "Izaberite boju unutrasnjosti", innerColor);
					if (innerColor == null) innerColor = Color.WHITE;
					btnInnerColor.setBackground(innerColor);
				});
				panel.add(btnInnerColor);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.SOUTH);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton btnOk = new JButton("Potvrdi");
				btnOk.addActionListener(arg0 -> {
					try {
						int newX = Integer.parseInt(txtX.getText());
						int newY = Integer.parseInt(txtY.getText());
						int newRadius = Integer.parseInt(txtRadius.getText());

						if(newX < 0 || newY < 0 || newRadius < 1) {
							JOptionPane.showMessageDialog(null, "Uneli ste pogresne podatke!", "Greska!", JOptionPane.ERROR_MESSAGE);
							return;
						}
						hexagon = new HexagonAdapter(new Point(newX, newY), newRadius, edgeColor, innerColor);
						hexagon.setSelected(isSelected);
						dispose();
					} catch (Exception exception) {
						JOptionPane.showMessageDialog(null, "Uneli ste pogresne podatke!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}
				});
				panel.add(btnOk);
			}
			{
				JButton btnNotOk = new JButton("Odustani");
				btnNotOk.addActionListener(event -> dispose());
				panel.add(btnNotOk);
			}
		}
	}
	public HexagonAdapter getHexagon() {
		return hexagon;
	}
	public void setPoint(Point point) {
		txtX.setText("" + point.getxCoordinate());
		txtY.setText("" + point.getyCoordinate());
	}
	public void setColors(Color edgeColor, Color innerColor) {
		this.edgeColor = edgeColor;
		this.innerColor = innerColor;
		btnEdgeColor.setBackground(edgeColor);
		btnInnerColor.setBackground(innerColor);
	}
	public Color getEdgeColor() {
		return edgeColor;
	}
	public Color getInnerColor()
	{
		return innerColor;
	}
	public void setHexagon(HexagonAdapter hexagon) {
		txtX.setText("" + hexagon.getHexagon().getX());
		txtY.setText("" + hexagon.getHexagon().getY());
		txtRadius.setText("" + hexagon.getHexagon().getR());
		edgeColor = hexagon.getHexagon().getBorderColor();
		innerColor = hexagon.getHexagon().getAreaColor();
		isSelected = hexagon.isSelected();
		setColors(hexagon.getEdgeColor(), hexagon.getInnerColor());
	}
}

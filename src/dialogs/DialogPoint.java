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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogPoint extends JDialog {
	JTextField txtX;
	JTextField txtY;
	private Point point = null;
	private Color edgeColor = null;
	boolean isSelected = false;
	JButton btnEdgeColor = new JButton(" ");
	public DialogPoint() {
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 300, 150);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(3, 2, 0, 0));
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
				JLabel lblEdgeColor = new JLabel("Boja ivice");
				lblEdgeColor.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblEdgeColor);
			}
			{
				btnEdgeColor.setHorizontalAlignment(SwingConstants.CENTER);
				btnEdgeColor.addActionListener(e -> {
					edgeColor = JColorChooser.showDialog(null, "Izaberite boju ivice", edgeColor);
					if (edgeColor == null) edgeColor = Color.BLACK;
					btnEdgeColor.setBackground(edgeColor);
				});
				panel.add(btnEdgeColor);
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

						if(newX < 0 || newY < 0) {
							JOptionPane.showMessageDialog(null, "Uneli ste pogresne podatke!", "Greska!", JOptionPane.ERROR_MESSAGE);
							return;
						}
						point = new Point(newX, newY, edgeColor);
						point.setSelected(isSelected);
						dispose();
					} catch (Exception exception) {
						JOptionPane.showMessageDialog(null, "Uneli ste pogresne podatke!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}
				});
				panel.add(btnOk);
			}
			{
				JButton btnNotOk = new JButton("Odustani");
				btnNotOk.addActionListener(e -> dispose());
				panel.add(btnNotOk);
			}
		}
	}
	public Point getPoint() {
		return point;
	}
	public void setEdgeColor(Color edgeColor)
	{
		this.edgeColor = edgeColor;
	}
	public Color getEdgeColor()
	{
		return edgeColor;
	}
	public void setPoint(Point point) {
		txtX.setText("" + point.getxCoordinate());
		txtY.setText("" + point.getyCoordinate());
		edgeColor = point.getEdgeColor();
		isSelected = point.isSelected();
		btnEdgeColor.setBackground(point.getEdgeColor());
	}
}

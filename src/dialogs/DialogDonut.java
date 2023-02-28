package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;

import shapes.Donut;
import shapes.Point;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogDonut extends JDialog {
	JTextField txtX;
	JTextField txtY;
	JTextField txtRadius;
	JTextField txtInnerRadius;
	private Donut donut = null;
	Color edgeColor = null;
    Color innerColor = null;
	private boolean isSelected = false;
	JButton btnEdgeColor = new JButton(" ");
	JButton btnInnerColor = new JButton(" ");
	public DialogDonut() {
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 258, 213);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(6, 2, 0, 0));
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
				JLabel lblUnutrasnjiRadijus = new JLabel("Unutrasnji radijus");
				lblUnutrasnjiRadijus.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblUnutrasnjiRadijus);
			}
			{
				txtInnerRadius = new JTextField();
				panel.add(txtInnerRadius);
				txtInnerRadius.setColumns(10);
			}
			{
				JLabel lblEdgeColor = new JLabel("Boja ivice");
				lblEdgeColor.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblEdgeColor);
			}
			{
				btnEdgeColor.setHorizontalAlignment(SwingConstants.CENTER);
				btnEdgeColor.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						edgeColor = JColorChooser.showDialog(null, "Izaberite boju ivice", edgeColor);
						if (edgeColor == null) edgeColor = Color.BLACK;
						btnEdgeColor.setBackground(edgeColor);
					}
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
				btnInnerColor.addActionListener(e -> {
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
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							int newX = Integer.parseInt(txtX.getText());
							int newY = Integer.parseInt(txtY.getText());
							int newRadius = Integer.parseInt(txtRadius.getText());
							int newInnerRadius = Integer.parseInt(txtInnerRadius.getText());

							if(newX < 0 || newY < 0 || newRadius < 1 || newInnerRadius < 1 || newInnerRadius >= newRadius) {
								JOptionPane.showMessageDialog(null, "Uneli ste pogresne podatke!", "Greska!", JOptionPane.ERROR_MESSAGE);
								return;
							}
							donut = new Donut(new Point(newX, newY), newRadius, newInnerRadius, edgeColor, innerColor);
							donut.setSelected(isSelected);
							dispose();
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Uneli ste pogresne podatke!", "Greska!", JOptionPane.ERROR_MESSAGE);
						}
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
	public Donut getDonut() {
		return donut;
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
	public void setDonut(Donut donut) {
		txtX.setText("" + donut.getCenter().getxCoordinate());
		txtY.setText("" + donut.getCenter().getyCoordinate());
		txtRadius.setText("" + donut.getRadius());
		txtInnerRadius.setText("" + donut.getInnerRadius());
		edgeColor = donut.getEdgeColor();
		innerColor = donut.getInnerColor();
		isSelected = donut.isSelected();
		setColors(donut.getEdgeColor(), donut.getInnerColor());
	}
}

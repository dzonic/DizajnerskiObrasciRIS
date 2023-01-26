package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import shapes.Circle;
import shapes.Point;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogCircle extends JDialog {
	JTextField txtX;
	JTextField txtY;
	JTextField txtRadius;
	private Circle circle = null;
	Color edgeColor = null;
    Color innerColor = null;
	private boolean isSelected = false;
	JButton btnEdgeColor = new JButton(" ");
	JButton btnInnerColor = new JButton(" ");

	public DialogCircle() {
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 228, 184);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			JPanel mainPanel = new JPanel();
			getContentPane().add(mainPanel, BorderLayout.CENTER);
			mainPanel.setLayout(new GridLayout(5, 2, 0, 0));
			{
				JLabel lblXkoordinata = new JLabel("X koordinata", SwingConstants.CENTER);
				mainPanel.add(lblXkoordinata);
			}
			{
				txtX = new JTextField();
				mainPanel.add(txtX);
				txtX.setColumns(10);
			}
			{
				JLabel lblYkoordinata = new JLabel("Y koordinata");
				lblYkoordinata.setHorizontalAlignment(SwingConstants.CENTER);
				mainPanel.add(lblYkoordinata);
			}
			{
				txtY = new JTextField();
				mainPanel.add(txtY);
				txtY.setColumns(10);
			}
			{
				JLabel lblRadijus = new JLabel("Radijus");
				lblRadijus.setHorizontalAlignment(SwingConstants.CENTER);
				mainPanel.add(lblRadijus);
			}
			{
				txtRadius = new JTextField();
				mainPanel.add(txtRadius);
				txtRadius.setColumns(10);
			}
			{
				JLabel lblEdgeColor = new JLabel("Boja ivice");
				lblEdgeColor.setHorizontalAlignment(SwingConstants.CENTER);
				mainPanel.add(lblEdgeColor);
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
				mainPanel.add(btnEdgeColor);
			}
			{
				JLabel lblInnerColor = new JLabel("Boja unutrasnjosti");
				lblInnerColor.setHorizontalAlignment(SwingConstants.CENTER);
				mainPanel.add(lblInnerColor);
			}
			{
				btnInnerColor.setHorizontalAlignment(SwingConstants.CENTER);
				btnInnerColor.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						innerColor = JColorChooser.showDialog(null, "Izaberite boju unutrasnjosti", innerColor);
						if (innerColor == null) innerColor = Color.WHITE;
						btnInnerColor.setBackground(innerColor);
					}
				});
				mainPanel.add(btnInnerColor);
			}
		}
		{
			JPanel actionPanel = new JPanel();
			getContentPane().add(actionPanel, BorderLayout.SOUTH);
			actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton btnOk = new JButton("Potvrdi");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							int newX = Integer.parseInt(txtX.getText());
							int newY = Integer.parseInt(txtY.getText());
							int newRadius = Integer.parseInt(txtRadius.getText());

							if(newX < 0 || newY < 0 || newRadius < 1) {
								JOptionPane.showMessageDialog(null, "Uneli ste pogresne podatke!", "Greska!", JOptionPane.ERROR_MESSAGE);
								return;
							}
							circle = new Circle(new Point(newX, newY), newRadius, edgeColor, innerColor);
							circle.setSelected(isSelected);
							dispose();
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Uneli ste pogresne podatke!", "Greska!", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				actionPanel.add(btnOk);
			}
			{
				JButton btnNotOk = new JButton("Odustani");
				btnNotOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				actionPanel.add(btnNotOk);
			}
		}
	}

	public Color getEdgeColor() {
		return edgeColor;
	}
	
	public Color getInnerColor()
	{
		return innerColor;
	}
	
	public Circle getCircle() {
		return circle;
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
	
	public void setCircle(Circle circle) {
		txtX.setText("" + circle.getCenter().getxCoordinate());
		txtY.setText("" + circle.getCenter().getyCoordinate());
		txtRadius.setText("" + circle.getRadius());
		edgeColor = circle.getEdgeColor();
		innerColor = circle.getInnerColor();
		isSelected = circle.isSelected();
		setColors(circle.getEdgeColor(), circle.getInnerColor());
	}
}

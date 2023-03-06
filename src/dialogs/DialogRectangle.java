package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;

import shapes.Point;
import shapes.Rectangle;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogRectangle extends JDialog {
	JTextField txtX;
	JTextField txtY;
	JTextField txtHeight;
	JTextField txtWidth;
	private Rectangle rectangle = null;
	Color edgeColor = null;
    Color innerColor = null;
	boolean isSelected = false;
	JButton btnEdgeColor = new JButton(" ");
	JButton btnInnerColor = new JButton(" ");
	public DialogRectangle() {
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 300, 210);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			JPanel panelRectangle = new JPanel();
			getContentPane().add(panelRectangle, BorderLayout.CENTER);
			panelRectangle.setLayout(new GridLayout(6, 2, 0, 0));
			{
				JLabel lblXkoordinata = new JLabel("X koordinata", SwingConstants.CENTER);
				panelRectangle.add(lblXkoordinata);
			}
			{
				txtX = new JTextField();
				panelRectangle.add(txtX);
				txtX.setColumns(10);
			}
			{
				JLabel lblYkoordinata = new JLabel("Y koordinata");
				lblYkoordinata.setHorizontalAlignment(SwingConstants.CENTER);
				panelRectangle.add(lblYkoordinata);
			}
			{
				txtY = new JTextField();
				panelRectangle.add(txtY);
				txtY.setColumns(10);
			}
			{
				JLabel lblVisina = new JLabel("Visina");
				lblVisina.setHorizontalAlignment(SwingConstants.CENTER);
				panelRectangle.add(lblVisina);
			}
			{
				txtHeight = new JTextField();
				panelRectangle.add(txtHeight);
				txtHeight.setColumns(10);
			}
			{
				JLabel lblSirina = new JLabel("Sirina");
				lblSirina.setHorizontalAlignment(SwingConstants.CENTER);
				panelRectangle.add(lblSirina);
			}
			{
				txtWidth = new JTextField();
				panelRectangle.add(txtWidth);
				txtWidth.setColumns(10);
			}
			{
				JLabel lblEdgeColor = new JLabel("Boja ivice");
				lblEdgeColor.setHorizontalAlignment(SwingConstants.CENTER);
				panelRectangle.add(lblEdgeColor);
			}
			{
				btnEdgeColor.setHorizontalAlignment(SwingConstants.CENTER);
				btnEdgeColor.addActionListener(e -> {
					edgeColor = JColorChooser.showDialog(null, "Izaberite boju ivice", edgeColor);
					if (edgeColor == null) edgeColor = Color.BLACK;
					btnEdgeColor.setBackground(edgeColor);
				});
				panelRectangle.add(btnEdgeColor);
			}
			{
				JLabel lblInnerColor = new JLabel("Boja unutrasnjosti");
				lblInnerColor.setHorizontalAlignment(SwingConstants.CENTER);
				panelRectangle.add(lblInnerColor);
			}
			{
				btnInnerColor.setHorizontalAlignment(SwingConstants.CENTER);
				btnInnerColor.addActionListener(e -> {
					innerColor = JColorChooser.showDialog(null, "Izaberite boju unutrasnjosti", innerColor);
					if (innerColor == null) innerColor = Color.WHITE;
					btnInnerColor.setBackground(innerColor);
				});
				panelRectangle.add(btnInnerColor);
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
						int newHeight = Integer.parseInt(txtHeight.getText());
						int newWIdth = Integer.parseInt(txtWidth.getText());

						if(newX < 0 || newY < 0 || newHeight < 1 || newWIdth < 1) {
							JOptionPane.showMessageDialog(null, "Uneli ste pogresne podatke!", "Greska!", JOptionPane.ERROR_MESSAGE);
							return;
						}
						rectangle = new Rectangle(new Point(newX, newY), newHeight, newWIdth, edgeColor, innerColor);
						rectangle.setSelected(isSelected);
						dispose();
					} catch (Exception exeption) {
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
	public Rectangle getRectangle() {
		return rectangle;
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
	
	public void setRectangle(Rectangle rect) {
		txtX.setText("" + rect.getUpperLeftPoint().getxCoordinate());
		txtY.setText("" + rect.getUpperLeftPoint().getyCoordinate());
		txtHeight.setText("" + rect.getHeight());
		txtWidth.setText("" + rect.getWidth());
		edgeColor = rect.getEdgeColor();
		innerColor = rect.getInnerColor();
		isSelected = rect.isSelected();
		
		setColors(rect.getEdgeColor(), rect.getInnerColor());
	}
}

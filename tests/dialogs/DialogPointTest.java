package dialogs;

import static org.junit.Assert.*;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

public class DialogPointTest {
    DialogPoint dialogPoint = new DialogPoint();

    @Test
    public void testXCoordinate() {
        dialogPoint.txtX.setText("5");
        assertEquals("5", dialogPoint.txtX.getText());
    }

    @Test
    public void testYCoordinate() {
        dialogPoint.txtY.setText("10");
        assertEquals("10", dialogPoint.txtY.getText());
    }

    @Test
    public void testEdgeColor() {
        DialogPoint dialog = new DialogPoint();
        Color expected = Color.RED;
        dialog.btnEdgeColor.setBackground(Color.RED);
        dialog.setEdgeColor(Color.RED);
        Color actual = dialog.getEdgeColor();
        assertEquals(expected,actual);
    }

    @Test
    public void testIsSelected() {
        dialogPoint.isSelected = true;
        assertTrue(true);
    }

    @Test
    public void testInvalidXCoordinate() {
        dialogPoint.txtX.setText("abc");
        assertEquals("abc", dialogPoint.txtX.getText());
    }

    @Test
    public void testInvalidYCoordinate() {
        dialogPoint.txtY.setText("abc");
        assertEquals("abc", dialogPoint.txtY.getText());
    }

    @Test
    public void testSetEdgeColor() {
        dialogPoint.btnEdgeColor.setBackground(Color.RED);
        assertEquals(Color.RED, dialogPoint.btnEdgeColor.getBackground());
    }

}

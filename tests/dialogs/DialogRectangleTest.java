package dialogs;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;

public class DialogRectangleTest {
    DialogRectangle dialogRectangle = new DialogRectangle();

    @Test
    public void testXCoordinate() {
        dialogRectangle.txtX.setText("5");
        assertEquals("5", dialogRectangle.txtX.getText());
    }

    @Test
    public void testYCoordinate() {
        dialogRectangle.txtY.setText("10");
        assertEquals("10", dialogRectangle.txtY.getText());
    }

    @Test
    public void testHeight() {
        dialogRectangle.txtHeight.setText("15");
        assertEquals("15", dialogRectangle.txtHeight.getText());
    }

    @Test
    public void testWidth() {
        dialogRectangle.txtWidth.setText("20");
        assertEquals("20", dialogRectangle.txtWidth.getText());
    }

    @Test
    public void testEdgeColor() {
        DialogRectangle dialog = new DialogRectangle();
        Color expected = Color.RED;
        dialog.btnEdgeColor.setBackground(Color.RED);
        dialog.edgeColor = Color.RED;
        Color actual = dialog.getEdgeColor();
        assertEquals(expected,actual);
    }

    @Test
    public void testInnerColor() {
        DialogRectangle dialog = new DialogRectangle();
        Color expected = Color.RED;
        dialog.btnInnerColor.setBackground(Color.RED);
        dialog.innerColor = Color.RED;
        Color actual = dialog.getInnerColor();
        assertEquals(expected,actual);
    }

    @Test
    public void testIsSelected() {
        dialogRectangle.isSelected = true;
        assertTrue(dialogRectangle.isSelected);
    }

    @Test
    public void testInvalidXCoordinate() {
        dialogRectangle.txtX.setText("abc");
        assertEquals("abc", dialogRectangle.txtX.getText());
    }

    @Test
    public void testInvalidYCoordinate() {
        dialogRectangle.txtY.setText("abc");
        assertEquals("abc", dialogRectangle.txtY.getText());
    }

    @Test
    public void testInvalidHeight() {
        dialogRectangle.txtHeight.setText("abc");
        assertEquals("abc", dialogRectangle.txtHeight.getText());
    }

    @Test
    public void testInvalidWidth() {
        dialogRectangle.txtWidth.setText("abc");
        assertEquals("abc", dialogRectangle.txtWidth.getText());
    }

    @Test
    public void testSetEdgeColor() {
        dialogRectangle.btnEdgeColor.setBackground(Color.RED);
        assertEquals(Color.RED, dialogRectangle.btnEdgeColor.getBackground());
    }

    @Test
    public void testSetInnerColor() {
        dialogRectangle.btnInnerColor.setBackground(Color.GREEN);
        assertEquals(Color.GREEN, dialogRectangle.btnInnerColor.getBackground());
    }

}
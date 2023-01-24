package dialogs;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

class DialogCircleTest {

    @Test
    void testConstruction() {
        DialogCircle dialog = new DialogCircle();
        assertNotNull(dialog);
    }

    @Test
    void testEdgeColor() {
        DialogCircle dialog = new DialogCircle();
        Color expected = Color.RED;
        dialog.btnEdgeColor.setBackground(Color.RED);
        dialog.edgeColor = Color.RED;
        Color actual = dialog.getEdgeColor();
        assertEquals(expected,actual);
    }

    @Test
    void testInnerColor() {
        DialogCircle dialog = new DialogCircle();
        Color expected = Color.RED;
        dialog.btnInnerColor.setBackground(Color.RED);
        dialog.innerColor = Color.RED;
        Color actual = dialog.getInnerColor();
        assertEquals(expected,actual);
    }

    @Test
    void testTxtX() {
        DialogCircle dialog = new DialogCircle();
        dialog.txtX.setText("10");
        assertEquals("10", dialog.txtX.getText());
    }

    @Test
    void testTxtY() {
        DialogCircle dialog = new DialogCircle();
        dialog.txtY.setText("20");
        assertEquals("20", dialog.txtY.getText());
    }

    @Test
    void testTxtRadius() {
        DialogCircle dialog = new DialogCircle();
        dialog.txtRadius.setText("15");
        assertEquals("15", dialog.txtRadius.getText());
    }

}

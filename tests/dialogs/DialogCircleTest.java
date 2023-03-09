package dialogs;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

class DialogCircleTest {
    DialogCircle dialogCircle = new DialogCircle();
    @Test
    void testConstruction() {
        assertNotNull(dialogCircle);
    }
    @Test
    void testEdgeColor() {
        Color expected = Color.RED;
        dialogCircle.btnEdgeColor.setBackground(Color.RED);
        dialogCircle.edgeColor = Color.RED;
        Color actual = dialogCircle.getEdgeColor();
        assertEquals(expected,actual);
    }
    @Test
    void testInnerColor() {
        Color expected = Color.RED;
        dialogCircle.btnInnerColor.setBackground(Color.RED);
        dialogCircle.innerColor = Color.RED;
        Color actual = dialogCircle.getInnerColor();
        assertEquals(expected,actual);
    }
    @Test
    void testTxtX() {
        dialogCircle.txtX.setText("10");
        assertEquals("10", dialogCircle.txtX.getText());
    }
    @Test
    void testTxtY() {
        dialogCircle.txtY.setText("20");
        assertEquals("20", dialogCircle.txtY.getText());
    }
    @Test
    void testTxtRadius() {
        dialogCircle.txtRadius.setText("15");
        assertEquals("15", dialogCircle.txtRadius.getText());
    }
}

package dialogs;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

class DialogDonutTest {
    DialogDonut dialogDonut = new DialogDonut();
    @Test
    void testConstruction() {
        assertNotNull(dialogDonut);
    }
    @Test
    void testEdgeColor() {
        Color expected = Color.RED;
        dialogDonut.btnEdgeColor.setBackground(Color.RED);
        dialogDonut.edgeColor = Color.RED;
        Color actual = dialogDonut.getEdgeColor();
        assertEquals(expected,actual);
    }
    @Test
    void testInnerColor() {
        Color expected = Color.RED;
        dialogDonut.btnInnerColor.setBackground(Color.RED);
        dialogDonut.innerColor = Color.RED;
        Color actual = dialogDonut.getInnerColor();
        assertEquals(expected,actual);
    }
    @Test
    void testTxtX() {
        dialogDonut.txtX.setText("10");
        assertEquals("10", dialogDonut.txtX.getText());
    }
    @Test
    void testTxtY() {
        dialogDonut.txtY.setText("20");
        assertEquals("20", dialogDonut.txtY.getText());
    }
    @Test
    void testTxtRadius() {
        dialogDonut.txtRadius.setText("15");
        assertEquals("15", dialogDonut.txtRadius.getText());
    }
    @Test
    void testTxtInnerRadius() {
        dialogDonut.txtInnerRadius.setText("5");
        assertEquals("5", dialogDonut.txtInnerRadius.getText());
    }
}

package dialogs;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

class DialogDonutTest {

    @Test
    void testConstruction() {
        DialogDonut dialog = new DialogDonut();
        assertNotNull(dialog);
    }

    @Test
    void testEdgeColor() {
        DialogDonut dialog = new DialogDonut();
        Color expected = Color.RED;
        dialog.btnEdgeColor.setBackground(Color.RED);
        dialog.edgeColor = Color.RED;
        Color actual = dialog.getEdgeColor();
        assertEquals(expected,actual);
    }

    @Test
    void testInnerColor() {
        DialogDonut dialog = new DialogDonut();
        Color expected = Color.RED;
        dialog.btnInnerColor.setBackground(Color.RED);
        dialog.innerColor = Color.RED;
        Color actual = dialog.getInnerColor();
        assertEquals(expected,actual);
    }

    @Test
    void testTxtX() {
        DialogDonut dialog = new DialogDonut();
        dialog.txtX.setText("10");
        assertEquals("10", dialog.txtX.getText());
    }

    @Test
    void testTxtY() {
        DialogDonut dialog = new DialogDonut();
        dialog.txtY.setText("20");
        assertEquals("20", dialog.txtY.getText());
    }

    @Test
    void testTxtRadius() {
        DialogDonut dialog = new DialogDonut();
        dialog.txtRadius.setText("15");
        assertEquals("15", dialog.txtRadius.getText());
    }

    @Test
    void testTxtInnerRadius() {
        DialogDonut dialog = new DialogDonut();
        dialog.txtInnerRadius.setText("5");
        assertEquals("5", dialog.txtInnerRadius.getText());
    }

}

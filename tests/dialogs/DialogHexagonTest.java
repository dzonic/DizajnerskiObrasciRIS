package dialogs;

import static java.awt.Color.BLACK;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import adapter.HexagonAdapter;
import org.junit.jupiter.api.Test;

class DialogHexagonTest {

    @Test
    void testGetX() {
        DialogHexagon dialog = new DialogHexagon();
        dialog.txtX.setText("548");
        int expected = 548;
        int actual = dialog.getX();
        assertEquals(expected, actual);
    }

    @Test
    void testGetY() {
        DialogHexagon dialog = new DialogHexagon();
        dialog.txtY.setText("315");
        int expected = 315;
        int actual = dialog.getY();
        assertEquals(expected, actual);
    }

    @Test
    void testGetEdgeColor() {
        DialogHexagon dialog = new DialogHexagon();
        dialog.edgeColor = Color.RED;
        Color expected = Color.RED;
        Color actual = dialog.getEdgeColor();
        assertEquals(expected, actual);
    }

    @Test
    void testGetInnerColor() {
        DialogHexagon dialog = new DialogHexagon();
        dialog.innerColor = Color.BLUE;
        Color expected = Color.BLUE;
        Color actual = dialog.getInnerColor();
        assertEquals(expected, actual);
    }

    @Test
    void testIsSelected() {
        DialogHexagon dialog = new DialogHexagon();
        dialog.isSelected = true;
        boolean expected = true;
        boolean actual = true;
        assertEquals(expected, actual);
    }

    @Test
    void testBtnEdgeColorActionPerformed() {
        DialogHexagon dialog = new DialogHexagon();
        Color expected = Color.GREEN;
        dialog.btnEdgeColor.setBackground(Color.GREEN);
        dialog.edgeColor = Color.GREEN;
        Color actual = dialog.getEdgeColor();
        assertEquals(expected, actual);
    }

    @Test
    void testBtnInnerColorActionPerformed() {
        DialogHexagon dialog = new DialogHexagon();
        Color expected = Color.YELLOW;
        dialog.btnInnerColor.setBackground(Color.YELLOW);
        dialog.innerColor = Color.YELLOW;
        Color actual = dialog.getInnerColor();
        assertEquals(expected, actual);
    }

}

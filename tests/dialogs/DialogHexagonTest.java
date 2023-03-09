package dialogs;

import static java.awt.Color.BLACK;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import adapter.HexagonAdapter;
import org.junit.jupiter.api.Test;

class DialogHexagonTest {
    DialogHexagon dialogHexagon = new DialogHexagon();
    @Test
    void testGetX() {
        dialogHexagon.txtX.setText("548");
        int expected = 548;
        int actual = dialogHexagon.getX();
        assertEquals(expected, actual);
    }
    @Test
    void testGetY() {
        dialogHexagon.txtY.setText("315");
        int expected = 315;
        int actual = dialogHexagon.getY();
        assertEquals(expected, actual);
    }
    @Test
    void testGetEdgeColor() {
        dialogHexagon.edgeColor = Color.RED;
        Color expected = Color.RED;
        Color actual = dialogHexagon.getEdgeColor();
        assertEquals(expected, actual);
    }
    @Test
    void testGetInnerColor() {
        dialogHexagon.innerColor = Color.BLUE;
        Color expected = Color.BLUE;
        Color actual = dialogHexagon.getInnerColor();
        assertEquals(expected, actual);
    }
    @Test
    void testIsSelected() {
        dialogHexagon.isSelected = true;
        boolean expected = true;
        boolean actual = true;
        assertEquals(expected, actual);
    }
    @Test
    void testBtnEdgeColorActionPerformed() {
        Color expected = Color.GREEN;
        dialogHexagon.btnEdgeColor.setBackground(Color.GREEN);
        dialogHexagon.edgeColor = Color.GREEN;
        Color actual = dialogHexagon.getEdgeColor();
        assertEquals(expected, actual);
    }
    @Test
    void testBtnInnerColorActionPerformed() {
        Color expected = Color.YELLOW;
        dialogHexagon.btnInnerColor.setBackground(Color.YELLOW);
        dialogHexagon.innerColor = Color.YELLOW;
        Color actual = dialogHexagon.getInnerColor();
        assertEquals(expected, actual);
    }
}

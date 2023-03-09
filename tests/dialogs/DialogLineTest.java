package dialogs;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

class DialogLineTest {
    DialogLine dialogLine = new DialogLine();
    @Test
    void testConstruction() {
        assertNotNull(dialogLine);
    }
    @Test
    void testEdgeColor() {
        Color expected = Color.RED;
        dialogLine.btnEdgeColor.setBackground(Color.RED);
        dialogLine.setEdgeColor(Color.RED);
        Color actual = dialogLine.getEdgeColor();
        assertEquals(expected,actual);
    }
    @Test
    void testTxtFirstX() {
        dialogLine.txtFirstX.setText("10");
        assertEquals("10", dialogLine.txtFirstX.getText());
    }
    @Test
    void testTxtFirstY() {
        dialogLine.txtFirstY.setText("20");
        assertEquals("20", dialogLine.txtFirstY.getText());
    }
    @Test
    void testTxtSecondX() {
        dialogLine.txtSecondX.setText("30");
        assertEquals("30", dialogLine.txtSecondX.getText());
    }
    @Test
    void testTxtSecondY() {
        dialogLine.txtSecondY.setText("40");
        assertEquals("40", dialogLine.txtSecondY.getText());
    }
}

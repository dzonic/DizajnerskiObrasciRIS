package dialogs;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

class DialogLineTest {

    @Test
    void testConstruction() {
        DialogLine dialog = new DialogLine();
        assertNotNull(dialog);
    }

    @Test
    void testEdgeColor() {
        DialogLine dialog = new DialogLine();
        Color expected = Color.RED;
        dialog.btnEdgeColor.setBackground(Color.RED);
        dialog.setEdgeColor(Color.RED);
        Color actual = dialog.getEdgeColor();
        assertEquals(expected,actual);
    }

    @Test
    void testTxtFirstX() {
        DialogLine dialog = new DialogLine();
        dialog.txtFirstX.setText("10");
        assertEquals("10", dialog.txtFirstX.getText());
    }

    @Test
    void testTxtFirstY() {
        DialogLine dialog = new DialogLine();
        dialog.txtFirstY.setText("20");
        assertEquals("20", dialog.txtFirstY.getText());
    }

    @Test
    void testTxtSecondX() {
        DialogLine dialog = new DialogLine();
        dialog.txtSecondX.setText("30");
        assertEquals("30", dialog.txtSecondX.getText());
    }

    @Test
    void testTxtSecondY() {
        DialogLine dialog = new DialogLine();
        dialog.txtSecondY.setText("40");
        assertEquals("40", dialog.txtSecondY.getText());
    }


}

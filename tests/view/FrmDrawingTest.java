package view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

class FrmDrawingTest {

    private FrmDrawing frmDrawingUnderTest;

    @BeforeEach
    void setUp() {
        frmDrawingUnderTest = new FrmDrawing();
    }

    @Test
    void testGetDefaultListLogModel() {
        final DefaultListModel<String> result = frmDrawingUnderTest.getDefaultListLogModel();
    }

    @Test
    void testUpdate() {
        frmDrawingUnderTest.update(0, 0, 0, 0, 0, 0, "typeOfFile");

    }

    @Test
    void testMain() {
        FrmDrawing.main(new String[]{"args"});

    }
}

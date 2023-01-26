package command;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.DrawingModel;
import shapes.Point;
import java.awt.*;


public class CmdAddShapeTest {
    private DrawingModel model;
    private CmdAddShape cmdAddShape;
    private Point point;

    @Before
    public void setUp() {
        model = new DrawingModel();
        point = new Point(0,0, Color.BLACK);
        cmdAddShape = new CmdAddShape(point, model);
    }

    @Test
    public void testExecute() {
        cmdAddShape.execute();
        assertTrue(model.getShapes().contains(point));
    }

    @Test
    public void testUnexecute() {
        cmdAddShape.execute();
        cmdAddShape.unexecute();
        assertFalse(model.getShapes().contains(point));
    }

    @Test
    public void testGetLog() {
        cmdAddShape.execute();
        assertEquals("CMD_ADD_EXECUTE:" + point, cmdAddShape.getLog());
    }

    @Test
    public void testSetLog() {
        cmdAddShape.setLog("Test log");
        assertEquals("Test log", cmdAddShape.getLog());
    }
}

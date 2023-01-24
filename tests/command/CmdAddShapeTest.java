package command;

import static org.junit.Assert.*;

import command.CmdAddShape;
import org.junit.Before;
import org.junit.Test;
import model.DrawingModel;
import shapes.Point;

import java.awt.*;


public class CmdAddShapeTest {
    private DrawingModel model;
    private CmdAddShape cmd;
    private Point point;

    @Before
    public void setUp() {
        model = new DrawingModel();
        point = new Point(0,0, Color.BLACK);
        cmd = new CmdAddShape(point, model);
    }

    @Test
    public void testExecute() {
        cmd.execute();
        assertTrue(model.getShapes().contains(point));
    }

    @Test
    public void testUnexecute() {
        cmd.execute();
        cmd.unexecute();
        assertFalse(model.getShapes().contains(point));
    }

    @Test
    public void testGetLog() {
        cmd.execute();
        assertEquals("CMD_ADD_EXECUTE:" + point, cmd.getLog());
    }

    @Test
    public void testSetLog() {
        cmd.setLog("Test log");
        assertEquals("Test log", cmd.getLog());
    }
}

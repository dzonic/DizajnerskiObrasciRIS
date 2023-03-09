package command;

import model.DrawingModel;
import org.junit.Before;
import org.junit.Test;
import shapes.*;
import shapes.Point;

import java.awt.*;

import static org.junit.Assert.*;

public class CmdToBackTest {
    private DrawingModel model;
    private Point point;
    private int indexOfShape;
    private CmdBringToBack cmdBringToBack;
    @Before
    public void setUp() {
        model = new DrawingModel();
        point = new Point(1, 2, Color.BLACK);
        model.addShape(new Line(new Point(1, 2,Color.BLACK), new Point(3, 4),Color.BLACK));
        model.addShape(point);
        indexOfShape = model.getIndex(point);
        cmdBringToBack = new CmdBringToBack(point,model);
    }
    @Test
    public void testExecute() {
        cmdBringToBack.execute();
        assertEquals(0, model.getIndex(point));
    }
    @Test
    public void testUnexecuteExecuteNotCalled() {
        cmdBringToBack.unexecute();
        assertNotEquals(indexOfShape, model.getIndex(point));
    }
    @Test
    public void testUnexecuteExecuteCalled() {
        cmdBringToBack.execute();
        cmdBringToBack.unexecute();
        assertEquals(indexOfShape, model.getIndex(point));
    }
}

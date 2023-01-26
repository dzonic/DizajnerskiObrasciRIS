package command;

import model.DrawingModel;
import org.junit.Before;
import org.junit.Test;
import shapes.*;
import shapes.Point;

import java.awt.*;

import static org.junit.Assert.*;

public class CmdToFrontTest {
    private DrawingModel model;
    private Point point;
    private int indexOfShape;
    private CmdToFront cmdToFront;

    @Before
    public void setUp() {
        model = new DrawingModel();
        point = new Point(1, 2, Color.BLACK);
        model.addShape(new Line(new Point(1, 2,Color.BLACK), new Point(3, 4),Color.BLACK));
        model.addShape(point);
        indexOfShape = model.getIndex(point);
        cmdToFront = new CmdToFront(point,model);
    }

    @Test
    public void testExecute() {
        cmdToFront.execute();
        assertEquals(indexOfShape, model.getIndex(point));
    }

    @Test
    public void testUnexecuteExecuteNotCalled() {
        cmdToFront.unexecute();
        assertEquals(indexOfShape, model.getIndex(point));
    }

    @Test
    public void testUnexecuteExecuteCalled() {
        cmdToFront.execute();
        cmdToFront.unexecute();
        assertEquals(indexOfShape, model.getIndex(point));
    }
}

package command;

import command.CmdToFront;
import model.DrawingModel;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import shapes.Line;
import shapes.Point;
import shapes.Shape;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

class CmdToFrontTest {
    private DrawingModel model;
    private Point point;
    private Line line;
    private int indexOfShape;
    private CmdToFront cmdToFront;

    @Before
    public void setUp() {
        model = new DrawingModel();
        point = new Point(1, 2, Color.RED);
        line = new Line(new Point(1, 2,Color.RED), new Point(3, 4,Color.RED));
        model.addShape(point);
        model.addShape(line);
    }

    @Test
    public void testExecuteIndexIsZero() {
        indexOfShape = model.getIndex(point);
        cmdToFront = new CmdToFront(point,model);
        cmdToFront.execute();
        assertEquals(0, model.getIndex(point));
    }

    @Test
    public void testExecute() {
        model.addShape(line);
        indexOfShape = model.getIndex(point);
        cmdToFront = new CmdToFront(point,model);
        cmdToFront.execute();
        assertEquals(indexOfShape + 1, model.getIndex(point));
        assertEquals(indexOfShape, model.getIndex(line));
    }

    @Test
    public void testUnexecuteIndexIsZero() {
        indexOfShape = model.getIndex(point);
        cmdToFront = new CmdToFront(point,model);
        cmdToFront.unexecute();
        assertEquals(0, model.getIndex(point));
    }

    @Test
    public void testUnexecuteExecuteNotCalled() {
        model.addShape(line);
        indexOfShape = model.getIndex(point);
        cmdToFront = new CmdToFront(point,model);
        cmdToFront.unexecute();
        assertEquals(indexOfShape, model.getIndex(line));
        assertEquals(indexOfShape + 1, model.getIndex(point));
    }

    @Test
    public void testUnexecute() {
        model.addShape(line);
        indexOfShape = model.getIndex(point);
        cmdToFront = new CmdToFront(point,model);
        cmdToFront.execute();
        cmdToFront.unexecute();
        assertEquals(indexOfShape, model.getIndex(point));
        assertEquals(indexOfShape + 1, model.getIndex(line));
    }
}

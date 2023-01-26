package command;

import static org.junit.Assert.assertEquals;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import model.DrawingModel;
import shapes.Shape;

public class CmdDeleteShapesTest {

    public Shape testShape = new Shape() {
        @Override
        public void moveBy(int byX, int byY) {

        }

        @Override
        public int compareTo(Object o) {
            return 0;
        }

        @Override
        public boolean contains(int x, int y) {
            return false;
        }

        @Override
        public void draw(Graphics g) {

        }

        @Override
        public Shape clone() {
            return null;
        }
    };
    @Test
    public void testExecute() {
        List<Shape> shapesForDelete = new ArrayList<Shape>();
        shapesForDelete.add(testShape);
        DrawingModel model = new DrawingModel();
        model.addShape(testShape);
        CmdDeleteShapes cmd = new CmdDeleteShapes(shapesForDelete, model);
        cmd.execute();
        assertEquals(0, model.getShapes().size());
    }

    @Test
    public void testUnexecute() {
        List<Shape> shapesForDelete = new ArrayList<Shape>();
        shapesForDelete.add(testShape);
        DrawingModel model = new DrawingModel();
        CmdDeleteShapes cmd = new CmdDeleteShapes(shapesForDelete, model);
        cmd.execute();
        cmd.unexecute();
        assertEquals(1, model.getShapes().size());
    }
}

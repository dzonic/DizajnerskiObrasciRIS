package command;

import model.DrawingModel;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import shapes.*;
import shapes.Point;
import shapes.Shape;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class CmdBringToFrontTest {
    private DrawingModel model = new DrawingModel();
    private Shape shape = new Shape() {
        @Override
        public boolean contains(int x, int y) {
            return false;
        }
        @Override
        public void draw(Graphics g) { }
        @Override
        public Shape clone() {
            return null;
        }
        @Override
        public int compareTo(Object o) {
            return 0;
        }
        @Override
        public void moveBy(int byX, int byY) { }
    };
    private CmdBringToFront cmdBringToFront = new CmdBringToFront(shape,model) ;
    @Test
    public void testExecute() {
        cmdBringToFront.execute();
        assertEquals(shape, model.getShapes().get(0));
    }
    @Test
    public void testUnexecuteExecuteNotCalled() {
        model.addShape(shape);
        cmdBringToFront.execute();
        cmdBringToFront.unexecute();
        assertEquals(shape, model.getShapes().get(model.getShapes().size()-1));
    }
}
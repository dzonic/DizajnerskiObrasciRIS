package command;

import model.DrawingModel;
import org.junit.Test;
import shapes.Shape;

import java.awt.*;

import static org.junit.Assert.*;
public class CmdBringToBackTest {
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
    private CmdBringToBack cmd = new CmdBringToBack(shape, model);
    @Test
    public void executeTest() {
        model.addShape(shape);
        cmd.execute();
        assertEquals(shape, model.getShapes().get(0));
    }
    @Test
    public void unexecuteTest() {
        model.addShape(shape);
        cmd.execute();
        cmd.unexecute();
        assertEquals(shape, model.getShapes().get(model.getShapes().size()-1));
    }
    @Test
    public void getLogTest() {
        cmd.execute();
        assertEquals("CMD_BRING_TO_BACK_EXECUTE:" + shape, cmd.getLog());
    }
}
